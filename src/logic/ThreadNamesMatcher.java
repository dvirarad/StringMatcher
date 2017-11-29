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

    /**
     * Responsible for finding a key in the text
     * @param reader Text holder
     * @param mapWriter - Writer of map
     * @param namesToFind List of Key to find at text
     */
    public ThreadNamesMatcher(ReaderChunk reader, MapWriter mapWriter, List<String> namesToFind) {
        this.reader = reader;
        this.writerHashMap = mapWriter;
        this.namesToFind = namesToFind;
    }

    @Override
    public void run() {
        Optional<LinesPacket> lp;
        //get chunk of lines
        while( (lp = reader.readLines()).isPresent()){
            initMap();
            processPacket(lp.get());
            writeMap();
        }
    }

    /**
     * create new Map
     */
    private void initMap() {
        this.nameLocationHashMap = ArrayListMultimap.create();
    }

    /**
     *  Write task map result
     */
    private void writeMap() {
        writerHashMap.writeMapFromMatcher(nameLocationHashMap);
    }

    /**
     * finding key at text and update Map
     * @param lp Holder of Text
     */
    private void processPacket(LinesPacket lp) {
        StringBuilder stringBuilder = lp.stringBuilder;
        for (String name : namesToFind) {
            findNameInText(lp, stringBuilder, name);
        }
    }

    private void findNameInText(LinesPacket lp, StringBuilder stringBuilder, String name) {
        for (int i = -1; (i = stringBuilder.indexOf(name, i + 1)) != -1; i++) {
            nameLocationHashMap.put(name,new NameLocation(lp.lineOffset,i));
        }
    }
}
