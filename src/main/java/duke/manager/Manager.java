package duke.manager;

import duke.Storage.Storage;
import duke.command.*;
import duke.exception.MissingArgumentException;
import duke.exception.NoSuchCommandException;
import duke.exception.TooManyArgumentsException;
import duke.task.TaskList;
import java.util.Scanner;

/**
 * Handles the loading of saved tasks in the hard drive if any and the creation of commands.
 */
public class Manager {

    /**
     * Creates a command based on the type of command the user provide.
     *
     * @param input the user command that was loaded or read by the scanner
     * @return the command created
     * @throws NoSuchCommandException If the command provided is not recognised by Duke
     */
    private static Command commandCreator(String input) throws NoSuchCommandException {
        String keyword = CommandParser.getKeyword(input);
        Command newCommand;
        switch (keyword) {
        case "bye":
            newCommand = new ByeCommand();
            break;
        case "list":
            newCommand = new ListCommand();
            break;
        case "mark":
            newCommand = new MarkCommand();
            break;
        case "unmark":
            newCommand = new UnmarkCommand();
            break;
        case "delete":
            newCommand = new DeleteCommand();
            break;
        case "todo":
            newCommand = new TodoCommand();
            break;
        case "event":
            newCommand = new EventCommand();
            break;
        case "deadline":
            newCommand = new DeadlineCommand();
            break;
        case "find":
            newCommand = new FindCommand();
            break;
        default:
            throw new NoSuchCommandException();
        }
        return newCommand;
    }

    /**
     * Reads the user input and passes it the relevant methods and classes for execution.
     *
     * @param taskList the list of tasks
     */
    private static void readInput(TaskList taskList) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            // read in the next line of input
            try {
                String input = in.nextLine();
                // initialise Command
                UserInterface.printBorderLines();
                Command c = commandCreator(input);
                // parse
                CommandParser.parse(c, input);
                // execute
                if (c.isLegal()) {
                    TaskExecutor.execute(taskList, c);
                }
                // print goodbye if command is bye
                if (c.isBye()) {
                    UserInterface.printGoodbye();
                }
                Storage.saveManager();
                UserInterface.printBorderLines();
            } catch (NoSuchCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                UserInterface.printBorderLines();
            } catch (TooManyArgumentsException | MissingArgumentException e) {
                System.out.println(e.getMessage());
                UserInterface.printBorderLines();
            }
        }
    }

    /**
     * Prints the hello message for the user upon program start up. Then calls
     * <code>loadManager</code> and stores the returned list and reads it for storing
     * into Duke.
     */
    public static void run() {
        UserInterface.printHello();
        // load
        TaskList taskList = Storage.loadManager();
        // run
        readInput(taskList);
    }
}
