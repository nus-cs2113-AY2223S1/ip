package duke.ui;

import duke.TaskList;
import duke.task.Task;

import java.util.Scanner;

import duke.exception.UndefinedCommandException;

import static duke.common.Messages.MESSAGE_DATA_FILE_CREATION;
import static duke.common.Messages.MESSAGE_DUKE_FILE_CREATION;
import static duke.common.Messages.MESSAGE_DUKE_FILE_NOT_FOUND;
import static duke.common.Messages.MESSAGE_IO_EXCEPTION_TRIGGERED;
import static duke.common.Messages.MESSAGE_SECURITY_EXCEPTION_TRIGGERED;
import static duke.common.Messages.MESSAGE_WELCOME;
import static duke.common.Messages.MESSAGE_BYE;
import static duke.common.Messages.MESSAGE_UNDEFINED_COMMAND;
import static duke.common.Messages.MESSAGE_MISSING_TASK_INDEX;
import static duke.common.Messages.MESSAGE_INCORRECT_NUMBER_FORMAT;
import static duke.common.Messages.MESSAGE_OUT_OF_BOUNDS;
import static duke.common.Messages.MESSAGE_EMPTY_TASK_DESCRIPTION;
import static duke.common.Messages.MESSAGE_DEADLINE_MISSING_BY;
import static duke.common.Messages.MESSAGE_EVENT_MISSING_AT;
import static duke.common.Messages.MESSAGE_EMPTY_LIST;

public class Ui {
    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public static void showToUser(String message) {
        System.out.println(message);
    }

    public void showDataFileCreationMessage() {
        showToUser(MESSAGE_DATA_FILE_CREATION);
    }

    public void showDukeFileCreationMessage() {
        showToUser(MESSAGE_DUKE_FILE_CREATION);
    }

    public void showWelcomeMessage() {
        showToUser(MESSAGE_WELCOME);
    }

    public void showByeMessage() {
        showToUser(MESSAGE_BYE);
    }

    public void showUndefinedCommandMessage() {
        try {
            throw new UndefinedCommandException();
        } catch (UndefinedCommandException e) {
            showToUser(MESSAGE_UNDEFINED_COMMAND);
        }
    }

    public String getUserCommand() {
        String fullInputLine = in.nextLine();
        // silently consume all ignored lines
        boolean isBlankInput = ignoreBlankInput(fullInputLine);
        while (isBlankInput) {
            fullInputLine = in.nextLine();
            isBlankInput = ignoreBlankInput(fullInputLine);
        }
        return fullInputLine;
    }

    private boolean ignoreBlankInput(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public void printList(TaskList taskList, int currentListSize) {
        boolean isSingleTask = (currentListSize == 1);
        String taskString = isSingleTask ? "is the task" : "are the tasks";
        System.out.println("Here " + taskString + " in your list:");
        int taskIndex = 1;
        for (Task task : taskList.getTaskList()) {
            System.out.println((taskIndex) + "." +  task);
            taskIndex++;
        }
    }

    public void showTaskMarkAsUndoneMessage(TaskList taskList, int unmarkTaskIndex) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskList.getTaskList().get(unmarkTaskIndex));
    }

    public void showTaskMarkAsDoneMessage(TaskList taskList, int markTaskIndex) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList.getTaskList().get(markTaskIndex));
    }

    public void showTaskDeletedMessage(TaskList taskList, String deletedTaskDescription) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deletedTaskDescription);
        int currentListSize = taskList.getCurrentListSize();
        boolean isEmptyListOrSingleTask = (currentListSize == 0 || currentListSize == 1);
        String task = isEmptyListOrSingleTask ? "task" : "tasks";
        System.out.println("Now you have " + currentListSize + " " + task + " in the list.");
    }

    public void printTaskAddedMessage(TaskList taskList) {
        int currentListSize = taskList.getCurrentListSize();
        boolean isSingleTask = (currentListSize == 1);
        String task = isSingleTask ? " task" : " tasks";
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.getTaskList().get(currentListSize - 1));
        System.out.println("Now you have " + currentListSize + task + " in the list.");
    }

    //Show Exception Messages Zone
    public void showDukeTextFileNotFoundMessage() {
        showToUser(MESSAGE_DUKE_FILE_NOT_FOUND);
    }

    public void showIOExceptionMessage() {
        showToUser(MESSAGE_IO_EXCEPTION_TRIGGERED);
    }

    public void showSecurityExceptionMessage() {
        showToUser(MESSAGE_SECURITY_EXCEPTION_TRIGGERED);
    }

    public void showMissingTaskIndexMessage() {
        showToUser(MESSAGE_MISSING_TASK_INDEX);
    }

    public void showNumberFormatExceptionMessage() {
        showToUser(MESSAGE_INCORRECT_NUMBER_FORMAT);
    }

    public void showIndexOutOfBoundsExceptionMessage() {
        showToUser(MESSAGE_OUT_OF_BOUNDS);
    }

    public void showEmptyToDoOrDeadlineOrEventDescriptionExceptionMessage() {
        showToUser(MESSAGE_EMPTY_TASK_DESCRIPTION);
    }

    public void showMissingDeadlineDateTimeReferenceExceptionMessage() {
        showToUser(MESSAGE_DEADLINE_MISSING_BY);
    }

    public void showMissingEventDateTimeReferenceExceptionMessage() {
        showToUser(MESSAGE_EVENT_MISSING_AT);
    }

    public void showEmptyListMessage() {
        showToUser(MESSAGE_EMPTY_LIST);
    }
}