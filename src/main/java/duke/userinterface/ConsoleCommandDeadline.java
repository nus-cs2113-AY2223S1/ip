package duke.userinterface;

import java.time.LocalDateTime;

public class ConsoleCommandDeadline extends ConsoleCommand {
    private String description;
    private LocalDateTime by;

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
