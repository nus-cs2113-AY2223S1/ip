package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.exception.MissingKeywordDukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ArrayList<Task> matchingTasks = extractMatchingTasks(taskList);
            if (matchingTasks.size() > 0) {
                ui.output("Slave Kai found these tasks:");
                for (Task task : matchingTasks) {
                    ui.output(task.listTask(taskList.tasks));
                }
            } else {
                ui.output("Nothing found, better luck next time");
            }
        } catch (DukeException e) {
            e.handle(ui);
        }
    }

    private ArrayList<Task> extractMatchingTasks(TaskList taskList) throws DukeException {
        if (keyword.length() == 0) {
            throw new MissingKeywordDukeException();
        }
        return (ArrayList<Task>) taskList.tasks.stream()
                .filter(t -> t.getName().contains(keyword))
                .collect(Collectors.toList());
    }

}
