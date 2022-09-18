package duke.userinterface;

/**
 * Stores arguments for deadline command.
 */
public class ConsoleCommandDeadline extends ConsoleCommand {
    private String description;
    private String by;

    /**
     * Initializes object with arguments for deadline command.
     *
     * @param description Description of the deadline.
     * @param by Due date and time of the deadline.
     */
    public ConsoleCommandDeadline(String description, String by) {
        this.description = description;
        this.by = by;
    }

    public String getDescription() {
        return description;
    }

    public String getBy() {
        return by;
    }
}
