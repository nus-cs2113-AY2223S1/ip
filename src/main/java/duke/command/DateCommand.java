package duke.command;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDate;

import duke.data.task.*;

public class DateCommand extends Command {
    public LocalDate date;

    public DateCommand(LocalDate date) {
        super(COMMAND_NAME);
        this.date = date;
    }

    public static final String COMMAND_NAME = "date";
    public static final String SYNTAX = "Syntax for date\n\t>>>date <yyyy-mm-dd>";

    @Override
    public CommandResult execute() {


        ArrayList<? extends Task> target = new ArrayList<>(
                this.taskList.data.stream()
                        .filter(i -> !i.date.isNull())
                        .filter(i-> i.date.getData().equals(this.date))
                        .collect(Collectors.toList()));

        this.message = "Found " + targetCount() + " " + printTaskPlural() +" on " + date.toString();
        return new CommandResult(message, target);
    }
}
