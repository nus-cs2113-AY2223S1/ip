package duke;

import duke.commands.Command;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath); //create file
        tasks = new TaskList();
        tasks.loadData(storage);
    }

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

