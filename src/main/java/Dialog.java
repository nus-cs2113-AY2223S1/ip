import java.util.ArrayList;
import java.util.List;

public class Dialog {
    private final int HORIZONTAL_PADDING = 6;
    private final int INDENT_SIZE = 3;
    private int maxRowLength;
    private int height;
    private int width;
    private List<String> textRows = new ArrayList<String>();
    private List<String> textBox = new ArrayList<String>();

    public Dialog(String text, int consoleWidth, int boxHorizontalPosition) {
        this.maxRowLength = consoleWidth - boxHorizontalPosition - this.HORIZONTAL_PADDING*2 - 2;
        for (String row : text.split("\n")) {
            boolean isFirstLine = true;
            if (row.length() > maxRowLength) {
                String bufferString = "";
                String indent = "";
                int indentLength = 0;
                for (String word : row.split(" ")){
                    if (word.length() > maxRowLength){
                        word = bufferString.trim() + " " + word;
                        bufferString = generateIndent(INDENT_SIZE);
                        for (int i = 0; i < word.length(); i += maxRowLength) {
                            if (isFirstLine){
                                textRows.add(word.substring(i, i + maxRowLength));
                                isFirstLine = false;
                            }
                            else {
                                textRows.add(generateIndent(INDENT_SIZE) + word.substring(i, Math.min(word.length(), i + maxRowLength - INDENT_SIZE)));
                            }
                        }
                    }
                    else {
                        if (bufferString.length() + word.length() <= maxRowLength - indentLength) {
                            bufferString += word + " ";
                            isFirstLine = false;
                        } else {
                            indentLength = INDENT_SIZE;
                            indent = generateIndent(indentLength);
                            textRows.add(bufferString.substring(0, bufferString.length() - 1));
                            bufferString = indent + word + " ";
                        }
                    }
                }
                if (!bufferString.isEmpty() || !bufferString.equals(generateIndent(INDENT_SIZE))){
                    textRows.add(bufferString.substring(0, bufferString.length()-1));
                }
            }
            else {
                textRows.add(row);
            }
        }
        makeBox();
    }
    private void makeBox(){
        width = maxRowLength + HORIZONTAL_PADDING*2;
        height = textRows.size() + 2;
        String vertical = "";
        for (int i = 0; i <= width; i++){
            vertical+="=";
        }
        for (String row: textRows){
            textBox.add(" |" + generateIndent(HORIZONTAL_PADDING) + row + generateIndent( width - row.length() - HORIZONTAL_PADDING) + "|");
        }

        textBox.add(0,"  " + vertical);
        textBox.add("/=" + vertical);
    }

    public String generateIndent(int indentsize){
        String indent = "";
        for (int i = 0; i < indentsize; i++) {
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
        return textBox;
    }
}
