package duke.userinterface;

public class ConsoleCommandDeadline extends ConsoleCommand {
    private String description;
    private String by;

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
