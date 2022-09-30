package duke.commands;

import duke.Ui;

/**
 * When executed, it prints an invalid command text
 */
public class InvalidCommand extends Command {

    public InvalidCommand() {
        Ui.printCorrectFormatText("invalid");
    }
}
