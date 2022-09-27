package duke.commands;

import duke.ui.Ui;
import duke.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.Storage;

import java.time.LocalDate;

public class FindByDateCommand extends Command {
    public static final String COMMAND_WORD = "findd";

    private final String date;

    public FindByDateCommand(String processedDate) {
        this.date = processedDate;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (emptyListCheck(taskList, ui)) {
            return;
        }
        StringBuilder matchingDeadlineOrEvent = extractMatchingDeadlineOrEvent(taskList);
        printMatchingResult(ui, matchingDeadlineOrEvent);
    }

    private static boolean emptyListCheck(TaskList taskList, Ui ui) {
        int currentListSize = taskList.getCurrentListSize();
        boolean hasEmptyList = (currentListSize == 0);
        if (hasEmptyList) {
            ui.showEmptyListMessage();
            return true;
        }
        return false;
    }

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

    private static void printMatchingResult(Ui ui, StringBuilder matchingDeadlineOrEvent) {
        boolean hasMatchingDate = !matchingDeadlineOrEvent.toString().trim().isEmpty();
        if (hasMatchingDate) {
            ui.showMatchingDeadlineOrEvent(matchingDeadlineOrEvent.toString().trim());
        } else {
            ui.showNoMatchingDeadlineOrEvent();
        }
    }
}

