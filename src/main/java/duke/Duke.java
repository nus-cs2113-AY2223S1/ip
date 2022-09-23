package duke;

import duke.taskmanager.TaskManager;

public class Duke {
    public static void main(String[] args) {
        TaskManager duke = new TaskManager();
        duke.receiveCommands();
    }
}
