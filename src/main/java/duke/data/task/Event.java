package duke.data.task;

import java.time.LocalDateTime;

/**
 * Stores events.
 */
@SuppressWarnings("unused")
public class Event extends Task {
    protected LocalDateTime startAt;
    protected LocalDateTime endAt;

    /**
     * Initializes event object.
     *
     * @param description Description of the event.
     * @param startAt Start date and time of the event.
     * @param endAt End date and time of the event.
     */
    public Event(String description, LocalDateTime startAt, LocalDateTime endAt) {
        super(description);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }
}
