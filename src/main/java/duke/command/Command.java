package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public abstract class Command {

    String keyword;
    String statement;
    Boolean isExit = false;
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public Boolean isExit() {
        return isExit;
    }

    static void createDirectory(Ui ui, Storage storage, ArrayList<Task> tasks) {
        ui.showLine();
        System.out.println("     Seems the data directory doesn't exist yet. Try to create for you!");
        try {
            Path path = Paths.get("./data");
            Files.createDirectories(path);
            storage.write(tasks);
        } catch (IOException ex) {
            System.out.println("     Sorry, creation failed!");
        }
    }

}
