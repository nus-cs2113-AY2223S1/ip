package duke.command;

/**
 * Represent an command
 */

public class Command {
    protected Command(){

    }

    public CommandResult execute(){
        throw new UnsupportedOperationException("Method to be implemented in child classes");

    }
}
