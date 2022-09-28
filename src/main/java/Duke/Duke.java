package Duke;

import Duke.Tasks.*;
import Duke.Exceptions.*;
import Duke.Ui.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Duke {
    private static TasksList tasksList = new TasksList();


    public static void main(String[] args) throws EmptyArgumentException, InvalidCommandFormatException, TaskListEmptyException, TaskNumberOutOfBoundsException, IOException, TaskNumberNotNumberException {
        Storage.loadTasksToTasksList(tasksList);
        Ui.printGreeting();
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            String[] inputWords = input.split(" ", 2);
            switch (inputWords[0]) {
            case "bye":
                Ui.printGoodbye();
                System.exit(0);
            case "list":
                tasksList.printTaskList();
                break;
            case "todo":
                tasksList.addTodoTask(inputWords);
                Storage.loadTasktoDataFile(tasksList);
                break;

            case "deadline":
                tasksList.addDeadlineTask(inputWords);
                Storage.loadTasktoDataFile(tasksList);
                break;

            case "event":
                tasksList.addEventTask(inputWords);
                Storage.loadTasktoDataFile(tasksList);
                break;

            case "mark":
                tasksList.doMarkTask(inputWords);
                Storage.updateTaskInDataFile(tasksList, "edit");
                break;

            case "unmark":
                tasksList.doUnmarkTask(inputWords);
                Storage.updateTaskInDataFile(tasksList, "edit");
                break;

            case "delete":
                tasksList.doDeleteTask(inputWords);
                Storage.updateTaskInDataFile(tasksList, "delete");
                break;

            default:
                System.out.println("Please provide a correct command!");
            }
        }
    }
}
