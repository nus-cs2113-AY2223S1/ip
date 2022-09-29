package consoleCommands;

import java.util.Scanner;

/**
 * Class to execute interface-based commands in programme
 */
public class Ui {
    public static final String HELLO_MESSAGE = "Hello! I'm Duke\n" + "What can I do for you?";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String LOGO = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Method prints a line across, to demarcate between consecutive inputs and outputs
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Called at the start of the programme, to print introduction message
     */
    public void start() {
        printLine();
        System.out.println(LOGO);
        System.out.println(HELLO_MESSAGE);
        printLine();
    }

    /**
     * Called at the end of the programme, to print goodbye message
     */
    public void end() {
        printLine();
        System.out.println(BYE_MESSAGE);
        printLine();
    }
    public Ui() {
    }

    /**
     * Method to read input
     * @return input to Duke.java for further parsing
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Method to print exception error message
     * @param message is the error message of the respective exceptions
     */
    public void showError(String message) {
        System.out.println(message);
        printLine();
    }
}
