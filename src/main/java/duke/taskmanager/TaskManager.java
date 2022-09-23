package duke.taskmanager;

import duke.UI;
import duke.exceptions.DukeException;
import duke.taskmanager.tasks.Task;
import duke.taskmanager.tasks.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    public static StringBuilder toSave = new StringBuilder();

    public static ArrayList<Task> tasks = new ArrayList<>() {
        {
            add(new Todo("Todo buffer for one based input", ' '));
        }
    };

    private Storage storage;
    private final UI ui;
    private Tasklist tasks1;

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
                tasks1 = new Tasklist(storage.storedTasks);
            } catch (DukeException e) {
                ui.showLoadingError();
                tasks1 = new Tasklist();
            }
        } else {
            tasks1 = new Tasklist();
        }
    }

    public void receiveCommands() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine().trim();
        while (!command.equals("bye")) {
            boolean isList = command.equals("list");
            if (isList) {
                ui.printList(tasks1);
            } else {
                tasks1.tryCommand(command);
            }
            command = in.nextLine().trim();
        }
        try {
            storage.saveTasks(toSave);
        } catch (IOException e) {
            System.out.println("I/O error...");
        }
        ui.printExitMessage();
    }

}