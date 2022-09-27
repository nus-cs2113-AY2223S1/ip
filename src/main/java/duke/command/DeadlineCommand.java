package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static duke.task.TaskList.tryAddDeadline;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String keyword, String statement) {
        this.keyword = keyword;
        this.statement = statement;
    }
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        tryAddDeadline(tasks, statement);
        try {
            storage.write(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
