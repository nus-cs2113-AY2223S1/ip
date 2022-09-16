package duke;

import java.io.IOException;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = storage.loadFile();
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greetUser();
        Parser parser = new Parser(ui);
        boolean isProgramFinished = false; //variable to indicate if the program should be terminated
        while(!isProgramFinished) {
            String command = ui.getUserCommand();
            parser.parse(command, taskList);
            isProgramFinished = parser.getTerminationStatus();
        }
        ui.sayByeToUser();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
