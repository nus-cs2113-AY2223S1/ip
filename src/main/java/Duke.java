import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private ArrayList<String> tasks;
    private ArrayList<String> marks;
    private ArrayList<String> types;

    private Ui ui;
    private Integer count;

    public Duke(String filePath) {
        ui = new Ui();
        //Create a new file if not created
        storage = new Storage(filePath);
        try {
            // Load respective lists
            storage.load();
            types = storage.getTypes();
            marks = storage.getMarks();
            tasks = storage.getTasks();
            count = storage.getCount();
        } catch (Exception e) {
            ui.showLoadingError();
            types = new ArrayList<String>();
            marks = new ArrayList<String>();
            tasks = new ArrayList<String>();
            count = 0;
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(types, marks, tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                storage.updateFile(storage.getCount(), tasks, marks, types);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}