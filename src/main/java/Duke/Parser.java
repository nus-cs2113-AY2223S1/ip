package Duke;

import java.io.IOException;

public class Parser {
    public static void parseCommand(String input, TaskList taskList) throws IOException {
        String[] commandAndParams = input.split(" ", 2);
        String command = commandAndParams[0];
        switch (command) {
        case "bye":
            Ui.printGoodbye();
            System.exit(0);
        case "list":
            Ui.printTaskList();
            break;
        case "todo":
            taskList.addTodoTask(commandAndParams);
            Storage.loadTasktoDataFile(taskList);
            break;

        case "deadline":
            taskList.addDeadlineTask(commandAndParams);
            Storage.loadTasktoDataFile(taskList);
            break;

        case "event":
            taskList.addEventTask(commandAndParams);
            Storage.loadTasktoDataFile(taskList);
            break;

        case "mark":
            taskList.doMarkTask(commandAndParams);
            Storage.updateTaskInDataFile(taskList, "edit");
            break;

        case "unmark":
            taskList.doUnmarkTask(commandAndParams);
            Storage.updateTaskInDataFile(taskList, "edit");
            break;

        case "delete":
            taskList.doDeleteTask(commandAndParams);
            Storage.updateTaskInDataFile(taskList, "delete");
            break;

        case "find":
            taskList.findTask(commandAndParams);
            break;

        default:
            System.out.println("Please provide a correct command!");
        }
    }
}
