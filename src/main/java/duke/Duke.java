package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.manager.CommandManager;
import duke.task.List;
import duke.ui.UI;

public class Duke {

    private UI ui;
    private List list;
    private static final String EXIT_PREFIX = "bye";
    private boolean isActive = true;

    public Duke() {
        ui = new UI();
        list = new List();
    }

    public void exit() {
        isActive = false;
    }

    public void run() {
        ui.greet();
        while (isActive) {
            try {
                String input = ui.getUserInput();
                Command command = CommandManager.manageCommand(input);
                command.execute(list, ui);

                if (input.startsWith(EXIT_PREFIX)) {
                    exit();
                }
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
