package duke.command;

import duke.Ui;


public class ByeCommand extends Command {


    /**
     * Initialises default state and execute exit command.
     *
     * @param commands User input which contains the command type.
     */
    public ByeCommand(String[] commands) {
        super();
        executeCommand(commands);
    }


    /**
     * Execute a farewell message before ending the operation.
     *
     * @param commands User input which contains the command type.
     */
    @Override
    public void executeCommand(String[] commands) {
        Ui.farewellMessage(commands);
    }
}
