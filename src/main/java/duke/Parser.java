package duke;

import duke.exceptions.DukeException;

public class Parser {
    static boolean checkBye(String line) {
        return line.equals("bye");
    }

    static String getAction(String line){
        return line.split(" ")[0];
    }
    static void parse(TaskManager Manager,String line) throws DukeException {
        String action = getAction(line);
        switch (action) {
        case "mark":
            Manager.markTasks(line);
            break;
        case "unmark":
            Manager.unmarkTasks(line);
            break;
        case "list":
            Manager.printTasks();
            break;
        case "todo":
            Manager.storeTodo(line);
            break;
        case "deadline":
            Manager.storeDeadline(line);
            break;
        case "event":
            Manager.storeEvent(line);
            break;
        case "delete":
            Manager.deleteTasks(line);
            break;
        default:
            throw new DukeException();
        }
    }

}
