package duke.userinterface;

import java.time.LocalDateTime;

/**
 * Stores arguments for event command.
 */
public class ConsoleCommandEvent extends ConsoleCommand {
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    /**
     * Initializes object with arguments for event command.
     *
     * @param description Description of the event.
     * @param startAt Start date and time of the event.
     * @param endAt End date and time of the event.
     */
    public ConsoleCommandEvent(String description, LocalDateTime startAt, LocalDateTime endAt) {
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }
}
