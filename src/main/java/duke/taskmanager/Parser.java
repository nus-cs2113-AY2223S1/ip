package duke.taskmanager;

import duke.exceptions.EmptyException;
import duke.exceptions.WrongCommandException;
import duke.taskmanager.commands.*;

/**
 * Checks the user input to detect invalid inputs. If the input is valid it will proceed to detect
 * the type of command.
 *
 */
public class Parser {
    /**
     * Checks the user input for valid commands. First checks for valid one word commands before checking
     * for multiple word commands.
     *
     * @param userInput the <code>String</code> that the user input
     * @return <code>Command</code> class type representing the user's command
     */
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

    /**
     * Checks the user input for valid multiple word commands
     *
     * @param userInput the multiple word <code>String</code> that the user input
     * @return <code>Command</code> class type representing the user's command
     */
    public static Command multiWordParser(String userInput) {
        String firstWord;
        try {
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
            default:
                return new WrongCommand(userInput,firstWord);
            }
        } catch (StringIndexOutOfBoundsException e) {
            //isOneWord = true;
            try {
                checkExceptions(userInput);
            } catch (EmptyException ee) {
                return new EmptyCommand(userInput);
            } catch (WrongCommandException ee) {
                return new WrongCommand(userInput,userInput);
            }
        }
        return new WrongCommand(userInput,userInput);
    }


    /**
     * Checks if the user input is invalid
     *
     * @param userInput              The <code>String</code> that the user input
     * @throws EmptyException        The command must have multiple words
     * @throws WrongCommandException The command does not exist and is not valid
     */
    private static void checkExceptions(String userInput) throws EmptyException, WrongCommandException {
        if (userInput.matches("mark|unmark|todo|deadline|event|delete")) {
            throw new EmptyException();
        } else {
            throw new WrongCommandException();
        }
    }
}
