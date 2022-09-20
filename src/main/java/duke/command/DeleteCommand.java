package duke.command;

import duke.data.tag.TaskList;
import duke.data.task.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DeleteCommand extends Command {
    public DeleteCommand(int... index) {
        super(COMMAND_NAME);
        this.index = index;
    }

    /* Variables */
    public static final String COMMAND_NAME = "delete";
    public static final String SYNTAX = "Syntax for delete\n\t>>> delete <index 1> <index 2> <...>\nNote: item indices must exist in the current list";
    public static final String MESSAGE_TOP = "Tasks deleted";

    public int[] index;

    @Override
    public CommandResult execute() {

        ArrayList<Task> target = new ArrayList<Task>(
            IntStream.range(0, this.taskList.size())
                .filter(i -> contains(index, i))
                .mapToObj(i -> this.taskList.get(i))
                .collect(Collectors.toList()));

        Arrays.sort(index);
        for (int i = index.length - 1; i > -1; i--) {
            TaskList.list.remove(index[i]);
        }

        return new CommandResult(MESSAGE_TOP, target, TaskList.getTotalMessage());

    }

}
