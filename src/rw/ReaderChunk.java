package rw;

import configuration.Globals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

/**
 * Created by dvir arad on 11/29/17.
 */
public class ReaderChunk {

private BufferedReader in;
private int linesCounter=0;
private int defaultChunkSize;

    public ReaderChunk(String webAddress) {
        this(webAddress, Globals.DEFAULT_CHUNK_SIZE);
    }

    /**
     * Responsible of reading text from web address
     * @param webAddress  address of web to read from
     * @param defaultChunkSize - amount of rows read each time
     */
    public ReaderChunk(String webAddress, int defaultChunkSize){
        this.defaultChunkSize = defaultChunkSize;
        try {
            URL addressPath = new URL(webAddress);
            URLConnection urlConnection = addressPath.openConnection();
            in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Optional<LinesPacket> readLines(){
        return readLines(this.defaultChunkSize);
    }

    /**
     * Read text
     * @param chunksSize amount of rows to read
     * @return Chunk of rows from original text
     */
    private synchronized Optional<LinesPacket> readLines(int chunksSize){
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;
        boolean isFirstLine = true;
        LinesPacket lp = null;
        try {
            while ((linesCounter % chunksSize != 0 || isFirstLine) && (inputLine = in.readLine()) != null){
               stringBuilder.append(inputLine);
                linesCounter++;
                isFirstLine=false;
            }
            lp = new LinesPacket(stringBuilder, linesCounter-this.defaultChunkSize);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return Optional.ofNullable(lp); // Safe from NullPointerException

        }
    }


    public void close(){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

