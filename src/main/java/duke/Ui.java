package duke;

/**
 * Class to represent the user interface through the command line
 */
public class Ui {
    private static final String GREETING = "Hello! I'm Timmy\n What can I do for you?\n \n";
    private static final String GOODBYE = "Bye. Hope to see you again soon\n";
    public static final String LOGO =  " ____        _        \n" +
                                        "|  _ \\ _   _| | _____ \n" +
                                        "| | | | | | | |/ / _ \\\n" +
                                        "| |_| | |_| |   <  __/\n" +
                                        "|____/ \\__,_|_|\\_\\___|";
    public static final String DASH = "____________________________________________________________";

    /**
     * Method to welcome the user after program starts
     */
    public static void welcomeUser() {
        System.out.println(GREETING);
        System.out.println(DASH);
        System.out.println(LOGO);
        System.out.println(DASH);
    }

    /**
     * Method to say goodbye to the user after program exits
     */
    public static void goodbyeUser() {
        System.out.println(GOODBYE);
        System.out.println(DASH);
    }
}
