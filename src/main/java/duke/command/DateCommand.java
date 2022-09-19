package duke.command;

import java.util.ArrayList;

import duke.data.TaskList;

import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.time.LocalDate;

import duke.data.task.*;

public class DateCommand extends Command {
    public DateCommand(LocalDate date) {
        super(COMMAND_NAME);
        this.date = date;
    }

    public static final String COMMAND_NAME = "date";
    public static final String SYNTAX = "Syntax for date\n\t>>>date <yyyy-mm-dd>";
    public LocalDate date;

    @Override
    public CommandResult execute() {


        ArrayList<Task> target = new ArrayList<Task>(
                TaskList.list.stream()
                        .filter(i -> !i.isDateNull())
                        .filter(i-> i.getDate().equals(this.date))
                        .collect(Collectors.toList()));

        long count = target.stream().count();
        String messageTop = count + " tasks happen on this date";

        return new CommandResult(messageTop, target, "");
    }
}
