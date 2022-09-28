package duke;

import duke.data.Storage;
import duke.exceptions.DukeException;
import duke.task.Task;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    static TaskManager Manager = new TaskManager();

    /**
     * Prints the User Interface associated with the start of the program
     */
    static void startSession() {
        UI.printLogo();
        UI.printLine();
        System.out.println("Hello! I'm duke.Duke Nukem");
        System.out.println("What can I do for you today? Let's rock!");
        UI.printLine();
    }

    /**
     * Prints the User Interface associated with the end of the program
     */
    static void endSession() {
        UI.printLine();
        System.out.println("Bye. Hope to see you again soon! Groovy!");
        UI.printLine();
    }

    /**
     * Initializes the program
     */
    public static void main(String[] args) {

        startSession();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!Parser.checkBye(line)) {
            Parser.parse(Manager, line);
            line = in.nextLine();
        }

        endSession();
    }
}
