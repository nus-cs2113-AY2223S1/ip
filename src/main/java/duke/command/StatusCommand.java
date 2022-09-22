package duke.command;

import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents command for searching tasks by done status
 * User can search for tasks marked as done or not done
 */
public class StatusCommand extends Command {

    private final String statusComparator;

    public StatusCommand(String statusComparator) {
        this.statusComparator = statusComparator;
    }

    /**
     * Prints all tasks in taskList matching status specified by user
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
        } else if (statusComparator.equals("done")) {
            ui.output("Better buck up. You ain't accomplished nuttin' yet");
        } else {
            ui.output("What a legend. You've gone and done them all");
        }
    }

    /**
     * Extracts all tasks in taskList matching status specified by user
     *
     * @param taskList ArrayList containing all tasks
     * @return ArrayList of tasks matching comparator
     */
    public ArrayList<Task> extractMatchingTasks(TaskList taskList) {
        return (ArrayList<Task>) taskList.tasks.stream()
                .filter(t -> (isMatch(t.doneIcon())))
                .collect(Collectors.toList());
    }

    /**
     * Compares task date with status specified by user
     *
     * @param doneIcon task done status as icon
     * @return true if task done status matches comparator, false otherwise
     */
    private boolean isMatch(char doneIcon) {
        switch (statusComparator) {
        case "done":
            return doneIcon == 'X';
        case "undone":
            return doneIcon == ' ';
        default:
            return false;
        }
    }

}
