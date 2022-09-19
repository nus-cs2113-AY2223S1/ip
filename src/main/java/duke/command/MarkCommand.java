package duke.command;

import duke.data.TaskList;
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
    public static final String MESSAGES_TOP = "Marked Tasks";

    public int[] index;

    @Override
    public CommandResult execute() {

        for (int i = 0; i < index.length; i++) {
            TaskList.list.get(index[i]).setIsDone(true);
        }
        ArrayList<Task> target = new ArrayList<Task>(
                IntStream.range(0, TaskList.list.size())
                        .filter(i -> contains(index, i))
                        .mapToObj(i -> TaskList.list.get(i)).collect(Collectors.toList()));

        return new CommandResult(MESSAGES_TOP, target, TaskList.getUnmarkMessage());
    }


}
