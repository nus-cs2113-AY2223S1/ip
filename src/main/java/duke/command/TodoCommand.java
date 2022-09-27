package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static duke.task.TaskList.*;

public class TodoCommand extends Command {
    public TodoCommand(String keyword, String statement) {
        this.keyword = keyword;
        this.statement = statement;
    }
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        tryAddTodo(tasks, statement);
        try {
            storage.write(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
