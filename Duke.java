import java.io.*;
import java.util.Scanner;
import duke.error.DukeException;
import duke.tasks.*;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int taskCounter = 0;

    static String HORIZONTAL_LINE = "------------------------------------------------------------";
    static String FILE_PATH = "./duke.txt";
    static String FILE_SEPARATOR = "-";
    static String LINE_DIVIDER = "/";

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

    static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }

    static String findTaskType(Task task) {
        String type = "";
        if (task instanceof Todo) {
            type = "T";
        } else if (task instanceof Event) {
            type = "E";
        } else if (task instanceof Deadline) {
            type = "D";
        }
        return type;
    }

    static String writeText() {
        String text = "";
        for (Task task : tasks) {
            String type = findTaskType(task);
            String date = task.getDate();
            String name = task.getName();
            text += type + FILE_SEPARATOR + name + FILE_SEPARATOR + date
                    + System.lineSeparator();
        }
        return text;
    }

    static void saveFile() {
        try {
            String text = writeText();
            writeToFile(text);
        } catch (IOException e) {
            System.out.println ("The file does not exist yet!");
        }
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
            addTodo(line);
        } else if (command.matches(COMMAND_WORD_EVENT)) {
            addEvent(line);
        } else if (command.matches(COMMAND_WORD_DEADLINE)) {
            addDeadline(line);
        } else if (command.matches(COMMAND_WORD_DELETE)) {
            deleteTask(line);
        } else {
            errorMessage();
        }
    }

    static void handleFile(String line) {
        String[] words = line.split(FILE_SEPARATOR);
        String taskType = words[0];
        String taskName = words[1];
        Task task = new Task("");
        if (taskType.matches("T")) {
            task = new Todo(taskName);
            tasks.add(task);
        } else if (taskType.matches("E")) {
            String taskDate = words[2];
            task = new Event(taskName, taskDate);
            tasks.add(task);
        } else if (taskType.matches("D")) {
            String taskDate = words[2];
            task = new Deadline(taskName, taskDate);
            tasks.add(task);
        }
        taskCounter += 1;
        printAddMessage(task);
        saveFile();
    }

    static void loadFile() {
        try {
            File data = new File(FILE_PATH);
            Scanner fileReader = new Scanner(data);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                handleFile(line);
            }
        } catch(FileNotFoundException e) {
            System.out.println("No previous usage of Duke");
            System.out.println("Loading a new save");
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
        loadFile();
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
        System.out.println("added: " + task.getName());
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
        System.out.println("[" + task.getStatusIcon() + "] " + task.getName());
        System.out.println(HORIZONTAL_LINE);
    }

    // Method to unmark task as undone
    public static void unmarkTask(Task task) {
        System.out.println(HORIZONTAL_LINE);
        task.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.getName());
        System.out.println(HORIZONTAL_LINE);
    }

    static boolean checkDivider(String line) throws DukeException {
        if (!line.contains(LINE_DIVIDER)) {
            throw new DukeException("Don't forget to add a date!");
        }
        return true;
    }

    // Method to add todo, deadline and event tasks
    static void printAddMessage(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCounter + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }
    public static void addTodo(String line) {
        try {
            checkSpace(line);
        } catch (IllegalArgumentException e) {
            System.out.println("Don't forget to include your task!");
        }

        int spaceIndex = line.indexOf(" ");
        String taskName = line.substring(spaceIndex + 1);
        Todo task = new Todo(taskName);
        tasks.add(task);
        taskCounter += 1;
        printAddMessage(task);
        saveFile();
    }

    public static void addEvent(String line) {
        try {
            checkSpace(line);
            checkDivider(line);
        } catch (IllegalArgumentException e) {
            System.out.println("Don't forget to include your task!");
        } catch (DukeException e) {
            System.out.println("Don't forget to include your deadline!");
        }

        int spaceIndex = line.indexOf(" ");
        int dividerIndex = line.indexOf(LINE_DIVIDER);
        String taskName = line.substring(spaceIndex + 1, dividerIndex);
        String taskDate = line.substring(dividerIndex);
        Event task = new Event(taskName, taskDate);
        tasks.add(task);
        taskCounter += 1;
        printAddMessage(task);
        saveFile();
    }

    public static void addDeadline(String line) {
        try {
            checkSpace(line);
            checkDivider(line);
        } catch (IllegalArgumentException e) {
            System.out.println("Don't forget to include your task!");
        } catch (DukeException e) {
            System.out.println("Don't forget to include the deadline!");
        }

        int spaceIndex = line.indexOf(" ");
        int dividerIndex = line.indexOf(LINE_DIVIDER);
        String taskName = line.substring(spaceIndex + 1, dividerIndex);
        String taskDate = line.substring(dividerIndex);
        Deadline task = new Deadline(taskName, taskDate);
        tasks.add(task);
        taskCounter += 1;
        printAddMessage(task);
        saveFile();
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