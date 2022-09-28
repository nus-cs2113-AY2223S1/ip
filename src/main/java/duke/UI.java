package duke;

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
}
