import java.io.FileNotFoundException;

/**
 * Duke is a task tracking application that can read user inputs and execute the commands
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Reads from local txt file for existing TaskList, and creates a new file if it doesn't exist
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList();
            Storage.getFileContents(filePath, tasks);
        } catch (FileNotFoundException e) {
            Ui.showFileWriteError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the application till user ends it by the input "bye"
     */
    private void run() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandInput = ui.readCommand();
                ui.showDivider();
                Command command = new Command(Parser.parse(commandInput));
                isExit = command.executeCommand(tasks, commandInput);
            } catch (Exception e) {
                ui.showError("Invalid command! Try again :)");
            } finally {
                System.out.println();
            }
        }
    }

    /**
     * Entry point for Duke
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }




}
