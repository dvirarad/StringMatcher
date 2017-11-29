package logic;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import rw.LinesPacket;
import rw.ReaderChunk;
import rw.MapWriter;

import java.util.List;
import java.util.Optional;

/**
 * Created by dvir arad on 11/29/17.
 */
public class ThreadNamesMatcher implements Runnable {

    ReaderChunk reader;
    MapWriter writerHashMap;
    List<String> namesToFind;
    Multimap<String,NameLocation> nameLocationHashMap;

    public ThreadNamesMatcher(ReaderChunk reader, MapWriter writerHashMap, List<String> namesToFind) {
        this.reader = reader;
        this.writerHashMap = writerHashMap;
        this.namesToFind = namesToFind;
    }

    @Override
    public void run() {
        Optional<LinesPacket> lp;
        while( (lp = reader.readLines()).isPresent()){
            initMap();
            processPacket(lp.get());
            writeMap();
        }
    }

    private void initMap() {
        this.nameLocationHashMap = ArrayListMultimap.create();
    }

    private void writeMap() {
        writerHashMap.writeMapFromMatcher(nameLocationHashMap);
    }

    private void processPacket(LinesPacket lp) {
        StringBuilder stringBuilder = lp.stringBuilder;
        for (String name : namesToFind) {
            findNameInText(lp, stringBuilder, name);
        }
    }

    private void findNameInText(LinesPacket lp, StringBuilder stringBuilder, String name) {
        for (int i = -1; (i = stringBuilder.indexOf(name, i + 1)) != -1; i++) {
            nameLocationHashMap.put(name,new NameLocation(lp.linesCounter,i));
        }
    }
}
