import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ________      ________ _____  \n" +
            "|  ____\\ \\    / /  ____|  __ \\ \n" +
            "| |__   \\ \\  / /| |__  | |__) |\n" +
            "|  __|   \\ \\/ / |  __| |  _  / \n" +
            "| |____   \\  /  | |____| | \\ \\ \n" +
            "|______|   \\/   |______|_|  \\_\\";

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String[] COMMANDS = {"help", "list", "todo", "deadline", "event",
        "mark", "unmark", "bye"};

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

    private static void addTask(Task task) {
        tasks[tasksCount++] = task;
    }

    private static void listTasks() {
        for (int i = 0; i < tasksCount; i++) {
            System.out.printf("%d. %s\n", i + 1, tasks[i].getPrintString());
        }
    }

    private static void displayCommandMenu() {
        for (String command: COMMANDS) {
            System.out.printf("\t* %s\n", command);
        }
    }

    public static void main(String[] args) {

        System.out.println(LOGO);

        greet();

        /* Get input from user */
        String inputMessage;

        while (true) {
            System.out.print(">> ");
            inputMessage = SCANNER.nextLine();
            String[] inputWords = inputMessage.split(" ", 2);
            if (inputMessage.equals("bye")) {
                break;
            } else if (inputMessage.equals("help")){
                displayCommandMenu();
            } else if (inputMessage.equals("list")){
                listTasks();
            } else if (inputWords[0].equals("mark")) {
                int taskIndex = Integer.parseInt(inputWords[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.printf("Marked as done: %s\n", tasks[taskIndex].getPrintString());
            } else if (inputWords[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(inputWords[1]) - 1;
                tasks[taskIndex].unmarkDone();
                System.out.printf("Unmarked done: %s\n", tasks[taskIndex].getPrintString());
            } else if (inputWords[0].equals("todo")) {
                addTask(new Todo(inputWords[1]));
                System.out.println("Todo task \"" + inputWords[1] + "\" added");
            } else {
                System.out.println("Sorry, I don't get what you mean. Can you try again?");
            }
        }

        exit();
    }
}
