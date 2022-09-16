package duke;

/**
 * TO DO:
 * Update EXPECTED.TXT
 */

import duke.command.Command;
import duke.command.ExecutedCommand;
import duke.storage.Storage;
import duke.tasks.*;
import duke.parser.Parser;
import duke.ui.UI;

import java.io.IOException;
import java.util.Scanner;

public class Duke {


    /**
     * Commands
     */
    public static final String BYE = "bye";


    /**
     * Helper function to format message to be printed
     */
    public static final String DIVIDER = "______________________________________";

    public static void printFormattedMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);

    }

    // Duke attributes

    private TaskList taskList;
    private Storage storage;
    private UI ui;
    private Parser parser;


    public Duke() {
        this.ui = new UI();
        storage = new Storage();
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


    public static ExecutedCommand executeCommand(Command command) {
        try {
            return command.execute();
        } catch (Exception e) {
            return new ExecutedCommand(e.getMessage(),null,false);
        }
    }

    public static void main(String[] args) {

        Duke duke = new Duke();
        // Print greeting message
        UI.printGreeting();

        // Initialise instance of duke.tasks.TaskList
        TaskList taskList = new TaskList();

        System.out.println("Please enter your taskList command: (send 'bye' to exit)");
        Scanner in = new Scanner(System.in);

        while (true) {
            String userInput = in.nextLine();
            Command command = Parser.parsedCommand(duke.taskList, userInput);

            ExecutedCommand executedCommand = executeCommand(command);

            if (executedCommand.isTaskListChanged()){
                duke.taskList = executedCommand.getTaskList();
                try {
                    duke.storage.writeToFile(duke.taskList);
                } catch (IOException e){
                    UI.printMessageWithLines("Error writing to file.");
                }
            }

            String executionMessage = executedCommand.getExecutionMessage();
            printFormattedMessage(executionMessage);

            if (userInput.equals(BYE)) {
                break;
            }
        }
        UI.printGoodbye();
    }

}

