package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The chatting bot Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialization of Duke and loading of file.
     *
     * @param filePath The path of the file that stores the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Process of user's input and storage of the new taskList.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = this.tasks.getTasks();

        while (!isExit) {
            try {
                String fullCommand = in.nextLine();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        try {
            storage.write("data/duke.txt", tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }


}



