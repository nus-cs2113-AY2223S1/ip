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
    private final Storage storage;
//    public static final String FILEPATH = "./data/data.txt";

    private final UI ui;
    private final Parser parser;


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


    public static ExecutedCommand executeCommand(Command command) {
        try {
            return command.execute();
        } catch (Exception e) {
            return new ExecutedCommand(e.getMessage(),null,false);
        }
    }

    public void run(){
        ui.printGreeting();
        System.out.println("Please enter your taskList command: (send 'bye' to exit)");
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (!userInput.equals(BYE)) {

            Command command = parser.parsedCommand(taskList, userInput);

            ExecutedCommand executedCommand = executeCommand(command);

            if (executedCommand.isTaskListChanged()){
                taskList = executedCommand.getTaskList();
                try {
                    storage.writeToFile(taskList);
                } catch (IOException e){
                    UI.printMessageWithLines("Error writing to file.");
                }
            }

            String executionMessage = executedCommand.getExecutionMessage();
            printFormattedMessage(executionMessage);
            userInput = in.nextLine();

        }
        ui.printGoodbye();
    }
    public static void main(String[] args) {
        new Duke("./data/data.txt").run();
    }

}

//