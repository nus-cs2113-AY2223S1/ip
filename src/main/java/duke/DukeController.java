package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to control the program flow of Duke.
 */
public class DukeController {
    private TaskList taskList = new TaskList();
    private final Ui ui = new Ui();
    private final Storage storage = new Storage();
    private final Parser parser = new Parser();
    private String userInput;

    /**
     * Calls Parser class to read user input by line and stores in userInput.
     */
    public void getUserInput(){
        userInput = parser.readInput();
    }

    /**
     * Called when Duke is started.
     * Prints welcome message and read saved data from previous session if any.
     * If it is the first time Duke is run, makes data directory and duke.txt file for saving information.
     */
    public void initialise(){
        storage.setHomePath();
        ui.printNewLine();
        ui.printWelcome();
        ui.printNewLine();
        try {
            storage.makeDirectory();
            taskList = storage.readData();
        } catch (IOException e){
            ui.printExceptionMessage(e);
            ui.printNewLine();
        }
    }

    /**
     * Calls Parser to parse user input.
     * Calls Ui to output appropriate responses to user.
     * Calls TaskList to update taskList when needed.
     * Calls Storage to update save file when needed.
     */
    public void handleUserInput(){
        try {
            String[] parsedInput = parser.parseInput(userInput);
            String userCommand = parsedInput[0];
            switch(userCommand){
            case "bye":
                ui.printGoodbye();
                ui.printNewLine();
                Duke.exitDuke();
                return;
            case "find":
                ArrayList<Task> searchResults = taskList.search(parsedInput[1]);
                ui.printSearchResults(searchResults, parsedInput[1]);
                ui.printNewLine();
                break;
            case "list":
                if (taskList.getTaskList().size() == 0){
                    ui.printTaskListEmpty();
                    ui.printNewLine();
                } else {
                    ui.printTaskListItems(taskList.getTaskList());
                    ui.printNewLine();
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    String[] details = parser.parseTaskInformation(parsedInput[1], userCommand);
                    Task newTask = taskList.generateTask(userCommand, details);
                    taskList.addTask(newTask);
                    ui.printAddTaskSuccess(newTask);
                    ui.printTaskCount(taskList.getTaskList().size());
                    ui.printNewLine();
                    storage.saveData(taskList);
                } catch (IllegalInputException | IOException e) {
                    ui.printExceptionMessage(e);
                    ui.printNewLine();
                }
                break;
            case "delete":
            case "mark":
            case "unmark":
                try {
                    int taskIndex = parser.parseTaskIndex(parsedInput[1], userCommand);
                    if (userCommand.equals("mark") || userCommand.equals("unmark")) {
                        taskList.updateTaskStatus(taskIndex, userCommand);
                        ui.printUpdateTaskSuccess(taskList.getTaskList().get(taskIndex-1), userCommand);
                        ui.printNewLine();
                    } else {
                        Task taskDeleted = taskList.deleteTask(taskIndex);
                        ui.printDeleteTaskSuccess(taskDeleted);
                        ui.printTaskCount(taskList.getTaskList().size());
                        ui.printNewLine();
                    }
                    storage.saveData(taskList);
                } catch (IllegalInputException | IOException e){
                        ui.printExceptionMessage(e);
                        ui.printNewLine();
                }
                break;
            default:
                ui.printNewLine();
            }
        } catch (IllegalInputException e) {
            ui.printExceptionMessage(e);
            ui.printNewLine();
        }
    }

}
