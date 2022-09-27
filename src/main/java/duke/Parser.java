package duke;

import duke.commands.*;
import duke.exceptions.IncorrectFormatException;
import duke.exceptions.UnrecognizedCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


/**
 * This class manages everything that has to do with input processing and validation
 *
 * @author Dhanish
 */
public class Parser {

    public static final String DATA_TIME_FORMAT = "uuuu-MM-dd H:mm";
    public static final String PRINT_TIME_FORMAT = "MMM dd uuuu, HH:mm";

    /**
     * Extracts the word that should be the command word in the given input
     *
     * @param input
     * @return expected command keyword
     */
    public static String retrieveCommand(String input) {
        return input.split(" ")[0];
    }

    /**
     * checks and validates whether a given word is a recognized command, throws exception otherwise
     *
     * @param command - word to be checked
     * @throws UnrecognizedCommandException
     */
    public static void validateCommand(String command) throws UnrecognizedCommandException {
        if (!TaskList.isValidCommand(command)) {
            throw new UnrecognizedCommandException();
        }
    }

    private static int retrieveTaskNumber(String input) throws NumberFormatException {
        return Integer.parseInt(input) - 1;
    }

    private static String retrieveParameters(String input) {
        String[] parsed = input.split(" ", 2);
        if (parsed.length > 1) {
            return parsed[1].trim();
        }
        return "";
    }

    private static String retrieveTime(String parameters, String separator) {
        String[] parsed = parameters.split(separator);
        if (parsed.length > 1) {
            return parsed[1];
        }
        return "";
    }


    public static boolean isValidTime(String time) {
        try {
            LocalDateTime.parse(time, DateTimeFormatter.ofPattern(DATA_TIME_FORMAT));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static String retrieveTaskDescription(String parameters, String separator) {
        return parameters.split(separator)[0];
    }

    private static void validateFormat(String command, String parameters) throws IncorrectFormatException, NumberFormatException {
        if (command.equals("list") || command.equals("help") || command.equals("bye"))
            return;
        if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
            if (parameters.isEmpty()) {
                throw new IncorrectFormatException(Ui.MISSING_TASK_NUMBER_ERROR_MESSAGE);
            }
            retrieveTaskNumber(parameters);
        }
        if (command.equals("deadline")) {
            handlePossibleDeadlineExceptions(parameters);
        }
        if (command.equals("event")) {
            handlePossibleEventExceptions(parameters);
        }
        if (parameters.isEmpty()) {
            throw new IncorrectFormatException(Ui.MISSING_TODO_DESCRIPTION_ERROR_MESSAGE);
        }
    }

    private static void handlePossibleEventExceptions(String parameters) throws IncorrectFormatException {
        if (!parameters.contains(TaskList.EVENT_SEPERATOR.trim())) {
            throw new IncorrectFormatException(Ui.EVENT_SEPERATOR_ERROR_MESSAGE);
        }
        if (parameters.trim().equals(TaskList.EVENT_SEPERATOR.trim())) {
            throw new IncorrectFormatException(Ui.EVENT_SEPERATOR_ERROR_MESSAGE);
        }
        if (retrieveTaskDescription(parameters, TaskList.EVENT_SEPERATOR.trim()).isEmpty()) {
            throw new IncorrectFormatException(Ui.MISSING_EVENT_DESCRIPTION_ERROR_MESSAGE);
        }
        if (retrieveTime(parameters, TaskList.EVENT_SEPERATOR).isEmpty()) {
            throw new IncorrectFormatException(Ui.MISSING_EVENT_TIME_ERROR_MESSAGE);
        }
        if (!isValidTime(retrieveTime(parameters, TaskList.EVENT_SEPERATOR))) {
            throw new IncorrectFormatException("Error! The time entered is not valid!");
        }

    }

    private static void handlePossibleDeadlineExceptions(String parameters) throws IncorrectFormatException {
        if (!parameters.contains(TaskList.DEADLINE_SEPERATOR.trim())) {
            throw new IncorrectFormatException(Ui.DEADLINE_SEPERATOR_ERROR_MESSAGE);
        }
        if (parameters.trim().equals(TaskList.DEADLINE_SEPERATOR.trim())) {
            throw new IncorrectFormatException(Ui.DEADLINE_SEPERATOR_ERROR_MESSAGE);
        }
        if (retrieveTaskDescription(parameters, TaskList.DEADLINE_SEPERATOR.trim()).isEmpty()) {
            throw new IncorrectFormatException(Ui.MISSING_DEADLINE_DESCRIPTION_ERROR_MESSAGE);
        }
        if (retrieveTime(parameters, TaskList.DEADLINE_SEPERATOR).isEmpty()) {
            throw new IncorrectFormatException(Ui.MISSING_DEADLINE_TIME_ERROR_MESSAGE);
        }
        if (!isValidTime(retrieveTime(parameters, TaskList.DEADLINE_SEPERATOR))) {
            throw new IncorrectFormatException(Ui.INVALID_TIME_FORMAT_ERROR_MESSAGE);
        }
    }

    /**
     * Given user input with a valid command, this method parses the input and extracts the command name and parameters
     * It then checks if the given command and parameters are in valid format
     * If yes, it returns the corresponding {@code Command} object
     * Else, an exception is caught and the method terminates
     *
     * @param input - user input with validated command
     * @return - corresponding {@code Command} object to be executed
     */
    public static Command parse(String input) {
        Command command = null;
        String commandName = Parser.retrieveCommand(input);
        String parameters = Parser.retrieveParameters(input);

        try {
            Parser.validateFormat(commandName, parameters);
        } catch (NumberFormatException e) {
            Ui.showErrorMessage(Ui.INVALID_TASK_NUMBER_ERROR_MESSAGE);
            return command;
        } catch (IncorrectFormatException e) {
            Ui.showErrorMessage(e.getMessage());
            return command;
        }

        return createCommand(commandName, parameters);
    }

    public static LocalDateTime parseDateTime(String time) {
        return LocalDateTime.from(DateTimeFormatter.ofPattern(DATA_TIME_FORMAT).withResolverStyle(ResolverStyle.STRICT).parse(time));
    }

    public static String getFormattedTime(LocalDateTime dateTime, String format) {
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }

    private static Command createCommand(String commandName, String parameters) {
        Command command = null;
        String deadline;
        String description;
        int taskNumber;
        Task task = null;

        switch (commandName) {
        case "bye":
            command = new ExitCommand();
            return command;
        case "help":
            command = new HelpCommand();
            return command;
        case "list":
            command = new ListCommand();
            return command;
        case "mark":
            taskNumber = Parser.retrieveTaskNumber(parameters);
            command = new MarkCommand(taskNumber);
            return command;
        case "unmark":
            taskNumber = Parser.retrieveTaskNumber(parameters);
            command = new UnmarkCommand(taskNumber);
            return command;
        case "delete":
            taskNumber = Parser.retrieveTaskNumber(parameters);
            command = new DeleteCommand(taskNumber);
            return command;
        case "todo":
            task = new Todo(parameters);
            break;
        case "deadline":
            description = Parser.retrieveTaskDescription(parameters, TaskList.DEADLINE_SEPERATOR);
            deadline = Parser.retrieveTime(parameters, TaskList.DEADLINE_SEPERATOR);
            task = new Deadline(description, deadline);
            break;
        case "event":
            description = Parser.retrieveTaskDescription(parameters, TaskList.EVENT_SEPERATOR);
            deadline = Parser.retrieveTime(parameters, TaskList.EVENT_SEPERATOR);
            task = new Event(description, deadline);
            break;
        }
        command = new AddCommand(task);
        return command;
    }
}
