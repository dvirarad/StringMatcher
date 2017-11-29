import com.google.common.base.Stopwatch;
import configoration.Globals;
import logic.ThreadNamesMatcher;
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
public class Main {
    public static void main(String[] args) {
        System.out.println("Start");
        final Stopwatch stopwatch = Stopwatch.createStarted();

        List<Thread> runnableList = new ArrayList<>(Globals.NUMBER_OF_THREAD);
        ReaderChunk reader = new ReaderChunk(Globals.WEB_ADDRESS);
        MapWriter mapWriter = new MapWriter();
        List<String> nameList = new ArrayList(Arrays.asList(Globals.NAME_LIST));

        for (int i = 0; i < Globals.NUMBER_OF_THREAD; i++) {
            runnableList.add(new Thread(new ThreadNamesMatcher(reader,mapWriter,nameList), "Thread: " + (i + 1)));
        }
        for (Thread worker : runnableList) {
            worker.start();
        }
        for (Thread worker : runnableList) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        MapReader mapReader = new MapReader();
        mapReader.loadMapFromFile();
        mapWriter.writeMapSummary(mapReader.getNameLocationMap());


        System.out.println("Time pass:" + stopwatch.elapsed(TimeUnit.SECONDS));
        System.out.println("Done");



    }
}
