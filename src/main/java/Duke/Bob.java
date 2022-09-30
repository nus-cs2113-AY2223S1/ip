package Duke;

import Duke.Exceptions.*;

import java.io.IOException;

/**
 * Main class of the Bob bot.
 * Initialises program, starts user interaction, and user can update taskList or data file.
 */
public class Bob {
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
            try {
                String input = Ui.readInput();
                Parser.parseCommand(input, taskList);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please write your commands to Bob again.");
                Ui.printHorizontalLine();
            }
        }
    }

    public static void main(String[] args) throws InvalidCommandFormatException, TaskListEmptyException, TaskNumberOutOfBoundsException, IOException, TaskNumberNotNumberException {
        Bob.run();
    }
}
