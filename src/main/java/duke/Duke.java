package duke;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        System.out.println(Printables.initialGreeting);
        TaskManager taskManager = new TaskManager();
        while (true) {
            command= in.nextLine();
            if(!Parser.isOnline(command, taskManager)) {
                break;
            }
        }
        in.close();
    }
}