package duke.util;

import java.util.ArrayList;

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

    private static final String MESSAGE_GREET = "Hello! I'm Duke\n What can I do for you?";

    private static final String MESSAGE_BYE = "BEEP BEEP >>>> SEE >>> YOU >>>> AGAIN >>> BEEP BEWWWWW >>>";

    private static final String LINE_DIVIDER = "-------------------";
    private static ArrayList<String> uiBuffer = new ArrayList<>();


    public Ui() {
        uiBuffer = new ArrayList<>();
    }

    public static void init() {
        uiBuffer = new ArrayList<>();
    }

    public static void close() {
        uiBuffer = new ArrayList<>();
    }

    public static void printUi() {
        //print line divider by default
        printUi(true);
    }

    public static void printUi(boolean hasLineDivider) {
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

    public static void addLine(String message) {
        uiBuffer.add(message);
    }

    public static void addLines(String[] messages) {
        for(String message: messages) {
            addLine(message);
        }
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void showLine() {
        System.out.println(LINE_DIVIDER);
    }

    public static void greetUser() {
        System.out.println(LOGO);
        System.out.println(MESSAGE_GREET);
    }

    public static void endMessage() {
        System.out.println(MESSAGE_BYE);
    }

}
