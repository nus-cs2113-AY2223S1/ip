package duke.userinterface;

import java.time.LocalDateTime;

public class ConsoleCommandEvent extends ConsoleCommand {
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

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
