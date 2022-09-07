import java.util.Scanner;

public class Duke {
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
        isToDo = splitUserInputs[0].equals("todo");
        isDeadline = splitUserInputs[0].equals("deadline");
        isEvent = splitUserInputs[0].equals("event");
        boolean isNoType = !isToDo && !isDeadline && !isEvent;

        try {
            if (isNoType) {
                System.out.println("\t ☹ HMM?? I'm sorry, but I don't know what that means :-(");
                return;
            }
            addTypeOfTask(splitUserInputs);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            printTypeOfTaskError();
            return;
        }
        printTypeOfTaskDetails();
        indexTask++;
    }

    /**
     * @param splitUserInputs array of strings that is split into two.
     */
    public static void addTypeOfTask(String[] splitUserInputs) throws DukeException {
        if (splitUserInputs[1].isBlank()) {
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
     * @param splitUserInputs array of strings that is split into two.
     */
    private static void addEventTask(String[] splitUserInputs) {
        addTask(new Event(splitUserInputs[1]));
        assignments[indexTask].markTypeTask();
    }

    /**
     * Adds in the type of task which in this case Deadline task.
     *
     * @param splitUserInputs array of strings that is split into two.
     */
    private static void addDeadlineTask(String[] splitUserInputs) {
        addTask(new Deadline(splitUserInputs[1]));
        assignments[indexTask].markTypeTask();
    }

    /**
     * Adds in the type of task which in this case to do task.
     *
     * @param splitUserInputs array of strings that is split into two.
     */
    private static void addToDoTask(String[] splitUserInputs) {
        String taskDetail = splitUserInputs[1];
        addTask(new ToDo(taskDetail));
        assignments[indexTask].markTypeTask();
    }

    /**
     * validates the marked or unmarked task then proceeds to mark or unmark a task.
     *
     * @param splitUserInputs array of strings that is split into two.
     */
    private static void validateMarkOrUnmarkTask(String[] splitUserInputs) {
        try {
            int indexOfMark = readIndexOfMark(splitUserInputs);
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
        boolean isMark = splitUserInputs[0].equals("mark");

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

    public static int readIndexOfMark(String[] splitUserInputs) throws DukeException {
        boolean isPositiveDigits = splitUserInputs[1].matches("[0-9]+")
                && !splitUserInputs[1].startsWith("-");
        if (!isPositiveDigits) {
            throw new DukeException();
        }
        //Digit taken from userInput index from 1 onwards
        int indexOfMark = Integer.parseInt(splitUserInputs[1]);
        //Minus 1 to get index of mark of in list of task as index from 0 onwards
        indexOfMark = indexOfMark - 1;
        return indexOfMark;
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
                + "\t What can I do for you?\n"
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
                + "] " + assignments[markIndex].description);
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

    public static void main(String[] args) {

        String lineDivider = printWelcomeMessage();
        String userInput;
        Scanner in = new Scanner(System.in);
        boolean isBye;

        do {
            //Enable user to enter text
            userInput = in.nextLine();
            System.out.print("\t" + lineDivider);
            String[] splitUserInputs = userInput.split(" ", 2);
            isBye = splitUserInputs[0].equals("bye");

            boolean isUserInputData = !splitUserInputs[0].equals("bye")
                    && !splitUserInputs[0].equals("list")
                    && !splitUserInputs[0].equals("mark")
                    && !splitUserInputs[0].equals("unmark");
            boolean isList = splitUserInputs[0].equals("list");
            boolean isMarkOrUnmark = splitUserInputs[0].equals("mark")
                    || splitUserInputs[0].equals("unmark");

            if (isUserInputData) {
                validateTypeOfTask(splitUserInputs);
            } else if (isList) {
                printList();
            } else if (isMarkOrUnmark) {
                validateMarkOrUnmarkTask(splitUserInputs);
            } else {
                System.out.println("\t Bye. Hope to see you again soon!");
            }
            System.out.println("\t" + lineDivider);
        } while (!isBye);
    }
}



