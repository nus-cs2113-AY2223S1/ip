package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static Boolean isRunning = true;
    public static String inputLine;
    public static ArrayList<Task> tasks = new ArrayList<>();

    protected static final String PATHNAME = "data/duke.txt";

    public static void printGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\nWhat can I do for you?");
    }

    public static String findTaskSpecifics(String details) throws IllegalTaskException {
        String[] taskDetails = details.split(" ");
        if (taskDetails.length < 2) {
            throw new IllegalTaskException();
        }
        String typeOfTask = details.substring(details.indexOf(" "), details.indexOf("/"));
        return typeOfTask;
    }

    public static void printDefaultTaskResponse(int number, ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:\n" + tasks.get(number - 1).toString() +
                "\n" + "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        isRunning = false;
    }

    public static void insertDeadlineTask(String inputLine, ArrayList<Task> tasks) {
        try {
            String deadlineSpecifics = findTaskSpecifics(inputLine);
            String deadline = inputLine.substring(inputLine.indexOf("/by") + 3);
            addTask(new Deadline(deadlineSpecifics, false, deadline));
            printDefaultTaskResponse(tasks.size(), tasks);
        } catch (IllegalTaskException exception) {
            System.out.println("Uh oh, the description of a deadline task cannot be empty. Try again.");
        }
    }

    public static void insertToDoTask(String inputLine, ArrayList<Task> tasks) {
        try {
            String todoSpecifics = inputLine.substring(inputLine.indexOf(" "));
            addTask(new Todo(todoSpecifics, false));
            printDefaultTaskResponse(tasks.size(), tasks);
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println("Uh oh, the description of a todo task cannot be empty. Try again.");
        }
    }

    public static void insertEventTask(String inputLine, ArrayList<Task> tasks) {
        try {
            String eventSpecifics = findTaskSpecifics(inputLine);
            String eventDate = inputLine.substring(inputLine.indexOf("/at") + 3);
            addTask(new Event(eventSpecifics, false, eventDate));
            printDefaultTaskResponse(tasks.size(), tasks);
        } catch (IllegalTaskException exception) {
            System.out.println("Uh oh, the description of a event task cannot be empty. Try again");
        }
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).toString());
        }
    }

    public static void markTask(String[] taskDescriptions, ArrayList<Task> tasks) {
       try {
           int taskToMark = Integer.parseInt(taskDescriptions[1]) - 1;
           System.out.println("Nice! I've marked this task as done:\n");
           tasks.get(taskToMark).markAsDone();
           insertIntoFile();
           System.out.println(tasks.get(taskToMark).toString());
       } catch (IOException ioException) {
           System.out.println("Error: Could not mark task.");
       }
    }

    public static void unmarkTask(String[] taskDescriptions, ArrayList<Task> tasks) {
        try {
            int taskToUnmark = Integer.parseInt(taskDescriptions[1]) - 1;
            System.out.println("OK, I've marked this task as not done yet:\n");
            tasks.get(taskToUnmark).unmark();
            insertIntoFile();
            System.out.println(tasks.get(taskToUnmark).toString());
        } catch (IOException ioException) {
            System.out.println("Error: Could not unmark task.");
        }
    }

    public static void readAndInitialiseFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            inputLine = s.nextLine();
            String[] details = inputLine.split("\\|");
            switch (details[0]) {
            case "T":
                addTask(new Todo(details[2], details[1].equals("1")));
                break;
            case "D":
                addTask(new Deadline(details[2], details[1].equals("1"), details[3]));
                break;
            case "E":
                addTask(new Event(details[2], details[1].equals("1"), details[3]));
                break;
            default:
                System.out.println("Error reading from file");
                break;
            }

        }
    }

    public static void insertIntoFile() throws IOException {
        FileWriter fw = new FileWriter(PATHNAME);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).convertToFileFormat());
        }
        fw.close();
    }

    public static void addTask(Task task) {
        tasks.add(task);
        try {
            insertIntoFile();
        } catch (IOException ioexception) {
            System.out.println("Error: file not updated");
        }
    }

    public static void deleteTask(String[] taskDescriptions, ArrayList<Task> tasks){
        try {
            int taskToDelete = Integer.parseInt(taskDescriptions[1]);
            System.out.println("Noted. I've removed this task:\n" + tasks.get(taskToDelete-1).toString());
            tasks.remove(taskToDelete);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("This task number is invalid, try again.");
        }

    }
    public static void runTaskList() {
        Scanner in = new Scanner(System.in);
        while (isRunning) {
            inputLine = in.nextLine();
            String[] details = inputLine.split(" ");
            String command = details[0];
            switch (command) {
            case "bye":
                sayGoodbye();
                break;
            case "deadline":
                insertDeadlineTask(inputLine, tasks);
                break;
            case "todo":
                insertToDoTask(inputLine, tasks);
                break;
            case "event":
                insertEventTask(inputLine, tasks);
                break;
            case "mark":
                markTask(details, tasks);
                break;
            case "unmark":
                unmarkTask(details, tasks);
                break;
            case "list":
                printTaskList(tasks);
                break;
            case "delete":
                deleteTask(details,tasks);
                break;
            default:
                System.out.println("You have entered an invalid command, please try again.");
                break;
            }
        }
    }

    public static void createFile() {
        try {
            readAndInitialiseFileContents(PATHNAME);
        } catch (FileNotFoundException exception) {
            System.out.println("Error reading data from file: File or directory does not exist.\n");
            try {
                System.out.println("Trying to create file now.");
                File file = new File(PATHNAME);
                if (!file.getParentFile().mkdirs()) {
                    System.out.println("Error: Directory could not be created");
                }
                if (file.createNewFile()) {
                    System.out.println("File is created at: " + PATHNAME);
                } else {
                    System.out.println("File already exists at: " + PATHNAME);
                }
            } catch (IOException ioException) {
                System.out.println("Error: File could not be created");
                ioException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        createFile();
        printGreetings();
        runTaskList();
    }
}
