import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void WelcomeUser() {
        String welcomeText = "-------------------------------------\n"
                + "Hello! I am not Duke.\n"
                + "Here are a list of commands: \n"
                + "\tadd <string> : adds a task to the list\n"
                + "\tlist : shows list of tasks\n"
                + "\tmark <task id> : marks a task \n"
                + "\tunmark <task id> : unmarks a task \n"
                + "\tbye : exits the program \n"
                + "I WILL CRASH IF YOU DO NOT COMPLY!!!\n"
                + "-------------------------------------";
        System.out.println(welcomeText);
    }

    public static String CleanInput(String input) {
        return input.toLowerCase().trim();
    }

    public static void ListTasks(Task[] list) {
        if (Task.getTaskCount() != 0) {
            for (int i = 0; i < Task.getTaskCount(); i++) {
                System.out.println(list[i].getStatusIcon() + (i + 1) + ": " + list[i].getDescription());
            }
        } else {
            System.out.println("The list is empty, please add something!");
        }
    }

    public static void AddTask(Task[] taskList, String taskDescription) {
        taskList[Task.getTaskCount()] = new Task(taskDescription);
        System.out.println("Added \"" + taskDescription + "\"");
    }

    public static void TrackInput() {
        Scanner scanner = new Scanner(System.in);
        Task[] taskList = new Task[100];
        final String LINEDIVIDER = "-------------------------------------";
        while (true) {
            String input = CleanInput(scanner.nextLine());
            System.out.println(LINEDIVIDER);
            if (input.equals("bye")) {
                System.out.println("Bye bye!!! \n" + LINEDIVIDER + "\n");
                break;
            } else if (input.equals("list")) {
                ListTasks(taskList);
            } else if (input.startsWith("add ")) {
                AddTask(taskList, input.substring(4));
            } else if (input.startsWith("mark ")) {
                int toMark = Integer.parseInt(input.substring(5)) - 1;
                System.out.println(taskList[toMark].getDescription() + " marked");
                taskList[toMark].setDone(true);
            } else if (input.startsWith("unmark ")) {
                int toMark = Integer.parseInt(input.substring(7)) - 1;
                System.out.println(taskList[toMark].getDescription() + " unmarked");
                taskList[toMark].setDone(false);
            } else {
                System.out.println("Invalid command.");
            }
            System.out.println(LINEDIVIDER);
        }
    }

    public static void main(String[] args) {
        WelcomeUser();
        TrackInput();
    }
}
