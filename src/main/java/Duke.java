import Exceptions.InvalidCommandException;

import Storage.Storage;
import Tasks.Task;

import java.util.Scanner;
import java.util.ArrayList;

import Ui.TextUi;
import Parser.Parser;

public class Duke {
    public static final String DATA_FILE_PATH = "data.txt";
    public static ArrayList<Task> list = new ArrayList<Task>();
    private TextUi ui;

    public void handleInput() {
        String[] line;
        do {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            line = input.split(" ");

            new Parser().parseCommand(input, line, line[0], list);

        } while (!line[0].equals("bye"));
    }

    public void start() {
        this.ui = new TextUi();
        ui.printWelcomeMessage();
    }

    public void exit() {
        ui.printGoodbyeMessage();
    }

    public void runCommandLoop() {
        Storage storage = new Storage();
        storage.populateInitialList(list, DATA_FILE_PATH);
        handleInput();
    }

    public void run() {
        start();
        runCommandLoop();
        exit();
    }

    public static void main(String[] args) throws InvalidCommandException {
        new Duke().run();
    }
}
