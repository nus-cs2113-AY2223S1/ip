package duke;

import duke.command.CommandDocumentation;
import duke.command.CommandMenu;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represent the user interface of the application.
 * All interactions between the user and the application are handled by the Ui object.
 */
public class Ui {
    private static final String LOGO = " ________      ________ _____\n" +
            "|  ____\\ \\    / /  ____|  __ \\\n" +
            "| |__   \\ \\  / /| |__  | |__) |\n" +
            "|  __|   \\ \\/ / |  __| |  _  /\n" +
            "| |____   \\  /  | |____| | \\ \\\n" +
            "|______|   \\/   |______|_|  \\_\\";
    private static final String HORIZONTAL_LINE = "==================================================================";

    private static Scanner scanner;

    /**
     * Constructor of <code>Ui</code> class. It initializes the <code>Scanner</code> object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Return the complete command inputted by the user as a string.
     * @return Command inputted by the user.
     */
    public String readCommand() {
        showHorizontalLine();
        return scanner.nextLine().trim();
    }

    /**
     * Show the command documentation when the command menu is being listed to the users.
     * @param commandDoc Documentation of command that includes the syntax and description.
     */
    public void showCommandDocumentation(CommandDocumentation commandDoc) {
        System.out.println();
        System.out.println(commandDoc);
    }

    /**
     * Show the horizontal line that separates the user interface before every new command.
     */
    public void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Show the logo of the application.
     */
    public void showLogo() {
        System.out.println(LOGO);
    }

    /**
     * Show the greeting message on start of the application.
     */
    public void showGreetMessage() {
        System.out.println(Message.GREETING_MESSAGE);
    }

    /**
     * Show the exit message before the application ends.
     */
    public void showExitMessage() {
        System.out.println(Message.EXIT_MESSAGE);
    }

    /**
     * Show the syntax of the given command keyword for user to correct their incorrect format of command.
     * @param commandKeyword Command keyword, which is the first word in the entire command inputted by the user.
     *                       The command keyword must be supported when using this method.
     */
    public void showCommandSyntaxHint(String commandKeyword) {
        String syntaxHint = CommandMenu.getCommandSyntaxHint(commandKeyword);
        System.out.println("Syntax: " + syntaxHint);
    }

    /**
     * Show the error message when the user did not input anything.
     */
    public void showEmptyInputErrorMessage() {
        System.out.println(Message.EMPTY_INPUT_ERROR_MESSAGE + " " + Message.HELP_MESSAGE);
    }

    /**
     * Show the error message when the user inputted a command that is not a valid operation in the application.
     */
    public void showInvalidCommandErrorMessage() {
        System.out.println(Message.INVALID_COMMAND_MESSAGE + " " + Message.HELP_MESSAGE);
    }

    /**
     * Show the tasks one by one as a numbered list.
     * @param tasks List of <code>Task</code> objects to be displayed.
     */
    public void showTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    /**
     * Show the message when the list is empty and no listing operation can be done.
     */
    public void showNoTaskMessage() {
        System.out.println(Message.NO_TASKS_MESSAGE + " " + Message.HELP_MESSAGE);
    }

    /**
     * Show the success message when the specific task is marked as done.
     * @param task Task that is marked done.
     */
    public void showMarkTaskSuccessMessage(Task task) {
        System.out.printf("Marked as done: %s\n", task);
    }

    /**
     * Show the success message when the specific task is marked as undone.
     * @param task Task that is marked undone.
     */
    public void showUnmarkTaskSuccessMessage(Task task) {
        System.out.printf("Unmarked done: %s\n", task);
    }

    /**
     * Show the success message when the specific task is deleted.
     * @param taskNumber Task number of the task being deleted.
     */
    public void showDeleteTaskSuccessMessage(int taskNumber) {
        System.out.printf("Task %d deleted\n", taskNumber);
    }

    /**
     * Show the success message when the specific task is added to the task list.
     * @param task Task that is added to the task list.
     */
    public void showAddTaskSuccessMessage(Task task) {
        System.out.println("Task added: " + task);
    }

    /**
     * Show the error message when the user inputted a task number that is out of range.
     * The range is from 1 to the number of tasks in the list inclusively.
     */
    public void showTaskNumberOutOfRangeMessage() {
        System.out.println(Message.WRONG_TASK_NUMBER_RANGE_ERROR_MESSAGE);
    }

    /**
     * Show the error message when the user did not input the task number for commands that require task number to be
     * specified.
     */
    public void showMissingTaskNumberErrorMessage() {
        System.out.println(Message.MISSING_TASK_NUMBER_ERROR_MESSAGE);
    }

    /**
     * Show the error message when the user inputted a non-integer as the task number.
     */
    public void showTaskNumberFormatErrorMessage() {
        System.out.println(Message.WRONG_TASK_NUMBER_FORMAT_ERROR_MESSAGE);
    }

    /**
     * Show the error message when a todo task cannot be added successfully.
     */
    public void showAddTodoErrorMessage() {
        System.out.println(Message.INVALID_ADD_TODO_FORMAT_ERROR_MESSAGE);
    }

    /**
     * Show the error message when a deadline cannot be added successfully.
     */
    public void showAddDeadlineErrorMessage() {
        System.out.println(Message.INVALID_ADD_DEADLINE_FORMAT_ERROR_MESSAGE);
    }

    /**
     * Show the error message when an event cannot be added successfully.
     */
    public void showAddEventErrorMessage() {
        System.out.println(Message.INVALID_ADD_EVENT_FORMAT_ERROR_MESSAGE);
    }

    /**
     * Show the error message when the tasks cannot be saved to the file successfully.
     */
    public void showSaveTaskFailErrorMessage() {
        System.out.println(Message.SAVE_TASK_FAIL_ERROR_MESSAGE);
    }

    /**
     * Show the error message when the file cannot be created successfully.
     */
    public void showCreateFileFailErrorMessage() {
        System.out.println(Message.CREATE_FILE_FAIL_ERROR_MESSAGE);
    }
}
