package duke;

import duke.task.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final UI ui = new UI();

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int numberOfTasks = 0;

    public static void run(String filePath) {
        Storage storage = new Storage(filePath, tasks, numberOfTasks);

        //Read tasks from the file and store them in the tasks array
        try {
            storage.readFile();
            tasks = storage.getTasks();
            numberOfTasks = storage.getNumberOfTasks();
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
            Parser task = new Parser(userInput, filePath, tasks, numberOfTasks);
            try {
                task.performAction(userInput);
                tasks = task.getTasks();
                numberOfTasks = task.getNumberOfTasks();
            } catch (DukeException e) {
                ui.printUnrecognizedCommandError();
            }
        }

        ui.printExitMessage();
    }

    public static void main(String[] args) {
        run("duke.txt");
    }
}
