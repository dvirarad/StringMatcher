package tests;

import com.google.common.collect.*;
import logic.KeyLocation;
import org.junit.Before;
import org.junit.Test;
import rw.MapWriter;

/**
 * Created by dvir arad on 11/29/17.
 */
public class MapWriterTest {
    Multimap<String, KeyLocation> nameLocationHashMap;
    MapWriter writerHashMap;
    @Before
    public void readerInit(){
        nameLocationHashMap = ArrayListMultimap.create();
        writerHashMap = new MapWriter();
    }

    @Test
    public void writeSomeTable(){
        nameLocationHashMap.put("David",new KeyLocation(1,4));
        nameLocationHashMap.put("David",new KeyLocation(3,8));
        nameLocationHashMap.put("David",new KeyLocation(2,5));
        nameLocationHashMap.put("Eyal",new KeyLocation(5,1));
        nameLocationHashMap.put("Eyal",new KeyLocation(9,52));
        nameLocationHashMap.put("Maayan",new KeyLocation(2,56));
        writerHashMap.writeMapFromMatcher(nameLocationHashMap);
    }
}
