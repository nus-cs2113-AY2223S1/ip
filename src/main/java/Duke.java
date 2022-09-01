import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static Task[] taskList = new Task[100];
    private static int taskSize = 0;
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreeting() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void printExitText() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void echo(String input) {
        System.out.println(input);
        printHorizontalLine();
    }

    public static void addItem(Task task) {
        taskSize++;
        taskList[taskSize] = task;
    }

    public static void printAddItemText(Task input) {
        System.out.println("Got it. I've added this task:");
        System.out.println(input.taskStatusWithDescriptionText());
        System.out.println("Now you have " + taskSize + " tasks in the list.");
        printHorizontalLine();
    }

    public static void printTaskList() {
        if (taskSize == 0) {
            System.out.println("There is nothing on your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < taskSize + 1; i++) {
                System.out.println(i + "." + taskList[i].taskStatusWithDescriptionText());
            }
        }
        printHorizontalLine();
    }

    public static void printMarkTaskText(int taskNumber, String command, boolean isDone) {
        String previousIcon = taskList[taskNumber].getStatusIcon();
        if (previousIcon == "X") {
            if (isDone) {
                System.out.println("This task has already been marked!");
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
            }
        } else {
            if (!isDone) {
                System.out.println("This task has already been unmarked!");
            } else {
                System.out.println("Nice! I've marked this task as done:");
            }
        }
        taskList[taskNumber].setDone(isDone);
        String newIcon = taskList[taskNumber].getStatusIcon();
        System.out.println("[" + newIcon + "] " + taskList[taskNumber].description);
        printHorizontalLine();
    }

    public static void main(String[] args) {
        printGreeting();
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            String[] inputWords = input.split(" ", 2);
            switch (inputWords[0]) {
            case "bye":
                printExitText();
                break;
            case "list":
                printTaskList();
                break;
            case "mark":
                int taskNumber =  Integer.parseInt(inputWords[1]);
                printMarkTaskText(taskNumber, "mark", true);
                break;
            case "unmark":
                taskNumber =  Integer.parseInt(inputWords[1]);
                printMarkTaskText(taskNumber, "unmark", false);
                break;
            case "todo":
                Todo newTodo = new Todo(inputWords[1], 'T');
                addItem(newTodo);
                printAddItemText(newTodo);
                break;
            case "deadline":
                String[] taskWithDeadline = inputWords[1].split("/by ", 2);
                String taskDescription = taskWithDeadline[0];
                String taskDeadline = taskWithDeadline[1];
                Deadline newDeadlineTask = new Deadline(taskDescription, 'D', taskDeadline);
                addItem(newDeadlineTask);
                printAddItemText(newDeadlineTask);
                break;
            case "event":
                String[] eventTask = inputWords[1].split("/at ", 2);
                taskDescription = eventTask[0];
                String taskTime = eventTask[1];
                Event newEvent = new Event(taskDescription, 'E', taskTime);
                addItem(newEvent);
                printAddItemText(newEvent);
                break;
            default:
                Task newTask = new Task(input, 'N');
                addItem(newTask);
                printAddItemText(newTask);
                break;
            }
        }
    }
}
