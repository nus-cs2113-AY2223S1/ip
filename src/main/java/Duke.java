import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class Duke {

    private static final String DEFAULT_FILE_PATH = System.getProperty("user.dir")
            + File.separator + "Saves" + File.separator +"Tasks";
    private final UI ui;
    private TaskList tasks = new TaskList(new ArrayList<>());
    private final Storage storage;

    /**
     * construct a duke class that contain key functions of software
     *
     * @param filePath path of saved data file
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadSave());
        } catch (IOException e) {
            System.out.format("Create new saved file failed!%n");
        }
    }

    /**
     * This method illustrates the process of how duke run with each line of input
     */
    public void run() {
        ui.greet();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = "";
            try {
                fullCommand = ui.readCommand();
                ui.separateLine();
                Command c = Parser.parseCommand(fullCommand);
                isExit = tasks.execute(c);
                storage.writeSave(tasks.getTasks());
                if (isExit) {
                    ui.bye();
                }
            } catch (UnknownCommandException e) {
                System.out.format("Exception: Unknown command%n" +
                        "%s is not a valid command%n", fullCommand.split(" ")[0]);
            } catch (NullCommandException e) {
                System.out.format("Exception: Null command%n" +
                        "No command is entered%n");
            } finally {
                ui.separateLine();
            }
        }

    }

    public static void main(String[] args) {
        new Duke(DEFAULT_FILE_PATH).run();
    }
}
