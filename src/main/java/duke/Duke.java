package duke;

import duke.command.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> assignments = new ArrayList<>();
    private static final int COMMAND_INDEX = 0;
    private static final int TASK_DETAIL_INDEX = 1;
    /** Minus 1 to convert user input index which starts from 1 to 0 for array indexing */
    private static final int OFFSET_TO_ARRAY_INDEX = 1;

    /** Use to track the number of Task that is added */
    private static int indexTask = 0;
    /** Index that the user have chosen to (un)mark or delete a task */
    private static int indexOfChoice = 0;
    private static int countTask = 0;
    private static boolean isList = false;
    private static boolean isBye = false;
    private static boolean isMarkOrUnmark = false;
    private static boolean isMark = false;
    private static boolean isToDo = false;
    private static boolean isDeadline = false;
    private static boolean isEvent = false;
    private static boolean isDelete = false;
    private static boolean isNotCommand = false;
    /** User input a task command without providing details */
    private static boolean isBlankDetail = false;

    /**
     * Checks a set of boolean variables to map out the types of command being called.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    public static void checkTypeOfCommand(String[] splitUserInputs) {
        isBye = splitUserInputs[COMMAND_INDEX].equals("bye");
        isList = splitUserInputs[COMMAND_INDEX].equals("list");
        isMarkOrUnmark = splitUserInputs[COMMAND_INDEX].equals("mark")
                || splitUserInputs[COMMAND_INDEX].equals("unmark");
        isToDo = splitUserInputs[COMMAND_INDEX].equals("todo");
        isDeadline = splitUserInputs[COMMAND_INDEX].equals("deadline");
        isEvent = splitUserInputs[COMMAND_INDEX].equals("event");
        isMark = splitUserInputs[COMMAND_INDEX].equals("mark");
        isDelete = splitUserInputs[COMMAND_INDEX].equals("delete");
        isNotCommand = !(isList || isMarkOrUnmark || isToDo || isDeadline || isEvent || isDelete);
    }

    /**
     * Checks a set of boolean variables to map out the types of details
     * that the user has input.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    public static void checkDetailOfCommand(String[] splitUserInputs) {
        boolean isTaskCommand = splitUserInputs.length > 1;
        if (isTaskCommand) {
            isBlankDetail = splitUserInputs[TASK_DETAIL_INDEX].isBlank();
        }
    }

    /**
     * Gets the user input and split them into two parts of a string.
     *
     * @param in an input variable that the user to assigned too.
     * @return userInput.split(" ", 2) which is the split user input.
     */
    public static String[] getSplitUserInput(Scanner in) {
        String userInput;
        userInput = in.nextLine();
        return userInput.split(" ", 2);
    }

    /**
     * Runs the command that is given by the user input.
     *
     * @param splitUserInput array of strings that is split into two words.
     */
    public static void runCommand(String[] splitUserInput) {
        if (isBye) {
            System.out.println("\t Bye. Hope to see you again soon!");
        } else {
            validateTypeOfCommand(splitUserInput);
        }
    }

    /**
     * Validates the type of commands that is input by the user.
     * Based on the keyword commands.
     * Has helper functions checkValidCommand(splitUserInput)
     *
     * @param splitUserInput array of strings that is split into two words.
     */
    public static void validateTypeOfCommand(String[] splitUserInput) {
        if (isNotCommand) {
            System.out.println("\t ☹ HMM?? I'm sorry, but I don't know what that means :-(");
        } else {
            checkValidCommand(splitUserInput);
        }
    }

    /**
     * Checks the type of command that the user input, if the input specified does not belong
     * to any of the key word command, it would print a warning. Likewise, if an exception arise from a
     * specific type of task, a warning message will be printed.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    private static void checkValidCommand(String[] splitUserInputs) {
        try {
            executeValidCommand(splitUserInputs);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            printTypeOfTaskError();
        }
    }

    /**
     * Executes the verified commands based on the respective keywords.
     *
     * @param splitUserInputs array of strings that is split into two words.
     * @throws DukeException which generates an error if the user input a blank task detail or
     *                       did not fill up the task detail.
     */
    public static void executeValidCommand(String[] splitUserInputs) throws DukeException {
        if (isList) {
          printList();
        } else if (isBlankDetail) {
            throw new DukeException();
        } else if (isMarkOrUnmark) {
            markOrUnmarkTask(splitUserInputs);
        }  else if (isToDo) {
            addToDoTask(splitUserInputs);
        } else if (isDeadline) {
            addDeadlineTask(splitUserInputs);
        } else if (isEvent) {
            addEventTask(splitUserInputs);
        } else if (isDelete) {
            deleteTask(splitUserInputs);
        }
    }

    /**
     * Adds in the assignment to the specific task.
     *
     * @param assignment that takes in the type of task.
     */
    public static void addTask(Task assignment) {
        assignments.add(assignment);
    }

    /**
     * Adds in the type of task which in this case Event task.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    private static void addEventTask(String[] splitUserInputs) {
        addTask(new Event(splitUserInputs[TASK_DETAIL_INDEX]));
        assignments.get(indexTask).markTypeTask();
        printTypeOfTaskDetail();
        indexTask++;
    }

    /**
     * Adds in the type of task which in this case Deadline task.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    private static void addDeadlineTask(String[] splitUserInputs) {
        addTask(new Deadline(splitUserInputs[TASK_DETAIL_INDEX]));
        assignments.get(indexTask).markTypeTask();
        printTypeOfTaskDetail();
        indexTask++;
    }

    /**
     * Adds in the type of task which in this case to do task.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    private static void addToDoTask(String[] splitUserInputs) {
        String taskDetail = splitUserInputs[TASK_DETAIL_INDEX];
        addTask(new ToDo(taskDetail));
        assignments.get(indexTask).markTypeTask();
        printTypeOfTaskDetail();
        indexTask++;
    }

    /**
     * Deletes the task that is specified by the user.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    private static void deleteTask(String[] splitUserInputs) {
        validateIndexOfChoice(splitUserInputs);
        countTask--;
        printDeletedTask();
        assignments.remove(indexOfChoice);
        indexTask--;

    }

    /**
     * Validates the index of choice of the user input to check if it is input correctly.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    private static void validateIndexOfChoice(String[] splitUserInputs) {
        //To handle a case where user tries to mark or unmark a task that has not been specified
        try {
            indexOfChoice = readIndexOfChoice(splitUserInputs);
        } catch (DukeException e) {
            System.out.println("\t Hey! Please choose a positive digit that correspondence to the list.");
        }
    }

    /**
     * Marks or unmark a task that the User specifies and also checks that the User
     * has input the mark command correctly before proceeding.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    public static void markOrUnmarkTask(String[] splitUserInputs) {
        validateIndexOfChoice(splitUserInputs);
        try {
            if (isMark) {
                assignments.get(indexOfChoice).markAsDone();
            } else {
                assignments.get(indexOfChoice).unmarkAsDone();
            }
        } catch (IndexOutOfBoundsException e) {
            if (isMark) {
                System.out.println("\t You are trying to mark a task that has not been specified!");
            } else {
                System.out.println("\t You are trying to unmark a task that has not been specified!");
            }
            return;
        }
        printMarkOrUnmarkTask(isMark);
    }

    /**
     * Reads the index of choice from the User input.
     *
     * @param splitUserInputs array of strings that is split into two words.
     * @return indexOfChoice that would be offset to an array index.
     * @throws DukeException an error generated if the User input a negative number or blank.
     */
    public static int readIndexOfChoice(String[] splitUserInputs) throws DukeException {
        boolean isNotPositiveDigit = !splitUserInputs[TASK_DETAIL_INDEX].matches("[0-9]+")
                || splitUserInputs[TASK_DETAIL_INDEX].startsWith("-");
        if (isNotPositiveDigit) {
            throw new DukeException();
        }
        int indexOfTask = Integer.parseInt(splitUserInputs[TASK_DETAIL_INDEX]);
        return indexOfTask - OFFSET_TO_ARRAY_INDEX;
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
        String lineDivider = "____________________________________________________________";
        System.out.println("\t" + lineDivider
                + "\n\t Hello! I'm Flash\n"
                + "\t What can I do for you?\n"
                + "\t" + lineDivider + "\n");
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
                    + assignments.get(i).getStatusOfTypeTask() + "]["
                    + assignments.get(i).getStatusOfDone() + "] "
                    + assignments.get(i).displayTypeTaskDetails());
            numberOrder++;
        }
    }

    /**
     * Prints a message in CLI to inform the user if the task has been marked or not.
     *
     * @param isMark boolean true or false to check if the task has been marked.
     */
    public static void printMarkOrUnmarkTask(boolean isMark) {
        if (isMark) {
            System.out.println("\t Awesome! I've marked this task as done:");
        } else {
            System.out.println("\t Awesome! I've marked this task as not done yet:");
        }
        printTaskDetail();
    }

    /**
     * Prints the type of task and its respective details
     * of the description that the user input.
     */
    public static void printTypeOfTaskDetail() {
        System.out.println("\t Roger that. I've added this task:");
        printTaskDetail();
        countTask++;
        System.out.println("\t Now you have " + countTask + " in the list.");
    }

    /**
     * Prints the specific task detail that is acts as a helper function for
     * printTypeOfTaskDetail(), printMarkOrUnmarkTask(boolean isMark) and printDeletedTask().
     */
    public static void printTaskDetail() {
        int index = indexTask;
        if (isMarkOrUnmark || isDelete) {
            index = indexOfChoice;
        }
        System.out.println("\t   [" + assignments.get(index).getStatusOfTypeTask()
                + "]" + "[" + assignments.get(index).getStatusOfDone()
                + "] " + assignments.get(index).displayTypeTaskDetails());
    }

    /**
     * Prints the deleted task for the user to see.
     */
    public static void printDeletedTask() {
        System.out.println("\t Noted. I've removed this task:");
        printTaskDetail();
        System.out.println("\t Now you have " + countTask + " tasks in the list.");
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
        } else if (isMarkOrUnmark) {
            System.out.println("\t ☹ OH MAN!!! You did not tell me which to (un)mark.");
        }
    }

    public static void main(String[] args) {

        String lineDivider = printWelcomeMessage();
        Scanner in = new Scanner(System.in);

        while (!isBye) {
            String[] splitUserInputs = getSplitUserInput(in);
            System.out.println("\t" + lineDivider);
            checkTypeOfCommand(splitUserInputs);
            checkDetailOfCommand(splitUserInputs);
            runCommand(splitUserInputs);
            System.out.println("\t" + lineDivider + "\n");
        }
    }
}



