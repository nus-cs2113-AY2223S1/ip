import java.util.Scanner;

public class Duke {
    public static Boolean isRunning = true;
    public static int numberOfTasks = 0;
    public static String inputLine;
    public static Task[] tasks = new Task[100];

    public static void printGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static String findTaskSpecifics(String details) {
        return details.substring(details.indexOf(" "), details.indexOf("/"));
    }

    public static void printDefaultTaskResponse(int number, Task[] tasks) {
        System.out.println("Got it. I've added this task:\n" + tasks[number].toString() + "\n"
                + "Now you have " + (number + 1) + " tasks in the list.");
    }

    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        isRunning = false;
    }

    public static void insertDeadlineTask(String inputLine, Task[] tasks) {
        String deadlineSpecifics = findTaskSpecifics(inputLine);
        String deadline = inputLine.substring(inputLine.indexOf("/by") + 3);
        tasks[numberOfTasks] = new Deadline(deadlineSpecifics, deadline);
        printDefaultTaskResponse(numberOfTasks, tasks);
        numberOfTasks++;
    }

    public static void insertToDoTask(String inputLine, Task[] tasks) {
        String todoSpecifics = inputLine.substring(inputLine.indexOf(" "));
        tasks[numberOfTasks] = new Todo(todoSpecifics);
        printDefaultTaskResponse(numberOfTasks, tasks);
        numberOfTasks++;
    }

    public static void insertEventTask(String inputLine, Task[] tasks) {
        String eventSpecifics = findTaskSpecifics(inputLine);
        String eventDate = inputLine.substring(inputLine.indexOf("/at") + 3);
        tasks[numberOfTasks] = new Event(eventSpecifics, eventDate);
        printDefaultTaskResponse(numberOfTasks, tasks);
        numberOfTasks++;
    }

    public static void printTaskList(int numberOfTasks, Task[] tasks) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
    }

    public static void markTask(String[] taskDescriptions, Task[] tasks) {
        int taskToMark = Integer.parseInt(taskDescriptions[1]) - 1;
        System.out.println("Nice! I've marked this task as done:\n");
        tasks[taskToMark].markAsDone();
        System.out.println(tasks[taskToMark].toString());
    }

    public static void unmarkTask(String[] taskDescriptions, Task[] tasks) {
        int taskToUnmark = Integer.parseInt(taskDescriptions[1]) - 1;
        System.out.println("OK, I've marked this task as not done yet:\n");
        tasks[taskToUnmark].unmark();
        System.out.println(tasks[taskToUnmark].toString());
    }

    public static void runTaskList() {
        Scanner in = new Scanner(System.in);
        while (isRunning) {
            inputLine = in.nextLine();
            String[] details = inputLine.split(" ");
            String command = details[0];
            switch (command) {
            case "bye":
                sayGoodbye();
                break;
            case "deadline":
                insertDeadlineTask(inputLine, tasks);
                break;
            case "todo":
                insertToDoTask(inputLine, tasks);
                break;
            case "event":
                insertEventTask(inputLine, tasks);
                break;
            case "mark":
                markTask(details, tasks);
                break;
            case "unmark":
                unmarkTask(details, tasks);
                break;
            case "list":
                printTaskList(numberOfTasks, tasks);
                break;
            default:
                System.out.println("You have entered an invalid command, please try again.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        printGreetings();
        runTaskList();
    }
}
