package duke;

import duke.taskings.Task;

import java.util.ArrayList;

public class Message {
    static final String LINE_DIVIDER = "____________________________________________________________";

    public static void greet() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("Enter <help> if you need the list of commands");
        System.out.println(LINE_DIVIDER);
    }

    public static void getHelp() { // To be updated.
        System.out.println("Available commands:");
        System.out.println("1) todo ");
        System.out.println("2) event, use </at> to indicate time period");
        System.out.println("3) deadline use </by> to indicate deadline");
        System.out.println("4) mark <item number>  ");
        System.out.println("5) unmark <item number>)");
        System.out.println("6) list ");
        System.out.println("7) bye ");
        System.out.println(LINE_DIVIDER);
    }

    public static void showExit() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Good bye! See you soon!!");
        System.out.println(LINE_DIVIDER);
    }

    public static void displayLineDivider() {
        System.out.println(LINE_DIVIDER);
    }

    public static void showWrongCommand() {
        System.out.println(LINE_DIVIDER);
        System.out.println("You have entered the wrong command. ");
        System.out.println("Please enter <help> for the list of available commands");
        System.out.println(LINE_DIVIDER);
    }

    public static void showErrorMessage(String command, String errorType) {
        switch (errorType) {
        case "NonNumericError":
            System.out.println("Invalid " + command + " command - non numeric index detected.");
            System.out.println(LINE_DIVIDER);
            break;
        case "IndexBeyondBoundError":
            System.out.println("Invalid " + command + " command - Index out of bounds.");
            System.out.println(LINE_DIVIDER);
            break;
        default:
            showWrongCommand();
        }
    }

    public static void getAcknowledgement(String command) {
        switch (command) {
        case "mark":
            System.out.println("Nice! I've marked this task as done:");
            break;

        case "unmark":
            System.out.println("OK, I've marked this task as NOT done yet:");

            break;
        case "delete":
            System.out.println("Noted. I've removed this task:");
            break;

        case "list":
            System.out.println("Here are the tasks in your list:");
            break;
        default:
            System.out.println("Error displaying acknowledgement message. Please inform adminstrator to fix");
        }
    }


    public static void getEntryFullStatus(ArrayList<Task> tasks, int index) {
        System.out.println("[" + tasks.get(index).getTaskType() + "]" + "[" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).getDescription());
    }



    /**
     * Prints the correct number of task message when called.
     *
     * @param numOfTasks the current count of tasking
     */
    public static void printNumOfTasks(int numOfTasks) {
        if (numOfTasks == 0) {
            System.out.println("There are no tasks");
        } else if (numOfTasks == 1) {
            System.out.println("There is only 1 task!");
        } else {
            System.out.println("There are " + numOfTasks + " tasks left :( ");
        }
    }
}
