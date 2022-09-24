package duke.command;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

import static duke.command.Command.*;

import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            readFile("./data/duke.txt", tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        untilBye:
        while (true) {
            try {
                String line = in.nextLine();
                String[] parsedInput = line.split(" ");
                switch (parsedInput[0]) {
                case "list":
                    printTaskList(tasks);
                    break;
                case "mark":
                    tryMarkTask(tasks, line);
                    break;
                case "unmark":
                    tryUnmarkTask(tasks, line);
                    break;
                case "todo":
                    tryAddTodo(tasks, line);
                    break;
                case "deadline":
                    tryAddDeadline(tasks, line);
                    break;
                case "event":
                    tryAddEvent(tasks, line);
                    break;
                case "bye":
                    printByeMessage();
                    break untilBye;
                case "delete":
                    tryDeleteTask(tasks, parsedInput);
                    break;
                default:
                    throw new DukeException();
                    // Fallthrough
                }
            } catch (DukeException e) {
                printHorizontalLine();
                System.out.println("     T_T OOPS!!! I'm sorry, but I don't know what that means :-(");
                printHorizontalLine();
            }
        }

        try {
            writeToFile("./data/duke.txt", tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                String[] parsedInput = line.split(" ");

                switch (parsedInput[0]) {
                case "list":
                    printTaskList(tasks);
                    break;
                case "mark":
                    tryMarkTask(tasks, line);
                    break;
                case "unmark":
                    tryUnmarkTask(tasks, line);
                    break;
                case "todo":
                    tryAddTodo(tasks, line);
                    break;
                case "deadline":
                    tryAddDeadline(tasks, line);
                    break;
                case "event":
                    tryAddEvent(tasks, line);
                    break;
                case "bye":
                    printByeMessage();
                    break untilBye;
                case "delete":
                    tryDeleteTask(tasks, parsedInput);
                    break;
                default:
                    throw new DukeException();
                    // Fallthrough
                }
            } catch (DukeException e) {
                printHorizontalLine();
                System.out.println("     T_T OOPS!!! I'm sorry, but I don't know what that means :-(");
                printHorizontalLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}



