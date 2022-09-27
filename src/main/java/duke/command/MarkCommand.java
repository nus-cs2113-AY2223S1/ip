package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static duke.task.TaskList.tryMarkTask;


public class MarkCommand extends Command {
    public MarkCommand(String keyword, String statement) {
        this.keyword = keyword;
        this.statement = statement;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.getTasks();
        tryMarkTask(tasks, statement);
        try {
            storage.write(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
