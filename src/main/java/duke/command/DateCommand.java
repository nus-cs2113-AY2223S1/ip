package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeDukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DateCommand extends Command {

    private final LocalDate date;
    private final String comparison;

    public DateCommand(String date, String comparison) throws DukeException {
        try {
            this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-M-yyyy"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeDukeException();
        }
        this.comparison = comparison;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = extractMatchingTasks(taskList);
        Ui.line();
        if (matchingTasks.size() > 0) {
            Ui.outputWithoutLines("Slave Kai found these:");
            for (Task task : matchingTasks) {
                Ui.outputWithoutLines(task.listTask());
            }
        } else {
            Ui.outputWithoutLines("Nothing found, better luck next time");
        }
        Ui.line();
    }

    public ArrayList<Task> extractMatchingTasks(TaskList taskList) {
        return (ArrayList<Task>) TaskList.Tasks.stream()
                .filter(t -> (t instanceof Deadline && isMatch(((Deadline) t).getDate()))
                        || (t instanceof Event && isMatch(((Event) t).getDate())))
                .collect(Collectors.toList());
    }

    private boolean isMatch(LocalDate date) {
        switch (comparison) {
        case "on":
            return date.equals(this.date);
        case "before":
            return date.isBefore(this.date);
        case "after":
            return date.isAfter(this.date);
        default:
            return false;
        }
    }
}
