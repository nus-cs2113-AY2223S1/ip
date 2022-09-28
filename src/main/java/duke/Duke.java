package duke;

import duke.command.Command;
import duke.command.ExecutedCommand;
import duke.storage.Storage;
import duke.tasks.*;
import duke.parser.Parser;
import duke.ui.UI;

import java.io.IOException;


/**
 * <h1>Duke</h1>
 * Duke is an application that helps users manage their tasks such as Events, To-dos and Deadlines through the CLI.
 *
 * @author Amit Rahman
 */
public class Duke {


    /**
     * Exit command literal
     */
    public static final String BYE = "bye";
    /**
     * Sets up the application state by creatine new instances of TaskList, Storage, UI and Parser.
     */
    private TaskList taskList;
    private final Storage storage;

    private final UI ui;
    private final Parser parser;

    /**
     * Sets up the application state by creatine new instances of TaskList, Storage, UI and Parser.
     * @param filePath file path of where taskList data is to be stored in and accessed from.
     */
    public Duke(String filePath) {
        this.ui = new UI();
        storage = new Storage(filePath);
        parser = new Parser();

        InitializeTaskList initializedTaskList = null;
        try {
            initializedTaskList = storage.initializeTaskList();
        } catch (IOException e) {
            System.out.println("Fatal error creating file.");
        }

        taskList = initializedTaskList.taskList;
        UI.printMessageWithLines(initializedTaskList.initialisationMessage);
    }

    /**
     * Helper function to execute the command and returns acknowledgement message of result of executed command
     *
     * @param command command extracted from parsed user input
     * @return acknowledgement message from executing command
     */

    public static ExecutedCommand executeCommand(Command command) {
        try {
            return command.execute();
        } catch (Exception e) {
            return new ExecutedCommand(e.getMessage(),null,false);
        }
    }

    /**
     *
     * Method to continuously run on a loop while taking in userInput,
     * parse the userInput into commands and command details via Parser, executing command,
     * printing acknowledgement of results of running the command. Then a new taskList is written
     * to storage if there are any updates to taskList data. The loop is exited when the userInput is "bye"
     */
    public void run(){
        ui.printGreeting();
        System.out.println("Please enter your taskList command: (send 'bye' to exit)");
        String userInput = ui.readInput();

        while (!userInput.equals(BYE)) {

            Command command = Parser.parsedCommand(taskList, userInput);

            ExecutedCommand executedCommand = executeCommand(command);

            if (executedCommand.isTaskListChanged()){
                taskList = executedCommand.getTaskList();
                try {
                    Storage.writeToFile(taskList);
                } catch (IOException e){
                    UI.printMessageWithLines("Error writing to file.");
                }
            }

            String executionMessage = executedCommand.getExecutionMessage();
            ui.printMessageWithLines(executionMessage);
            userInput = ui.readInput();


        }
        UI.printGoodbye();
    }

    /**
     * This is the main method which instantiates Duke and executes the run method.
     */
    public static void main(String[] args) {
        new Duke("./data/data.txt").run();
    }

}