import java.io.FileNotFoundException;

public class Duke {
    public static final int MAX = 100;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }




}
