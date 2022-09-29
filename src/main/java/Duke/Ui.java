package Duke;

import Duke.Commands.*;
import Duke.Exceptions.TaskListEmptyException;
import Duke.Exceptions.TaskNumberOutOfBoundsException;
import Duke.Tasks.Task;

import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the interactions with the user,
 * including all the text UI to be displayed to the use
 */
public class Ui {
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
    public static void printGreeting() {
        printHorizontalLine();
        System.out.println("Hello! Bob is awake!!");
        System.out.println("I'm here to give you the sanity check of the list of things that you need to keep track :>");
        printHorizontalLine();
    }

    /**
     * Reads in input from the user
     */
    public static String readInput() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    /**
     * Prints text when new task has been added to the list
     * @param task the newly added task
     */
    public static void printAddTaskText(Task task) {
        System.out.println("Bob has added this task to your list:");
        System.out.println(task.taskStatusWithDescriptionText());
        System.out.println("Now you have " + TaskList.getTasksListSize() + " tasks in the list. You can do it!!");
        Ui.printHorizontalLine();
    }

    /**
     * Prints a list of all the tasks in the task list
     */
    public static void printTaskList() {
        try {
            if (TaskList.getTasksListSize() == 0) {
                throw new TaskListEmptyException();
            } else {
                System.out.println("Here are the list of tasks that you have:");
                for (int i = 0; i < TaskList.getTasksListSize(); i++) {
                    System.out.println((i + 1) + "." + TaskList.tasksList.get(i).taskStatusWithDescriptionText());
                }
            }
            Ui.printHorizontalLine();
        } catch (TaskListEmptyException e) {
            System.out.println(e);
        }
    }

    /**
     * Prints text when task has been newly mark or unmark
     * @param newMark whether the task is newly mark or unmark
     */
    public static void printMarkTaskText(boolean newMark) throws TaskNumberOutOfBoundsException {
        try {
            int taskNumber = TaskList.getTaskNumberOfInterest();
            String previousIcon = TaskList.tasksList.get(taskNumber).getStatusIcon();
            if (previousIcon.equals("X")) {
                if (newMark) {
                    System.out.println("Bob has already marked this task!");
                } else {
                    System.out.println("Oh no... Bob has mark this task as UNdone:");
                }
            } else {
                if (!newMark) {
                    System.out.println("Bob has already unmarked this task!");
                } else {
                    System.out.println("Good job! Bob has marked this task as done:");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberOutOfBoundsException();
        }
    }

    /**
     * Prints text when a task is deleted from the task list
     * @param task the task to be deleted
     */
    public static void printDeleteTaskText(Task task) {
        System.out.println("Noted. Bob has removed this task: ");
        System.out.println(task.taskStatusWithDescriptionText());
        System.out.println("Now you have " + TaskList.getTasksListSize() + " tasks in the list. All the best!");
        Ui.printHorizontalLine();
    }

    /**
     * Prints the correct format text according to the wrongly input command
     * @param command user's input command
     */
    public static void printCorrectFormatText(String command) {
        switch(command) {
        case AddTodoCommand.TODO_COMMAND:
            System.out.println("The command should be 'todo (task name)'.");
            break;
        case AddDeadlineCommand.DEADLINE_COMMAND:
            System.out.println("The command should be 'deadline (task name) /by (deadline)'.");
            break;
        case AddEventCommand.EVENT_COMMAND:
            System.out.println("The command should be 'event (task name) /by (event date)'.");
            break;
        case MarkTaskCommand.MARK_COMMAND:
            System.out.println("The command should be 'mark (task number)'.");
            break;
        case UnmarkTaskCommand.UNMARK_COMMAND:
            System.out.println("The command should be 'unmark (task number)'.");
            break;
        case DeleteCommand.DELETE_COMMAND:
            System.out.println("The command should be 'delete (task number)'.");
            break;
        case FindCommand.FIND_COMMAND:
            System.out.println("The command should be 'find (task)'.");
            break;
        default:
            System.out.println("Bob doesn't understand this command :<");
        }
    }

    /**
     * Prints the updated mark and description of a task
     */
    public static void printUpdatedTaskMarkAndDescription() {
        int taskNumber = TaskList.getTaskNumberOfInterest();
        String newIcon = TaskList.tasksList.get(taskNumber).getStatusIcon();
        System.out.println("[" + newIcon + "] " + TaskList.tasksList.get(taskNumber).description);
    }

    public static void printTaskNumberNotIntegerError() {
        System.out.println("Task Number should be an integer!");
    }

    /**
     * Prints a list of tasks with the keyword from the task list
     * @param keyword user's input keyword to find
     */
    public static void printTasksWithKeyword(String keyword) {
        int count = 1;
        for (Task task : TaskList.tasksList) {
            if (task.description.contains(keyword)) {
                System.out.println(count + ". " + task.taskStatusWithDescriptionText());
                count += 1;
            }
        }
        if (count == 1 ) {
            System.out.println("Bob cannot find any related task in your list.");
        }
    }

    public static void printCreateParentFolderErrorText() {
        System.out.println("Error creating parent folder(s)");
    }

    /**
     * Prints text when the data file has been created
     * or when the file already exists, along with the file path
     * @param isNewlyCreated whether the file is newly created
     * @param file_path file path of data file
     */
    public static void printFilePath(boolean isNewlyCreated, String file_path) {
        if (isNewlyCreated) {
            System.out.printf("Bob has created the file at %s\n", file_path);
        } else {
            System.out.printf("File already exists at %s\n", file_path);
        }
    }

    /**
     * Prints text when there is an error in creating the file
     * @param file_path the file path in which there is an error
     */
    public static void printCreateFileErrorText(String file_path) {
        System.out.printf("Error creating file: Could not create file at %s\n", file_path);
    }

    public static void printReadFileErrorText() {
        System.out.println("Error reading data from file: Invalid format");
    }

    public static void printLoadTaskToDataFileErrorText() {
        System.out.println("Error has occurred when loading the task to data file.");
    }

    public static void printUpdateTaskToDataFileErrorText(IOException e) {
        System.out.println("Error occurred when updating the task in data file.");
        e.printStackTrace();
    }

    public static void printGoodbye() {
        System.out.println("Bob bids goodbye. Hope to see you again soon! :>");
        printHorizontalLine();
    }
}
