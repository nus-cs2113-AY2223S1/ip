package duke;

import duke.task.Task;

public class Message extends Duke {
    public static final String helloMessage = "Hello I'm Duke\nwhat can I do for you?\nenter help for input options";
    public static final String line = "-----------------------------------------------------------";
    public static final String errorLine = "!---------------------------------------------------------!";
    public static final String helloFromMessage = "Hello from\n";
    public static final String amountOfTasksInList = "Now you have %d tasks in the list.\n";
    public static final String fileErrorMessage = "Error using list text, check file";
    public static final String invalidInputMessage = "Invalid input, enter again in a correct format";
    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String beginningDeleteMessage = "Noted. I've removed this task: ";
    public static final String unknownErrorMessage = "Sorry, an unknown error occurred ";
    public static final String helpMessage = "The following option are available:\n" +
            "find {keyword}\nlist\nmark/unmark {index in duke list}\ntodo {description)\n" +
            "event {description} /at {time_description}\ndeadline {description} /by {time_description}\n" +
            "delete {index in duke list}";
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
        System.out.println(line);
    }

    /**
     * print horizontal error line message to the user
     */
    public static void printHorizontalErrorLine() {
        System.out.println(errorLine);
    }

    /**
     * prints an error message to the user -
     * "Invalid input, enter again in a correct format"
     */
    public static void printError() {
        printHorizontalErrorLine();
        System.out.println(invalidInputMessage);
        printHorizontalErrorLine();
    }


    /**
     * prints an error the user when there is an
     * error with the text file
     */

    public static void printSystemError() {
        printHorizontalErrorLine();
        System.out.println(fileErrorMessage);
        printHorizontalErrorLine();
    }

    /**
     * prints the duke logo to the user
     */
    public static void printLogo() {
        System.out.println(helloFromMessage + logo);
    }

    /**
     * prints an error to the user
     * that an unknown error has happened
     */

    public static void printUnknownError() {
        printHorizontalErrorLine();
        System.out.println(unknownErrorMessage);
        printHorizontalErrorLine();
    }


    public static void printHelp() {
        System.out.println(helpMessage);
    }

    /**
     * prints to the user that the input task has been deleted
     *
     * @param taskToDelete
     */
    public static void printTaskDeleted(Task taskToDelete) {
        System.out.println(beginningDeleteMessage);
        System.out.println(taskToDelete);
        System.out.printf(amountOfTasksInList, dukeList.getListSize());
    }


}
