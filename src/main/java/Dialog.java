import java.util.ArrayList;
import java.util.List;

public class Dialog {
    private final int HORIZONTAL_PADDING = 3;
    private final int INDENT_SIZE = 3;
    private final int maxRowLength;
    private int height;
    private int width;
    private List<String> textRows;
    private List<String> textBoxRows;

    public Dialog (String text, int consoleWidth, int boxHorizontalPosition) {
        textRows = new ArrayList<String>();
        textBoxRows = new ArrayList<String>();
        maxRowLength = consoleWidth - boxHorizontalPosition - this.HORIZONTAL_PADDING * 2 - 2;
        for (String row : text.split("\n")) {
            if (row.length() > maxRowLength) {
                String bufferString = "";
                String indent = "";
                int indentLength = 0;
                for (String word : row.split(" ")) {
                    if (word.length() > maxRowLength) {
                        word = indent + (bufferString.isEmpty() ? bufferString : bufferString.trim() + " ") + word;
                        bufferString = generateIndent(indentLength);
                        int i = 0;
                        while (i < word.length()) {
                            textRows.add(indent + word.substring(i, Math.min(word.length(), i + maxRowLength - indentLength)));
                            i += maxRowLength - indentLength;
                            indentLength = INDENT_SIZE;
                            indent = generateIndent(INDENT_SIZE);
                            bufferString = indent;
                        }
                    } else {
                        if (bufferString.length() + word.length() <= maxRowLength - indentLength) {
                            bufferString += word + " ";
                        } else {
                            indentLength = INDENT_SIZE;
                            indent = generateIndent(indentLength);
                            textRows.add(bufferString.substring(0, bufferString.length() - 1));
                            bufferString = indent + word + " ";
                        }
                    }
                }
                if (!bufferString.isEmpty() && !bufferString.equals(generateIndent(INDENT_SIZE))) {
                    textRows.add(bufferString.substring(0, bufferString.length() - 1));
                }
            } else {
                textRows.add(row);
            }
        }
        makeBox();
    }

    private void makeBox () {
        width = maxRowLength + HORIZONTAL_PADDING * 2;
        height = textRows.size() + 2;
        String vertical = "";
        for (int i = 0; i <= width; i++) {
            vertical += "=";
        }
        for (String row : textRows) {
            textBoxRows.add(" |" + generateIndent(HORIZONTAL_PADDING) + row +
                    generateIndent(width - row.length() - HORIZONTAL_PADDING) + "|");
        }

        textBoxRows.add(0, "  " + vertical);
        textBoxRows.add("/=" + vertical);
    }

    public static String generateIndent (int indentsize) {
        String indent = "";
        for (int i = 0; i < indentsize; i++) {
            indent += " ";
        }
        return indent;
    }

    public int getHeight () {
        return height;
    }

    public int getWidth () {
        return width;
    }

    public List<String> getTextBox () {
        return textBoxRows;
    }
}
