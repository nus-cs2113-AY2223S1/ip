import java.util.Scanner;

public class Duke {
    // static variables
    private static String[] taskList = new String[100];
    private static int taskCounter = 0;

    static String HORIZONTAL_LINE = "------------------------------------------------------------";

    // class methods
    public static void main(String[] args) {
        greet();
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        while (!command.matches("bye")) {
            switch (command) {
            case "list":
                listTasks();
                break;
            default:
                addTask(command);
                break;
            }
            command = input.nextLine();
        }
        exit(); 
    }

    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
        
    }

    public static void echo(String command) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(command);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void addTask(String task) {
        taskList[taskCounter] = task;
        taskCounter += 1;
        System.out.println(HORIZONTAL_LINE);
        System.out.println("added: " + task);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < taskCounter; ++i) {
            System.out.println(i + 1 + ". " + taskList[i]);
        }
        System.out.println(HORIZONTAL_LINE);
    }
}
