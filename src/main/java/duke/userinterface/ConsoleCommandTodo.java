package duke.userinterface;

/**
 * Stores arguments for todo command.
 */
public class ConsoleCommandTodo extends ConsoleCommand {
    private String description;

    /**
     * Initializes object with arguments for todo command.
     *
     * @param description Description of the todo.
     */
    public ConsoleCommandTodo(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
