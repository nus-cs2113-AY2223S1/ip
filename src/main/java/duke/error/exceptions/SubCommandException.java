package duke.error.exceptions;

public abstract class SubCommandException extends DukeException {
    public SubCommandException(String firstWord, String subCommand) {
        super();
        this.command = firstWord;
        this.subCommand = subCommand;
    }

    String command;
    String subCommand;
}
