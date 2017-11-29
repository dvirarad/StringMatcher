package logic;

import configoration.Globals;

import java.io.Serializable;

/**
 * Created by dvira on 11/28/17.
 */
public class NameLocation implements Serializable {
    private static final long serialVersionUID = 1L;

    int lineNumber;
    int charNumber;

    public NameLocation(int lineNumber, int charNumber) {
        this.lineNumber = lineNumber;
        this.charNumber = charNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getCharNumber() {
        return charNumber;
    }

    @Override
    public String toString() {
//        return "[" +"lineOffset="+ lineNumber +
//                Globals.NAME_LOCATION_SEPARATOR +
//                "charOffset="+charNumber +"]";
        return "[" +""+ lineNumber +
                Globals.NAME_LOCATION_SEPARATOR +
                ""+charNumber +"]";
    }
}
