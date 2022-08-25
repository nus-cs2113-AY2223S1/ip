import java.util.Scanner;

public class Bloom {
    public static void drawLine() {    //print underscore symbol 50 times
        System.out.println("__________________________________________________");
    }

    public static void welcomeUser() {
        drawLine();
        System.out.println("Hello! I'm Bloom");
        System.out.println("What can I do for you?");
        drawLine();
    }

    public static void executeUserCommands() {
        Scanner in = new Scanner(System.in);
        String command = "";
        Task[] tasks = new Task[100];
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
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= taskCounter; ++i) {
                    System.out.println(i + ". [" + (tasks[i-1].isDone() ? "X] " : " ] ")+ tasks[i-1].getTaskName());
                }
                drawLine();
            }
            else if (command.contains("unmark")) {
                command = command.trim();
                String[] words = command.split(" ");
                int taskId = Integer.parseInt(words[1]);
                tasks[taskId-1].setDone(false);
                System.out.println("Okay, I've marked this task as not done yet:");
                System.out.println("[ ] " + tasks[taskId-1].getTaskName());
                drawLine();
            }
            else if (command.contains("mark")) {
                command = command.trim();
                String[] words = command.split(" ");
                int taskId = Integer.parseInt(words[1]);
                tasks[taskId-1].setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + tasks[taskId-1].getTaskName());
                drawLine();
            }
            else {
                Task task = new Task(command, false);
                tasks[taskCounter] = task;
                taskCounter++;
                System.out.println("added: " + command);
                drawLine();
            }
        } while (command != "bye");

    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String logo =  " _____ __    _____ _____ _____\n"
                     + "| __  |  |  |     |     |     |\n"
                     + "| __ -|  |__|  |  |  |  | | | |\n"
                     + "|_____|_____|_____|_____|_|_|_|\n";
        System.out.println("Hello from\n" + logo);
        welcomeUser();
        executeUserCommands();
    }
}
