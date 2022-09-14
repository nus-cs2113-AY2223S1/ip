package duke.command;

import duke.task.Task;

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
            readFile("data/duke.txt", tasks);
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
            writeToFile("data/duke.txt", tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }


    }
}



