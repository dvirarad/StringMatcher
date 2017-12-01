package rw;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import configuration.Globals;
import logic.KeyLocation;

import java.io.*;

/**
 * Created by dvir arad on 11/29/17.
 */
public class MapReader {

    Multimap<String, KeyLocation> nameLocationMap;
    BufferedReader br = null;
    FileReader fr = null;

    public MapReader(){
        this(Globals.MAP_PATH);
    }

    /**
     * Responsible of deserialization map from file
     * @param fileLocation - location of file to read from
     */
    public MapReader(String fileLocation) {
        try {
            File file = new File(fileLocation);
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

    /**
     * Deserialization map from file
     */
    public void loadMapFromFile(){
        String line;
        while ((line = getLine()) != null)
            parseLine(line);

        close();
    }

    /**
     * Deserialization line into <String,KeyLocation> entity
     * @param line - one line from file
     */
    public void parseLine(String line) {
        String[] mapLine = line.split(Globals.DATA_SEPARATOR);
        String key = mapLine[0];
        String row = mapLine[1];
        String[] nameLocationAsStringArray = row.split("],");
        updateMap(key, nameLocationAsStringArray);
    }

    /**
     * Add new KeyLocation into Key value at Map
     * @param key
     * @param nameLocationAsStringArray
     */
    public void updateMap(String key, String[] nameLocationAsStringArray) {
        for (String nameLocationString : nameLocationAsStringArray) {
            nameLocationString = cleanNameLocationString(nameLocationString);
            KeyLocation keyLocation = getNameLocation(nameLocationString);
            nameLocationMap.put(key, keyLocation);
        }
    }


    public String cleanNameLocationString(String nameLocationString) {
        nameLocationString = nameLocationString.replace("[", "").replace("]", "");
        return nameLocationString;
    }

    /**
     * parse String into KeyLocation object
     * @param nameLocationString String to parse
     * @return  KeyLocation object
     */
    public KeyLocation getNameLocation(String nameLocationString) {
        String[] nameLocationArray = nameLocationString.split(Globals.NAME_LOCATION_SEPARATOR);
        String[] arrLineOffset=nameLocationArray[0].split("=");
        String[] arrCharOffset=nameLocationArray[1].split("=");;
        int lineOffset = Integer.valueOf(arrLineOffset[1].trim());
        int charOffset = Integer.valueOf(arrCharOffset[1].trim());
        return new KeyLocation(lineOffset,charOffset);
    }

    public Multimap<String, KeyLocation> getKeyLocationMap() {
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

    /**
     * Responsible of loading line from File
     * @return One line from file
     */
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


