package rw;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import configoration.Globals;
import logic.NameLocation;

import java.io.*;
import java.nio.file.Files;

/**
 * Created by dvir arad on 11/29/17.
 */
public class MapReader {

    Multimap<String, NameLocation> nameLocationMap;
    BufferedReader br = null;
    FileReader fr = null;

    public MapReader(){
        this(Globals.MAP_PATH);
    }

    public MapReader(String path) {
        try {
            File file = new File(path);
            if(!file.exists())
                file.createNewFile();
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            nameLocationMap = ArrayListMultimap.create();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMapFromFile(){
        String line;
        while ((line = getLine()) != null)
            parseLine(line);

        close();
    }


    public void parseLine(String line) {
        String[] mapLine = line.split(Globals.DATA_SEPARATOR);
        String key = mapLine[0];
        String row = mapLine[1];
        row = row.replace("[", "").replace("]", "");
        String[] nameLocationAsStringArray = row.split(",");
        updateMap(key, nameLocationAsStringArray);
    }

    public void updateMap(String key, String[] nameLocationAsStringArray) {
        for (String nameLocationString : nameLocationAsStringArray) {
            NameLocation nameLocation = getNameLocation(nameLocationString);
            nameLocationMap.put(key,nameLocation);
        }
    }

    public NameLocation getNameLocation(String nameLocationString) {
        String[] nameLocationArray = nameLocationString.split(Globals.NAME_LOCATION_SEPARATOR);
        int lineNumber = Integer.valueOf(nameLocationArray[0].trim());
        int charNumber = Integer.valueOf(nameLocationArray[1].trim());
        return new NameLocation(lineNumber,charNumber);
    }

    public Multimap<String, NameLocation> getNameLocationMap() {
        return nameLocationMap;
    }

    private void close() {
        try {

            if (br != null)
                br.close();

            if (fr != null)
                fr.close();

        } catch (IOException ex) {

            ex.printStackTrace();

        }
    }


    public String getLine() {
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return line;
        }
    }
}


