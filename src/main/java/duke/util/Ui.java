package duke.util;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to provide interface with the user
 * Read from user and formats the output before printing to terminal
 */
public class Ui implements Utilities {

    private static final String LOGO = System.lineSeparator()
            + "     _________________________________________" + System.lineSeparator()
            + "   /  _____________________________________   \\ " + System.lineSeparator()
            + "   | |                                     |  | " + System.lineSeparator()
            + "   | |  C:\\> Initiating programme. .  .    |  | " + System.lineSeparator()
            + "   | |                                     |  | " + System.lineSeparator()
            + "   | |  C:\\> Creating Duke...              |  | " + System.lineSeparator()
            + "   | |                                     |  | " + System.lineSeparator()
            + "   | |                                     |  | " + System.lineSeparator()
            + "   | |                                     |  | " + System.lineSeparator()
            + "   | |_____________________________________|  | " + System.lineSeparator()
            + "    \\_____________________________________/ " + System.lineSeparator()
            + "       \\________________________________/ " + System.lineSeparator()
            + "        _________________________________ " + System.lineSeparator()
            + "   _-'.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.--- `-_ " + System.lineSeparator()
            + "_-'.-.-. .---.-.-.-.-.-.-.-.-.-.-.-.-.-.-.--..-.-.`-_ " + System.lineSeparator();

    private static final String MESSAGE_GREET = "Hello! I'm Duke, your Personal Task Manager"
            + System.lineSeparator() + "What can I do for you?";
    private static final String MESSAGE_HELP = "Enter \"help\" to get started!";
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
        if (hasLineDivider) {
            showLine();
        }

        for (String line: uiBuffer) {
            System.out.println(line);
        }

        if (hasLineDivider) {
            showLine();
        }

        uiBuffer.clear();
    }

    /**
     * To store a message into the ui buffer
     *
     * @param message
     */
    public void addLine(String message) {
        uiBuffer.add(message);
    }

    public void addLine(ArrayList<String> messages) {
        for (String message: messages) {
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
        System.out.println(MESSAGE_HELP);
    }

    public void endMessage() {
        System.out.println(MESSAGE_BYE);
        showLine();
        showLine();
    }

}
