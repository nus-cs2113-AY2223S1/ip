package duke;

import java.util.Scanner;

public class UI {
    /**
     * A concatenation of strings that visually represents the word Duke
     */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * A string visually repressing a line
     */
    private static final String LINEBREAK = "____________________________________________________________";

    /**
     * prints the LINEBREAK String
     */
    static void printLine() {
        System.out.println(LINEBREAK);
    }

    /**
     * prints the LOGO string preceded by a Hello from greeting
     */
    static void printLogo() {
        System.out.println("Hello from\n" + LOGO);
    }


    /**
     * Prints the User Interface associated with the start of the program
     */
    static void startSession() {
        UI.printLogo();
        UI.printLine();
        System.out.println("Hello! I'm Duke Nukem");
        System.out.println("What can I do for you today? Let's rock!");
        UI.printLine();
    }

    /**
     * Prints the User Interface associated with the end of the program
     */
    static void endSession() {
        UI.printLine();
        System.out.println("Bye. Hope to see you again soon! Groovy!");
        UI.printLine();
    }

    /**
     * Reads lines from the User
     */
    static void readLines() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!Parser.checkBye(line)) {
            Parser.parse(Duke.Manager, line);
            line = in.nextLine();
        }
    }
}
