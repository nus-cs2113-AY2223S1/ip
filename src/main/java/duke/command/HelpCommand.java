package duke.command;

import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to list out commands available.
 */
public class HelpCommand extends Command {
    public static final String KEYWORD = "help";
    private static final String HELP_INTRO = "Duke - CLI based Task list manager";
    private static final List<String> COMMAND_DESCRIPTIONS = List.of("bye - exits the application",
            "list - list out all your tasks", "todo NAME - adds a todo task",
            "deadline NAME /by DATE_TIME - adds a deadline task", "event NAME /at DATE_TIME - adds an event task",
            "delete INDEX - deletes a task", "mark INDEX - marks a task as done",
            "unmark INDEX - marks a task as undone", "find KEYWORD - finds a task by name");
    private static final String USER_GUIDE_URL = "Full guide at https://owenl131.github.io/ip";
    private static final String BULLET_POINT = "> ";

    /**
     * Creates a Help command.
     * 
     * @param input The user input string
     */
    public HelpCommand(String input) {
        super(input);
    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage, TaskList lastResults) throws DukeException {
        String commands = String.join("\n",
                COMMAND_DESCRIPTIONS.stream().map(s -> BULLET_POINT + s).collect(Collectors.toList()));
        ui.displayMessage(HELP_INTRO + "\n\n" + commands + "\n\n" + USER_GUIDE_URL);
        return taskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
