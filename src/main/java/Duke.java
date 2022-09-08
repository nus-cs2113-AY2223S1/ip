import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINEBREAK = "____________________________________________________________";

    static TaskManager Manager = new TaskManager();

    static void startSession() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINEBREAK);
        System.out.println("Hello! I'm Duke Nukem");
        System.out.println("What can I do for you today? Let's rock!");
        System.out.println(LINEBREAK);
    }

    static void endSession() {
        System.out.println(LINEBREAK);
        System.out.println("Bye. Hope to see you again soon! Groovy!");
        System.out.println(LINEBREAK);
    }

    public static void main(String[] args) {

        startSession();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.length() > 3 && line.startsWith("mark")) {

                Manager.markTasks(line);
            } else if (line.length() > 4 && line.startsWith("unmark")) {
                Manager.unmarkTasks(line);
            } else if (line.equals("list")) {
                Manager.printTasks();
            } else {
                Manager.storeTasks(line);
            }
            line = in.nextLine();
        }

        endSession();
    }
}
