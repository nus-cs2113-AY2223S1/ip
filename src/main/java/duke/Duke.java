package duke;

import duke.exceptions.DukeException;

import java.util.Scanner;

public class Duke {
    static TaskManager Manager = new TaskManager();

    static void startSession() {
        UI.printLogo();
        UI.printLine();
        System.out.println("Hello! I'm duke.Duke Nukem");
        System.out.println("What can I do for you today? Let's rock!");
        UI.printLine();
    }

    static void endSession() {
        UI.printLine();
        System.out.println("Bye. Hope to see you again soon! Groovy!");
        UI.printLine();
    }

    public static void main(String[] args) throws DukeException {

        startSession();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!Parser.checkBye(line)) {
            Parser.parse(Manager,line);
            line = in.nextLine();
        }

        endSession();
    }
}
