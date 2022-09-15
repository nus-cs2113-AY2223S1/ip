package duke;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();
    public static final String PATHNAME = "data/duke.txt";

    private static void writeToFile(String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (int i = 0; i < taskList.size(); i++) {
            fw.write(taskList.get(i).printToFile());
        }
        fw.close();
    }

    public static void addTask(Task task, boolean isPrint) {
        taskList.add(task);
        try {
            writeToFile(PATHNAME);
        } catch (IOException ioException) {
            System.out.println("Error updating file");
        }
        if (isPrint) {
            System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n",
                    task.print(), taskList.size());
        }
    }

    private static void loadFromFile(String path) throws FileNotFoundException {
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

    public  static  void todo(String line, String[] words) {
        if (words.length >= 2) {
            String task = line.substring(line.indexOf(' ') + 1);
            Todo todo = new Todo(task, false);
            addTask(todo, true);
        } else {
            System.out.println("Usage: todo <task>");
        }
    }

    public static void deadline(String line) {
        try {
            String task = line.substring(line.indexOf(' ')+1, line.indexOf("/by")-1);
            String date = line.substring(line.indexOf("/by")+4);
            Deadline deadline = new Deadline(task, false, date);
            addTask(deadline, true);
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            System.out.println("Usage: deadline <task> /by <date>");
        }
    }

    public static void event(String line) {
        try {
            String task = line.substring(line.indexOf(' ')+1, line.indexOf("/at")-1);
            String date = line.substring(line.indexOf("/at")+4);
            Event event = new Event(task, false, date);
            addTask(event, true);
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            System.out.println("Usage: event <task> /at <date>");
        }
    }

    public static void list() throws ArrayEmptyException {
        if (taskList.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, taskList.get(i).print());
            }
        } else {
            throw new ArrayEmptyException();
        }
    }

    public static void mark(boolean isMark, String[] words) {
        if (words.length == 2) {
            try {
                int d = Integer.parseInt(words[1]);
                if (d > 0 && d <= taskList.size()) { // Valid
                    if (taskList.get(d-1).getStatus()) { // Task is done
                        if (isMark) {
                            System.out.printf("This task is already done:\n" + "%s\n",
                                    taskList.get(d - 1).print());
                        } else {
                            taskList.get(d - 1).setDone(false);
                            System.out.printf("OK, I've marked this task as not done yet:\n" + "%s\n",
                                    taskList.get(d - 1).print());
                        }
                    } else { // Task is not done
                        if (isMark) {
                            taskList.get(d - 1).setDone(true);
                            System.out.printf("Nice! I've marked this task as done:\n" + "%s\n",
                                    taskList.get(d - 1).print());
                        } else {
                            System.out.printf("This task is already not done:\n" + "%s\n",
                                    taskList.get(d - 1).print());
                        }
                    }
                    writeToFile(PATHNAME);
                } else { // Invalid index
                    if (taskList.size() == 0) {
                        System.out.println("Error: list is empty");
                    } else {
                        System.out.printf("Error: enter an integer between 1 - %d\n", taskList.size());
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Usage: mark <integer>");
            } catch (IOException ioException) {
                System.out.println("Error updating file");
            }
        } else {
            System.out.println("Usage: mark <integer>");
        }
    }

    public static void delete(String[] words) {
        if (words.length == 2) {
            if (taskList.size() > 0) {
                try {
                    int d = Integer.parseInt(words[1]);
                    Task tempTask = taskList.get(d - 1);
                    taskList.remove(d - 1);
                    System.out.printf("Noted. I've removed this task:\n" + "%s\n" +
                            "Now you have %d tasks in the list.\n", tempTask.print(), taskList.size());
                    writeToFile(PATHNAME);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.printf("Error: enter an integer between 1 - %d\n", taskList.size());
                } catch (NumberFormatException ex) {
                    System.out.println("Usage: delete <integer>");
                } catch (IOException ioException) {
                    System.out.println("Error updating file");
                }
            } else {
                System.out.println("Error: list is empty!");
            }
        } else {
            System.out.println("Usage: delete <integer>");
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
        try {
            loadFromFile(PATHNAME);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Error reading data from file: File or directory does not exist. Creating new file.");
            try {
                File file = new File(PATHNAME);
                if (!file.getParentFile().mkdirs()) {
                    System.out.println("Error creating parent folder(s)");
                }
                if (file.createNewFile()) {
                    System.out.printf("File created at %s\n", PATHNAME);
                } else {
                    System.out.printf("File already exists at %s\n", PATHNAME);
                }
            } catch (IOException ioException) {
                System.out.printf("Error creating file: Could not create file at %s\n", PATHNAME);
                ioException.printStackTrace();
            }
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
//                try {
//                    writeToFile(pathname);
//                } catch (IOException ioException) {
//                    System.out.println("Error writing to file");
//                }
                bye();
                break;
            } else if (!line.equals("")) {
                String[] words = line.split("\\s+");
                if (words.length == 0) {
                    System.out.println("Enter a valid command.");
                    printLine();
                    continue;
                }
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
                case "delete":
                    delete(words);
                    break;
                default:
                    System.out.println("Oops! I don't understand that command. Try again.");
                    break;
                }
            } else {
                System.out.println("Oops! I don't understand that command. Try again.");
            }
            printLine();
        }
    }
}
