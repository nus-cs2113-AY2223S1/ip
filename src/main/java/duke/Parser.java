package duke;

import duke.exception.DukeException;
import duke.task.TaskList;

import java.util.Scanner;

public class Parser {
    private static final String END_CMD = "bye";

    public static boolean readUserCmd() throws DukeException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals(END_CMD)) {
            String[] args = input.split(" ", 2);
            String arg1 = args[0];
            String arg2 = "";
            if (args.length > 1) {
                arg2 = args[1];
            }
            switch (arg1) {
            case "list":
                TaskList.listTask();
                break;
            case "mark":
                TaskList.markTask(Integer.parseInt(arg2),
                        true);
                break;
            case "unmark":
                TaskList.markTask(Integer.parseInt(arg2),
                        false);
                break;
            case "todo":
                TaskList.addTodo(arg2);
                break;
            case "deadline":
                TaskList.addDeadline(arg2);
                break;
            case "event":
                TaskList.addEvent(arg2);
                break;
            case "delete":
                TaskList.deleteTask(Integer.valueOf(arg2));
                break;
            case "find":
                TaskList.findTask(arg2);
                break;
            default:
                TaskList.addTask(input);
            }
            Ui.showSeparator();
            input = sc.nextLine();
        }
        return true;
    }
}
