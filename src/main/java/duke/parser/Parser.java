package duke.parser;

import duke.commands.Command;
import duke.commands.add.ToDoCommand;
import duke.commands.add.DeadlineCommand;
import duke.commands.add.EventCommand;
import duke.commands.ListCommand;
import duke.commands.UnmarkCommand;
import duke.commands.MarkCommand;
import duke.commands.DeleteCommand;
import duke.commands.ByeCommand;
import duke.commands.IncorrectCommand;

import duke.exception.EmptyTaskDescriptionException;
import duke.exception.MissingDateTimeReferenceException;
import duke.exception.MissingDeadlineDateTimeReferenceException;
import duke.exception.MissingEventDateTimeReferenceException;
import duke.exception.MissingListIndexException;

public class Parser {

    public Parser() {
    }

    public Command parseCommand(String userInput) throws MissingListIndexException, EmptyTaskDescriptionException,
            MissingDeadlineDateTimeReferenceException, MissingEventDateTimeReferenceException {
        // split the input into command and arguments
        String[] words = userInput.trim().split(" ", 2);
        if (words.length == 0) {
            return new IncorrectCommand();
        }

        final String commandWord = words[0];
        final String commandDetails = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {
        case ToDoCommand.COMMAND_WORD:
            return prepareToDoCommand(commandDetails);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadlineCommand(commandDetails);

        case EventCommand.COMMAND_WORD:
            return prepareEventCommand(commandDetails);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmarkCommand(commandDetails);

        case MarkCommand.COMMAND_WORD:
            return prepareMarkCommand(commandDetails);

        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(commandDetails);

        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();

        default:
            return new IncorrectCommand();
        }
    }

    private static Command prepareToDoCommand(String commandDetails) throws EmptyTaskDescriptionException {
        checkForEmptyTaskDescription(commandDetails);
        return new ToDoCommand(commandDetails);
    }

    private static Command prepareDeadlineCommand(String commandDetails)
            throws EmptyTaskDescriptionException , MissingDeadlineDateTimeReferenceException {
        checkForEmptyTaskDescription(commandDetails);
        try {
            String deadlineTaskName = extractTaskName(commandDetails, "/by");
            String deadlineTaskBy = extractTaskDateTime(commandDetails, "/by");
            return new DeadlineCommand(deadlineTaskName, deadlineTaskBy);
        } catch (MissingDateTimeReferenceException e) {
            throw new MissingDeadlineDateTimeReferenceException();
        }
    }

    private static Command prepareEventCommand(String commandDetails)
            throws EmptyTaskDescriptionException, MissingEventDateTimeReferenceException {
        checkForEmptyTaskDescription(commandDetails);
        try {
            String eventTaskName = extractTaskName(commandDetails, "/at");
            String eventTaskAt = extractTaskDateTime(commandDetails, "/at");
            return new EventCommand(eventTaskName, eventTaskAt);
        } catch (MissingDateTimeReferenceException e) {
            throw new MissingEventDateTimeReferenceException();
        }
    }

    private static void checkForEmptyTaskDescription(String commandDetails) throws EmptyTaskDescriptionException {
        if (commandDetails.isEmpty()) {
            throw new EmptyTaskDescriptionException();
        }
    }

    public static String extractTaskName(String taskDescription, String dateTimeReference)
            throws MissingDateTimeReferenceException {
        int dateTimeIndex = taskDescription.indexOf(dateTimeReference);
        boolean haveDataTimeReference = (dateTimeIndex != -1);
        if (!haveDataTimeReference) {
            throw new MissingDateTimeReferenceException();
        }
        return taskDescription.substring(0, dateTimeIndex - 1).trim();
    }

    public static String extractTaskDateTime(String taskDescription, String dateTimeReference)
            throws MissingDateTimeReferenceException {
        int dateTimeIndex = taskDescription.indexOf(dateTimeReference);
        boolean haveDataTimeReference = (dateTimeIndex != -1);
        if (!haveDataTimeReference) {
            throw new MissingDateTimeReferenceException();
        }
        return taskDescription.substring(dateTimeIndex + 3).trim();
    }

    private static Command prepareUnmarkCommand(String commandDetails)
            throws MissingListIndexException, IndexOutOfBoundsException, NumberFormatException {
        int taskIndex = extractTaskIndex(commandDetails);
        return new UnmarkCommand(taskIndex);
    }

    private static Command prepareMarkCommand(String commandDetails)
            throws MissingListIndexException, IndexOutOfBoundsException, NumberFormatException {
        int taskIndex = extractTaskIndex(commandDetails);
        return new MarkCommand(taskIndex);
    }

    private static Command prepareDeleteCommand(String commandDetails)
            throws MissingListIndexException, IndexOutOfBoundsException, NumberFormatException {
        int taskIndex = extractTaskIndex(commandDetails);
        return new DeleteCommand(taskIndex);
    }

    private static int extractTaskIndex(String commandDetails) throws MissingListIndexException {
        if (commandDetails.isEmpty()) {
            throw new MissingListIndexException();
        }
        return Integer.parseInt(commandDetails) - 1;
    }
}
