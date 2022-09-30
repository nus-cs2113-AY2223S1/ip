package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.parser.CommandParser;
import duke.task.TaskList;

import java.util.Scanner;

import static duke.Ui.printIntroMessage;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Initialises the Duke object and loads the tasks from the default data file.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();
    }

    /**
     * Runs the Duke program until keyword "bye" is entered.
     */
    public void run() {
        printIntroMessage(tasks);
        Scanner sc = new Scanner(System.in);
        String fullCommand;
        boolean isExit = false;
        while (!isExit) {
            fullCommand = sc.nextLine();
            try {
                Command keyword = CommandParser.parseCommand(fullCommand);
                keyword.execute(tasks, storage, ui, fullCommand);
                isExit = keyword.isExit();
            } catch (DukeException e) {
                Ui.printUnknownCommand();
            } 
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}



