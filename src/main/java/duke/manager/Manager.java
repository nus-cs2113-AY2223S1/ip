package duke.manager;

import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.DeleteCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.exception.NoSuchCommandException;
import duke.task.TaskList;

import java.util.Scanner;

public class Manager {

    public static Command commandCreator(String input) throws NoSuchCommandException {
        String keyword = Parser.getKeyword(input);
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
        default:
            throw new NoSuchCommandException();
        }
        return newCommand;
    }

    public static void readInput() {
        UserInterface.printHello();
        TaskList taskList = new TaskList();
        Scanner in = new Scanner(System.in);
        boolean isBye = false;
        while (!isBye) {
            // read in the next line of input
            try {
                String input = UserInterface.readInput(in);
                // initialise Command
                UserInterface.printBorderLines();
                Command c = commandCreator(input);
                // parse
                Parser.parse(taskList, c, input);
                // execute
                if (c.isLegal()) {
                    TaskExecutor.execute(taskList, c);
                }
                // change isBye state if command = "bye"
                isBye = c.isBye();
                if (isBye) {
                    UserInterface.printGoodbye();
                }
                UserInterface.printBorderLines();
            } catch (NoSuchCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                UserInterface.printBorderLines();
            }
        }
    }
}
