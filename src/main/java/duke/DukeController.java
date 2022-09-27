package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class DukeController {
    private TaskList taskList = new TaskList();
    private final Ui ui = new Ui();
    private final Storage storage = new Storage();
    private final InputParser inputParser = new InputParser();
    private String userInput;
    public void getUserInput(){
        userInput = inputParser.readInput();
    }
    public void initialise(){
        storage.setHomePath();
        ui.printWelcome();
        try {
            taskList = storage.readData();
        } catch (FileNotFoundException e){
            ui.printExceptionMessage(e);
        }
    }
    public void handleUserInput(){
        try {
            String[] parsedInput = inputParser.parseInput(userInput);
            String userCommand = parsedInput[0];
            switch(userCommand){
            case "bye":
                ui.printGoodbye();
                Duke.exitDuke();
                return;
            case "list":
                if (taskList.getTaskList().size() == 0){
                    ui.printTaskListEmpty();
                } else {
                    ui.printTaskListItems(taskList.getTaskList());
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    String[] details = inputParser.parseTaskInformation(parsedInput[1], userCommand);
                    Task newTask = taskList.generateTask(userCommand, details);
                    taskList.addTask(newTask);
                    ui.printAddTaskSuccess(newTask);
                    ui.printTaskCount(taskList.getTaskList().size());
                    storage.saveData(taskList);
                } catch (IllegalInputException | IOException e) {
                    ui.printExceptionMessage(e);
                }
                break;
            case "delete":
            case "mark":
            case "unmark":
                try {
                    int taskIndex = inputParser.parseTaskIndex(parsedInput[1], userCommand);
                    if (userCommand.equals("mark") || userCommand.equals("unmark")) {
                        taskList.updateTaskStatus(taskIndex, userCommand);
                        ui.printUpdateTaskSuccess(taskList.getTaskList().get(taskIndex-1), userCommand);
                    } else {
                        Task taskDeleted = taskList.deleteTask(taskIndex);
                        ui.printDeleteTaskSuccess(taskDeleted);
                        ui.printTaskCount(taskList.getTaskList().size());
                    }
                    storage.saveData(taskList);
                } catch (IllegalInputException | IOException e){
                        ui.printExceptionMessage(e);
                }
                break;
            default:
                ui.printNewLine();
            }
        } catch (IllegalInputException e) {
            ui.printExceptionMessage(e);
        }
    }

}
