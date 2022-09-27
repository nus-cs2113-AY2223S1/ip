package duke;

import duke.commands.Command;

/**
 * This is the main class that starts the program and runs the main logic of accepting input and responding in kind.
 *
 * @author Dhanish
 */
public class Duke {

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;
    private static final int NORMAL_EXIT_VALUE = 0;

    /**
     * Constructor that creates necessary objects for program function, such as {@code Ui}, {@code taskList} and {@code storage}.
     */
    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage();
        storage.loadData(taskList);
    }

    /**
     * Main method that creates a {@code Duke} object and starts the execution of the main part of the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.acceptAndValidateInput();
            Command command = Parser.parse(input);
            if (command != null) {
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            }
        }
        exit(0);
    }

    /**
     * Method used to terminate the program.
     *
     * @param status - number used to indicate whether program terminates peacefully or abruptly and print output accordingly
     */
    public static void exit(int status) {
        Ui.printLine();
        if (status == NORMAL_EXIT_VALUE) {
            System.out.println("\t" + "Bye. Hope to see you again soon!");
        } else {
            System.out.println("Terminating due to errors!");
        }
        Ui.printLine();
        System.exit(status);
    }
}
