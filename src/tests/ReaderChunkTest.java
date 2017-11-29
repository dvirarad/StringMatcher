package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rw.LinesPacket;
import rw.ReaderChunk;


/**
 * Created by dvir arad on 11/29/17.
 */
public class ReaderChunkTest {

    ReaderChunk reader;

    @Before
    public void readerInit(){
        reader = new ReaderChunk(" http://norvig.com/big.txt",1);
    }

    @Test
    public void readOneChunk(){
        LinesPacket lp = reader.readLines().get();
        assert lp.lineOffset == 0;
        assert lp.stringBuilder.toString().equals("The Project Gutenberg EBook of The Adventures of Sherlock Holmes") ;
    }

    @Test
    public void readTwoChunk(){
        reader.readLines();
        LinesPacket lp = reader.readLines().get();
        assert lp.lineOffset == 1;
        assert lp.stringBuilder.toString().equals("by Sir Arthur Conan Doyle") ;
    }

    @Test
    public void readFiveChunk(){
        reader.readLines();
        reader.readLines();
        reader.readLines();
        reader.readLines();
        LinesPacket lp = reader.readLines().get();
        assert lp.lineOffset == 4;
        assert lp.stringBuilder.toString().equals("Copyright laws are changing all over the world. Be sure to check the");
    }

    @After
    public void close(){
        reader.close();
    }
}
