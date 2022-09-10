package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public enum TaskType {
        LIST,
        MARK,
        ADD,
        BYE,
        DELETE,
        ERROR
    }
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void showWelcomeMessage() {
        // Pikachu logo to show in front
        final String logo =
                        "⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿\n";

        System.out.println(logo);
        System.out.println("Hello! How can I help you?");
    }

    public static void markOrUnmarkTask(String command) {
        String markOrUnmark = command.split(" ")[0];
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        tasks.get(index).setStatusIcon(markOrUnmark);
        tasks.get(index).printTask(index);
    }

    public static void printNumberOfTask() {
        if (tasks.size() > 1) {
            System.out.println("You now have " + tasks.size() + " tasks.");
        } else {
            System.out.println("You now have " + tasks.size() + " task.");
        }
    }

    public static void addTask(String command) {
        boolean isTodo = command.contains("todo");
        boolean isEvent = command.contains("event");
        boolean isDeadline = command.contains("deadline");


        try {
            if (isTodo) {
                tasks.add(new Todo(command.replace("todo", "").trim()));
            } else if (isEvent) {
                tasks.add(new Event(command.replace("event", "").trim()));
            } else if (isDeadline) {
                tasks.add(new Deadline(command.replace("deadline", "").trim()));
            }
            printNumberOfTask();
        } catch (EmptyDescriptionException emptyException) {
            System.out.println("OOPS!!! The description cannot be empty.");
        } catch (NoSpecificDeadlineException e) {
            System.out.println("You did not enter the deadline. Please re-enter the deadline by including '/by'.");
        } catch (NoSpecficTimeException e) {
            System.out.println("You did not specify the time for the event. Please re-enter the event by including '/at'. ");
        }
    }

    public static void printDeletedTask(int deleteNumber) {
        System.out.println("  [T][ ] " + tasks.get(deleteNumber).getTask());
    }

    public static void deleteTask(String command) {
        int deleteNumber = Integer.parseInt(command.split(" ")[1]) - 1;
        printDeletedTask(deleteNumber);
        tasks.remove(deleteNumber);
        printNumberOfTask();
    }

    public static void farewellMessage() {
        //Goodbye
        System.out.println("Bye bye!");
    }

    public static void printAllTasks() {
        for (int i = 0; i < tasks.size(); i += 1) {
            tasks.get(i).printTask(i);
        }
    }

    public static TaskType identifyTask(String command) {
        String commandType = command.split(" ")[0];

        switch (commandType) {
        case "list":
            return TaskType.LIST;
        case "mark":
            return TaskType.MARK;
        case "todo":
        case "deadline":
        case "event":
            return TaskType.ADD;
        case "bye":
            return TaskType.BYE;
        case "delete":
            return TaskType.DELETE;
        default:
            return TaskType.ERROR;
        }
    }


    public static void main(String[] args) {
        int index = 0;
        boolean isGoodbye;
        String command;

        showWelcomeMessage();

        Scanner in = new Scanner(System.in);

        // Loop that stops only when user enter "bye"
        do {
            command = in.nextLine();
            isGoodbye = command.contains("bye");

            // If user select list, show all the task
            try {
                TaskType taskType = identifyTask(command);
                switch (taskType) {
                case MARK:
                    markOrUnmarkTask(command);
                    break;
                case LIST:
                    printAllTasks();
                    break;
                case ADD:
                    addTask(command);
                    break;
                case BYE:
                    farewellMessage();
                    break;
                case DELETE:
                    deleteTask(command);
                    break;
                case ERROR:
                    throw new IncorrectCommandException();
                }
            } catch (IncorrectCommandException incorrectCommand) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } while (!isGoodbye);
    }
}
