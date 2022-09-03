package duke.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * UI.DialogBox class to indent and wrap a given snippet of text and enclose a box around it.
 * Variable height depending on size of the given string of text.
 */
public class DialogBox {
    /** Character to use for each indent */
    private static final char INDENT_CHARACTER = ' ';

    /** Character to use for the top and bottom line in box */
    private static final char LINE_HORIZONTAL_CHARACTER = '=';

    /** Character to use for the left and right wall of box */
    private static final char LINE_VERTICAL_CHARACTER = '|';

    /** Spacing between text and horizontal walls of dialog box */
    private static final int HORIZONTAL_PADDING = 3;

    /** Size of each indentation */
    private static final int INDENT_SIZE = 4;

    /** Maximum row width. Based on calculations done with desired console width, horizontal position and padding */
    private static int MAX_ROW_WIDTH;

    /** Text of the box expressed as a list of strings */
    private final List<String> textBoxRows;

    /** Height of the dialog box */
    private int height;

    /** Boolean used in switching logic for indentations between line breaks in text */
    private boolean isFirstLine = true;

    /**
     * Constructor for UI.DialogBox class. Calculates the max possible width of the box
     * and formats text to be indented, wrapped and then boxed in with lines.
     *
     * @param text                  text of the box to be formatted
     * @param consoleWidth          maximum width of the console
     * @param boxHorizontalPosition absolute horizontal position the left of the box should align with
     */
    public DialogBox(String text, int consoleWidth, int boxHorizontalPosition) {
        MAX_ROW_WIDTH = consoleWidth - boxHorizontalPosition - HORIZONTAL_PADDING * 2 - 2;
        textBoxRows = new ArrayList<String>();
        makeBox(indentAndWrapText(text));
    }

    /**
     * Method for generating indents the size of a given integer.
     *
     * @param indentSize desired width of the indent
     * @return an indent string of given size. Empty string if {@code indentSize} = 0
     */
    public static String generateIndent(int indentSize, char indentCharacter) {
        String indent = "";
        for (int i = 0; i < indentSize; i++) {
            indent += indentCharacter;
        }
        return indent;
    }

    /**
     * Right-aligns text with reference to text box width by adding spaces
     * to the front.
     *
     * @param text string of text to be aligned
     * @return aligned string, or nothing if text is empty
     */
    public static String rightAlign(String text) {
        if (text.isEmpty()) {
            // Nothing to align if text is empty.
            // Avoids an extra line break
            return "";
        } else if (text.length() >= MAX_ROW_WIDTH) {
            // No need to align (or add a line break) if
            //text is longer than the max width
            return " " + text;
        } else {
            return "\n" + generateIndent(MAX_ROW_WIDTH - text.length(), ' ')
                    + text;
        }
    }

    /**
     * Indents and wraps text to be less wide or equal to {@link DialogBox#MAX_ROW_WIDTH}
     *
     * @param text text to be indented
     * @return indented and wrapped text expressed as a list of strings
     */
    private List<String> indentAndWrapText(String text) {
        List<String> textRows = new ArrayList<String>();
        for (String row : text.split("\n")) {
            textRows.addAll(indentAndWrapItem(row));
        }
        return textRows;
    }

    /**
     * Indents and wraps each individual row of text to be less wide
     * or equal to {@link DialogBox#MAX_ROW_WIDTH}
     *
     * @param item A string of text separated by line breaks
     * @return indented and wrapped text for each item expressed as a list of strings
     */
    private List<String> indentAndWrapItem(String item) {
        isFirstLine = true;
        List<String> textRowsIndividual = new ArrayList<>();

        if (item.length() <= MAX_ROW_WIDTH) {
            // if entire item is shorter or equal to max length,
            // just return the entire row
            textRowsIndividual.add(item);
            return textRowsIndividual;
        } else {
            int indentLength = 0;
            int maxLength = MAX_ROW_WIDTH;
            String bufferString = "";

            // Add words to a single buffer string until the
            // string is too long, then add entire buffer string
            // as a row and repeat.
            for (String word : item.split(" ")) {
                if (word.length() > maxLength) {
                    // if word is longer than max length
                    // split it into multiple chunks
                    bufferString = splitWord(word, textRowsIndividual, maxLength, bufferString);
                    isFirstLine = false;
                    indentLength = INDENT_SIZE;
                    maxLength = MAX_ROW_WIDTH - INDENT_SIZE;
                    continue;
                }

                if (bufferString.length() + word.length() > maxLength) {
                    // if length of buffer string + new word exceeds max length
                    // add buffer string as a new row and reset buffer string
                    textRowsIndividual.add(generateIndent(indentLength, INDENT_CHARACTER) + bufferString.trim());
                    bufferString = "";
                    isFirstLine = false;
                    indentLength = INDENT_SIZE;
                    maxLength = MAX_ROW_WIDTH - INDENT_SIZE;
                }
                bufferString += word + " ";
            }
            //append any remaining string as the last row
            textRowsIndividual.add(generateIndent(indentLength, INDENT_CHARACTER) + bufferString.trim());
        }
        return textRowsIndividual;
    }

    /**
     * Splits a word that is too long for the maximum length into chunks that
     * can be indented and then added to the {@code textRows} list as rows
     *
     * @param word         Text to be broken up into multiple rows
     * @param textRows     List that the rows should be appended to
     * @param maxLength    Maximum length that each row should wrap to
     * @param bufferString The string to append the word to
     * @return Any remaining string after the wrapping takes place
     */
    private String splitWord(String word, List<String> textRows, int maxLength, String bufferString) {
        int index = 0;

        // Add word to bufferString, so they get wrapped together as a single line of text
        word = bufferString + word;

        if (isFirstLine) {
            // if current line is the first in item, no indentation for the first row.
            textRows.add(word.substring(index, maxLength));
            index = maxLength;

            // all subsequent lines should have indentation
            maxLength -= INDENT_SIZE;
        }
        for (; index < word.length(); index += maxLength) {
            // break up word into indented chunks
            if (index + maxLength > word.length()) {
                return generateIndent(INDENT_SIZE, INDENT_CHARACTER) + word.substring(index) + " ";
            }
            textRows.add(generateIndent(INDENT_SIZE, INDENT_CHARACTER) + word.substring(index, index + maxLength));
        }
        return word.substring(index);
    }

    /**
     * Make an ASCII box to enclose (indented, wrapped) text.
     *
     * @param textRows wrapped text to modify and add ASCII box to.
     */
    private void makeBox(List<String> textRows) {
        int width = MAX_ROW_WIDTH + HORIZONTAL_PADDING * 2;
        height = textRows.size() + 2;

        // Vertical lines at the side of the text
        for (String row : textRows) {
            textBoxRows.add(" " + LINE_VERTICAL_CHARACTER + generateIndent(HORIZONTAL_PADDING, INDENT_CHARACTER)
                    + row + generateIndent(width - row.length() - HORIZONTAL_PADDING, ' ') + LINE_VERTICAL_CHARACTER);
        }

        // Horizontal lines to close the box
        textBoxRows.add(0, "  " + generateIndent(width, LINE_HORIZONTAL_CHARACTER));
        textBoxRows.add("/ " + generateIndent(width, LINE_HORIZONTAL_CHARACTER));
    }

    public int getHeight() {
        return height;
    }

    public List<String> getTextBox() {
        return textBoxRows;
    }
}
