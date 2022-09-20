package duke.command;

import duke.data.task.*;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        for (int i = 0; i < index.length; i++) {
            this.taskList.data.get(index[i]).setIsDone(true);
        }
        target = new ArrayList<Task>(
                IntStream.range(0, this.taskList.data.size())
                        .filter(i -> contains(index, i))
                        .mapToObj(i -> this.taskList.data.get(i)).collect(Collectors.toList()));
        this.message = "Mark " + targetCount() + " " + printTaskPlural();
        return new CommandResult(message, target);
    }


}
