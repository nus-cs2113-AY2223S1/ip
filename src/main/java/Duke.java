import java.util.Scanner;

public class Duke {
    public static Boolean isRunning = true;

    public static void Greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");
    }

    public static void Echo() {

        while (isRunning) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isRunning = false;
            } else {
                System.out.println(line);
            }
        }
    }

    public static void ToDoList() {
        int counter = 0;
        Task[] tasks = new Task[100];

        while (isRunning) {
            String line;
            int taskNumber = 0;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            String[] details = line.split(" ");
            if (details[0].equals("mark") || details[0].equals("unmark")) {
                taskNumber = Integer.parseInt(details[1])-1;
            }

            switch (details[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                isRunning = false;
                break;
            case "list":
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + "." + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].getTask());
                }
                break;
            case "mark":
                System.out.println("Nice! I've marked this task as done:\n");
                tasks[taskNumber].markasDone();
                System.out.println("[" + tasks[taskNumber].getStatusIcon() + "]" + tasks[taskNumber].getTask());
                break;
            case "unmark":
                System.out.println("OK, I've marked this task as not done yet:\n");
                tasks[taskNumber].Unmark();
                System.out.println("[" + tasks[taskNumber].getStatusIcon() + "]" + tasks[taskNumber].getTask());
                break;
            default:
                System.out.println("added: " + line);
                tasks[counter] = new Task(line);
                counter++;
                break;
            }

        }
    }

    public static void main(String[] args) {
        Greetings();
        ToDoList();

    }
}
