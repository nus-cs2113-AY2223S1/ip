import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int taskCounter = 0;

    static String HORIZONTAL_LINE = "------------------------------------------------------------";
    static String COMMAND_WORD_LIST = "list";
    static String COMMAND_WORD_MARK = "mark";
    static String COMMAND_WORD_UNMARK = "unmark";
    static String COMMAND_WORD_TODO = "todo";
    static String COMMAND_WORD_DEADLINE = "deadline";
    static String COMMAND_WORD_EVENT = "event";
    static String COMMAND_WORD_DELETE = "delete";
    static String COMMAND_WORD_EXIT = "bye";

    // class methods
    static void errorMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("I don't know what that means :(");
        System.out.println(HORIZONTAL_LINE);
    }

    static boolean checkSpace(String line) {
        // in the case of "todo"
        if (!line.contains(" ")) {
            throw new IllegalArgumentException("No task name included");
        }

        // in the case of "todo "
        int spaceLocation = line.indexOf(" ");
        if (spaceLocation == line.length()) {
            throw new IllegalArgumentException("No task name included");
        }

        return true;
    }

    static void handleMark(String line) {
        String markIndex = line.split(" ")[1];
        int taskIndex = Integer.parseInt(markIndex) - 1;
        Task task = tasks.get(taskIndex);
        markTask(task);
    }

    static void handleUnmark(String line) {
        String markIndex = line.split(" ")[1];
        int taskIndex = Integer.parseInt(markIndex) - 1;
        Task task = tasks.get(taskIndex);
        unmarkTask(task);
    }

    static void handleCommand(String command, String line) {
        if (command.matches(COMMAND_WORD_LIST)) {
            listTasks();
        } else if (command.matches(COMMAND_WORD_MARK)) {
            handleMark(line);
        } else if (command.matches(COMMAND_WORD_UNMARK)) {
            handleUnmark(line);
        } else if (command.matches(COMMAND_WORD_TODO)) {
            addTaskSpecial(line, "todo");
        } else if (command.matches(COMMAND_WORD_EVENT)) {
            addTaskSpecial(line, "event");
        } else if (command.matches(COMMAND_WORD_DEADLINE)) {
            addTaskSpecial(line, "deadline");
        } else if (command.matches(COMMAND_WORD_DELETE)) {
            deleteTask(line);
        } else {
            errorMessage();
        }
    }

    public static void main(String[] args) {
        greet();
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        int COMMAND_INDEX = 0;
        String command = line.split(" ")[COMMAND_INDEX];

        while (!command.matches(COMMAND_WORD_EXIT)) {
            handleCommand(command, line);
            line = input.nextLine();
            command = line.split(" ")[COMMAND_INDEX];
        }
        exit(); 
    }

    // Method to greet user when they enter Duke
    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
        
    }

    // Method to print the same command as user input
    public static void echo(String command) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(command);
        System.out.println(HORIZONTAL_LINE);
    }

    // Function to exit out of Duke
    public static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    // Method to add normal tasks
    public static void addTask(Task task) {
        tasks.add(task);
        taskCounter += 1;
        System.out.println(HORIZONTAL_LINE);
        System.out.println("added: " + task.name);
        System.out.println(HORIZONTAL_LINE);
    }

    // Method to list tasks
    public static void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        if (taskCounter == 0) {
            System.out.println("You have no tasks in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCounter; ++i) {
                Task task = tasks.get(i);
                System.out.println(task.toString());
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    // Method to mark a task as done
    public static void markTask(Task task) {
        System.out.println(HORIZONTAL_LINE);
        task.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.name);
        System.out.println(HORIZONTAL_LINE);
    }

    // Method to unmark task as undone
    public static void unmarkTask(Task task) {
        System.out.println(HORIZONTAL_LINE);
        task.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.name);
        System.out.println(HORIZONTAL_LINE);
    }

    // Method to add todo, deadline and event tasks
    public static void addTaskSpecial(String name, String type) {
        String divider = "/";
        if (!checkSpace(name)) {
            errorMessage();
            return;
        }

        if (!name.contains(divider)) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Please add a date");
            System.out.println(HORIZONTAL_LINE);
            greet();
            return;
        }

        name = name.substring(name.indexOf(" "), name.length());
        switch (type) {
        case "todo":
            Todo todoTask = new Todo(name);
            tasks.add(todoTask);
            taskCounter += 1;
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println(todoTask.toString());
            System.out.println("Now you have " + taskCounter + " tasks in the list.");
            System.out.println(HORIZONTAL_LINE);
            break;
        case "deadline":
            String deadlineDate = name.substring(name.indexOf(divider) + 3, name.length());
            Deadline deadlineTask = new Deadline(name, deadlineDate);
            tasks.add(deadlineTask);
            taskCounter += 1;
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println(deadlineTask.toString());
            System.out.println("Now you have " + taskCounter + " tasks in the list.");
            System.out.println(HORIZONTAL_LINE);
            break;
        case "event":
            String eventDate = name.substring(name.indexOf(divider) + 3, name.length());
            Event eventTask = new Event(name, eventDate);
            tasks.add(eventTask);
            taskCounter += 1;
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println(eventTask.toString());
            System.out.println("Now you have " + taskCounter + " tasks in the list.");
            System.out.println(HORIZONTAL_LINE);
            break;
        }
    }

    private static void deleteTask(String line) {
        String taskIndex = line.split(" ")[1];
        int listIndex = Integer.parseInt(taskIndex) - 1;
        Task task = tasks.get(listIndex);
        tasks.remove(listIndex);
        taskCounter -= 1;
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println(HORIZONTAL_LINE);
    }
}