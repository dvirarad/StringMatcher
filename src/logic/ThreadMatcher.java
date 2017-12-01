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
public class ThreadMatcher implements Runnable {

    ReaderChunk reader;
    MapWriter mapWriter;
    List<String> listKeyToFind;
    Multimap<String, KeyLocation> keyLocationMap;

    /**
     * Responsible for finding a key in the text
     * @param reader Text holder
     * @param mapWriter - Writer of map
     * @param keysToFind List of Key to find at text
     */
    public ThreadMatcher(ReaderChunk reader, MapWriter mapWriter, List<String> keysToFind) {
        this.reader = reader;
        this.mapWriter = mapWriter;
        this.listKeyToFind = keysToFind;
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
        this.keyLocationMap = ArrayListMultimap.create();
    }

    /**
     *  Write task map result
     */
    private void writeMap() {
        mapWriter.writeMapFromMatcher(keyLocationMap);
    }

    /**
     * finding key at text and update Map
     * @param lp Holder of Text
     */
    private void processPacket(LinesPacket lp) {
        StringBuilder stringBuilder = lp.stringBuilder;
        for (String key : listKeyToFind) {
            findKeyInText(lp, stringBuilder, key);
        }
    }

    private void findKeyInText(LinesPacket lp, StringBuilder stringBuilder, String key) {
        for (int i = -1; (i = stringBuilder.indexOf(key, i + 1)) != -1; i++) {
            keyLocationMap.put(key,new KeyLocation(lp.lineOffset,i));
        }
    }
}
