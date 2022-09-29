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

    public void showDataFileStatus(boolean isFileCreated) {
        if (isFileCreated) {
            System.out.println("\t --Data file was not found but"
                    + " I have made one for you.--\n");
        } else {
            System.out.println("\n\t --Data file already exist"
                    + " in your hard disk, you are ready to go!--\n");
        }
    }

    public void showDataFolderStatus() {
        System.out.println("\n\t --Data folder to store the data file does not exist "
                + "but I have made one for you.--");
    }

    public void showLine() {
        System.out.println(lineDivider);
    }

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

    public void showTaskDetail(ArrayList<Task> assignments, int index) {
        System.out.println("\t   [" + assignments.get(index).getStatusOfTypeTask()
                + "]" + "[" + assignments.get(index).getStatusOfDone()
                + "] " + assignments.get(index).displayTypeTaskDetails());
    }

    public void showDeletedTask(ArrayList<Task> assignments, int indexTask, int countTask) {
        System.out.println("\t Noted. I've removed this task:");
        showTaskDetail(assignments, indexTask);
        System.out.println("\t Now you have " + countTask + " tasks in the list.");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showTypeOfTaskDetail(ArrayList<Task> assignments, int index) {
        countTask = index;
        System.out.println("\t Roger that. I've added this task:");
        showTaskDetail(assignments, index);
        countTask++;
        System.out.println("\t Now you have " + countTask + " in the list.");
    }

    public void showMarkOrUnmarkTask(boolean isMark) {
        if (isMark) {
            System.out.println("\t Awesome! I've marked this task as done:");
        } else {
            System.out.println("\t Awesome! I've marked this task as not done yet:");
        }
    }

    public void showMarkOrUnmarkError(boolean isMark) {
        if (isMark) {
            System.out.println("\t You are trying to mark a task that has not been specified!");
        } else {
            System.out.println("\t You are trying to unmark a task that has not been specified!");
        }
    }

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
        System.out.println("\t There are no matching task as your TaskList is wiped clean!");
    }

    public static void showFileError() {
        System.out.println("\n\t --Error in trying to locate or make data file--");
    }

    public static void showDataToFileError() {
        System.out.println("\n\t --Unable to save file.--");
    }

}
