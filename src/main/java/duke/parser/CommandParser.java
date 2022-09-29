package duke.parser;

import javax.management.Descriptor;

import duke.command.ByeCommand;
import duke.command.Command;

public class CommandParser {
    // parse a user input string as a command
    public static void parseCommand(String input) {
        String command = Parser.getCommand(input);
        switch (command) {
        case "bye":
            // execute ByeCommand
            break;
        case "todo":
            // execute TodoCommand
            break;
        case "deadline":
            // execute DeadlineCommand
            break;
        case "event":
            // execute EventCommand
            break;
        }
    }
}
