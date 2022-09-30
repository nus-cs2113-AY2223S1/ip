package duke.manager;

import duke.Storage.Storage;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
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
        String commandType = CommandParser.getType(input);
        Command newCommand;
        switch (commandType) {
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
                Command command = commandCreator(input);
                // parse
                CommandParser.parse(command, input);
                // execute
                TaskExecutor.execute(taskList, command);
                // print goodbye if command is bye
                if (command.isBye()) {
                    UserInterface.printGoodbye();
                    System.exit(0);
                }
                Storage.saveManager();
            } catch (NoSuchCommandException | TooManyArgumentsException | MissingArgumentException e) {
                System.out.println(e.getMessage());
            }
            UserInterface.printBorderLines();
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
