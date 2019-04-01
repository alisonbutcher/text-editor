package com.company;


/**
 * Manipulates string to make unicode control chars visible / invisible
 */
public class Unicode {

    /**
     * Constructor
     */
    public Unicode () {}


    /**
     * Make unicode control characters within a string visible
     *
     * @param data is the string representing the entire text data
     * @return modified data string with visible unicode control characters
     */
    public String showUnicode(String data) {
        String newString = "";

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);

            if (c == ' '){// Space
                newString += (char) 9248;
            } else if (c == '\t') {// Tab
                newString += (char) 8677;
            } else if (c == '\n') {// Linefeed
                newString += (char) 9226 + System.lineSeparator();
            } else if (c == '\r') {// Carriage Return
                newString += (char) 9229;
            } else {
                newString += data.charAt(i);
            }
        }
        return newString;
    }


    /**
     * Hides unicode control characters
     *
     * @param data is the string representing the entire text data
     * @return modified data string with no visible unicode control characters
     */
    public String hideUnicode(String data) {
        String newString = "";

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);

            if (c == (char) 9248){// Space
                newString += " ";
            } else if (c == (char) 8677) {// Tab
                newString += "\t";
            } else if (c == (char) 9226) {// Linefeed
                newString += "\n";
                i = i + System.lineSeparator().length();
            } else if (c == (char) 9229) {// Carriage Return
                newString += "\r";
            } else {
                newString += data.charAt(i);
            }
        }
        return newString;
    }

}
