package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.exception.TaskNumberExceedException;

public abstract class Command {


    public abstract void executeCommand(String[] commands) throws EmptyDescriptionException, TaskNumberExceedException;


}
