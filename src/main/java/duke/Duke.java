package duke;

import duke.ui.Ui;
import duke.ui.parser.*;
import duke.io.Storage;

public class Duke {

    private static Ui ui;
    private static TaskList taskList;
    private static Storage storage;

    public Duke(String dataFileName, String dataDirectoryName) {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(dataFileName, dataDirectoryName);
    }

    public static void main(String[] args) {
        new Duke("dukeData.txt", "data").run();
    }

    public void run() {
        ui.showWelcomeMessage();

        taskList.setTaskData(storage.loadProgramData());
        boolean commandIsQuit = false;

        while (!commandIsQuit) {
            String input = ui.readInput();

            try {
                Command command = Parser.parse(input);
                String output = taskList.executeCommand(command);
                ui.printOutput(output);
                storage.saveProgramData(taskList.getTaskData());

                if (command instanceof CommandExit) {
                    commandIsQuit = true;
                }

            } catch (Exception e) {
                ui.printOutput(e.toString());
            }
        }
    }
}
