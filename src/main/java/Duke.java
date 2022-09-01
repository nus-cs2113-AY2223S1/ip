import java.util.Scanner;

public class Duke {
    private static final Task[] ASSIGNMENTS = new Task[100];
    private static int indexTask = 0;

    /**
     * Sort the type of Task that is input by the user and prints out the respective types.
     * Based on the keyword "todo", "deadline" or "event".
     * Has helper functions addToDoTask(), addEventTask() and addDeadlineTask()
     *
     * @param userInput the string input by the user.
     * @param splitUserInputs array of string that have been split into two.
     */
    public static void sortTypeOfTask(String userInput, String[] splitUserInputs) {
        boolean isToDo = splitUserInputs[0].equals("todo");
        boolean isDeadlines = splitUserInputs[0].equals("deadline");
        boolean isEvent = splitUserInputs[0].equals("event");

        System.out.println("\t Roger that. I've added this task:");


        if(isToDo) {
            addToDoTask(splitUserInputs);
        } else if (isDeadlines) {
            addDeadlineTask(splitUserInputs);
        } else if (isEvent) {
            addEventTask(splitUserInputs);
        } else {
            addNoTypeTask(userInput);
        }
        indexTask++;
        System.out.println("\t Now you have " + indexTask + " in the list.");
    }

    /**
     * Returns the indexTask that is .
     *
     * @param assignment assignment that is based on the type of task.
     */
    public static void addTask(Task assignment) {
        ASSIGNMENTS[indexTask] = assignment;
    }

    /**
     * Adds in the type of task which in this case no type task.
     *
     * @param userInput that is input by the user.
     */
    private static void addNoTypeTask(String userInput) {
        addTask(new Task(userInput));
        System.out.println("\t   [ ][ ] " + ASSIGNMENTS[indexTask].description);
    }

    /**
     * Adds in the type of task which in this case Event task.
     *
     * @param splitUserInputs array of strings that is split into two.
     */
    private static void addEventTask(String[] splitUserInputs) {
        addTask(new Event(splitUserInputs[1]));
        ASSIGNMENTS[indexTask].markTypeTask();
        System.out.println("\t   [" + ASSIGNMENTS[indexTask].getStatusOfTypeTask() + "]" + "[ ] "
                + ASSIGNMENTS[indexTask].displayTypeTaskDetails());
    }

    /**
     * Adds in the type of task which in this case Deadline task.
     *
     * @param splitUserInputs array of strings that is split into two.
     */
    private static void addDeadlineTask(String[] splitUserInputs) {
        addTask(new Deadline(splitUserInputs[1]));
        ASSIGNMENTS[indexTask].markTypeTask();
        System.out.println("\t   [" + ASSIGNMENTS[indexTask].getStatusOfTypeTask() + "]" + "[ ] "
                + ASSIGNMENTS[indexTask].displayTypeTaskDetails());
    }

    /**
     * Adds in the type of task which in this case to do task.
     *
     * @param splitUserInputs array of strings that is split into two.
     */
    private static void addToDoTask(String[] splitUserInputs) {
        addTask(new ToDo(splitUserInputs[1]));
        ASSIGNMENTS[indexTask].markTypeTask();
        System.out.println("\t   [" + ASSIGNMENTS[indexTask].getStatusOfTypeTask() + "]" + "[ ] "
                + ASSIGNMENTS[indexTask].description);
    }

    /**
     * Returns a boolean true or false called error to inform user if he or she is trying
     * to mark a task that is not defined or specified.
     *
     * @param markIndex index of the mark in splitUserInput[1].
     * @param lineDivider a string for line separator.
     * @return error which is a boolean that tell us if there is an error or not.
     */
    public static boolean hasErrorMarkTask(int markIndex, String lineDivider) {
        boolean error = false;
        try {
            ASSIGNMENTS[markIndex].markAsDone();
        } catch(NullPointerException e) {
            System.out.println("\t You are trying to mark a task that has not been specified!");
            System.out.println("\t" + lineDivider);
            error = true;
        }
        return error;
    }

    /**
     * Returns a boolean true or false error to inform user if he or she is trying
     * to unmark a task that is not defined or specified.
     *
     * @param unMarkIndex index of the unmark in splitUserInput[1].
     * @param lineDivider a string for line separator.
     * @return error which is a boolean that tell us if there is an error or not.
     */
    public static boolean hasErrorUnMarkTask(int unMarkIndex, String lineDivider){
        boolean error = false;
        try {
            ASSIGNMENTS[unMarkIndex].unmarkAsDone();
        } catch(NullPointerException e) {
            System.out.println("\t You are trying to unmark a task that has not been specified!");
            System.out.println("\t" + lineDivider);
            error = true;
        }
        return error;
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
        System.out.println("Hello from\n"+ logo);
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

        for(int i = 0; i < indexTask; i++) {
            System.out.println("\t " + numberOrder + ".["
                    + ASSIGNMENTS[i].getStatusOfTypeTask() + "]["
                    + ASSIGNMENTS[i].getStatusOfDone() + "] "
                    + ASSIGNMENTS[i].displayTypeTaskDetails());
            numberOrder++;
        }
    }

    /**
     * Prints a message in CLI to inform the user if the task has been marked or not.
     *
     * @param markIndex index of which task to mark in splitUserInput[1].
     * @param isMark boolean true or false to check if the task has been marked.
     */
    public static void printMarkOrUnmarkTask(int markIndex, boolean isMark) {
        if(isMark) {
            System.out.println("\t Awesome! I've marked this task as done:");
        } else {
            System.out.println("\t Awesome! I've marked this task as not done yet:");
        }
        System.out.println("\t\t [" + ASSIGNMENTS[markIndex].getStatusOfTypeTask()
                + "]" + "[" + ASSIGNMENTS[markIndex].getStatusOfDone()
                + "] " + ASSIGNMENTS[markIndex].description);
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
            boolean isMark = splitUserInputs[0].equals("mark");
            boolean isUnmark = splitUserInputs[0].equals("unmark");

            if(isUserInputData) {
                sortTypeOfTask(userInput, splitUserInputs);
            } else if (isList) {
                printList();
            } else if (isMark) {
                int indexMark = Integer.parseInt(splitUserInputs[1]) - 1;
                //To handle a case where user tries to mark a task that has not been specified
                if(hasErrorMarkTask(indexMark, lineDivider)) {
                    continue;
                }
                printMarkOrUnmarkTask(indexMark, true);
            } else if (isUnmark) {
                int indexMark = Integer.parseInt(splitUserInputs[1]) - 1;
                //To handle a case where user tries to unmark a task that has not been specified
                if(hasErrorUnMarkTask(indexMark, lineDivider)){
                    continue;
                }
                printMarkOrUnmarkTask(indexMark, false);
            } else {
                System.out.println("\t Bye. Hope to see you again soon!");
            }
            System.out.println("\t" + lineDivider);
        }
        while(!isBye);
    }
}
