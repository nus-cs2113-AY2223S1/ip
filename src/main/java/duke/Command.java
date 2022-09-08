package duke;

public class Command {
    public String syntax;
    public String description;

    public Command(String syntax, String description) {
        this.syntax = syntax;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s\n    %s", description, syntax);
    }
}
