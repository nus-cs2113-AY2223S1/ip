import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.ToDo;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Duke {

    public static final String BYE = "bye";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Duke duke = new Duke("src/main/Contents");
        Ui.printWelcomeMessage();
        String userInput = ui.getUserInput();
        while(!userInput.equals(BYE)){
            Parser.implementUserInstruction(duke.tasks.taskList, userInput, false);
            userInput = ui.getUserInput();
        }

        Ui.printByeMessage();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
