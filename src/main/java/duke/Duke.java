package duke;


import duke.file.Storage;
import duke.taskings.Task;
import duke.messages.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static final boolean EXIT = false;

    /**
     * Shows greeting message , initialise the storage file dukeFile.txt before processing user inputs.
     * Program ends when the Parser returns an EXIT.
     */
    public static void main(String[] args) {

        // array storing the Task objects.
        ArrayList<Task> tasks = new ArrayList<Task>();

        String inData;
        Scanner scan = new Scanner(System.in);
        Ui.greet();
        Storage.initialiseFile(tasks);
        Ui.displayLineDivider();

        while (true) {
            // continuously receive user input
            String command = "";
            inData = scan.nextLine();
            inData = inData.trim();

            if (Parser.processInput(inData, tasks) == EXIT) {
                break;
            }

        }
    }
}
