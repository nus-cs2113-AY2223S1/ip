package duke.ui.parser;

import duke.exception.DukeInvalidCommandTypeException;

public class Parser {

    public Parser() {

    }

    public static Command parse(String rawInput) throws Exception {
        String[] tokens = rawInput.split("[ \t]+");

        String commandType;

        try {
            commandType = tokens[0];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandTypeException("");
        }

        String rawArguments = tokens.length > 1 ? rawInput.substring(commandType.length()).trim() : "";

        Command command;

        switch (commandType) {
        case "bye":
            command = new CommandExit(rawArguments);
            break;
        case "list":
            command = new CommandList(rawArguments);
            break;
        case "mark":
            command = new CommandMark(rawArguments);
            break;
        case "unmark":
            command = new CommandUnmark(rawArguments);
            break;
        case "todo":
            command = new CommandToDo(rawArguments);
            break;
        case "deadline":
            command = new CommandDeadline(rawArguments);
            break;
        case "event":
            command = new CommandEvent(rawArguments);
            break;
        case "delete":
            command = new CommandDelete(rawArguments);
            break;
        case "find":
            command = new CommandFind(rawArguments);
            break;
        default:
            throw new DukeInvalidCommandTypeException(commandType);
        }
        command.verifyAndParse();
        return command;

    }

}
