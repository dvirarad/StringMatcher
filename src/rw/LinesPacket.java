package rw;

/**
 * Created by dvira on 11/28/17.
 */
public class LinesPacket {
    public StringBuilder stringBuilder;
    public int lineOffset;

    public LinesPacket(StringBuilder stringBuilder, int lineOffset) {
        this.stringBuilder = stringBuilder;
        this.lineOffset = lineOffset;
    }
}
