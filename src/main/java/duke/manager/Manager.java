package duke.manager;

import duke.command.*;
import duke.exception.NoSuchCommandException;
import duke.task.TaskList;

import java.util.Scanner;

public class Manager {

    public static Command commandCreator(String input) throws NoSuchCommandException {
        String keyword = Parser.getKeyword(input);
        Command c;
        switch (keyword) {
        case "bye":
        case "list":
            c = new ByeOrListCommand();
            break;
        case "mark":
            c = new MarkCommand();
            break;
        case "unmark":
            c = new UnmarkCommand();
            break;
        case "delete":
            c = new DeleteCommand();
            break;
        case "todo":
            c = new TodoCommand();
            break;
        case "event":
            c = new EventCommand();
            break;
        case "deadline":
            c = new DeadlineCommand();
            break;
        default:
            throw new NoSuchCommandException();
        }
        return c;
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
