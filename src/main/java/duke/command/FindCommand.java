package duke.command;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    public static final String COMMAND_NAME = "find";
    public static final String SYNTAX = "Find syntax\n\t>>>find <keyword>";
    public String keyword;

    public FindCommand(String keyword) {
        super(COMMAND_NAME);
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute() {
        target = new ArrayList<>( //Find target task with string that contains keyword
                this.taskList.data.stream()
                        .filter(i -> i.description.getData().contains(this.keyword))
                        .collect(Collectors.toList())
        );

        this.message = "Found " + targetCount() + " " + printTaskPlural() + " with \"" + keyword + "\"";
        return new CommandResult(message, target);
    }
}
