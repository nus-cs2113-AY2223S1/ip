import Exceptions.InvalidCommandException;

import Storage.Storage;
import TaskList.TaskList;

import java.util.Scanner;

import Ui.TextUi;
import Parser.Parser;

public class Duke {
    public static final String DATA_FILE_PATH = "data.txt";
    private TextUi ui;
    private TaskList taskList;


    /** Reads the user command and executes it, until the exit command is given. **/
    public void handleInput() {
        String[] line;
        do {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            line = input.split(" ");

            new Parser().parseCommand(input, line, line[0], TaskList.list);

        } while (!line[0].equals("bye"));
    }

    /** Starts the program by greeting the user with a welcome message and initializing a task list. **/
    public void start() {
        this.ui = new TextUi();
        ui.printWelcomeMessage();

        this.taskList = new TaskList();
    }

    /** Prints a goodbye message when the program is terminating. **/
    public void exit() {
        ui.printGoodbyeMessage();
    }

    /** Loads data from the storage file, and processes user commands until the exit command is issued. **/
    public void runCommandLoop() {
        Storage storage = new Storage();
        storage.populateInitialList(TaskList.list, DATA_FILE_PATH);
        handleInput();
    }

    /** Runs the program until termination. **/
    public void run() {
        start();
        runCommandLoop();
        exit();
    }

    public static void main(String[] args) throws InvalidCommandException {
        new Duke().run();
    }
}
