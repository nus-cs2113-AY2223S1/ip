package duke;

import duke.command.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

public class Duke {

    private static final int COMMAND_INDEX = 0;
    private static final int TASK_DETAIL_INDEX = 1;
    /** Minus 1 to convert user input index which starts from 1 to 0 for array indexing */
    private static final int OFFSET_TO_ARRAY_INDEX = 1;
    /** Array of assignments that is limited to 100 tasks */
    private static final Task[] assignments = new Task[100];
    /** Use to track the number of Task that is added */
    private static int indexTask = 0;
    /** Check to see if User input toDo task */
    private static boolean isToDo = false;
    /** Check to see if User input deadline task */
    private static boolean isDeadline = false;
    /** Check to see if User input event task */
    private static boolean isEvent = false;

    /**
     * Sorts the type of Task that is input by the user and prints out the respective types.
     * Based on the keyword "todo", "deadline" or "event".
     * Has helper functions addToDoTask(), addEventTask() and addDeadlineTask()
     *
     * @param splitUserInputs array of string that have been split into two.
     */
    public static void validateTypeOfTask(String[] splitUserInputs) {
        isToDo = splitUserInputs[COMMAND_INDEX].equals("todo");
        isDeadline = splitUserInputs[COMMAND_INDEX].equals("deadline");
        isEvent = splitUserInputs[COMMAND_INDEX].equals("event");
        boolean isNoType = !isToDo && !isDeadline && !isEvent;

        if (!hasValidTypeOfTask(splitUserInputs, isNoType)) {
            return;
        }
        printTypeOfTaskDetails();
        indexTask++;
    }

    /**
     * Checks the type of task that the user input, if the input specified does not belong
     * to any of the task, it would print a warning. Likewise, if an exception arise from a
     * specific type of task, a warning message will be printed.
     *
     * @param splitUserInputs array of strings that is split into two words.
     * @param isNoType to check if the user has input a nonTypeTask
     * @return isValidTypeOfTask a boolean that checks if the user has input a type of task correctly
     */
    private static boolean hasValidTypeOfTask(String[] splitUserInputs, boolean isNoType) {
        boolean isValidTypeOfTask = true;
        try {
            if (isNoType) {
                System.out.println("\t ☹ HMM?? I'm sorry, but I don't know what that means :-(");
                isValidTypeOfTask = false;
            }
            addTypeOfTask(splitUserInputs);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            printTypeOfTaskError();
            isValidTypeOfTask = false;
        }
        return isValidTypeOfTask;
    }

    /**
     * Adds on the type of task based on the COMMAND_INDEX.
     *
     * @param splitUserInputs array of strings that is split into two words.
     * @throws DukeException which generates an error if the user input a blank task detail or
     * did not fill up the task detail.
     */
    public static void addTypeOfTask(String[] splitUserInputs) throws DukeException {
        if (splitUserInputs[TASK_DETAIL_INDEX].isBlank()) {
            throw new DukeException();
        } else if (isToDo) {
            addToDoTask(splitUserInputs);
        } else if (isDeadline) {
            addDeadlineTask(splitUserInputs);
        } else if (isEvent) {
            addEventTask(splitUserInputs);
        }
    }

    /**
     * Returns the indexTask that is .
     *
     * @param assignment that takes in the type of task.
     */
    public static void addTask(Task assignment) {
        assignments[indexTask] = assignment;
    }

    /**
     * Adds in the type of task which in this case Event task.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    private static void addEventTask(String[] splitUserInputs) {
        addTask(new Event(splitUserInputs[TASK_DETAIL_INDEX]));
        assignments[indexTask].markTypeTask();
    }

    /**
     * Adds in the type of task which in this case Deadline task.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    private static void addDeadlineTask(String[] splitUserInputs) {
        addTask(new Deadline(splitUserInputs[TASK_DETAIL_INDEX]));
        assignments[indexTask].markTypeTask();
    }

    /**
     * Adds in the type of task which in this case to do task.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    private static void addToDoTask(String[] splitUserInputs) {
        String taskDetail = splitUserInputs[TASK_DETAIL_INDEX];
        addTask(new ToDo(taskDetail));
        assignments[indexTask].markTypeTask();
    }

    /**
     * Validates the marked or unmarked task then proceeds to mark or unmark a task.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    private static void validateMarkOrUnmarkTask(String[] splitUserInputs) {
        try {
            int indexOfMark = readIndexOfMarkOrUnmark(splitUserInputs);
            //To handle a case where user tries to mark or unmark a task that has not been specified
            markOrUnmarkTask(splitUserInputs, indexOfMark);
        } catch (DukeException e) {
            System.out.println("\t Hey! Please choose a positive digit that correspondence to the list.");
        }
    }

    /**
     * Returns a boolean true or false called error to inform user if he or she is trying
     * to mark a task that is not defined or specified.
     *
     * @param splitUserInputs an array of String that has been split into individual words.
     * @param indexOfMark     index of the mark in splitUserInput[1].
     */
    public static void markOrUnmarkTask(String[] splitUserInputs, int indexOfMark) {
        boolean isMark = splitUserInputs[COMMAND_INDEX].equals("mark");

        try {
            if (isMark) {
                assignments[indexOfMark].markAsDone();
            } else {
                assignments[indexOfMark].unmarkAsDone();
            }
        } catch (NullPointerException e) {
            if (isMark) {
                System.out.println("\t You are trying to mark a task that has not been specified!");
            } else {
                System.out.println("\t You are trying to unmark a task that has not been specified!");
            }
            return;
        }
        printMarkOrUnmarkTask(indexOfMark, isMark);
    }

