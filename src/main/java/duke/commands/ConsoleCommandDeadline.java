package duke.commands;

import java.time.LocalDateTime;

/**
 * Stores arguments for deadline command.
 */
@SuppressWarnings("FieldMayBeFinal")
public class ConsoleCommandDeadline extends ConsoleCommand {
    private String description;
    private LocalDateTime by;

    /**
     * Initializes object with arguments for deadline command.
     *
     * @param description Description of the deadline.
     * @param by Due date and time of the deadline.
     */
    public ConsoleCommandDeadline(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getBy() {
        return by;
    }
}
