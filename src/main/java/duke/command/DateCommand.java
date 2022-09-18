package duke.command;

import java.util.ArrayList;
import duke.data.TaskList;

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
    public static final String MESSAGE_TOP = "Tasks happen on this date";
    public LocalDate date;

    @Override
    public CommandResult execute() {
        // ArrayList<Task> target = new ArrayList<Task>(
        // IntStream.range(0, TaskList.list.size())
        // .filter(i -> i.isDateNull())
        // .mapToObj(i -> TaskList.list.get(i)).collect(Collectors.toList()));
        ArrayList<Task> target = new ArrayList<Task>(
                TaskList.list.stream().
                filter(i -> i.isDateNull()).
                collect(Collectors.toList()));

        return new CommandResult(MESSAGE_TOP, target, null);
    }
}
