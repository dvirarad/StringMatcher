package rw;

/**
 * Created by dvir arad on 11/29/17.
 */
public class LinesPacket {
    public StringBuilder stringBuilder;
    public int lineOffset;

    /**
     * Holder of Text
     * @param stringBuilder Chunk of text from Big file
     * @param lineOffset - Line starting point
     */
    public LinesPacket(StringBuilder stringBuilder, int lineOffset) {
        this.stringBuilder = stringBuilder;
        this.lineOffset = lineOffset;
    }
}
