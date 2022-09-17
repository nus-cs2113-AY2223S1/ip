package duke.userinterface;

public class ConsoleCommandEvent extends ConsoleCommand {
    private String description;
    private String at;

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
