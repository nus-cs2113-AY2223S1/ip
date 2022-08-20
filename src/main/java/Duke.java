import java.util.*;

public class Duke {
    public static void drawLine() {    //print underscore symbol 50 times
        System.out.println("__________________________________________________");
    }

    public static void welcomeUser() {
        drawLine();
        System.out.println("Hello! I'm Alpiit");
        System.out.println("What can I do for you?");
        drawLine();
    }

    public static void echoUser() {
        Scanner in = new Scanner(System.in);
        String command = "";
        String[] tasks = new String[100];
        int taskCounter = 0;
        do {
            command = in.nextLine();
            drawLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                drawLine();
                break;
            }
            else if (command.equals("list")) {
                for (int i = 1; i <= taskCounter; ++i) {
                    System.out.println(i + ". " + tasks[i-1]);
                }
                drawLine();
            }
            else {
                tasks[taskCounter] = command;
                taskCounter++;
                System.out.println("added: " + command);
                drawLine();
            }
        } while (command != "bye");

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        welcomeUser();
        echoUser();
    }
}
