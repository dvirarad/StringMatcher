package tests;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.sun.org.apache.bcel.internal.classfile.LineNumber;
import logic.NameLocation;
import org.junit.Before;
import org.junit.Test;
import rw.MapReader;

/**
 * Created by dvira on 11/29/17.
 */
public class MapReaderTest {

    MapReader mapReader;

    @Before
    public void initMap(){
        mapReader = new MapReader("src/tests/test_map_reader.txt");
    }

    @Test
    public void testParseLineMapSize(){
        String line = "Thomas-->[13000@@2665, 13000@@4302, 13000@@52028]\n";
        mapReader.parseLine(line);
        assert mapReader.getNameLocationMap().get("Thomas").size() == 3;
    }

    @Test
    public void testParseLineMapInfo(){
        String line = "Thomas-->[13000@@2665]\n";
        mapReader.parseLine(line);
         NameLocation nameLocation = (NameLocation) mapReader.getNameLocationMap().get("Thomas").toArray()[0];
         assert nameLocation.getLineNumber() == 13000;
         assert nameLocation.getCharNumber() == 2665;
    }
}
