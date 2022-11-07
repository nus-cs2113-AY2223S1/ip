package duke.command;

import duke.common.Message;
import duke.exception.EmptyFieldException;
import duke.exception.InvalidDateTimeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.DukeUI;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventCommand extends Command {

    private static final String FAILURE_MESSAGE_ADD_EVENT = Message.TEXT_INDENTATION
            + "Please specify a task with:\n"
            + Message.TEXT_INDENTATION + "\"" + Message.COMMAND_EVENT + "\"\n";

    private static final String FAILURE_PARSE_DURATION = Message.TEXT_INDENTATION
            + "Please enter the duration as an integer!\n";

    public EventCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public void execute(DukeUI ui, Storage storage, TaskList tasks) {
        try {
            String[] infoArr = Parser.extractEventInput(userInput);
            String taskDescription = infoArr[0];
            LocalDate date = LocalDate.parse(infoArr[1]);
            LocalTime time = LocalTime.parse(infoArr[2]);
            int duration = Integer.parseInt(infoArr[3]);

            tasks.addEvent(taskDescription, date, time, duration);
            ui.printAddedTask(tasks.getMostRecentAddedTask(), tasks.getTaskSize());
            storage.appendTaskToFile(ui, tasks.getMostRecentAddedTask());
        } catch (EmptyFieldException e) {
            ui.printMessage(FAILURE_MESSAGE_ADD_EVENT);
        } catch (InvalidDateTimeException e) {
            ui.printMessage(Message.FAILURE_PARSE_DATE);
        } catch (NumberFormatException e) {
            ui.printMessage(FAILURE_PARSE_DURATION);
        }
    }
}
