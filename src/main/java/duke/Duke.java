package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents the main class of the program.
 */
public class Duke {
    private static final String QUIT_FLAG = "quit";

    /**
     * Main method for the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TaskList taskList = new TaskList();

        runInitialLoad(taskList);
        runTaskProgram(in, taskList);
        runClosingLoad(taskList);

        in.close();
    }

    /**
     * Saves and says goodbye to the user.
     *
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     */
    private static void runClosingLoad(TaskList taskList) {
        try {
            Storage.saveData(taskList);
            Ui.printResponseToUser(Printables.SUCCESSFUL_SAVE_MESSAGE);
        } catch (IOException e) {
            Ui.printResponseToUser(Printables.FAIL_SAVE_MESSAGE);
        }

        Ui.printResponseToUser(Printables.GOODBYE_MESSAGE);
    }

    /**
     * Runs command request and gives user response through the Ui.
     *
     * @param in Scanner object to take in user input.
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     */
    private static void runTaskProgram(Scanner in, TaskList taskList) {
        String command;
        String response;
        while (true) {
            command = Ui.getCommandFromUser(in);
            response = Parser.runCommand(command, taskList);

            if (Objects.equals(response, QUIT_FLAG)) {
                return;
            }
            Ui.printResponseToUser(response);

            try {
                Storage.saveData(taskList);
            } catch (IOException e) {
                Ui.printResponseToUser(Printables.FAIL_SAVE_MESSAGE);
            }
        }
    }

    /**
     * Loads previous data if any and says hello to the user.
     *
     * @param taskList A task manager that contains the list of tasks and other task list related methods.
     */
    private static void runInitialLoad(TaskList taskList) {
        Ui.printResponseToUser(Printables.INITIAL_GREETING);

        try {
            Ui.printResponseToUser(Storage.loadData(taskList));
        } catch (FileNotFoundException e) {
            Ui.printResponseToUser(Printables.MISSING_FILE_MESSAGE);
        }
    }
}