package duke.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.data.task.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_NAME = "delete";
    public static final String SYNTAX = "Syntax for delete\n\t>>> delete <index 1> <index 2> <...>\nNote: item indices must exist in the current list";
    
    
    public DeleteCommand(int... index) {
        super(COMMAND_NAME);
        this.index = index;
    }



    public int[] index;

    @Override
    public CommandResult execute() {

        target = new ArrayList<Task>( // Get target tasks
            IntStream.range(0, this.taskList.data.size())
                .filter(i -> contains(index, i))
                .mapToObj(i -> this.taskList.data.get(i))
                .collect(Collectors.toList()));

        Arrays.sort(index);
        for (int i = index.length - 1; i > -1; i--) { //Delete from largest to smallest index
            this.taskList.data.remove(index[i]);
        }
        this.message = "Delete " + targetCount() + " " + printTaskPlural();
        return new CommandResult(message, target);

    }

}
