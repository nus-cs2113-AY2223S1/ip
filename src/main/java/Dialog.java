import java.util.ArrayList;
import java.util.List;

public class Dialog {
    private final int HORIZONTAL_PADDING = 3;
    private final int INDENT_SIZE = 3;
    private final int maxRowLength;
    private int height;
    private int width;
    private List<String> textBoxRows;
    private boolean isFirstLine = true;

    public Dialog(String text, int consoleWidth, int boxHorizontalPosition) {
        maxRowLength = consoleWidth - boxHorizontalPosition - this.HORIZONTAL_PADDING * 2 - 2;
        textBoxRows = new ArrayList<String>();
        makeBox(indentText(text));
    }

    private List<String> indentText(String text) {
        List<String> textRows = new ArrayList<String>();
        for (String row : text.split("\n")) {
            textRows.addAll(indentItem(row));
        }
        return textRows;
    }

    private List<String> indentItem(String row) {
        isFirstLine = true;
        List<String> textRowsIndividual = new ArrayList<String>();
        if (row.length() <= maxRowLength) {  //if row longer than max len
            textRowsIndividual.add(row);
            return textRowsIndividual;
        } else {
            int indentLength = 0;
            int maxLength = maxRowLength;
            String bufferString = "";
            for (String word : row.split(" ")) {
                if (word.length() > maxLength) {    //if word is longer, split it into multiple chunks
                    bufferString = splitWord(word, textRowsIndividual, maxLength, bufferString);
                    isFirstLine = false;
                    indentLength = INDENT_SIZE;
                    maxLength = maxRowLength - INDENT_SIZE;
                    continue;
                }
                //add word to buffer string until buffer string is full
                if (bufferString.length() + word.length() > maxLength) {
                    textRowsIndividual.add(generateIndent(indentLength) + bufferString.trim());
                    bufferString = "";
                    isFirstLine = false;
                    indentLength = INDENT_SIZE;
                    maxLength = maxRowLength - INDENT_SIZE;
                }
                bufferString += word + " ";
            }
            //append any remaining string as another row
            textRowsIndividual.add(generateIndent(indentLength) + bufferString.trim());
        }
        return textRowsIndividual;
    }

    private String splitWord(String word, List<String> textRows, int maxLength, String bufferString) {
        int index = 0;
        word = bufferString + word;
        if (isFirstLine) {
            textRows.add(word.substring(index, maxLength));
            index = maxLength;
            maxLength -= INDENT_SIZE;
        }
        for (; index < word.length(); index += maxLength) {
            if (index + maxLength > word.length()) {
                return generateIndent(INDENT_SIZE) + word.substring(index) + " ";
            }
            textRows.add(generateIndent(INDENT_SIZE) + word.substring(index, index + maxLength));
        }
        return "";
    }

    private void makeBox(List<String> textRows) {
        width = maxRowLength + HORIZONTAL_PADDING * 2;
        height = textRows.size() + 2;
        String vertical = "";
        for (int i = 0; i <= width; i++) {
            vertical += "=";
        }
        for (String row : textRows) {
            textBoxRows.add(" |" + generateIndent(HORIZONTAL_PADDING) + row + generateIndent(width - row.length() - HORIZONTAL_PADDING) + "|");
        }

        textBoxRows.add(0, "  " + vertical);
        textBoxRows.add("/=" + vertical);
    }

    public static String generateIndent(int indentSize) {
        String indent = "";
        for (int i = 0; i < indentSize; i++) {
            indent += " ";
        }
        return indent;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<String> getTextBox() {
        return textBoxRows;
    }
}
