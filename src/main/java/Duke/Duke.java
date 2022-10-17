package Duke;

import java.io.IOException;

public class Duke {
    /**
     * Runs all the necessary code. First, it prints the welcome message, then initializes the file, and then accepts commands
     * @throws IOException
     */
    void run() throws IOException {
        UI ui = new UI();
        Storage storage = new Storage();
        ui.welcomeMessage();
        TaskList taskList = new TaskList();
        storage.initializeFile(taskList);
        ui.acceptCommands(taskList, storage);
    }
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        duke.run();
    }
}
