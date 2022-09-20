package duke.command;

import duke.Ui;

public class ByeCommand extends Command {


    public ByeCommand() {
        super();
        Ui.farewellMessage();
    }

    @Override
    public void executeCommand(String[] commands) {

    }
}
