package duke.command;

import duke.Ui;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represent the menu of commands the user can input, alongside with their syntax and descriptions.
 * For example, the keyword for the mark task as done command is "mark", and the syntax is "mark task-number".
 */
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

    /**
     * Display the supported commands' documentation.
     * @param ui User interface of the application.
     */
    public static void display(Ui ui) {
        for (Map.Entry<String, CommandDocumentation> command: COMMANDS.entrySet()) {
            ui.showCommandDocumentation(command.getValue());
        }
    }

    /**
     * Return the command syntax of the command keyword as a hint for user to check.
     * @param commandKeyword Command keyword that is the first word inputted in a command.
     *                       The keyword must be supported in the command menu.
     * @return Syntax of the command specified by the command keyword.
     */
    public static String getCommandSyntaxHint(String commandKeyword) {
        return COMMANDS.get(commandKeyword).getSyntax();
    }
}
