import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws IOException {
        String userInput;
        String taskType;
        String userInputNewValue;

        int markedValue = 0;

        boolean bye = false;

        Commands commandType;

        ArrayList<Task> todoList;
        Parser parser = new Parser();
        Ui ui = new Ui();
        Storage storage = new Storage();

        ui.greet();

        Scanner myObj = new Scanner(System.in);

        Path path = Paths.get("src","main","data");

        storage.createDirectories(path);
        todoList = storage.readInPreviousTasks(path);

        TaskList taskList = new TaskList(todoList);

        while (!bye) {
            userInput = myObj.nextLine() + " ";
            taskType = parser.TaskType(userInput);

            userInputNewValue = parser.UserInputNewValue(userInput, taskType);

            try {
                markedValue = parser.MarkedValue(userInput);
            } catch (Exception ignored) {}

            try {
                commandType = parser.CommandType(taskType);
                switch (commandType) {
                case LIST:
                    taskList.printTasks();
                    break;
                case BYE:
                    bye = true;
                    break;
                case MARK:
                    taskList.markAsDone(markedValue);
                    break;
                case UNMARK:
                    taskList.markAsUndone(markedValue);
                    break;
                case TODO:
                    try {
                        Task newTask = new Todo(userInputNewValue);

                        taskList.addNewTask(newTask);
                    } catch (DukeExceptions e){
                        ui.printLines();
                        System.out.println("TODO needs description");
                        ui.printLines();
                    }
                    break;
                case DEADLINE:
                    if (userInputNewValue.contains("/by")) {
                        Task newDeadline = new Deadline(userInputNewValue);
                        taskList.addNewTask(newDeadline);
                    } else {
                        System.out.println("Input did not contain the correct identifation for deadline");
                    }

                    break;
                case EVENT:
                    if (userInputNewValue.contains("/at")) {
                        Task newEvent = new Event(userInputNewValue);
                        taskList.addNewTask(newEvent);
                    } else {
                        System.out.println("Input did not contain the correct identifation for event");
                    }
                    break;
                case DELETE:
                    taskList.deleteFromList(markedValue);
                    break;
                case FIND:
                    taskList.findTasks(userInputNewValue);
                    break;
                }
            } catch (Exception e) {
                ui.printUiException();
            }
        }
        ui.sayBye();
        Storage.writeToFile(path, todoList);
    }
}

