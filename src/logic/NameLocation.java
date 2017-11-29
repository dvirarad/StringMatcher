package logic;

import configoration.Globals;

/**
 * Created by dvira on 11/28/17.
 */
public class NameLocation {

    int lineOffset;
    int charOffset;

    public NameLocation(int lineNumber, int charNumber) {
        this.lineOffset = lineNumber;
        this.charOffset = charNumber;
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
