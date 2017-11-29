package rw;

/**
 * Created by dvira on 11/28/17.
 */
public class LinesPacket {
    public StringBuilder stringBuilder;
    public int linesCounter;

    public LinesPacket(StringBuilder stringBuilder, int linesCounter) {
        this.stringBuilder = stringBuilder;
        this.linesCounter = linesCounter;
    }
}
