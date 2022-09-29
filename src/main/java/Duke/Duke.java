package Duke;

import java.io.FileNotFoundException;

/**
 * Duke is a task tracking application using Command Line Interface.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private String filePath = "data/duke.txt";

    /**
     * Initialises application and reads existing data from local file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList();
            storage.getFileContents(filePath, taskList.list);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            taskList = new TaskList();
        }
    }

    /**
     * Runs the application, taking in user input until "bye" command is issued.
     */
    public void run() {
        ui.sayHi();
        while (!taskList.isLast) {
            try {
                String userInput = ui.readInputs();
                String commandType = Parser.commandType(userInput);
                String commandActual = Parser.commandActual(userInput);
                taskList.executeTask(commandType, commandActual, filePath);
            } catch (IllegalCommand e) {
                ui.showError();
            } finally {
                ui.drawLine();
            }
        }
        ui.sayBye();
    }

    /**
     * Main function to run application.
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
