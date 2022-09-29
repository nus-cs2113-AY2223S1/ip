package Duke;

import Duke.Exceptions.*;

import java.io.IOException;

/**
 * Main class of the Bob bot.
 * Initialises program, starts user interaction, and user can update taskList or data file.
 */
public class Duke {
    private static TaskList taskList = new TaskList();

    /**
     * Runs the program by loading the tasks from data file to taskList,
     * Bob greets the user, and user interacts with Bob until bye command.
     */
    public static void run() throws IOException {
        Storage.loadTasksToTasksList(taskList);
        Ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            String input = Ui.readInput();
            Parser.parseCommand(input, taskList);
        }
    }

    public static void main(String[] args) throws InvalidCommandFormatException, TaskListEmptyException, TaskNumberOutOfBoundsException, IOException, TaskNumberNotNumberException {
        Duke.run();
    }
}
