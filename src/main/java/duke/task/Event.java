package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime startAt;
    protected LocalDateTime endAt;

    public Event(String description, LocalDateTime startAt, LocalDateTime endAt) {
        super(description);
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
