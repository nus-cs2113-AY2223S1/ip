package duke.command;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.data.task.Task;

public class MarkCommand extends Command {
    public MarkCommand(int... index) {
        super(COMMAND_NAME);
        this.index = index;
    }

    /* Variables */
    public static final String COMMAND_NAME = "mark";
    public static final String SYNTAX = "Syntax for mark\n\t>>> mark <index 1> <index 2> <...>\nNote: item indices must exist in the current list";

    public int[] index;

    @Override
    public CommandResult execute() {
        for (int i = 0; i < index.length; i++) { // Mark and get target task based on index from user 
            this.taskList.data.get(index[i]).setIsDone(true);
            target.add(this.taskList.data.get(index[i]));
        }

        this.message = "Mark " + targetCount() + " " + printTaskPlural();
        return new CommandResult(message, target);
    }


}
