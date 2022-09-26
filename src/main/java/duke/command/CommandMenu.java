package duke.command;

import duke.Ui;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class CommandMenu {
    public static final String HELP_COMMAND = "help";
    public static final String LIST_COMMAND = "list";
    public static final String ADD_TODO_COMMAND = "todo";
    public static final String ADD_DEADLINE_COMMAND = "deadline";
    public static final String ADD_EVENT_COMMAND = "event";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String DELETE_COMMAND = "delete";
    public static final String EXIT_COMMAND = "bye";
    public static final String INVALID_COMMAND = "invalid";

    public static final Map<String, CommandDocumentation> COMMANDS;
    static {
        COMMANDS = new LinkedHashMap<>();

        COMMANDS.put(HELP_COMMAND, new CommandDocumentation("help",
                "Get help for the commands supported and their syntax"));
        COMMANDS.put(LIST_COMMAND, new CommandDocumentation("list",
                "View all tasks with their task number"));
        COMMANDS.put(ADD_TODO_COMMAND, new CommandDocumentation("todo <description>",
                "Add a todo task"));
        COMMANDS.put(ADD_DEADLINE_COMMAND, new CommandDocumentation("deadline <description> /by <deadline-datetime>",
                "Add a task with its deadline"));
        COMMANDS.put(ADD_EVENT_COMMAND, new CommandDocumentation("event <description> /at <event-datetime>",
                "Add an event with its date and time"));
        COMMANDS.put(MARK_COMMAND, new CommandDocumentation("mark <task-number>",
                "Mark a task as done"));
        COMMANDS.put(UNMARK_COMMAND, new CommandDocumentation("unmark <task-number>",
                "Unmark a task from done"));
        COMMANDS.put(DELETE_COMMAND, new CommandDocumentation("delete <task-number>",
                "Delete a task from the list"));
        COMMANDS.put(EXIT_COMMAND, new CommandDocumentation("bye", "Exit the application"));
    }

    public static void display(Ui ui) {
        for (Map.Entry<String, CommandDocumentation> command: COMMANDS.entrySet()) {
            ui.showCommandDocumentation(command.getValue());
        }
    }

    public static String getCommandSyntaxHint(String commandKeyword) {
        return COMMANDS.get(commandKeyword).syntax;
    }
}
