import java.io.*;
import java.util.Scanner;
import duke.error.DukeException;
import duke.main.*;
import duke.tasks.*;
import java.util.ArrayList;

public class Duke {
    static int COMMAND_INDEX = 0;
    static String FILE_PATH = "./duke.txt";
    static String FILE_SEPARATOR = "-";
    static String LINE_DIVIDER = "/";
    static String COMMAND_WORD_EXIT = "bye";

    // class methods
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
        Ui.greet();
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        String command = line.split(" ")[COMMAND_INDEX];

        while (!command.matches(COMMAND_WORD_EXIT)) {
            Parser.parse(line);
            line = input.nextLine();
            command = line.split(" ")[COMMAND_INDEX];
        }

        Ui.exit();
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}