import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ________      ________ _____  \n" +
            "|  ____\\ \\    / /  ____|  __ \\ \n" +
            "| |__   \\ \\  / /| |__  | |__) |\n" +
            "|  __|   \\ \\/ / |  __| |  _  / \n" +
            "| |____   \\  /  | |____| | \\ \\ \n" +
            "|______|   \\/   |______|_|  \\_\\";
    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;
    private static void greet() {
        String MESSAGE = "Hello! I'm Ever\n" +
                "What can I do for you?";
        System.out.println(MESSAGE);
    }
    private static void exit() {
        String MESSAGE = "Bye. Hope to see you again soon!";
        System.out.println(MESSAGE);
    }
    private static void addTask(String description) {
        tasks[tasksCount++] = new Task(description);
    }
    private static void listTasks() {
        for (int i = 0; i < tasksCount; i++) {
            System.out.printf("%d. %s\n", i + 1, tasks[i].getPrintTaskString());
        }
    }
    public static void main(String[] args) {

        System.out.println(LOGO);

        greet();

        /* Get input from user */
        Scanner scanner = new Scanner(System.in);
        String inputMessage;

        while (true) {
            System.out.print(">> ");
            inputMessage = scanner.nextLine();
            String[] inputWords = inputMessage.split(" ");
            if (inputMessage.equals("bye")) {
                break;
            } else if (inputMessage.equals("list")){
                listTasks();
            } else if (inputWords[0].equals("mark")) {
                int taskIndex = Integer.parseInt(inputWords[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.printf("Marked as done: %s\n", tasks[taskIndex].getPrintTaskString());
            } else if (inputWords[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(inputWords[1]) - 1;
                tasks[taskIndex].unmarkDone();
                System.out.printf("Unmarked done: %s\n", tasks[taskIndex].getPrintTaskString());
            } else {
                addTask(inputMessage);
                System.out.println("Task \"" + inputMessage + "\" added");
            }
        }

        exit();
    }
}
