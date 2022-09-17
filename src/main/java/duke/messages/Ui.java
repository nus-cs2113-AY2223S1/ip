package duke.messages;

import duke.taskings.Deadline;
import duke.taskings.Task;

import java.util.ArrayList;

public class Ui {
    static final String MISSING_FILE = "MissingFile";
    static final String FAILED_CREATION = "FailedFileCreation";
    static final String FILE_INPUT_FAILED = "FailedFileInput";
    static final String LINE_DIVIDER = "____________________________________________________________";
    static final String DEADLINE = "D";
    static final String EVENT = "E";
    static final String TODO = "T";

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

    public static void printMatchedTasks(ArrayList<Task> matchedTasks) {
        for (Task task : matchedTasks) {
            String taskType = task.getTaskType();
            switch (taskType) {
            case TODO:
                System.out.println("[" + task.getTaskType() + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription());
                break;
            case EVENT:
                System.out.println("[" + task.getTaskType() + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription() + "at ("+ task.getDateTime() +" )");
                break;
            case DEADLINE:
                System.out.println("[" + task.getTaskType() + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription() +"by ("+ task.getDateTime()+" )");
                break;
            default:
                break;
            }
        }
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
        case "WrongFormatError":
            System.out.println("Invalid " + command + " command - Wrong input format.");
            System.out.println(LINE_DIVIDER);
            break;
        default:
            showWrongCommand();
        }
    }

    public static void getAcknowledgement(String command) {
        System.out.println(LINE_DIVIDER);
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
        System.out.println(LINE_DIVIDER);
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


    public static void showFileError(String errorType) {
        switch (errorType) {
        case MISSING_FILE:
            System.out.println("File not found.");
            break;
        case FAILED_CREATION:
            System.out.println("File creation error occurred.");
            break;
        case FILE_INPUT_FAILED:
            System.out.println("Cannot write into file.");
            break;
        default:
            break;
        }
    }


}
