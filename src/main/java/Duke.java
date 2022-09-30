import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Initializes the program
     */
    public Duke () {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
        ui.showWelcomeMessage();

    }

    /**
     * Runs the program
     */
    public void run() {
        Parser parser = new Parser();
        while(true) {
            String input = ui.getCommand();
            try {
                String output =  parser.parseCommand(input, tasks);
                if (output != null) {
                    ui.displayOutput(output);
                } else {
                    break;
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbyeMessage();
        try {
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
