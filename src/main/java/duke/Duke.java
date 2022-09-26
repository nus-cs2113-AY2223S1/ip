package duke;

import duke.command.Command;
import duke.task.TaskList;

public class Duke {
    private final Ui ui;
    private final TaskList taskList;

    public Duke() {
        ui = new Ui();
        taskList = new TaskList(ui);
    }

    public void run() {
        ui.showLogo();
        ui.showGreetMessage();

        while (true) {
            String userCommand = ui.readCommand();
            Command command = Parser.parseCommand(userCommand, ui);
            command.execute(taskList, ui);
            if (command.isExit()) {
                break;
            }
        }

        ui.showExitMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
