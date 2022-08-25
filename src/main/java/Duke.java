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
        taskSize++;
        taskList[taskSize] = input;
    }

    public static void printAddItemText(Task input){
        System.out.println("added: " + input.description);
        printHorizontalLine();
    }

    public static void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskSize + 1; i++) {
            System.out.println(i + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
        }
        printHorizontalLine();
    }

    public static void markTask(int taskNumber, boolean done){
        taskList[taskNumber].setDone(done);
    }

    public static void printMarkTaskText(int taskNumber){

        String status = taskList[taskNumber].getStatusIcon();
        if (status == "X") {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("[" + status + "] " + taskList[taskNumber].description);
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
                markTask(taskNumber, true);
                printMarkTaskText(taskNumber);
            }else if (inputWords[0].equals("unmark")) {
                int taskNumber =  Integer.parseInt(inputWords[1]);
                markTask(taskNumber, false);
                printMarkTaskText(taskNumber);
            }
            else{
                Task newTask = new Task(input);
                addItem(newTask);
                printAddItemText(newTask);
            }
        }
    }
}
