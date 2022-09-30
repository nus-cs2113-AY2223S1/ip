package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner in = new Scanner(System.in);
    protected int countTask;
    protected String lineDivider;

    public Ui() {
        this.lineDivider = "\t____________________________________________________________";
    }

    /**
     * Shows the DataFileStatus for the user to know if the dataFile exist or not.
     *
     * @param isFileCreated checks if the file has been created before the start of the program.
     */
    public void showDataFileStatus(boolean isFileCreated) {
        if (isFileCreated) {
            System.out.println(" Data file was not found but"
                    + " I have made one for you.\n");
        } else {
            System.out.println("\n Data file already exist"
                    + " in your hard disk, you are ready to go!\n");
        }
    }

    /**
     * Shows the dataFolder status to the user.
     */
    public void showDataFolderStatus() {
        System.out.println("\n Data folder to store the data file does not exist "
                + "but I have made one for you.");
    }

    public void showLine() {
        System.out.println(lineDivider);
    }

    public void lineBreak() {
        System.out.println("\n");
    }

    /**
     * Shows the welcome message that the start of the program. For aesthetic purposes.
     */
    public void showWelcomeMessage() {
        String logo = "___________.__                ___.\n"
                + "\\_   _____/|  | _____    _____|  |__\n"
                + " |    __)  |  | \\__  \\  /  ___/  |  \\\n"
                + " |     \\   |  |__/ __ \\_\\___ \\|   Y  \\\n"
                + " \\___  /   |____(____  /____  >___|  /\n"
                + "     \\/              \\/     \\/     \\/\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(lineDivider
                + "\n\t Hello! I'm Flash\n"
                + "\t What can I do for you?\n"
                + lineDivider + "\n");
    }

    public void showExitMessage() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        String userInput;
        userInput = in.nextLine();
        return userInput;
    }

    /**
     * Shows a specific task details for the user.
     *
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param index which is the increment value in the loop to access the assignments.
     */
    public void showTaskDetail(ArrayList<Task> assignments, int index) {
        System.out.println("\t   [" + assignments.get(index).getStatusOfTypeTask()
                + "]" + "[" + assignments.get(index).getStatusOfDone()
                + "] " + assignments.get(index).displayTypeTaskDetails());
    }

    /**
     * Shows a specific task that have been deleted by the user.
     *
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param indexTask which is the index of the task.
     * @param countTask which tracks the number of task that the user has added.
     */
    public void showDeletedTask(ArrayList<Task> assignments, int indexTask, int countTask) {
        System.out.println("\t Noted. I've removed this task:");
        showTaskDetail(assignments, indexTask);
        System.out.println("\t Now you have " + countTask + " tasks in the list.");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showDeadlineTaskError() {
        System.out.println("Please complete the input for deadline details."
                + " \nDelete task and try again.");
    }

    public void showDeleteTaskError() {
        System.out.println("\t Stop there! Please delete something that is within the list!");
    }

    /**
     * Shows type of task detail to the user.
     * As a form of acknowledgment to let the user know that the task have been added.
     *
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param index which is the increment value in the loop to access the assignments.
     */
    public void showTypeOfTaskDetail(ArrayList<Task> assignments, int index) {
        countTask = index;
        System.out.println("\t Roger that. I've added this task:");
        showTaskDetail(assignments, index);
        countTask++;
        System.out.println("\t Now you have " + countTask + " in the list.");
    }

    /**
     * Shows the user if the task has been marked or unmarked.
     * As a form of acknowledgment to let the user know that the task is marked or unmarked.
     *
     * @param isMark which checks to see if the task have been completed or not.
     */
    public void showMarkOrUnmarkTask(boolean isMark) {
        if (isMark) {
            System.out.println("\t Awesome! I've marked this task as done:");
        } else {
            System.out.println("\t Awesome! I've marked this task as not done yet:");
        }
    }

    /**
     * Shows the error message if the user is trying to mark a task that is not within
     * the taskList.
     *
     * @param isMark which checks to see if the task have been completed or not.
     */
    public void showMarkOrUnmarkError(boolean isMark) {
        if (isMark) {
            System.out.println("\t You are trying to mark a task that has not been specified!");
        } else {
            System.out.println("\t You are trying to unmark a task that has not been specified!");
        }
    }

    /**
     * Shows a list of tasks that the user has stored in the taskList.
     *
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param numberOrder which is to label the ordering of the task starting from 1.
     * @param countTask used to track the number of task that have been added into the taskList.
     */
    public void showList(ArrayList<Task> assignments, int numberOrder, int countTask) {
        for (int i = 0; i < countTask; i++) {
            System.out.println("\t " + numberOrder + ".["
                    + assignments.get(i).getStatusOfTypeTask() + "]["
                    + assignments.get(i).getStatusOfDone() + "] "
                    + assignments.get(i).displayTypeTaskDetails());
            numberOrder++;
        }
    }

    public void showListMessage() {
        System.out.println("\t Here are the tasks in your list:");
    }

    public void showMatchListMessage() {
        System.out.println("\t Here are the matching tasks in your list:");
    }

    public void showEmptyList() {
        System.out.println("\t There is no task in the list.");
    }

    public void showEmptyMatchList() {
        System.out.println("\t There are no matching task in your list of task.");
    }

    public static void showFileError() {
        System.out.println("\n --Error in trying to locate or make data file--");
    }

    public static void showDataToFileError() {
        System.out.println("\n --Unable to save file.--");
    }

}
