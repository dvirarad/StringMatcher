package rw;

import com.google.common.collect.Multimap;
import configuration.Globals;
import logic.KeyLocation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by dvir arad on 11/29/17.
 */
public class MapWriter {
    /**
     * Responsible of serialization map to file
     */
    public MapWriter(){

    }

    public void writeMapFromMatcher(Multimap<String, KeyLocation> nameLocationMap) {
        writeMap(nameLocationMap,Globals.MAP_PATH);
    }

    public void writeMapSummary(Multimap<String, KeyLocation> nameLocationMap) {
        writeMap(nameLocationMap,Globals.MAP_SUMMARY_PATH);
    }

    /**
     * serialization map to file
     * @param nameLocationMap Map
     * @param path -location of file
     */
    public synchronized void writeMap(Multimap<String, KeyLocation> nameLocationMap, String path){

        try {
            Files.write(Paths.get(path), () -> nameLocationMap.asMap().entrySet().stream()
                    .<CharSequence>map(e -> e.getKey() + Globals.DATA_SEPARATOR + e.getValue())
                    .iterator(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
