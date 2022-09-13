package duke.manager;

import duke.command.*;
import duke.task.TaskList;

import java.util.Scanner;

public class Manager {

    public static Command commandCreator(String input) {
        String keyword = Parser.getKeyword(input);
        Command c = null;
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
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
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
            String input = UserInterface.readInput(in);
            // initialise Command
            Command c = commandCreator(input);
            // parse
            c = Parser.parse(taskList, c, input);
            UserInterface.printBorderLines();
            // execute
            TaskExecutor.execute(taskList, c);
            UserInterface.printBorderLines();
            // change isBye state if command = "bye"
            isBye = c.isBye();
        }
        UserInterface.printGoodbye();
    }
}
