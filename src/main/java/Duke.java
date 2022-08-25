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

    public static void addItem(Task input) {
        taskSize++;
        taskList[taskSize] = input;
    }

    public static void printAddItemText(Task input) {
        System.out.println("added: " + input.description);
        printHorizontalLine();
    }

    public static void printTaskList() {
        if (taskSize == 0) {
            System.out.println("There is nothing on your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < taskSize + 1; i++) {
                System.out.println(i + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
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
            String[] inputWords = input.split("\\s+");
            if (inputWords.length == 1 && inputWords[0].equals("bye")) {
                printExitText();
                break;
            } else if (inputWords.length == 1 && inputWords[0].equals("list")) {
                printTaskList();
            } else if (inputWords[0].equals("mark")) {
                int taskNumber =  Integer.parseInt(inputWords[1]);
                printMarkTaskText(taskNumber, "mark", true);
            }else if (inputWords[0].equals("unmark")) {
                int taskNumber =  Integer.parseInt(inputWords[1]);
                printMarkTaskText(taskNumber, "unmark", false);
            }
            else {
                Task newTask = new Task(input);
                addItem(newTask);
                printAddItemText(newTask);
            }
        }
    }
}
