package duke;

import duke.storage.Storage;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.UI;

public class Duke {

    private static Storage storage;
    public static UI ui;
    private static TaskList tasks;

    public Duke () {
        Duke.storage = new Storage();
        Duke.ui = new UI();
        Duke.tasks = new TaskList(storage.startReading());
    }

    public void start() {
        ui.welcomeUser();
        Parser parser = new Parser();
        parser.executeCommands(ui, tasks, storage);
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
