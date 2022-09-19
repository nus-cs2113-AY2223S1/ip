package duke.parser;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.*;
import duke.data.Messages;
import duke.data.exceptions.*;
import duke.data.task.*;
import duke.data.TaskList;

public class Parser {

    public Parser() {

    }

    public Command parseCommand(String userInput) {
        String[] parsedInput = userInput.split(" ");
        if (parsedInput.length == 0) {
            return new IncorrectCommand(Messages.UNKNOWN_COMMAND, Messages.COMMAND_LISTS);
        }

        final String commandWord = parsedInput[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord.toLowerCase()) {

        case TodoCommand.COMMAND_NAME:
            return parseTodoCommand(arguments);
        case DeadlineCommand.COMMAND_NAME:
            return parseDeadlineCommand(arguments);
        case EventCommand.COMMAND_NAME:
            return parseEventCommand(arguments);
        case MarkCommand.COMMAND_NAME:
            return parseMarkCommand(arguments);
        case UnmarkCommand.COMMAND_NAME:
            return parseUnmarkCommand(arguments);
        case DeleteCommand.COMMAND_NAME:
            return parseDeleteCommand(arguments);
        case FindCommand.COMMAND_NAME:
            return parseFindCommand(arguments);
        case DateCommand.COMMAND_NAME:
            return parseDateCommand(arguments);

        case ListCommand.COMMAND_NAME:
            return new ListCommand();
        case ExitCommand.COMMAND_NAME:
            return new ExitCommand();
        default:
            return new IncorrectCommand(Messages.UNKNOWN_COMMAND, Messages.COMMAND_LISTS);
        }
    }

    public void parseFile(String fileLine) throws DukeFileException {
        String[] parsedLine = fileLine.split(Task.PARSE_LIMITER);

        String[] parsedTrim = new String[parsedLine.length];
        for (int i = 0; i < parsedLine.length; i++) {
            parsedTrim[i] = parsedLine[i].trim();
        }
        final String taskType = parsedTrim[0];
        switch (taskType) {
        case Todo.TYPE_TODO:
            parseTodoLine(parsedTrim);
            break;
        case Deadline.TYPE_DEADLINE:
            parseDeadlineLine(parsedTrim);
            break;
        case Event.TYPE_EVENT:
            parseEventLine(parsedTrim);
            break;
        default:
            throw new DukeFileException();
        }

    }

    /* Parse Data Line */
    private void parseTodoLine(String[] parsed) throws DukeFileException {

        if (!(parsed.length == 3)) {
            throw new DukeFileException();
        }
        TaskList.list.add(new Todo(Boolean.valueOf(parsed[1]), parsed[2]));
    }

    private void parseDeadlineLine(String[] parsed) throws DukeFileException {
        if (!(parsed.length == 4)) {
            throw new DukeFileException();
        }
        TaskList.list.add(new Deadline(Boolean.valueOf(parsed[1]), parsed[2], parsed[3]));
    }

    private void parseEventLine(String[] parsed) throws DukeFileException {
        if (!(parsed.length == 4)) {
            throw new DukeFileException();
        }
        TaskList.list.add(new Event(Boolean.valueOf(parsed[1]), parsed[2], parsed[3]));
    }




    
    /* Parse Command */
    private Command parseTodoCommand(String arguments) {
        try {
            if (arguments.equals("")) {
                throw new DukeException();
            }
            return new TodoCommand(arguments);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeException e) {
            return new IncorrectCommand(TodoCommand.SYNTAX);
        }

    }

    private Command parseDeadlineCommand(String arguments) {
        try {
            String[] parsed = arguments.split("/");
            if (!(parsed.length == 2)) {
                throw new DukeException();
            }
            return new DeadlineCommand(parsed[0].trim(), parsed[1].trim());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeException e) {

            return new IncorrectCommand(DeadlineCommand.SYNTAX);
        }
    }

    private Command parseEventCommand(String arguments) {
        try {
            String[] parsed = arguments.split("/");
            if (!(parsed.length == 2)) {
                throw new DukeException();
            }
            return new EventCommand(parsed[0].trim(), parsed[1].trim());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeException e) {

            return new IncorrectCommand(EventCommand.SYNTAX);
        }
    }

    private Command parseMarkCommand(String arguments) {
        try {
            String[] parsed = arguments.split(" ");
            if (parsed.length < 1) {
                throw new DukeException();
            }
            return new MarkCommand(strToIntArray(parsed));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeException e) {

            return new IncorrectCommand(MarkCommand.SYNTAX);
        }
    }


    private Command parseUnmarkCommand(String arguments) {
        try {
            String[] parsed = arguments.split(" ");
            if (parsed.length < 1) {
                throw new DukeException();
            }
            return new UnmarkCommand(strToIntArray(parsed));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeException e) {

            return new IncorrectCommand(UnmarkCommand.SYNTAX);
        }
    }

    private Command parseDeleteCommand(String arguments) {
        try {
            String[] parsed = arguments.split(" ");
            if (parsed.length < 1) {
                throw new DukeException();
            }
            return new DeleteCommand(strToIntArray(parsed));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | DukeException e) {
            return new IncorrectCommand(DeleteCommand.SYNTAX);
        }
    }
    private Command parseFindCommand(String arguments){
        try{
            if (arguments.equals("")){
                throw new DukeException();
            }
            return new FindCommand(arguments);
        } catch (DukeException e){
            return new IncorrectCommand(FindCommand.SYNTAX);
        }
    }

    private Command parseDateCommand(String arguments){
        try {
            return new DateCommand(LocalDate.parse(arguments));
        } catch (DateTimeParseException e){
            return new IncorrectCommand(DateCommand.SYNTAX);
        }
    }

    private int[] strToIntArray(String[] parsed) throws DukeException {
        int[] intParsed = new int[parsed.length];
        for (int i = 0; i < parsed.length; i++) {
            intParsed[i] = Integer.parseInt(parsed[i]) - Messages.OFFSET;
            if (intParsed[i] < 0 | intParsed[i] > TaskList.list.size()) {
                throw new DukeException();
            }
        }
        return intParsed;
    }
}
