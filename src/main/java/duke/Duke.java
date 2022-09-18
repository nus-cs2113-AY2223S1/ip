package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final String QUIT_FLAG = "quit";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TaskList taskList = new TaskList();

        runInitialLoad(taskList);
        runTaskProgram(in, taskList);
        runClosingLoad(taskList);

        in.close();
    }

    private static void runClosingLoad(TaskList taskList) {
        try {
            Storage.saveData(taskList);
            Ui.printResponseToUser(Printables.SUCCESSFUL_SAVE_MESSAGE);
        } catch (IOException e) {
            Ui.printResponseToUser(Printables.FAIL_SAVE_MESSAGE);
        }

        Ui.printResponseToUser(Printables.GOODBYE_MESSAGE);
    }

    private static void runTaskProgram(Scanner in, TaskList taskList) {
        String command;
        String response;
        while (true) {
            command = Ui.getCommandFromUser(in);
            response = Parser.runCommand(command, taskList);

            if (Objects.equals(response, QUIT_FLAG)) {
                return;
            }
            Ui.printResponseToUser(response);

            try {
                Storage.saveData(taskList);
            } catch (IOException e) {
                Ui.printResponseToUser(Printables.FAIL_SAVE_MESSAGE);
            }
        }
    }

    private static void runInitialLoad(TaskList taskList) {
        Ui.printResponseToUser(Printables.INITIAL_GREETING);

        try {
            Ui.printResponseToUser(Storage.loadData(taskList));
        } catch (FileNotFoundException e) {
            Ui.printResponseToUser(Printables.MISSING_FILE_MESSAGE);
        }
    }
}