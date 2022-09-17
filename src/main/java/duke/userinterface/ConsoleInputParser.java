package duke.userinterface;

public class ConsoleInputParser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    public static ConsoleCommand parseConsoleInput(ConsoleInput consoleInput) {
        String command = consoleInput.getCommand();
        String arguments = consoleInput.getArguments();

        if (command.equalsIgnoreCase(COMMAND_BYE)) {
            return new ConsoleCommandBye();
        } else if (command.equalsIgnoreCase(COMMAND_LIST)) {
            return new ConsoleCommandList();
        } else if (command.equalsIgnoreCase(COMMAND_MARK)) {
            int taskNumber = Integer.parseInt(arguments);

            return new ConsoleCommandMark(taskNumber);
        } else if (command.equalsIgnoreCase(COMMAND_UNMARK)) {
            int taskNumber = Integer.parseInt(arguments);

            return new ConsoleCommandUnmark(taskNumber);
        } else if (command.equalsIgnoreCase(COMMAND_TODO)) {
            return new ConsoleCommandTodo(arguments);
        } else if (command.equalsIgnoreCase(COMMAND_DEADLINE)) {
            String[] argumentArray = arguments.split("/by");
            String description = argumentArray[0].trim();
            String by = argumentArray[1].trim();

            return new ConsoleCommandDeadline(description, by);
        } else if (command.equalsIgnoreCase(COMMAND_EVENT)) {
            String[] argumentArray = arguments.split("/at");
            String description = argumentArray[0].trim();
            String at = argumentArray[1].trim();

            return new ConsoleCommandEvent(description, at);
        } else if (command.equalsIgnoreCase(COMMAND_DELETE)) {
            int taskNumber = Integer.parseInt(arguments);

            return new ConsoleCommandDelete(taskNumber);
        } else {
            return null;
        }
    }
}
