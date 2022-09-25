import consoleCommands.ConsoleCommands;
import exception.TaskDoesNotExistException;
import exception.InvalidCommandException;
import exception.InvalidFileDataException;
import exception.InvalidArgumentsException;
import exception.NotEnoughArgumentsException;
import task.Task;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * End programme: bye
 * List tasks: List
 * Add todo: todo (name)
 * Add deadline: deadline (name) /by (time)
 * Add event: event (name) /at (time)
 * Mark/Unmark: mark/unmark (task number)
 * Delete: delete (task number)
 */

public class Duke {
    public static final String COMMAND_UNMARKED = "unmark";
    public static final String COMMAND_MARKED = "mark";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DELETE = "delete";
    public static final String TASK_DOES_NOT_EXIST_ERROR_MESSAGE = "Task does not exist. Please try again.";
    public static final String COMMAND_DOES_NOT_EXIST_ERROR_MESSAGE = "Invalid command. Please try again.";
    public static final String INVALID_ARGUMENTS_ERROR_MESSAGE = "Invalid arguments detected. Please try again.";
    public static final String NOT_ENOUGH_ARGUMENTS_ERROR_MESSAGE = "Not enough arguments entered. Please try again.";
    public static final String INVALID_FILE_DATA_ERROR_MESSAGE = "Invalid file data detected. File data will not be read.";
    public static final String filePath = "data/duke.txt";
    public static final String tempFilePath = "data/temp.txt";

    public static void main(String[] args) throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        try {
            ConsoleCommands.start(filePath, taskList);
        } catch (InvalidFileDataException e) {
            System.out.println(INVALID_FILE_DATA_ERROR_MESSAGE);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        while (in.hasNextLine()) {
            String input = in.nextLine();
            if (input.equals(COMMAND_BYE)) {
                try {
                    ConsoleCommands.end(filePath, tempFilePath, taskList);
                } catch (FileNotFoundException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                break;
            } else if (input.equals(COMMAND_LIST)) {
                ConsoleCommands.printList(taskList);
            } else if (input.contains(COMMAND_MARKED) || input.contains(COMMAND_UNMARKED)) {
                try {
                    ConsoleCommands.markStatus(input, taskList);
                } catch (TaskDoesNotExistException e) {
                    System.out.println(TASK_DOES_NOT_EXIST_ERROR_MESSAGE);
                    ConsoleCommands.printLine();
                }
            } else if (input.contains(COMMAND_DELETE)) {
                try {
                    ConsoleCommands.deleteTask(input, taskList);
                } catch (TaskDoesNotExistException e) {
                    System.out.println(TASK_DOES_NOT_EXIST_ERROR_MESSAGE);
                    ConsoleCommands.printLine();
                }
            } else {
                try {
                    ConsoleCommands.addTask(input, taskList);
                } catch (InvalidArgumentsException e) {
                    System.out.println(INVALID_ARGUMENTS_ERROR_MESSAGE);
                    ConsoleCommands.printLine();
                } catch (InvalidCommandException e) {
                    System.out.println(COMMAND_DOES_NOT_EXIST_ERROR_MESSAGE);
                    ConsoleCommands.printLine();
                } catch (NotEnoughArgumentsException e) {
                    System.out.println(NOT_ENOUGH_ARGUMENTS_ERROR_MESSAGE);
                    ConsoleCommands.printLine();
                }
            }
        }
    }
}
