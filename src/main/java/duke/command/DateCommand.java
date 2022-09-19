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
        if (matchingTasks.size() > 0) {
            ui.output("Slave Kai found these:");
            for (Task task : matchingTasks) {
                ui.output(task.listTask(taskList.tasks));
            }
        } else {
            ui.output("Nothing found, better luck next time");
        }
    }

    public ArrayList<Task> extractMatchingTasks(TaskList taskList) {
        return (ArrayList<Task>) taskList.tasks.stream()
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
