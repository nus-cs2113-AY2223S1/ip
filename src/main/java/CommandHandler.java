import java.util.Scanner;

public class CommandHandler {
    private static int taskCounter = 0;
    public static void drawLine() {    //print underscore symbol 50 times
        System.out.println("__________________________________________________");
    }

    public static void byeUser() {
        System.out.println("Bye. Hope to see you again!");
        drawLine();
    }

    public static void listTasks(Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskCounter; ++i) {
            System.out.println(i + ". [" + (tasks[i-1].isDone() ? "X] " : " ] ")+ tasks[i-1].getTaskName());
        }
        drawLine();
    }

    public static int getTaskID(String command) {
        command = command.trim();
        String[] words = command.split(" ");
        int taskID = Integer.parseInt(words[1]);
        return taskID;
    }

    public static void unmarkTasks(Task[] tasks, int taskID) {
        tasks[taskID-1].setDone(false);
        System.out.println("Okay, I've marked this task as not done yet:");
        System.out.println("[ ] " + tasks[taskID-1].getTaskName());
        drawLine();
    }

    public static void markTasks(Task[] tasks, int taskID) {
        tasks[taskID-1].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasks[taskID-1].getTaskName());
        drawLine();
    }

    public static void createNewTask(String command, Task[] tasks) {
        Task task = new Task(command, false);
        tasks[taskCounter] = task;
        taskCounter++;
        System.out.println("added: " + command);
        drawLine();
    }
    public static void executeUserCommands() {
        Scanner in = new Scanner(System.in);
        String command = "";
        Task[] tasks = new Task[100];
        do {
            command = in.nextLine();
            drawLine();
            if (command.equals("bye")) {
                byeUser();
                break;
            }
            else if (command.equals("list")) {
                listTasks(tasks);
            }
            else if (command.contains("unmark")) {
                int taskID = getTaskID(command);
                if (tasks[taskID - 1].isDone() == false) {
                    System.out.println("Unable to unmark as this task has not been done yet");
                    drawLine();
                } else {
                    unmarkTasks(tasks, taskID);
                }
            }
            else if (command.contains("mark")) {
                int taskID = getTaskID(command);
                if (tasks[taskID - 1].isDone() == true) {
                    System.out.println("Unable to mark as this task has already been done");
                    drawLine();
                } else {
                    markTasks(tasks, taskID);
                }
            }
            else {
                createNewTask(command, tasks);
            }
        } while (command != "bye");
    }
}
