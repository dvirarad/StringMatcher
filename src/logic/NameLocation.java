package logic;

/**
 * Created by dvir arad on 11/29/17.
 */
public class NameLocation {

    int lineOffset;
    int charOffset;

    /**
     * Represent location in text
     * @param lineOffset - line offset from the beginning of text
     * @param charOffset - char starting place at the current offset
     */
    public NameLocation(int lineOffset, int charOffset) {
        this.lineOffset = lineOffset;
        this.charOffset = charOffset;
    }

    public int getLineOffset() {
        return lineOffset;
    }

    public int getCharOffset() {
        return charOffset;
    }


    @Override
    public String toString() {
        return "[" +"lineOffset="+ lineOffset +
                "," +
                "charOffset="+ charOffset +"]";

    }
}
