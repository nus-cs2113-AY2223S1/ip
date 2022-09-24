package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    private static UI ui = new UI();
    private static Storage storage;

    public ArrayList<Task> tasks;
    public int numberOfTasks = 0;

    public static void run(String filePath) {
        storage = new Storage(filePath);

        try {
            storage.readFile();
        } catch (FileNotFoundException e) {
            ui.printLoadingError();
        }

        ui.printGreeting();

        String userInput = "";

        while (!userInput.equals("bye")) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            Parser task = new Parser(userInput, filePath);
            try {
                task.performAction(userInput);
            } catch (DukeException e) {
                ui.printUnrecognizedCommandError();
            }
        }

        ui.printExitMessage();
    }

    public static void main(String[] args) {
        run("files/duke.txt");
    }
}
