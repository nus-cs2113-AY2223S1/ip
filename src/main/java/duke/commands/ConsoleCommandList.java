package duke.commands;

import java.time.LocalDate;

/**
 * Stores arguments for list command.
 */
public class ConsoleCommandList extends ConsoleCommand {
    private LocalDate date;

    /**
     * Initializes object for list command.
     */
    public ConsoleCommandList() {
    }

    /**
     * Initializes object with arguments for list command.
     *
     * @param date Date.
     */
    public ConsoleCommandList(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
