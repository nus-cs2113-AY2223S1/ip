package duke;

import duke.data.Storage;
import duke.exceptions.DukeException;
import duke.task.Task;

import java.io.IOException;
import java.util.Scanner;


public class Duke {
    static TaskManager Manager = new TaskManager();

    /**
     * Initializes the program
     */
    public static void main(String[] args) {

        UI.startSession();

        UI.readLines();

        UI.endSession();
    }
}
