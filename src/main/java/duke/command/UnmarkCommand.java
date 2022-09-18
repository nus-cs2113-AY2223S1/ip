package duke.command;

import duke.data.Messages;
import duke.data.TaskList;
import duke.data.task.*;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class UnmarkCommand extends Command {
    public UnmarkCommand(int... index) {
        super(COMMAND_NAME);
        this.index = index;
    }

    public static final String COMMAND_NAME = "unmark";
    public static final String SYNTAX = "Syntax for unmark\n\t>>> unmark <index 1> <index 2> <...>\nNote: item indices must exist in the current list";
    public static final String MESSAGE_TOP = "Unmarked Task";

    public int[] index;

    @Override
    public CommandResult execute() {

        for (int i = 0; i < index.length; i++) {
            TaskList.list.get(index[i]).setIsDone(false);
        }

        ArrayList<Task> target = new ArrayList<Task>(
                IntStream.range(0, TaskList.list.size())
                        .filter(i -> contains(index, i))
                        .mapToObj(i -> TaskList.list.get(i)).collect(Collectors.toList()));

        CommandResult result = new CommandResult(MESSAGE_TOP, target, TaskList.getMarkMessage());
        return result;
    }

}
