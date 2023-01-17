package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidTaskDateTimeDukeException;
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

/**
 * Represents command for searching tasks by date
 * User can search for tasks before, on, or after a particular date
 */
public class DateCommand extends Command {

    private final LocalDate date;
    private final String dateComparator;

    /**
     * Constructs DateCommand object after converting user input into LocalDate
     *
     * @param date           date entered by user as string
     * @param dateComparator comparator for search entered by user as string
     * @throws DukeException if date is invalid
     */
    public DateCommand(String date, String dateComparator) throws DukeException {
        try {
            this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-M-yyyy"));
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateTimeDukeException();
        }
        this.dateComparator = dateComparator;
    }

    /**
     * Prints all tasks in taskList matching date and comparator specified by user
     * Informs user if no matching tasks were found
     *
     * @param taskList ArrayList containing all tasks
     * @param ui       Ui object for communicating with user
     * @param storage  Storage object for loading and saving tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = extractMatchingTasks(taskList);
        if (matchingTasks.size() > 0) {
            ui.output("Slave Kai found these " + matchingTasks.size() + " tasks:");
            for (Task task : matchingTasks) {
                ui.output(task.listTask(taskList.tasks));
            }
        } else {
            ui.output("Nothing found, better luck next time");
        }
    }

    /**
     * Extracts all tasks in taskList matching date and comparator specified by user
     *
     * @param taskList ArrayList containing all tasks
     * @return ArrayList of tasks matching comparator
     */
    public ArrayList<Task> extractMatchingTasks(TaskList taskList) {
        return (ArrayList<Task>) taskList.tasks.stream()
                .filter(t -> (t instanceof Deadline && isMatch(((Deadline) t).getDate()))
                        || (t instanceof Event && isMatch(((Event) t).getDate())))
                .collect(Collectors.toList());
    }

    /**
     * Compares task date with date specified by user
     *
     * @param date task date
     * @return true if task date matches comparator, false otherwise
     */
    private boolean isMatch(LocalDate date) {
        switch (dateComparator) {
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
