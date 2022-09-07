package duke;

/*
 * Import Java Packages
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Import Duke Packages
 */
import duke.command.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Duke {
    /**
     * Global Constant Section
     */
    private static final int MAXIMUM_NUMBER_OF_TASKS = 100;
    private static final String MESSAGE_PROMPT = ">>> ";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_HELLO = "Hello! I'm a chatbot Duke made by Than Duc Huy\n"
            + "Type the command to start interacting with Duke";
    private static final String MESSAGE_COMMAND_LISTS = "Supported commands: list, mark, unmark, todo, deadline, event, bye";
    private static final String MESSAGE_DIVIDER = "===============================================================================";
    private static final String MESSAGE_DIVIDER_LIST = "=====================================LIST======================================";
    private static final String MESSAGE_UNKNOWN_COMMAND = "Unknown Command";
    private static final String ERROR_MESSAGE_BYE = "";
    private static final String ERROR_MESSAGE_LIST = "";
    private static final String ERROR_MESSAGE_MARK = "Syntax for (un)mark \n\t>>> (un)mark <item index number> \nNote: item index must exist in the current list";
    private static final String ERROR_MESSAGE_TODO = "Syntax for todo \n\t>>>todo <task>";
    private static final String ERROR_MESSAGE_DEADLINE = "Syntax for deadline \n\t>>>deadline <task> / <date of deadline>";
    private static final String ERROR_MESSAGE_EVENT = "Syntax for event \n\t>>>event <task> / <date of event>";
    private static final String STORAGE_DIRECTORY = "./storage";
    private static final String STORAGE_FILE = "./storage/savedTaskList.txt";
    private static final String MESSAGE_NEW_FILE = "Storage file created";
    private static final String MESSAGE_OVERWRITE_FILE = "Storage file overwritten";
    private static final String MESSAGE_LOAD_FILE = "Storage file loaded";
    private static final String MESSAGE_NO_FILE = "There is no existing file!";
    private static final String MESSAGE_WRONG_FILE = "The storage files entry is invalid. Please save to overwrite";
    /**
     * Global Variables Section
     */
    static Scanner in = new Scanner(System.in);
    static Task[] tasksList = new Task[MAXIMUM_NUMBER_OF_TASKS];
    static boolean isRunning = true;

    /**
     * Main Function
     *
     * @param args
     */
    public static void main(String[] args) {
        helloMessage();
        load();
        while (isRunning) {
            System.out.print(MESSAGE_PROMPT);
            parseCommand();
        }
    }

    /**
     * Global Methods Sections
     */

    private static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    private static void showToUserDivider(String... message) {
        showToUser(MESSAGE_DIVIDER);
        showToUser(message);
        showToUser(MESSAGE_DIVIDER);
    }

    private static void parseCommand() {
        String line = in.nextLine();
        String[] parsedLine = line.split(" ");
        String commandWord = parsedLine[0].toLowerCase();

        switch (commandWord) {
        case "bye":
            exit();
            break;
        case "list":
            list();
            break;
        case "mark":
        case "unmark":
            mark(parsedLine);
            break;
        case "todo":
        case "deadline":
        case "event":
            add(line);
            break;
        case "save":
            save();
            break;
        default:
            unknownCommand();
            break;
        }
    }

    private static void commandErrorHandler(String errorMessage) {
        switch (errorMessage) {
        case "bye":
            showToUserDivider(ERROR_MESSAGE_BYE);
            break;
        case "list":
            showToUserDivider(ERROR_MESSAGE_LIST);
            break;
        case "mark":
            showToUserDivider(ERROR_MESSAGE_MARK);
            break;
        case "todo":
            showToUserDivider(ERROR_MESSAGE_TODO);
            break;
        case "deadline":
            showToUserDivider(ERROR_MESSAGE_DEADLINE);
            break;
        case "event":
            showToUserDivider(ERROR_MESSAGE_EVENT);
            break;
        default:
            showToUserDivider(errorMessage);
            break;
        }
    }

    private static void exit() {
        isRunning = false;
        exitMessage();
    }

    private static void unknownCommand() {
        showToUser(MESSAGE_DIVIDER, MESSAGE_UNKNOWN_COMMAND, MESSAGE_COMMAND_LISTS, MESSAGE_DIVIDER);
    }

    private static void list() {
        showToUser(MESSAGE_DIVIDER_LIST);
        for (int i = 0; i < Task.numberOfTasks; i++) {
            System.out.print((i + 1) + ".");
            tasksList[i].printTask();
        }
        showToUser(MESSAGE_DIVIDER);
    }

    private static void add(String line) {
        String newTaskType = line.split(" ")[0].toLowerCase();
        String[] taskDescriptors = line.replace(newTaskType, "").trim().split("/");
        switch (newTaskType) {
        case "todo":
            addTodo(taskDescriptors);
            break;
        case "deadline":
            addDeadline(taskDescriptors);
            break;
        case "event":
            addEvent(taskDescriptors);
            break;
        default:
            break;
        }
    }

    private static void addTodo(String[] taskDescriptors) {
        try {
            if (taskDescriptors[0].trim().isEmpty()) {
                throw new DukeException("No Task Description");
            }
            tasksList[Task.numberOfTasks] = new Todo(taskDescriptors[0]);
            addTaskMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            commandErrorHandler("todo");
        } catch (DukeException e) {
            commandErrorHandler("todo");
        }

    }

    private static void addDeadline(String[] taskDescriptor) {
        try {
            if (taskDescriptor[0].trim().isEmpty()) {
                throw new DukeException("Error: No Task Description");
            }
            tasksList[Task.numberOfTasks] = new Deadline(taskDescriptor[0], taskDescriptor[1].trim());
            addTaskMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            commandErrorHandler("deadline");
        } catch (DukeException e) {
            commandErrorHandler("deadline");
        }
    }

    private static void addEvent(String[] taskDescriptor) {
        try {
            if (taskDescriptor[0].trim().isEmpty()) {
                throw new DukeException("Error: No Task Description");
            }
            tasksList[Task.numberOfTasks] = new Event(taskDescriptor[0], taskDescriptor[1].trim());
            addTaskMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            commandErrorHandler("event");
        } catch (DukeException e) {
            commandErrorHandler("event");
        }
    }

    private static void addTaskMessage() {
        showToUser(MESSAGE_DIVIDER, "Got it! You just add a new Task");
        System.out.print("\t");
        tasksList[Task.numberOfTasks - 1].printTask();
        showToUser("Number of tasks in the list: " + Task.numberOfTasks);
        showToUser(MESSAGE_DIVIDER);
    }

    public static void mark(String[] parsedLine) {
        try {
            int index = Integer.parseInt(parsedLine[1]);
            if (parsedLine[0].equalsIgnoreCase("mark")) {
                tasksList[index - 1].isDone = true;
            } else {
                tasksList[index - 1].isDone = false;
            }
            list();
        } catch (NumberFormatException e) {
            commandErrorHandler("mark");
        } catch (ArrayIndexOutOfBoundsException e) {
            commandErrorHandler("mark");
        } catch (NullPointerException e) {
            commandErrorHandler("mark");
        }

    }

    private static void exitMessage() {
        showToUser(MESSAGE_DIVIDER, MESSAGE_EXIT, MESSAGE_DIVIDER);
    }

    private static void helloMessage() {
        showToUser(MESSAGE_DIVIDER, MESSAGE_HELLO, MESSAGE_COMMAND_LISTS, MESSAGE_DIVIDER);
    }

    private static void save() {
        try {
            FileWriter fileWrite = new FileWriter(STORAGE_FILE);
            for (int i = 0; i < Task.numberOfTasks; i++) {
                fileWrite.write(tasksList[i].toSave());
            }
            fileWrite.close();
        } catch (IOException e) {
            File newfile = new File(STORAGE_DIRECTORY);
            newfile.mkdir();
            showToUser(MESSAGE_NEW_FILE);
            save();
        } finally {
            showToUser(MESSAGE_OVERWRITE_FILE);
        }
    }

    private static void load() {
        try {
            File file = new File(STORAGE_FILE);
            Scanner fileRead = new Scanner(file);
            showToUser(MESSAGE_LOAD_FILE);
            while (fileRead.hasNext()) {
                fileParser(fileRead.nextLine());
            }
            fileRead.close();
        } catch (FileNotFoundException e) {
            showToUser(MESSAGE_NO_FILE);
        } catch (ArrayIndexOutOfBoundsException e) {
            showToUser(MESSAGE_WRONG_FILE);
        }
    }

    private static void fileParser(String newLine) {
        /*
         * Index 0: type 1: isDone 2: description 3: date
         */
        String[] parsedLine = newLine.split(Task.LIMITER);

        switch (parsedLine[0]) {
        case Todo.TYPE_TODO:
            tasksList[Task.numberOfTasks] = new Todo(parsedLine[2]);
            tasksList[Task.numberOfTasks - 1].isDone = Boolean.valueOf(parsedLine[1]);
            addTaskMessage();
            break;
        case Deadline.TYPE_DEADLINE:
            tasksList[Task.numberOfTasks] = new Deadline(parsedLine[2], parsedLine[3]);
            tasksList[Task.numberOfTasks - 1].isDone = Boolean.valueOf(parsedLine[1]);
            addTaskMessage();
            break;
        case Event.TYPE_EVENT:
            tasksList[Task.numberOfTasks] = new Event(parsedLine[2], parsedLine[3]);
            tasksList[Task.numberOfTasks - 1].isDone = Boolean.valueOf(parsedLine[1]);
            addTaskMessage();
            break;
        default:
            break;
        }

    }
}
