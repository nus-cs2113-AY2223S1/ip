package duke.command;

import duke.data.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    public FindCommand(String keyword) {
        super(COMMAND_NAME);
        this.keyword = keyword;
    }

    public static final String COMMAND_NAME = "find";
    public static final String SYNTAX = "Find syntax\n\t>>>find <keyword>";
    public String keyword;

    @Override
    public CommandResult execute() {
        target = new ArrayList<>(
                this.taskList.data.stream()
                        .filter(i -> i.description.getData().contains(this.keyword))
                        .collect(Collectors.toList())
        );

        this.message = "Found " + targetCount() + " " + printTaskPlural() + " with \"" + keyword + "\"";
        return new CommandResult(message, target);
    }
}
