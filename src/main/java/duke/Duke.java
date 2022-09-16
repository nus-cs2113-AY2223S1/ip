package duke;

import duke.command.DukeException;
import duke.datafile.DataFile;
import duke.datafile.EditDataFile;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> assignments = new ArrayList<>();
    private static DataFile file = new EditDataFile();
    private static final int COMMAND_INDEX = 0;
    private static final int TASK_DETAIL_INDEX = 1;
    /** Minus 1 to convert user input index which starts from 1 to 0 for array indexing */
    private static final int OFFSET_TO_ARRAY_INDEX = 1;
    private static final int NO_TASK = 0;
    private static final int STATUS_INDEX = 1;
    private static final int TASK_TITLE_INDEX = 2;
    private static final int EVENT_OR_DEADLINE_DETAIL = 3;
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
    private static boolean isNotValidIndexOfChoice = false;
    private static boolean isToDoFromDataFile = false;
    private static boolean isDeadlineFromDateFile = false;
    private static boolean isEventFromDataFile = false;
    private static boolean isMarkFromDataFile = false;
    private static boolean isEventFromStoredInput = false;
    private static boolean isDeadlineFromStoredInput = false;

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
          getList();
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
        saveToFile();
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
        saveToFile();
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
        saveToFile();
    }

    /**
     * Deletes the task that is specified by the user.
     *
     * @param splitUserInputs array of strings that is split into two words.
     */
    private static void deleteTask(String[] splitUserInputs) {
        validateIndexOfChoice(splitUserInputs);
        if (isNotValidIndexOfChoice) {
            isNotValidIndexOfChoice = false;
            return;
        }
        countTask--;
        try {
            printDeletedTask();
            assignments.remove(indexOfChoice);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t Stop there! Please delete something that is within the list!");
            countTask++;
            return;
        }
        indexTask--;
        saveToFile();
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
            isNotValidIndexOfChoice = true;
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
            checkMarkOrUnmark();
        } catch (IndexOutOfBoundsException e) {
            printMarkOrUnmarkError();
            return;
        }
        printMarkOrUnmarkTask(isMark);
        saveToFile();
    }

    /**
     * Check to see if the task is to be marked or unmarked.
     */
    private static void checkMarkOrUnmark() {
        if (isMark) {
            assignments.get(indexOfChoice).markAsDone();
        } else {
            assignments.get(indexOfChoice).unmarkAsDone();
        }
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
                + file.getDataFileStatus()
                + "\n\t What can I do for you?\n"
                + "\t" + lineDivider + "\n");
        return lineDivider;
    }

    /**
     * Gets a list from the user inputs.
     */
    public static void getList() {
        int numberOrder = 1;
        if (indexTask == NO_TASK) {
            System.out.println("\t There is no task in the list.");
        } else {
            printList(numberOrder);
        }
    }

    /**
     * Prints out a list of the tasks saved from the user inputs.
     *
     * @param numberOrder a numbering index that is used to index the task in the list.
     */
    private static void printList(int numberOrder) {
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
        } else if (isDelete) {
            System.out.println("\t ☹ OH MAN!!! You did not tell me what to delete.");
        }
    }

    /**
     * Prints the error message of mark or unmark if the user is trying to specify
     * a (un)mark on a task that does not exist.
     */
    private static void printMarkOrUnmarkError() {
        if (isMark) {
            System.out.println("\t You are trying to mark a task that has not been specified!");
        } else {
            System.out.println("\t You are trying to unmark a task that has not been specified!");
        }
    }

    /**
     * Saves the existing data into dataFile.
     */
    private static void saveToFile() {
        StringBuilder taskList = new StringBuilder();
        for (int i = 0; i < indexTask; i++) {
            addToTaskList(taskList, i);
        }
        file.saveToFile(taskList.toString());
    }

    /**
     * Adds the data in the dataFile into a list of strings.
     *
     * @param taskList which is a list of data that is obtained from the dataFile.
     * @param increment which iterates through the list.
     */
    private static void addToTaskList(StringBuilder taskList, int increment) {
        taskList.append(getTaskDetail(increment)).append("\n");
    }

    /**
     * Gets the task detail from the stored input and changes the display format
     * which is used to be displayed in the dataFile.
     *
     * @param index obtains the index of the assignment to access it.
     * @return A string that changes the format of the details being
     * displayed in the dataFIle.
     */
    private static String getTaskDetail(int index) {
        String statusOfTypeTask = assignments.get(index)
                .getStatusOfTypeTask();
        String changeStatusOfDone = changeStatusOfDone(assignments
                .get(index).getStatusOfDone());
        String changeTaskDetails = changeDetailDisplay(index);
        return statusOfTypeTask + " | " + changeStatusOfDone + " | " + changeTaskDetails;
    }

    /**
     * Changes the format of how the task status is displayed in the dataFile.
     *
     * @param statusOfDone which is a string to obtain the status of
     *                     done or not denoted by "X".
     * @return statusOfDone which changes "X" to "1" if not "0".
     */
    private static String changeStatusOfDone(String statusOfDone) {
        boolean isChecked = statusOfDone.contains("X");
        if (isChecked) {
            statusOfDone = "1";
        } else {
            statusOfDone = "0";
        }
        return statusOfDone;
    }

    /**
     * Changes the format of how the task detail is being display in the dataFile.
     *
     * @param index obtains the index of the assignment to access it.
     * @return changeTaskDetails which is a string of changed display format.
     */
    private static String changeDetailDisplay(int index) {
        String changeTaskDetails = assignments.get(index).getDescription();
        checkStoredInputStatus(index);
        changeTaskDetails = modifyEVentOrDeadlineDetails(index, changeTaskDetails);
        return changeTaskDetails;
    }

    /**
     * Modifies the event's or deadline's task details to be displayed on the
     * dataFile.
     *
     * @param index obtains the index of the assignment to access it.
     * @param changeTaskDetails which is a string of changed display format.
     * @return changeTaskDetails which is a modified detail.
     */
    private static String modifyEVentOrDeadlineDetails(int index, String changeTaskDetails) {
        if (isEventFromStoredInput) {
            changeTaskDetails = assignments.get(index)
                    .getDescription().replace("/at", "|");
        } else if (isDeadlineFromStoredInput) {
            changeTaskDetails = assignments.get(index)
                    .getDescription().replace("/by", "|");
        }
        return changeTaskDetails;
    }

    /**
     * Checks the stored input to see if it is an Event or Deadline task.
     *
     * @param index which is a string of changed display format.
     */
    public static void checkStoredInputStatus(int index) {
        isEventFromStoredInput = assignments.get(index)
                .getStatusOfTypeTask().contains("E");
        isDeadlineFromStoredInput = assignments.get(index)
                .getStatusOfTypeTask().contains("D");
    }

    /**
     * Adds the task from the data file into the stored input.
     */
    private static void addTaskFromDataFile() {
        int numOfTask = file.storeDataFileContents().size();
        ArrayList<String> taskList = file.storeDataFileContents();
        for(int i = 0; i < numOfTask; i++) {
            String[] task = taskList.get(i).split("\\|");
            addSingleTaskFromFile(task);
        }
        countTask = indexTask;
    }

    /**
     * Adds a single task from the file into the stored input.
     *
     * @param task which is a specific task obtained from the dataFile.
     */
    private static void addSingleTaskFromFile(String[] task) {
        checkDataFileCommand(task);
        addTypeOfTaskFromFile(task);
        checkTaskStatusFromFile();
        indexTask++;
    }

    /**
     * Adds the type of task from the file into the stored input.
     *
     * @param task which is a specific task obtained from the dataFile.
     */
    private static void addTypeOfTaskFromFile(String[] task) {
        if (isToDoFromDataFile) {
            addToDoTaskFromFile(task);
        } else if (isDeadlineFromDateFile) {
            addDeadlineTaskFromFile(task);
        } else if (isEventFromDataFile) {
            addEventTaskFromFile(task);
        }
    }

    private static void checkTaskStatusFromFile() {
        if (isMarkFromDataFile) {
            assignments.get(indexTask).markAsDone();
        }
    }

    /**
     * Adds the event task from the dataFile, some modified is needed for standardisation
     * with the stored input and dataFile.
     *
     * @param task which is a specific task obtained from the dataFile.
     */
    private static void addEventTaskFromFile(String[] task) {
        String taskDetail = task[TASK_TITLE_INDEX].trim()
                + " /at" + task[EVENT_OR_DEADLINE_DETAIL];
        addTask(new Event(taskDetail));
        assignments.get(indexTask).markTypeTask();
    }

    /**
     * Adds the deadline task from the dataFile, some modified is needed for standardisation
     * with the stored input and dataFile.
     *
     * @param task which is a specific task obtained from the dataFile.
     */
    private static void addDeadlineTaskFromFile(String[] task) {
        String taskDetail = task[TASK_TITLE_INDEX].trim()
                + " /by" + task[EVENT_OR_DEADLINE_DETAIL];
        addTask(new Deadline(taskDetail));
        assignments.get(indexTask).markTypeTask();
    }

    /**
     * Adds the todo task from the dataFile to the stored input.
     *
     * @param task which is a specific task obtained from the dataFile.
     */
    private static void addToDoTaskFromFile(String[] task) {
        addTask(new ToDo(task[TASK_TITLE_INDEX].trim()));
        assignments.get(indexTask).markTypeTask();
    }

    /**
     * Checks the type of command of each task from in the dataFile.
     *
     * @param task which is a specific task obtained from the dataFile.
     */
    private static void checkDataFileCommand(String[] task) {
        isToDoFromDataFile = task[COMMAND_INDEX].contains("T");
        isDeadlineFromDateFile = task[COMMAND_INDEX].contains("D");
        isEventFromDataFile = task[COMMAND_INDEX].contains("E");
        isMarkFromDataFile = task[STATUS_INDEX].contains("1");
    }

    /**
     * Runs the main Flash bot in the Duke java program.
     *
     * @param lineDivider a string to for line separator.
     * @param in a scanner variable to allow User Input.
     */
    private static void runFlash(String lineDivider, Scanner in) {
        String[] splitUserInputs = getSplitUserInput(in);
        System.out.println("\t" + lineDivider);
        checkTypeOfCommand(splitUserInputs);
        checkDetailOfCommand(splitUserInputs);
        runCommand(splitUserInputs);
        System.out.println("\t" + lineDivider + "\n");
    }

    public static void main(String[] args) {
        String lineDivider = printWelcomeMessage();
        addTaskFromDataFile();
        Scanner in = new Scanner(System.in);

        while (!isBye) {
            runFlash(lineDivider, in);
        }
    }
}



