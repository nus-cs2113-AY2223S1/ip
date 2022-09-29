package duke.parser;

import duke.command.Command;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.MissingArgumentException;
import duke.ui.Ui;

public class Parser {

    public Parser() {

    }

   public Command parse(String fullCommand) throws MissingArgumentException, InvalidCommandException {
       String[] ArrCommand = fullCommand.split(" ", 2);
       if (ArrCommand.length == 2) {
           String command = ArrCommand[0];
           String taskDescription = ArrCommand[1];
           return new Command(command, taskDescription);
       }
       return new Command(fullCommand, "");
   }

    public static int parseMark(String argument) {
       return Integer.parseInt(argument);
    }

    public static int parseUnmark(String argument) {
        return Integer.parseInt(argument);
    }

    public static String parseDeadline(String taskDescription) {
        return taskDescription.split("/by ")[0];
    }

    public static String parseDeadlineDate(String taskDescription) throws MissingArgumentException {
        return taskDescription.split("/by ")[1];
    }

    public static String parseEvent(String taskDescription) {
        return taskDescription.split("/at ")[0];
    }

    public static String parseEventDate(String taskDescription) {
        return taskDescription.split("/at ")[1];
    }

    public static int parseDelete(String argument) {
        return Integer.parseInt(argument);
    }
}
