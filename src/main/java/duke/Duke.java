package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        runInitialLoad(taskManager);
        runTaskProgram(in, taskManager);
        runClosingLoad(taskManager);

        in.close();
    }

    private static void runClosingLoad(TaskManager taskManager) {
        try {
            Storage.saveData(taskManager);
            System.out.println("Successfully saved your task list!");
        } catch (IOException e) {
            System.out.println("save fail");
        }

        System.out.println(Printables.goodbyeMessage);
    }

    private static void runTaskProgram(Scanner in, TaskManager taskManager) {
        String command;
        while (true) {
            command= in.nextLine();
            if(!Parser.isOnline(command, taskManager)) {
                break;
            }
            try {
                Storage.saveData(taskManager);
            } catch (IOException e) {
                System.out.println("save fail");
            }
        }
    }

    private static void runInitialLoad(TaskManager taskManager) {
        System.out.println(Printables.initialGreeting);

        try {
            Storage.loadData(taskManager);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, a new data.txt file will be created upon save!");
        }
    }
}