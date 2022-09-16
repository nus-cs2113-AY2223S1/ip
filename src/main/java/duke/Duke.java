package duke;

import java.io.IOException;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents the main program. It contains an instance of the Storage class to load data from the user's disk
 * and save data to the user's disk according to the changes made. It also contains an instance of the TaskList class to function
 * as a task manager for the user. The instantiation of the Ui class is to facilitate user interaction.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs an instance of the Duke class. The instance will contain an Ui object for interaction with the user
     * and a Storage object to load previously saved data and save new changes made to the data on the user's local disk.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = storage.loadFile();
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Serves as the backbone of the entire program, reading in user commands through the Ui object instantiated
     * earlier in the Duke constructor and instantiating a Parser object to interpret and execute user commands.
     * The function terminates when an exit signal is indicated by the user.
     */
    public void run() {
        ui.greetUser();
        Parser parser = new Parser(ui);
        boolean isProgramFinished = false; //variable to indicate if the program should be terminated
        while(!isProgramFinished) {
            String command = ui.getUserCommand();
            parser.parse(command, taskList);
            isProgramFinished = parser.getTerminationStatus();
        }
        ui.sayByeToUser();
    }

    /**
     * Represents the entry point to the program.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
