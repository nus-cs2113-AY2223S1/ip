package duke;

public class Message {
    public static final String helloMessage = "Hello I'm Duke\n what can I do for you?";
    public static final String byeMessage = "Bye. Hope to see you soon";

    public static void printGreeting() {
        printHorizontalLine();
        System.out.println(helloMessage);
        printHorizontalLine();
    }

    public static void printingExit() {
        printHorizontalLine();
        System.out.println(byeMessage);
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        System.out.println("-----------------------------------------------------------");
    }

    public static void printHorizontalErrorLine() {
        System.out.println("!                                                         !");
    }

    public static void printError(){
        printHorizontalErrorLine();
        System.out.println("Invalid input, enter again in a correct format");
        printHorizontalErrorLine();
    }

    public static void printSystemError(){
        printHorizontalErrorLine();
        System.out.println("Error using list text, check file");
        printHorizontalErrorLine();
    }

    public static void printLogo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printUnknownError(){
        printHorizontalErrorLine();
        System.out.println("Sorry, an unknown error occurred ");
        printHorizontalErrorLine();
    }


}
