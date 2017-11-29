package rw;

import com.google.common.collect.Multimap;
import configoration.Globals;
import logic.NameLocation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by dvir arad on 11/29/17.
 */
public class MapWriter {

    public void writeMapFromMatcher(Multimap<String, NameLocation> nameLocationMap) {
        writeMap(nameLocationMap,Globals.MAP_PATH);
    }

    public void writeMapSummary(Multimap<String, NameLocation> nameLocationMap) {
        writeMap(nameLocationMap,Globals.MAP_SUMMARY_PATH);
    }


    public synchronized void writeMap(Multimap<String, NameLocation> nameLocationMap, String path){

        try {
            Files.write(Paths.get(path), () -> nameLocationMap.asMap().entrySet().stream()
                    .<CharSequence>map(e -> e.getKey() + Globals.DATA_SEPARATOR + e.getValue())
                    .iterator(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
