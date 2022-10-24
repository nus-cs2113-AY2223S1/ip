package Duke;

import Duke.Tasks.TaskManager;
import Duke.Storage.Storage;
import Duke.UI.UI;
import Duke.Parser.Parser;

import java.io.IOException;
import java.util.Scanner;


/**
 * Duke is program that allows users to manage a tasklist on the command line interface.
 * It stores 3 types of tasks : todo, event and deadline
 *
 * @version 1.0
 * @author: Rachel Fong
 * @since 2022-09-30
 */
public class Duke {
    private final UI ui;
    private final Parser parser;
    private static Storage storage = null;
    static TaskManager tasks = new TaskManager();

    /**
     * Constructor to initiate Storage, Parse and UI objects.
     */
    public Duke() {
        this.ui = new UI();
        this.parser = new Parser();
        storage = new Storage();
    }

    /**
     * Runs main Duke program.
     */
    public void run() {
        UI.WelcomeMessage();
        try {
            storage.loadData(tasks);
        } catch (IOException e) {
            UI.loadingFileError();
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (Parser.parseCommand(command) == false) {
                break;
            }
        }
        UI.GoodbyeMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }


}


