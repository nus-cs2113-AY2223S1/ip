package duke.taskmanager;

import duke.UI;
import duke.exceptions.DukeException;
import duke.taskmanager.commands.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * A Task Manager to keep track of all the tasks that user supplies to it
 */
public class TaskManager {
    public static StringBuilder toSave = new StringBuilder();

    private Storage storage;
    private final UI ui;
    private TaskList tasks;

    /**
     * Manages all the tasks in one <code>TaskList</code>. It will check if there is any previously saved tasks and enter those
     * tasks into the <code>TaskList</code>.
     */
    public TaskManager() {
        ui = new UI();
        tasks = new TaskList();
        try {
            storage = new Storage();
            tasks = new TaskList(storage);
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundException();
            storage = new Storage("no saved tasks");
        } catch (DukeException e) {
            ui.showLoadingError();
        }
        ui.printGreetingMessage();
    }

    /**
     * Keeps on receiving user commands until the user closes the programme. Once the user closes the programme,
     * the <code>Task</code>s in the <code>TaskList</code> will be saved.
     */
    public void receiveCommands() {
        Scanner in = new Scanner(System.in);
        String userInput;
        boolean isExit = false;
        while (!isExit) {
            userInput = in.nextLine().trim();
            Command c = Parser.parser(userInput);
            c.execute(tasks,ui,storage);
            isExit = c.isExit;

        }
        try {
            storage.saveTasks(toSave);
        } catch (IOException e) {
            System.out.println("I/O error...");
        }
    }

}