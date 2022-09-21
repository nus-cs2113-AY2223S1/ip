package duke.command;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class UnmarkCommand extends Command {

    public static final String COMMAND_NAME = "unmark";
    public static final String SYNTAX = "Syntax for unmark\n\t>>> unmark <index 1> <index 2> <...>\nNote: item indices must exist in the current list";
    public static final String MESSAGE_TOP = "Unmarked Task";

    public int[] index;
    public UnmarkCommand(int... index) {
        super(COMMAND_NAME);
        this.index = index;
    }

    @Override
    public CommandResult execute() {

        for (int i = 0; i < index.length; i++) { // Unmark the task based on index and get target
            this.taskList.data.get(index[i]).setIsDone(false);
            target.add(this.taskList.data.get(index[i]));
        }

        // target = new ArrayList<>(
        //         IntStream.range(0, this.taskList.data.size()) 
        //                 .filter(i -> contains(index, i))
        //                 .mapToObj(i -> this.taskList.data.get(i)).collect(Collectors.toList()));

        this.message = "Unmark " + targetCount() + " " + printTaskPlural();
        return new CommandResult(message, target);
    }

}
