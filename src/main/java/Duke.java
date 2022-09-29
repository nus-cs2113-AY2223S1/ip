import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * The Duke program take userinput as instruction and do the corresponding
 * command. Duke can add, delete, mark, unmark tasks and save them into a file.
 */
public class Duke {

    public static final String BYE = "bye";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        // load data from the file into program.
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Run the serverc
     */
    public void run() {
        Ui.printWelcomeMessage();
        String userInput = ui.getUserInput();
        while(!userInput.equals(BYE)){
            // Implement user command.
            Parser.implementUserInstruction(tasks.taskList, userInput, false);
            // Store result into the file.
            try {
                Storage.saveTasks(tasks.taskList, "src/main/Contents");
            }
            catch (IOException e){
                System.out.println("Something went wrong: " + e.getMessage());
            }
            userInput = ui.getUserInput();
        }

        Ui.printByeMessage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("src/main/Contents");
        duke.run();
    }

}
