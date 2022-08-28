import java.util.Scanner;

public class Duke {

    static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        processInput();
    }

    public static void printLine() {
        System.out.print("\t");
        for (int i = 0; i < 60; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }

    public static void greet() {
        printLine();
        System.out.println("\tHello! I'm Duke!");
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    public static void processInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            printLine();
            String command = input.split(" ")[0];
            if (input.equals("list")) {
                taskManager.listTasks();
            } else if (command.equals("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                taskManager.markTaskAsDone(taskNumber);
            } else if (command.equals("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                taskManager.markTaskAsUndone(taskNumber);
            } else if (command.equals("todo")) {
                taskManager.addTask(new Todo(input.split(" ", 2)[1]));
            } else if(command.equals("deadline")){
                String parameters = input.split(" ", 2)[1];
                String description = parameters.split(" /by ")[0];
                String deadline = parameters.split(" /by ")[1];
                taskManager.addTask(new Deadline(description, deadline));
            }
            else if(command.equals("event")){
                String parameters = input.split(" ", 2)[1];
                String description = parameters.split(" /at ")[0];
                String deadline = parameters.split(" /at ")[1];
                taskManager.addTask(new Event(description, deadline));
            }
            else{
                taskManager.addTask(new Task(input));
            }
            printLine();
            input = scanner.nextLine();
        }
        exit();
    }

    public static void exit() {
        printLine();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        printLine();
    }
}
