package duke;

import duke.taskmanager.TaskManager;

/**
 * The main class for Duke the task manager
 */
public class Duke {
    /**
     * Creates a new <code>TaskManager</code> and constantly receives command until the user stops the programme
     * @param args no command line arguments are needed or expected
     */
    public static void main(String[] args) {
        TaskManager duke = new TaskManager();
        duke.receiveCommands();
    }
}
