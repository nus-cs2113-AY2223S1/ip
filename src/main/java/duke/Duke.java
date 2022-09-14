package duke;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
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

        System.out.println("Hello I'm\n" + logo2);
        System.out.println("What you want?");

        Scanner in = new Scanner(System.in);
        String filePath = "./data/duke.txt";
        Storage storage = new Storage(filePath);
        TaskList tasks;
        boolean run = true;
        try {
            tasks = new TaskList(storage.readFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
        while (run) {
            String line = in.nextLine();
            Command command = new Command(line, tasks, filePath);
            if (line.equals("bye")) {
                run = false;
                System.out.println("bye bye");
                try {
                    storage.writeFile(tasks);
                } catch (IOException e) {
                    System.out.println("error saving file");
                }
            } else {
                command.handleCommand();
            }
        }
    }
}
