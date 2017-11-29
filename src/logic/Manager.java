package logic;

import com.google.common.base.Stopwatch;
import configoration.Globals;
import rw.MapReader;
import rw.MapWriter;
import rw.ReaderChunk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by dvira on 11/29/17.
 */
public class Manager {
    List<Thread> runnableList;
    ReaderChunk reader;
    MapWriter mapWriter;
    List<String> nameList;
    final Stopwatch stopwatch = Stopwatch.createStarted();

    public Manager(){
        System.out.println("Start Manager");
        runnableList = new ArrayList<>(Globals.NUMBER_OF_THREAD);
        reader = new ReaderChunk(Globals.WEB_ADDRESS);
        mapWriter = new MapWriter();
        nameList = new ArrayList(Arrays.asList(Globals.NAME_LIST));


    }

    public void writeSummary() {
        MapReader mapReader = new MapReader();
        mapReader.loadMapFromFile();
        mapWriter.writeMapSummary(mapReader.getNameLocationMap());
        System.out.println("Done Job");
        System.out.println("Time pass:" + stopwatch.elapsed(TimeUnit.SECONDS));
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
            runnableList.add(new Thread(new ThreadNamesMatcher(reader,mapWriter,nameList), "Thread: " + (i + 1)));
        }
    }


}
