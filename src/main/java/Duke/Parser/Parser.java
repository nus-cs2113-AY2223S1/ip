package Duke.Parser;

import Duke.Exceptions.UnknownCommandException;
import Duke.Storage.Storage;
import Duke.Tasks.TaskManager;

import java.io.IOException;

public class Parser {
    static final boolean EXIT = false;
    static final boolean CONTINUE = true;
    public static boolean parseCommand(String command) {

        String[] splitCommand = command.split(" ");
        switch (splitCommand[0]) {
        case "bye":
            try {
                Storage.writeToFile();
            } catch (IOException e) {
                System.out.println("Error inputting data to file");
            }
            return EXIT;
        case "mark":
            TaskManager.markTask(command);
            break;
        case "unmark":
            TaskManager.unmarkTask(command);
            break;
        case "list":
            TaskManager.printList();
            break;
        case "todo":
            TaskManager.addToDo(command);
            break;
        case "event":
            TaskManager.addEvent(command);
            break;
        case "deadline":
            TaskManager.addDeadline(command);
            break;
        case "delete":
            TaskManager.deleteTask(command);
            break;
        case "find":
            TaskManager.findTask(command);
            break;
        default:
            UnknownCommandDetection();
            break;
        }

        return CONTINUE;
    }

    private static void UnknownCommandDetection() {
        try {
            throw new UnknownCommandException();
        } catch (UnknownCommandException e) {
            e.UnknownCommandMessage();
        }
    }
}
