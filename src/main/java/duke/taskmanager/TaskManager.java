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
     * Manages all the tasks in one <code>Tasklist</code>. It will check if there is any previously saved tasks and enter those
     * tasks into the <code>Tasklist</code>.
     */
    public TaskManager() {
        ui = new UI();
        boolean haveSavedTasks = true;
        try {
            storage = new Storage();
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundException();
            haveSavedTasks = false;
            storage = new Storage("no saved tasks");
        }
        ui.printGreetingMessage();
        if (haveSavedTasks) {
            try {
                tasks = new TaskList(storage.storedTasks);
            } catch (DukeException e) {
                ui.showLoadingError();
                tasks = new TaskList();
            }
        } else {
            tasks = new TaskList();
        }
    }

    /**
     * Keeps on receiving user commands until the user closes the programme. Once the user closes the programme,
     * the <code>Task</code>s in the <code>Tasklist</code> will be saved.
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