package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String logo2 = "                   _      \n"
                + "                  | |     \n"
                + "  _   _ _ __   ___| | ___ \n"
                + " | | | | '_ \\ / __| |/ _ |\n"
                + " | |_| | | | | (__| |  __/ \n"
                + "  \\__,_|_| |_|\\___|_|\\___| \n";

        System.out.println("Oi I'm\n" + logo2);
        System.out.println("What you want?");

        Scanner in = new Scanner(System.in);
        TaskList tasks = new TaskList();
        boolean run = true;
        while (run) {
            String line = in.nextLine();
            Command command = new Command(line, tasks);
            if (line.equals("bye")) {
                run = false;
                System.out.println("bye bye");
            } else {
                command.handleCommand();
            }
        }
    }
}
