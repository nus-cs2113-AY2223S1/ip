package duke.userinterface;

/**
 * Stores arguments for event command.
 */
public class ConsoleCommandEvent extends ConsoleCommand {
    private String description;
    private String at;

    /**
     * Initializes object with arguments for event command.
     *
     * @param description Description of the event.
     * @param at Date and time of the event.
     */
    public ConsoleCommandEvent(String description, String at) {
        this.description = description;
        this.at = at;
    }

    public String getDescription() {
        return description;
    }

    public String getAt() {
        return at;
    }
}
