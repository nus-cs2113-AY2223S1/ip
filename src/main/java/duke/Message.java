package duke;

public class Message {
    public static final String helloMessage = "Hello I'm Duke\n what can I do for you?";
    public static final String byeMessage = "Bye. Hope to see you soon";

    /**
     * prints greeting to the user
     */
    public static void printGreeting() {
        printHorizontalLine();
        System.out.println(helloMessage);
        printHorizontalLine();
    }

    /**
     * prints exit message to the user
     */

    public static void printingExit() {
        printHorizontalLine();
        System.out.println(byeMessage);
        printHorizontalLine();
    }


    /**
     * prints horizontal line to the user
     */

    public static void printHorizontalLine() {
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * print horizontal error line message to the user
     */
    public static void printHorizontalErrorLine() {
        System.out.println("!                                                         !");
    }

    /**
     * prints an error message to the user -
     * "Invalid input, enter again in a correct format"
     */
    public static void printError(){
        printHorizontalErrorLine();
        System.out.println("Invalid input, enter again in a correct format");
        printHorizontalErrorLine();
    }


    /**
     * prints an error the user when there is an
     * error with the text file
     */

    public static void printSystemError(){
        printHorizontalErrorLine();
        System.out.println("Error using list text, check file");
        printHorizontalErrorLine();
    }

    /**
     * prints the duke logo to the user
     */
    public static void printLogo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * prints an error to the user
     * that an unknown error has happend
     */

    public static void printUnknownError(){
        printHorizontalErrorLine();
        System.out.println("Sorry, an unknown error occurred ");
        printHorizontalErrorLine();
    }


}
