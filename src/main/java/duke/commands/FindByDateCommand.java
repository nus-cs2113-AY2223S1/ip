package duke.commands;

import duke.ui.Ui;
import duke.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.Storage;

import java.time.LocalDate;

/**
 * Finds task(s) with matching date from the task list.
 */
public class FindByDateCommand extends Command {
    public static final String COMMAND_WORD = "findd";

    private final String date;

    /**
     * Constructs constructor for Find By Date command which stores the date to be matched.
     *
     * @param processedDate Date to be matched.
     */
    public FindByDateCommand(String processedDate) {
        this.date = processedDate;
    }

    /**
     * Searches for task with matching date from the task list and prints matching results.
     * Ends function if task list is empty.
     *
     * @param taskList Used to access taskList information.
     * @param ui Used to print searching result or empty list messages.
     * @param storage Used to update task information in duke.txt.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (emptyListCheck(taskList, ui)) {
            return;
        }
        StringBuilder matchingDeadlineOrEvent = extractMatchingDeadlineOrEvent(taskList);
        printMatchingResult(ui, matchingDeadlineOrEvent);
    }

    /**
     * Checks for empty task list and prints empty list message if list is empty.
     *
     * @param taskList Used to access task list size.
     * @param ui Used to print exception message.
     * @return True if list is empty, False if list is not empty.
     */
    private static boolean emptyListCheck(TaskList taskList, Ui ui) {
        int currentListSize = taskList.getCurrentListSize();
        boolean hasEmptyList = (currentListSize == 0);
        if (hasEmptyList) {
            ui.showEmptyListMessage();
            return true;
        }
        return false;
    }

    /**
     * Searches and stores task(s) with matching date from the task list.
     *
     * @param taskList Used to access taskList information.
     * @return Descriptions of task(s) with matching date.
     */
    private StringBuilder extractMatchingDeadlineOrEvent(TaskList taskList) {
        LocalDate matchingDate = LocalDate.parse(date);
        boolean isMatched = false;
        StringBuilder matchingDeadlineOrEvent = new StringBuilder();
        for (Task task : taskList.getTaskList()) {
            if (task instanceof Deadline) {
                isMatched = matchingDate.equals(((Deadline) task).getByDate());
            }
            if (task instanceof Event) {
                isMatched = matchingDate.equals(((Event) task).getAtDate());
            }
            if (isMatched) {
                matchingDeadlineOrEvent.append(task).append(System.lineSeparator());
            }
            isMatched = false;
        }
        return matchingDeadlineOrEvent;
    }

    /**
     * Prints date matching results.
     *
     * @param ui Used to print matching results.
     * @param matchingDeadlineOrEvent Descriptions of matching task found (if any).
     */
    private static void printMatchingResult(Ui ui, StringBuilder matchingDeadlineOrEvent) {
        boolean hasMatchingDate = !matchingDeadlineOrEvent.toString().trim().isEmpty();
        if (hasMatchingDate) {
            ui.showMatchingDeadlineOrEvent(matchingDeadlineOrEvent.toString().trim());
        } else {
            ui.showNoMatchingDeadlineOrEvent();
        }
    }
}

