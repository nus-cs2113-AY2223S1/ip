import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
    public static void printWelcome() {
        printLine();
        System.out.println("    Hello! I'm Duke" + "\n" + "    What can I do for you?");
        printLine();
    }
    public static void printGoodbye() {
        printLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printLine();
    }
    public static void main(String[] args) {
        printWelcome();
        Scanner input = new Scanner(System.in);
        String command;
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            command = input.nextLine();
            if(command.equals("bye")) {
                printGoodbye();
                break;
            } else if (command.equals("list")) {
                printLine();
                System.out.println("    Here are the tasks in your list:");
                for (int counter = 0; counter < tasks.size(); counter++) {
                    System.out.println("    " + (counter + 1) + ".[" + tasks.get(counter).getStatusIcon() + "] " + tasks.get(counter).getTask());
                }
                printLine();
            } else if (command.startsWith("mark ")) {
                printLine();
                System.out.println("    Nice! I've marked this task as done:");
                command = command.replace("mark ", "");
                int index = Integer.parseInt(command) - 1;
                tasks.get(index).setAsDone(true);
                System.out.println("    [" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).getTask());
                printLine();
            } else if (command.startsWith("unmark ")) {
                printLine();
                System.out.println("    OK, I've marked this task as not done yet:");
                command = command.replace("unmark ", "");
                int index = Integer.parseInt(command) - 1;
                tasks.get(index).setAsDone(false);
                System.out.println("    [" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).getTask());
                printLine();
            } else {
                printLine();
                Task t = new Task(command);
                tasks.add(t);
                System.out.println("    Added task: " + command);
                printLine();
            }
        }
    }
}
