package ParserPackage;

import CommandPackage.*;
import ExceptionsPackage.*;

import java.lang.reflect.Array;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] fullCommandSplit = fullCommand.split(" ");
        String commandWord = fullCommandSplit[0];
        switch(commandWord){
            case "todo":
                try {
                    String todoDescription = fullCommand.substring(fullCommand.indexOf("todo") + 5);
                    return new AddCommand("todo", todoDescription, "");
                }catch(StringIndexOutOfBoundsException e){
                    System.out.println("Please input in format 'todo' <description>");
                }
                return new NullCommand();

            case "deadline":
                try{
                    String deadlineDescription = fullCommand.substring(fullCommand.indexOf(" ")+1, fullCommand.indexOf("/by") - 1);
                    String by = fullCommand.substring(fullCommand.indexOf("/by") + 4);
                    return new AddCommand("deadline", deadlineDescription, by);
                }catch(StringIndexOutOfBoundsException e){
                    System.out.println("Please input in format 'deadline' <description> <by>");
                }
                return new NullCommand();
            case "event":
                try{
                    String eventDescription = fullCommand.substring(fullCommand.indexOf(" ")+1, fullCommand.indexOf("/at") - 1);
                    String at = fullCommand.substring(fullCommand.indexOf("/at") + 4);
                    return new AddCommand("event", eventDescription, at);
                }catch(StringIndexOutOfBoundsException e){
                    System.out.println("Please input in format 'event' <description> <at>");
                }
                return new NullCommand();

            case "mark":
                try {
                    int markIndex = Integer.parseInt(fullCommand.split(" ")[1]);
                    return new MarkCommand(markIndex);
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Please indicate index");
                }
                return new NullCommand();

            case "unmark":
                try{
                    int unmarkIndex = Integer.parseInt(fullCommand.split(" ")[1]);
                    return new UnmarkCommand(unmarkIndex);
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Please indicate index");
                }
                return new NullCommand();

            case "delete":
                try{
                    int deleteIndex = Integer.parseInt(fullCommand.split(" ")[1]);
                    return new DeleteCommand(deleteIndex);
                }catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please indicate index");
                }
                return new NullCommand();

            case "find":
                try {
                    String findDescription = fullCommand.substring(fullCommand.indexOf(" ") + 1);
                    return new FindCommand(findDescription);
                }catch(StringIndexOutOfBoundsException e){
                    System.out.println("Please indicate keyword");
                }
                return new NullCommand();

            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand();
            default:
                throw new DukeException("Sorry, I don't understand.");
        }
    }
}
