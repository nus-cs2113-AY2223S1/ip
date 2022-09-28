package duke.command;

/**
 * Represent the command documentation with syntax and description of a command.
 */
public class CommandDocumentation {
    private final String syntax;
    private final String description;

    /**
     * Constructor of <code>CommandDocumentation</code> class with the specified syntax and description.
     * @param syntax Syntax of how the command should be inputted.
     * @param description Description of what the command is used for.
     */
    public CommandDocumentation(String syntax, String description) {
        this.syntax = syntax;
        this.description = description;
    }

    /**
     * Return the formatted string of the documentation to be printed.
     * @return Formatted string of the documentation.
     */
    @Override
    public String toString() {
        return String.format("%s\n    %s", description, syntax);
    }

    /**
     * Return the syntax of the command.
     * @return Syntax of the command.
     */
    public String getSyntax() {
        return syntax;
    }
}
