package duke;

import duke.taskmanager.TaskManager;

public class Duke extends UI {
    public static void main(String[] args) {
        printGreetingMessage();
        TaskManager duke = new TaskManager();
        duke.receiveCommands();
        printExitMessage();
    }
}