    /**
     * Reads the index of where the mark or unmark is based on the user input then translate
     * to indexing based on an array which index starts from 0 instead of 1.
     *
     * @param splitUserInputs an array of String that has been split into individual words.
     * @return indexOfMarkOrUnmark which is the index of where to mark or unmark in the list.
     * @throws DukeException when the indexOfMark is not a digit, it will generate an error
     */
    public static int readIndexOfMarkOrUnmark(String[] splitUserInputs) throws DukeException {
        boolean isPositiveDigits = splitUserInputs[TASK_DETAIL_INDEX].matches("[0-9]+")
                && !splitUserInputs[TASK_DETAIL_INDEX].startsWith("-");
        if (!isPositiveDigits) {
            throw new DukeException();
        }
        int indexOfMarkOrUnmark = Integer.parseInt(splitUserInputs[TASK_DETAIL_INDEX]);
        return indexOfMarkOrUnmark - OFFSET_TO_ARRAY_INDEX;
    }

    /**
     * Prints the starting message of the CLI.
     *
     * @return linDivider a string for line separator
     */
    public static String printWelcomeMessage() {
        String logo = "___________.__                ___.\n"
                + "\\_   _____/|  | _____    _____|  |__\n"
                + " |    __)  |  | \\__  \\  /  ___/  |  \\\n"
                + " |     \\   |  |__/ __ \\_\\___ \\|   Y  \\\n"
                + " \\___  /   |____(____  /____  >___|  /\n"
                + "     \\/              \\/     \\/     \\/\n";
        System.out.println("Hello from\n" + logo);
        String lineDivider = "____________________________________________________________\n";
        System.out.println("\t" + lineDivider
                + "\t Hello! I'm Flash\n"
                + getDataFileStatus()
                + "\n\t What can I do for you?\n"
                + "\t" + lineDivider);
        return lineDivider;
    }

    /**
     * Prints out a list of the tasks saved from the user inputs.
     */
    public static void printList() {
        int numberOrder = 1;

        System.out.println("\t Here are the tasks in your list:");

        for (int i = 0; i < indexTask; i++) {
            System.out.println("\t " + numberOrder + ".["
                    + assignments[i].getStatusOfTypeTask() + "]["
                    + assignments[i].getStatusOfDone() + "] "
                    + assignments[i].displayTypeTaskDetails());
            numberOrder++;
        }
    }

    /**
     * Prints a message in CLI to inform the user if the task has been marked or not.
     *
     * @param markIndex index of which task to mark in splitUserInput[1].
     * @param isMark    boolean true or false to check if the task has been marked.
     */
    public static void printMarkOrUnmarkTask(int markIndex, boolean isMark) {
        if (isMark) {
            System.out.println("\t Awesome! I've marked this task as done:");
        } else {
            System.out.println("\t Awesome! I've marked this task as not done yet:");
        }
        System.out.println("\t\t [" + assignments[markIndex].getStatusOfTypeTask()
                + "]" + "[" + assignments[markIndex].getStatusOfDone()
                + "] " + assignments[markIndex].getDescription());
    }

    /**
     * Prints the type of task and its respective details
     * of the description that the user input.
     */
    public static void printTypeOfTaskDetails() {
        int countTask = indexTask + 1;
        String startStatement = "\t Roger that. I've added this task:\n";
        String displayTaskDetails = "\t   [" + assignments[indexTask].getStatusOfTypeTask()
                + "]" + "[ ] " + assignments[indexTask].displayTypeTaskDetails() + "\n";
        String endStatement = "\t Now you have " + countTask + " in the list.";
        System.out.println(startStatement + displayTaskDetails + endStatement);
    }

    /**
     * Prints the Task Error in case the user calls for a type of task
     * but did not specify the task detail.
     */
    public static void printTypeOfTaskError() {
        if (isToDo) {
            System.out.println("\t ☹ OH MAN!!! The description of a todo cannot be empty.");
        } else if (isDeadline) {
            System.out.println("\t ☹ OH MAN!!! The description of a deadline cannot be empty.");
        } else if (isEvent) {
            System.out.println("\t ☹ OH MAN!!! The description of an event cannot be empty.");
        }
    }

    private static String getDataFileStatus() {
        DataFile file = new DataFile();
        String fileStatus = file.getDataFile();
        return fileStatus;
    }

    public static void main(String[] args) {

        String lineDivider = printWelcomeMessage();
        String userInput;
        Scanner in = new Scanner(System.in);
        boolean isBye;

        do {
            userInput = in.nextLine();
            System.out.print("\t" + lineDivider);
            String[] splitUserInputs = userInput.split(" ", 2);
            isBye = splitUserInputs[COMMAND_INDEX].equals("bye");

            boolean isList = splitUserInputs[COMMAND_INDEX].equals("list");
            boolean isMarkOrUnmark = splitUserInputs[COMMAND_INDEX].equals("mark")
                || splitUserInputs[COMMAND_INDEX].equals("unmark");

            if (isBye) {
                System.out.println("\t Bye. Hope to see you again soon!");
            } else if (isList) {
                printList();
            } else if (isMarkOrUnmark) {
                validateMarkOrUnmarkTask(splitUserInputs);
            } else {
                validateTypeOfTask(splitUserInputs);
            }
            System.out.println("\t" + lineDivider);
        } while (!isBye);
    }
}



