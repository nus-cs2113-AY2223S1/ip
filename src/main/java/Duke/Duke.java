package Duke;

import Duke.Exceptions.*;

import java.util.Scanner;
import java.io.IOException;

public class Duke {
    private static TaskList taskList = new TaskList();

    public static void run() throws IOException {
        Storage.loadTasksToTasksList(taskList);
        Ui.printGreeting();
        Scanner in = new Scanner(System.in);
        String input;
        while (true) {
            input = in.nextLine();
            Parser.parseCommand(input, taskList);
        }
    }

    public static void main(String[] args) throws EmptyArgumentException, InvalidCommandFormatException, TaskListEmptyException, TaskNumberOutOfBoundsException, IOException, TaskNumberNotNumberException {
        Duke.run();
    }
}
