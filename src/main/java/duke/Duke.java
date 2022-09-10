package duke;

import duke.storage.FileSaver;
import duke.task.TaskManager;

import java.io.IOException;
import java.util.Scanner;
//test new branch

public class Duke {
    static final String GREETING = "Hello! I'm Timmy\n What can I do for you?\n \n";
    static final String GOODBYE = "Bye. Hope to see you again soon\n";

    private static TaskManager myManager = new TaskManager();

    public static void main(String[] args) {
        System.out.println(GREETING);
        try {
            myManager = FileSaver.readFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            myManager.handleInput(userInput);
            System.out.println();
            userInput = in.nextLine();
        }
        System.out.println(GOODBYE);
    }
}

