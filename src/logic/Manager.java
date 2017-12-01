package logic;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Multimap;
import configuration.Globals;
import rw.MapReader;
import rw.MapWriter;
import rw.ReaderChunk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by dvir arad on 11/29/17.
 */
public class Manager {

    List<Thread> runnableList;
    ReaderChunk reader;
    MapWriter mapWriter;
    List<String> listKeyToFind;
    final Stopwatch stopwatch = Stopwatch.createStarted();

    /**
     *  Manages the Thread, Reader, Writer and creates a summarizing document
     */
    public Manager(){
        System.out.println("Start Manager");
        runnableList = new ArrayList<>(Globals.NUMBER_OF_THREAD);
        reader = new ReaderChunk(Globals.WEB_ADDRESS);
        mapWriter = new MapWriter();
        listKeyToFind = new ArrayList(Arrays.asList(Globals.NAME_LIST));
    }

    /**
     * Read map from File
     * Write Map to Summary File
     */
    public void writeSummary() {
        Multimap<String, KeyLocation> keyLocationMap = getKeyLocationMap();
        mapWriter.writeMapSummary(keyLocationMap);
        System.out.println("Done Job");
        System.out.println("Time pass:" + stopwatch.elapsed(TimeUnit.SECONDS));
    }

    /**
     * Read map from File(file path location at configuration )
     * @return Map of key and locations
     */
    public Multimap<String, KeyLocation> getKeyLocationMap() {
        MapReader mapReader = new MapReader();
        mapReader.loadMapFromFile();
        return mapReader.getKeyLocationMap();
    }

    public void startThreadMatcher() {
        startThread();
        joinThread();
        System.out.println("finish all matcher thread\nTime pass:" + stopwatch.elapsed(TimeUnit.SECONDS));

    }

    private void joinThread() {
        for (Thread matcher : runnableList) {
            try {
                matcher.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void startThread() {
        for (Thread matcher : runnableList) {
            matcher.start();
        }
    }

    public void createMatcher(int amountOfThread) {
        for (int i = 0; i <amountOfThread; i++) {
            runnableList.add(new Thread(new ThreadMatcher(reader,mapWriter, listKeyToFind), "Thread: " + (i + 1)));
        }
    }


    public void start() {
        createMatcher(Globals.NUMBER_OF_THREAD);
        startThreadMatcher();
        writeSummary();
    }
}
