import java.util.Scanner;

public class Duke {
    //========================================= GLOBAL CONSTANT ========================//
    private static final int MAXIMUM_NUMBER_OF_TASKS = 100;

    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_HELLO = "Hello! I'm a chatbot Duke made by Than Duc Huy\n" +
            "Type the command to start interacting with Duke";
    private static final String MESSAGE_COMMAND_LISTS = "Supported commands: list, mark, todo, deadline, event, bye";
    private static final String MESSAGE_HORIZONTAL_LINE = "===================================================";


    //========================================= GLOBAL VARIABLES ========================//
    static Scanner in = new Scanner(System.in);
    static Task[] tasksList = new Task[MAXIMUM_NUMBER_OF_TASKS];
    static boolean isRunning = true;


    //========================================= MAIN ========================//
    public static void main(String[] args) {
        helloMessage();
        while (isRunning) {
            parseCommand();
        }
    }

    private static void parseCommand() {
        String line = in.nextLine();
        String lowerCaseLine = line.toLowerCase();
        if (lowerCaseLine.contains("bye")) {
            exit();
        } else if (lowerCaseLine.contains("list")) {
            list();
        } else if (lowerCaseLine.contains("mark")) {
            mark(line);
        } else if (lowerCaseLine.contains("todo") || lowerCaseLine.contains("deadline") || lowerCaseLine.contains("event")) {
            add(line);
        } else {
            unknownCommandMessage();
        }
    }
    //========================================= GLOBAL METHOD ========================//

    private static void exit() {
        isRunning = false;
        exitMessage();
    }

    private static void unknownCommandMessage() {
        System.out.println("Please indicate task type: todo/deadline/event");
    }

    private static void list() {
        System.out.println("========================LIST=======================");
        for (int i = 0; i < Task.numberOfTasks; i++) {
            System.out.print((i + 1) + ".");
            tasksList[i].printTask();
        }
        System.out.println("===================================================");
    }

    private static void add(String line) {
        String newTaskType = line.split(" ")[0];
        String[] taskDescriptor = line.replace(newTaskType, "").trim().split("/");
        switch (newTaskType.toLowerCase()) {
        case "todo":
            tasksList[Task.numberOfTasks] = new Todo(taskDescriptor[0]);
            break;
        case "deadline":
            tasksList[Task.numberOfTasks] = new Deadline(taskDescriptor[0], taskDescriptor[1].trim());
            break;
        case "event":
            tasksList[Task.numberOfTasks] = new Event(taskDescriptor[0], taskDescriptor[1].trim());
            break;
        default:
            break;
        }
        addTaskMessage();
    }

    private static void addTaskMessage() {
        drawHorizontal();
        System.out.println("Got it! You just add a new Task");
        System.out.print("\t");
        tasksList[Task.numberOfTasks - 1].printTask();
        System.out.println("Number of tasks in the list: " + Task.numberOfTasks);
        drawHorizontal();
    }

    public static void mark(String line) {
        String[] parseArg = line.split(" ");
        int index = Integer.parseInt(parseArg[1]);
        if (parseArg[0].equalsIgnoreCase("mark")) {
            tasksList[index - 1].isDone = true;
        } else {
            tasksList[index - 1].isDone = false;
        }
        list();
    }

    private static void exitMessage() {
        System.out.println(MESSAGE_EXIT);
        drawHorizontal();
    }

    private static void drawHorizontal() {
        System.out.println(MESSAGE_HORIZONTAL_LINE);
    }

    private static void helloMessage() {
        drawHorizontal();
        System.out.println(MESSAGE_HELLO);
        System.out.println(MESSAGE_COMMAND_LISTS);
        drawHorizontal();
    }

}
