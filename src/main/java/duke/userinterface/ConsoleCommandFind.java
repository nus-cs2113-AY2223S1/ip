package duke.userinterface;

public class ConsoleCommandFind extends ConsoleCommand {
    private String description;

    public ConsoleCommandFind(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
