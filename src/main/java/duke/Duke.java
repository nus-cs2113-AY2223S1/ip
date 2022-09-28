package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Represent the chat bot Duke. The application starts in this class.
 * Duke can interact with users through the user interface, and maintain the task list through different commands.
 */
public class Duke {
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Constructor of <code>Duke</code> class.
     * It initializes the user interface and the task list.
     */
    public Duke() {
        ui = new Ui();
        taskList = new TaskList(ui);
    }

    /**
     * Start running the application. The entire flow of application is maintained here.
     */
    public void run() {
        ui.showLogo();
        ui.showGreetMessage();

        while (true) {
            String userCommand = ui.readCommand();
            Command command = Parser.parseCommand(userCommand, ui);
            command.execute(taskList, ui);
            if (command.isExit()) {
                break;
            }
        }

        ui.showExitMessage();
    }

    /**
     * Main function of application
     * @param args Optional arguments to be inputted through the command line.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
