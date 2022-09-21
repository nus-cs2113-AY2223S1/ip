package duke.command;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.data.task.Date;
import duke.data.task.Task;

/**
 * Represent the DateCommand
 */
public class DateCommand extends Command {
    public static final String COMMAND_NAME = "date";
    public static final String SYNTAX = "Syntax for date\n\t>>>date <yyyy-mm-dd>";
    public LocalDate date;

    public DateCommand(LocalDate date) {
        super(COMMAND_NAME);
        this.date = date;
    }

    @Override
    public CommandResult execute() {
        ArrayList<? extends Task> target = new ArrayList<>( // Get the target by compare the date of task in the
                // tasklist
                this.taskList.data.stream().filter(i -> !i.date.isNull()).filter(
                                i -> i.date.getData().equals(this.date.format(DateTimeFormatter.ofPattern(Date.PRINT_FORMAT))))
                        .collect(Collectors.toList()));

        this.message = "Found " + targetCount() + " " + printTaskPlural() + " on " + date.toString();
        return new CommandResult(message, target);
    }
}
