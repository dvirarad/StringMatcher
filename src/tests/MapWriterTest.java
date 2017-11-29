package tests;

import com.google.common.collect.*;
import logic.NameLocation;
import org.junit.Before;
import org.junit.Test;
import rw.MapWriter;

/**
 * Created by dvira on 11/28/17.
 */
public class MapWriterTest {
    Multimap<String,NameLocation> nameLocationHashMap;
    MapWriter writerHashMap;
    @Before
    public void readerInit(){
        nameLocationHashMap = ArrayListMultimap.create();
        writerHashMap = new MapWriter();
    }

    @Test
    public void writeSomeTable(){
        nameLocationHashMap.put("David",new NameLocation(1,4));
        nameLocationHashMap.put("David",new NameLocation(3,8));
        nameLocationHashMap.put("David",new NameLocation(2,5));
        nameLocationHashMap.put("Eyal",new NameLocation(5,1));
        nameLocationHashMap.put("Eyal",new NameLocation(9,52));
        nameLocationHashMap.put("Maayan",new NameLocation(2,56));
        writerHashMap.writeMapFromMatcher(nameLocationHashMap);
    }
}
