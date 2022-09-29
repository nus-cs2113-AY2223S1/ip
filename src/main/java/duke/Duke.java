package duke;

import duke.commands.Command;

/**
 * The main class containing the skeletal steps to run the Duke
 */

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor that create objects necessary for the program.
     * After creating TaskList, load the history data present data file into the list.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath); //create file
        tasks = new TaskList();
        tasks.loadData(storage);
    }

    /**
     * First, printing a opening message. Then, run the code through a loop until the exit point is reached.
     * The loop represents the routine that run through the code every time for each input.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readInput();
            ui.printDivider();
            Command c = Parser.parserCommand(input);
            if (c != null) {
                c.execute(tasks, storage);
                isExit = c.checkExit();
            }
            ui.printDivider();
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}

