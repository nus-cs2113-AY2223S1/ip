package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    public FindCommand(String keyword){
        super(COMMAND_NAME);
        this.keyword = keyword;
    }
    public static final String COMMAND_NAME = "find";
    public static final String SYNTAX = "Find syntax\n\t>>>find <keyword>";
    public String keyword;

    @Override
    public CommandResult execute(){
        ArrayList<Task> target = new ArrayList<>(
            TaskList.list.stream()
                    .filter(i -> i.description.contains(this.keyword))
                    .collect(Collectors.toList())
        );
        int count = (int) target.stream().count();
        String messageTop = "Found " + count + TaskList.printTaskPlural(count);
        return new CommandResult(messageTop,target,"");
    }
}
