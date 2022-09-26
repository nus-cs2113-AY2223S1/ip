package duke.command;

public class CommandDocumentation {
    public String syntax;
    public String description;

    public CommandDocumentation(String syntax, String description) {
        this.syntax = syntax;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s\n    %s", description, syntax);
    }
}
