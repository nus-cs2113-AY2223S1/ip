package duke;

import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) {
        new Duke().run();
    }


    public void run() {
        start();
        runProgrammeUntilExitCommand();
        exit();
    }

    private void start() {
        this.ui = new Ui();
        this.storage = new Storage();
        storage.createFile();
        ui.printWelcomeMessage();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void runProgrammeUntilExitCommand() {
        Command command;
        do {
            Parser parser = new Parser();
            String userCommandText = ui.getUserCommand();
            command = parser.parseCommand(userCommandText);
            tasks.runTaskCommand(command, userCommandText);
        } while (command != Command.EXIT);
    }

    private void exit() {
        ui.printGoodbyeMessage();
        System.exit(0);
    }


}
