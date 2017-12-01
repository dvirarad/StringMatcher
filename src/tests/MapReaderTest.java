package tests;

import logic.KeyLocation;
import org.junit.Before;
import org.junit.Test;
import rw.MapReader;

/**
 * Created by dvir arad on 11/29/17.
 */
public class MapReaderTest {

    MapReader mapReader;

    @Before
    public void initMap(){
        mapReader = new MapReader("src/tests/test_map_reader.txt");
    }

    @Test
    public void testParseLineMapSize(){
        String line = "John-->[[lineOffset=13000,charOffset=1755], [lineOffset=13000,charOffset=7741], [lineOffset=13000,charOffset=10844]\n";
        mapReader.parseLine(line);
        assert mapReader.getKeyLocationMap().get("John").size() == 3;
    }

    @Test
    public void testParseLineMapInfo(){
        String line = "John-->[[lineOffset=13000,charOffset=1755]\n";
        mapReader.parseLine(line);
         KeyLocation keyLocation = (KeyLocation) mapReader.getKeyLocationMap().get("John").toArray()[0];
         assert keyLocation.getLineOffset() == 13000;
         assert keyLocation.getCharOffset() == 1755;
    }
}
