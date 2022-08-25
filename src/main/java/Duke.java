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

    public static void printExitText(){
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void echo(String input) {
        System.out.println(input);
        printHorizontalLine();
    }

    public static void addItem(Task input) {
        taskList[taskSize] = input;
        System.out.println("added: " + input.description);
        taskSize++;
        printHorizontalLine();
    }

    public static void printTextList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskSize; i++) {
            System.out.println((i+1) + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
        }
        printHorizontalLine();
    }
    public static void main(String[] args) {
        printGreeting();
        String input;
        Scanner in = new Scanner(System.in);
        while (true) {
            input = in.nextLine();
            Task newTask = new Task(input);
            if (input.equals("bye")) {
                printExitText();
                break;
            } else if (input.equals("list")) {
                printTextList();
            } else {
                addItem(newTask);
            }
        }
    }
}
