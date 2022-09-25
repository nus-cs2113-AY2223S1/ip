package duke.taskmanager;

import duke.UI;
import duke.exceptions.EmptyException;
import duke.exceptions.WrongCommandException;
import duke.taskmanager.commands.*;

public class Parser {
    private static final UI ui = new UI();
    public static Command parser(String userInput) {
        switch (userInput) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        default:
            return multiWordParser(userInput);
        }
    }

    public static Command multiWordParser(String userInput) {
        String firstWord;
        boolean isOneWord = false;
        try {
            firstWord = userInput.substring(0, userInput.indexOf(' '));
        } catch (StringIndexOutOfBoundsException e) {
            isOneWord = true;
            try {
                checkExceptions(userInput);
            } catch (EmptyException ee) {
                ui.printEmptyException(userInput);
            } catch (WrongCommandException ee) {
                ui.printWrongCommandException();
            }
        } finally {
            if (isOneWord) {
                return new WrongCommand(userInput,userInput);
            }
        }
        firstWord = userInput.substring(0, userInput.indexOf(' '));
        switch (firstWord) {
        case "mark":
            //Fallthrough
        case "unmark":
            return new MarkCommand(userInput,firstWord);
        case "todo":
            //Fallthrough
        case "deadline":
            //Fallthrough
        case "event":
            return new AddCommand(userInput,firstWord);
        case "delete":
            return new DeleteCommand(userInput,firstWord);
        case "find":
            return new FindCommand(userInput,firstWord);
        default:
            ui.printWrongCommandException();
            return new WrongCommand(userInput,firstWord);
        }
    }

    private static void checkExceptions(String command) throws EmptyException, WrongCommandException {
        if (command.matches("mark|unmark|todo|deadline|event|delete|find")) {
            throw new EmptyException();
        } else {
            throw new WrongCommandException();
        }
    }
}
