package duke.userinterface;

public class ConsoleCommandTodo extends ConsoleCommand {
    private String description;

    public ConsoleCommandTodo(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
