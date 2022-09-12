package duke;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Duke {

    public static final int MAX_SIZE = 100;

    public static int listSize = 0;
    public static Task[] taskList = new Task[MAX_SIZE];

    public static void addTask(Task task, boolean isPrint) throws ArrayOutOfBoundException {
        if (listSize < MAX_SIZE) {
            taskList[listSize] = task;
            listSize++;
            if (isPrint) {
                System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n",
                        task.print(), listSize);
            }
        } else {
            throw new ArrayOutOfBoundException();
        }
    }

    private static void loadFromFile(String path) throws FileNotFoundException, ArrayOutOfBoundException {
        File f = new File(path);
        System.out.println("Path: " + f.getAbsolutePath());
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] words = s.nextLine().split("\\|");
            switch (words[0]) {
            case "T":
                Todo todo = new Todo(words[2], words[1].equals("1"));
                addTask(todo, false);
                break;
            case "D":
                Deadline deadline = new Deadline(words[2], words[1].equals("1"), words[3]);
                addTask(deadline, false);
                break;
            case "E":
                Event event = new Event(words[2], words[1].equals("1"), words[3]);
                addTask(event, false);
                break;
            default:
                System.out.println("Error reading data from file: Invalid format");
                break;
            }
        }
    }

    private static void writeToFile(String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (int i = 0; i < listSize; i++) {
            fw.write(taskList[i].printToFile());
        }
        fw.close();
    }

    public  static  void todo(String line, String[] words) {
        if (words.length >= 2) {
            String task = line.substring(line.indexOf(' ') + 1);
            Todo todo = new Todo(task, false);
            try {
                addTask(todo, true);
            } catch (ArrayOutOfBoundException arrayOutOfBoundException) {
                System.out.println("Error! Too many items in list!");
            }
        } else {
            System.out.println("Usage: todo <task>");
        }
    }

    public static void deadline(String line) {
        try {
            String task = line.substring(line.indexOf(' ')+1, line.indexOf("/by")-1);
            String date = line.substring(line.indexOf("/by")+4);
            Deadline deadline = new Deadline(task, false, date);
            try {
                addTask(deadline, true);
            } catch (ArrayOutOfBoundException arrayOutOfBoundException) {
                System.out.println("Error! Too many items in list!");
            }
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            System.out.println("Usage: deadline <task> /by <date>");
        }
    }

    public static void event(String line) {
        try {
            String task = line.substring(line.indexOf(' ')+1, line.indexOf("/at")-1);
            String date = line.substring(line.indexOf("/at")+4);
            Event event = new Event(task, false, date);
            try {
                addTask(event, true);
            } catch (ArrayOutOfBoundException arrayOutOfBoundException) {
                System.out.println("Error! Too many items in list!");
            }
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            System.out.println("Usage: event <task> /at <date>");
        }
    }

    public static void list() throws ArrayEmptyException {
        if (listSize > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < listSize; i++) {
                System.out.printf("%d. %s\n", i + 1, taskList[i].print());
            }
        } else {
            throw new ArrayEmptyException();
        }
    }

    public static void mark(boolean isMark, String[] words) {
        if (words.length == 2) {
            try {
                int d = Integer.parseInt(words[1]);
                if (d > 0 && d <= listSize) { // Valid
                    if (taskList[d-1].getStatus()) { // Task is done
                        if (isMark) {
                            System.out.printf("This task is already done:\n" + "%s\n",
                                    taskList[d - 1].print());
                        } else {
                            taskList[d - 1].setDone(false);
                            System.out.printf("OK, I've marked this task as not done yet:\n" + "%s\n",
                                    taskList[d - 1].print());
                        }
                    } else { // Task is not done
                        if (isMark) {
                            taskList[d - 1].setDone(true);
                            System.out.printf("Nice! I've marked this task as done:\n" + "%s\n",
                                    taskList[d - 1].print());
                        } else {
                            System.out.printf("This task is already not done:\n" + "%s\n",
                                    taskList[d-1].print());
                        }
                    }
                } else { // Invalid index
                    if (listSize == 0) {
                        System.out.println("Error: list is empty");
                    } else {
                        System.out.printf("Error: enter an integer between 1 - %d\n", listSize);
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Usage: mark <integer>");
            }
        } else {
            System.out.println("Usage: mark <integer>");
        }
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {

        // Read file
        String pathname = new String("data/duke.txt");
        try {
            loadFromFile(pathname);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Error reading data from file: File or directory does not exist. Creating new file.");
            try {
                File file = new File(pathname);
                if (!file.getParentFile().mkdirs()) {
                    System.out.println("Error creating parent folder(s)");
                }
                if (file.createNewFile()) {
                    System.out.printf("File created at %s\n", pathname);
                } else {
                    System.out.printf("File already exists at %s\n", pathname);
                }
            } catch (IOException ioException) {
                System.out.printf("Error creating file: Could not create file at %s\n", pathname);
                ioException.printStackTrace();
            }
        } catch (ArrayOutOfBoundException arrayOutOfBoundException) {
            System.out.printf("Error reading from file: Number of tasks exceeds %d\n", MAX_SIZE);
        }

        // Wait for input
        String line;
        Scanner in = new Scanner(System.in);

        // Greet user
        printLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printLine();

        // Handle user input
        while (true) {
            line = in.nextLine();
            printLine();
            if (line.equals("bye")) {
                try {
                    writeToFile(pathname);
                } catch (IOException ioException) {
                    System.out.println("Error writing to file");
                }
                bye();
                break;
            } else if (!line.equals("")) {
                String[] words = line.split("\\s+");
                switch (words[0]) {
                case "todo":
                    todo(line, words);
                    break;
                case "deadline":
                    deadline(line);
                    break;
                case "event":
                    event(line);
                    break;
                case "list":
                    try {
                        list();
                    } catch (ArrayEmptyException arrayEmptyException) {
                        System.out.println("No items in list! Type something to add to list.");
                    }
                    break;
                case "mark":
                    mark(true, words);
                    break;
                case "unmark":
                    mark(false, words);
                    break;
                }
            }
            printLine();
        }
    }
}
