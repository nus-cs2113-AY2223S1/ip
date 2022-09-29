package duke.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui implements Utilities {

    private static final String LOGO = "\n"
            + "     _________________________________________\n"
            + "   /  _____________________________________   \\ \n"
            + "   | |                                     |  | \n"
            + "   | |  C:\\> Initiating programme. .  .    |  | \n"
            + "   | |                                     |  | \n"
            + "   | |  C:\\> Creating Duke...              |  | \n"
            + "   | |                                     |  | \n"
            + "   | |                                     |  | \n"
            + "   | |                                     |  | \n"
            + "   | |_____________________________________|  | \n"
            + "    \\_____________________________________/ \n"
            + "       \\________________________________/ \n"
            + "        _________________________________ \n"
            + "   _-'.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.--- `-_ \n"
            + "_-'.-.-. .---.-.-.-.-.-.-.-.-.-.-.-.-.-.-.--..-.-.`-_ \n";

    private static final String MESSAGE_GREET = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_BYE = "BEEP BEEP >>>> SEE >>> YOU >>>> AGAIN >>> BEEP BEWWWWW >>>";
    private static final String LINE_DIVIDER = "--------------------------------------";

    private ArrayList<String> uiBuffer;
    private Scanner scanner;

    public Ui() {
        uiBuffer = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void close() {
        uiBuffer.clear();
        scanner.close();
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void printUi() {
        //do not print line divider by default
        printUi(false);
    }

    public void printUi(boolean hasLineDivider) {
        if (hasLineDivider){
            showLine();
        }

        for(String line: uiBuffer) {
            System.out.println(line);
        }

        if (hasLineDivider){
            showLine();
        }

        uiBuffer.clear();
    }

    public void addLine(String message) {
        uiBuffer.add(message);
    }

    public void addLine(ArrayList<String> messages) {
        for(String message: messages) {
            addLine(message);
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println(LINE_DIVIDER);
    }

    public void greetUser() {
        System.out.println(LOGO);
        System.out.println(MESSAGE_GREET);
    }

    public void endMessage() {
        System.out.println(MESSAGE_BYE);
    }

}
