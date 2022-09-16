package duke;

import duke.command.Command;

public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Duke() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
    }

    public void run() {
        boolean isExit = false;
        ui.initialize();
        while (!isExit) {
            String input = ui.getInput().trim();
            try {
                Command command = Parser.parseCommand(input);
                if (command.isExit()) {
                    isExit = true;
                }
                command.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.displayMessage(e.getMessage());
            }
        }
        ui.cleanUp();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
