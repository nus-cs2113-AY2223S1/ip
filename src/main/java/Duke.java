import java.util.Scanner;

public class Duke {
    public static Boolean isRunning = true;

    public static void printGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
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
            String command = details[0];
            String task = details[1];
            if (command.equals("mark") || command.equals("unmark")) {
                taskNumber = Integer.parseInt(task)-1;
            }

            switch (command) {
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
                tasks[taskNumber].markAsDone();
                System.out.println("[" + tasks[taskNumber].getStatusIcon() + "]" + tasks[taskNumber].getTask());
                break;
            case "unmark":
                System.out.println("OK, I've marked this task as not done yet:\n");
                tasks[taskNumber].unmark();
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
        printGreetings();
//        ToDoList();

    }
}
