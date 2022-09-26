package duke;

import duke.command.CommandDocumentation;
import duke.command.CommandMenu;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ________      ________ _____\n" +
            "|  ____\\ \\    / /  ____|  __ \\\n" +
            "| |__   \\ \\  / /| |__  | |__) |\n" +
            "|  __|   \\ \\/ / |  __| |  _  /\n" +
            "| |____   \\  /  | |____| | \\ \\\n" +
            "|______|   \\/   |______|_|  \\_\\";
    private static final String HORIZONTAL_LINE = "==================================================================";

    private static final Scanner SCANNER = new Scanner(System.in);

    public Ui() {
    }

    public String readCommand() {
        showHorizontalLine();
        return SCANNER.nextLine().trim();
    }

    public void showCommandDocumentation(CommandDocumentation commandDoc) {
        System.out.println();
        System.out.println(commandDoc);
    }

    public void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void showLogo() {
        System.out.println(LOGO);
    }

    public void showGreetMessage() {
        System.out.println(Message.GREETING_MESSAGE);
    }

    public void showExitMessage() {
        System.out.println(Message.EXIT_MESSAGE);
    }

    public void showCommandSyntaxHint(String commandKeyword) {
        String syntaxHint = CommandMenu.getCommandSyntaxHint(commandKeyword);
        System.out.println("Syntax: " + syntaxHint);
    }

    public void showEmptyInputErrorMessage() {
        System.out.println(Message.EMPTY_INPUT_ERROR_MESSAGE + " " + Message.HELP_MESSAGE);
    }

    public void showInvalidCommandErrorMessage() {
        System.out.println(Message.INVALID_COMMAND_MESSAGE + " " + Message.HELP_MESSAGE);
    }

    public void showTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    public void showNoTaskMessage() {
        System.out.println(Message.NO_TASKS_MESSAGE + " " + Message.HELP_MESSAGE);
    }

    public void showMarkTaskSuccessMessage(Task task) {
        System.out.printf("Marked as done: %s\n", task);
    }

    public void showUnmarkTaskSuccessMessage(Task task) {
        System.out.printf("Unmarked done: %s\n", task);
    }

    public void showDeleteTaskSuccessMessage(int taskNumber) {
        System.out.printf("Task %d deleted\n", taskNumber);
    }

    public void showAddTaskSuccessMessage(Task task) {
        System.out.println("Task added: " + task);
    }

    public void showTaskNumberOutOfRangeMessage() {
        System.out.println(Message.WRONG_TASK_NUMBER_RANGE_ERROR_MESSAGE);
    }

    public void showMissingTaskNumberErrorMessage() {
        System.out.println(Message.MISSING_TASK_NUMBER_ERROR_MESSAGE);
    }

    public void showTaskNumberFormatErrorMessage() {
        System.out.println(Message.WRONG_TASK_NUMBER_FORMAT_ERROR_MESSAGE);
    }

    public void showAddTodoErrorMessage() {
        System.out.println(Message.INVALID_ADD_TODO_FORMAT_ERROR_MESSAGE);
    }

    public void showAddDeadlineErrorMessage() {
        System.out.println(Message.INVALID_ADD_DEADLINE_FORMAT_ERROR_MESSAGE);
    }

    public void showAddEventErrorMessage() {
        System.out.println(Message.INVALID_ADD_EVENT_FORMAT_ERROR_MESSAGE);
    }

    public void showSaveTaskFailErrorMessage() {
        System.out.println(Message.SAVE_TASK_FAIL_ERROR_MESSAGE);
    }

    public void showCreateFileFailErrorMessage() {
        System.out.println(Message.CREATE_FILE_FAIL_ERROR_MESSAGE);
    }
}
