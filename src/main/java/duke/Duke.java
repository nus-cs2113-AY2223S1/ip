package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    private static String filePath = "./data/duke.txt";

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


    public static void markOrUnmarkTask(String command) throws IOException {
        String markOrUnmark = command.split(" ")[0];
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        tasks.get(index).setStatusIcon(markOrUnmark);
        tasks.get(index).printTask(index);

        String task = tasks.get(index).getTask();

        editTask(index, markOrUnmark);
    }

    public static void printNumberOfTask() {
        if (tasks.size() > 1) {
            System.out.println("You now have " + tasks.size() + " tasks.");
        } else {
            System.out.println("You now have " + tasks.size() + " task.");
        }
    }


    public static void editTask(int index, String markOrUnmark) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String text = "";
        int currentIndex = 0;
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            if (index == currentIndex) {
                text += changeMarkValue(currentLine, markOrUnmark) + System.lineSeparator();
            } else {
                text += currentLine + System.lineSeparator();
            }
            currentIndex += 1;
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();

    }


    public static String changeMarkValue(String currentLine, String markOrUnmark) {
        if (markOrUnmark.equals("mark")) {
            return currentLine.replace(" | 0 | ", " | 1 | ");
        }
        return currentLine.replace(" | 1 | ", " | 0 | ");
    }


    public static void addTask(String command) {
        boolean isTodo = command.contains("todo");
        boolean isEvent = command.contains("event");
        boolean isDeadline = command.contains("deadline");

        try {
            String taskType = "";
            if (isTodo) {
                tasks.add(new Todo(command.replace("todo ", "")));
                taskType = "todo";
            } else if (isEvent) {
                tasks.add(new Event(command.replace("event ", "")));
                taskType = "event";
            } else if (isDeadline) {
                tasks.add(new Deadline(command.replace("deadline ", "")));
                taskType = "deadline";
            }

            addCompleteMessage(taskType);
            appendToFile(taskType);

        } catch (EmptyDescriptionException emptyException) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        } catch (NoSpecficTimeException e) {
            System.out.println("No time is entered. Please re-enter and include '/at'.");
        } catch (NoSpecificDeadlineException e) {
            System.out.println("No deadline is entered. Please re-enter and include '/by'. ");
        }

    }

    public static void reAddTask(String command) {
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


    public static void addCompleteMessage(String type) {
        boolean isTodo = type.equals("todo");
        boolean isEvent = type.equals("event");
        boolean isDeadline = type.equals("deadline");

        System.out.println("Got it. I added to the list.");
        int lastIndex = tasks.size() - 1;

        if (isTodo) {
            System.out.println("  [D][ ] " + tasks.get(lastIndex).getTask());
        } else if (isEvent) {
            System.out.println("  [E][ ] " + tasks.get(lastIndex).getTask());
        } else if (isDeadline){
            System.out.println("  [T][ ] " + tasks.get(lastIndex).getTask());
        }


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

    private static void appendToFile(String taskType) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            int lastIndex = tasks.size() - 1;
            String appendTask = tasks.get(lastIndex).getTask();

            if (taskType.equals("todo")) {
                fw.write("T | 0 | " + appendTask + System.lineSeparator());
            } else if (taskType.equals("deadline")) {
                fw.write("D | 0 | " + appendTask + System.lineSeparator());
            } else {
                fw.write("E | 0 | " + appendTask + System.lineSeparator());
            }
            fw.close();

        } catch (IOException exception) {
            System.out.println("Error writing to file.");
        }
    }

    public static void load() throws FileNotFoundException {

        File f = new File(filePath);

        if (f.exists()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String entry = s.nextLine();
                String[] entries = entry.split("\\s\\|\\s");

                String entryType = entries[0];
                boolean isMark = entries[1].equals("1");
                String entryText = entries[2];
                String command;
                if (entryType.equals("T")) {
                    command = "todo " + entryText;
                } else if (entryType.equals("D")) {
                    command = "deadline " + entryText;
                } else {
                    command = "event " + entryText;
                }

                reAddTask(command);
                if (isMark) {
                    tasks.get(tasks.size() -1).setStatusIcon("mark");
                }
            }
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        boolean isGoodbye;
        String command;

        showWelcomeMessage();
        load();

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
            } catch (IOException e) {
                System.out.println("Unable to write to file");
            }
        } while (!isGoodbye);
    }
}
