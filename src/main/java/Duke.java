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

    private static final int MAX_TASK = 100;
    private static Task[] tasks = new Task[MAX_TASK];
    private static int taskCount = 0;

    private static void greet() {
        final String MESSAGE = "Hello! I'm Ever\n" +
                "What can I do for you?";
        System.out.println(LOGO);
        System.out.println(MESSAGE);
    }

    private static void exit() {
        final String MESSAGE = "Bye. Hope to see you again soon!";
        System.out.println(MESSAGE);
    }

    private static void addTask(Task task) {
        if (taskCount < MAX_TASK) {
            tasks[taskCount++] = task;
            System.out.println("Task added: " + task);
        }
        else {
            System.out.println("Maximum number of tasks reached");
        }
    }

    private static void listTasks() {
        if (taskCount > 0) {
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("%d. %s\n", i + 1, tasks[i]);
            }
        } else {
            System.out.println("There are no tasks added yet. Type 'help' if you need help.");
        }
    }

    private static void displayCommandMenu() {
        for (String command: COMMANDS) {
            System.out.printf("\t* %s\n", command);
        }
    }

    private static String getUserInput() {
        System.out.print(">> ");
        return SCANNER.nextLine().trim();
    }

    private static void evaluateUserInput(String input) {
        String[] inputWords = input.split(" ", 2);

        switch (inputWords[0]) {
        case "help":
            displayCommandMenu();
            break;
        case "list":
            listTasks();
            break;
        case "mark": {
            int taskIndex = Integer.parseInt(inputWords[1]) - 1;
            tasks[taskIndex].markAsDone();
            System.out.printf("Marked as done: %s\n", tasks[taskIndex]);
            break;
        }
        case "unmark": {
            int taskIndex = Integer.parseInt(inputWords[1]) - 1;
            tasks[taskIndex].unmarkDone();
            System.out.printf("Unmarked done: %s\n", tasks[taskIndex]);
            break;
        }
        case "todo":
            try {
                String[] parameters = Todo.extractParameters(input);
                String description = parameters[0].trim();
                addTask(new Todo(description));
            } catch (Exception exception) {
                System.out.println("Invalid input, todo task could not be added");
            }
            break;
        case "deadline":
            try {
                String[] parameters = Deadline.extractParameters(input);
                String description = parameters[0].trim();
                String deadlineDate = parameters[1].trim();
                addTask(new Deadline(description, deadlineDate));
            } catch (Exception exception) {
                System.out.println("Invalid input, deadline task could not be added");
            }
            break;
        case "event":
            try {
                String[] parameters = Event.extractParameters(input);
                String description = parameters[0].trim();
                String datetime = parameters[1].trim();
                addTask(new Event(description, datetime));
            } catch (Exception exception) {
                System.out.println("Invalid input, event task could not be added");
            }
            break;
        default:
            System.out.println("Sorry, I don't get what you mean. Can you try again?");
            break;
        }
    }

    public static void main(String[] args) {
        greet();

        while (true) {
            String inputMessage = getUserInput();
            if (inputMessage.equals("bye")) {
                break;
            }
            evaluateUserInput(inputMessage);
        }

        exit();
    }
}
