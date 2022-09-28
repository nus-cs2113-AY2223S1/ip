package Duke;

import Duke.Exceptions.*;

import java.io.IOException;

public class Duke {
    private static TaskList taskList = new TaskList();

    public static void run() throws IOException {
        Storage.loadTasksToTasksList(taskList);
        Ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            String input = Ui.readInput();
            Parser.parseCommand(input, taskList);
        }
    }

    public static void main(String[] args) throws EmptyArgumentException, InvalidCommandFormatException, TaskListEmptyException, TaskNumberOutOfBoundsException, IOException, TaskNumberNotNumberException {
        Duke.run();
    }
}
