package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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
        ERROR
    }
    public static final int maxLength = 100;
    private static int index = 0;
    private static Task[] tasks = new Task[maxLength];
    private static String filePath = "./data/duke.txt";

    public static void showWelcomeMessage() {
        // Pikachu logo to show in front
        final String logo =
                        "⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿\n";

        System.out.println(logo);
        System.out.println("{\\__／}\n" +
                "(• w •）\n" +
                "／ >\uDDA4 Hey! How can I help you?");
    }
    public static void markOrUnmarkTask(String command) throws IOException {
        String markOrUnmark = command.split(" ")[0];
        int taskNumber = Integer.parseInt(command.split(" ")[1]);
        int taskIndex = taskNumber - 1;
        String task = tasks[taskIndex].getTask();

        tasks[taskIndex].setStatusIcon(markOrUnmark);
        tasks[taskIndex].printTask(taskIndex);
        editTask(task, markOrUnmark);
    }


    public static void editTask(String task, String markOrUnmark) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String text = "";
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            if (currentLine.contains(task)) {
                text += changeMarkValue(currentLine, markOrUnmark) + System.lineSeparator();
            } else {
                text += currentLine + System.lineSeparator();
            }
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
    public static void printNumOfItems(int numOfItem) {
        if (numOfItem > 1) {
            System.out.println("You now have " + numOfItem + " tasks.");
        } else {
            System.out.println("You now have " + numOfItem + " task.");
        }
    }
    public static void addTask(String command) {
        boolean isTodo = command.contains("todo");
        boolean isEvent = command.contains("event");
        boolean isDeadline = command.contains("deadline");


        try {
            String taskType = "";
            if (isTodo) {
                tasks[index] = new Todo(command.replace("todo ", ""));
                taskType = "todo";
            } else if (isEvent) {
                tasks[index] = new Event(command.replace("event ", ""));
                taskType = "event";
            } else if (isDeadline) {
                tasks[index] = new Deadline(command.replace("deadline ", ""));
                taskType = "deadline";
            }
            addCompleteMessage(taskType);
            appendToFile(taskType);
            index += 1;

        } catch (EmptyDescriptionException emptyException) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }

    }

    public static void reAddTask(String command, int index) {
        boolean isTodo = command.contains("todo");
        boolean isEvent = command.contains("event");
        boolean isDeadline = command.contains("deadline");

        try {
            if (isTodo) {
                tasks[index] = new Todo(command.replace("todo ", ""));
            } else if (isEvent) {
                tasks[index] = new Event(command.replace("event ", ""));
            } else if (isDeadline) {
                tasks[index] = new Deadline(command.replace("deadline ", ""));
            }
        } catch (EmptyDescriptionException emptyException) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    public static void addCompleteMessage(String type) {
        boolean isTodo = type.equals("todo");
        boolean isEvent = type.equals("event");
        boolean isDeadline = type.equals("deadline");
        int numOfItem = index + 1;

        System.out.println("Got it. I added to the list.");

        if (isTodo) {
            System.out.println("  [D][ ] " + tasks[index].getTask());
        } else if (isEvent) {
            System.out.println("  [E][ ] " + tasks[index].getTask());
        } else if (isDeadline){
            System.out.println("  [T][ ] " + tasks[index].getTask());
        }


        printNumOfItems(numOfItem);

    }
    public static void farewellMessage() {
        System.out.println("Bye bye!");
    }
    public static void printAllTasks(int index) {
        for (int i = 0; i < index; i += 1) {
            tasks[i].printTask(i);
        }
    }
    public static TaskType identifyTask(String command) {
        boolean isList = command.contains("list");
        boolean isAdd = command.contains("todo") || command.contains("deadline") || command.contains("event");
        boolean isMarkedOrUnmarked = command.contains("mark") || command.contains("unmark");
        boolean isBye = command.contains("bye");

        if (isList) {
            return TaskType.LIST;
        } else if (isMarkedOrUnmarked) {
            return TaskType.MARK;
        } else if (isAdd) {
            return TaskType.ADD;
        } else if (isBye) {
            return TaskType.BYE;
        } else {
            return TaskType.ERROR;
        }


    }
    private static void appendToFile(String taskType) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String appendTask = tasks[index].getTask();

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
                String entryText = entries[2];
                String command;
                if (entryType.equals("T")) {
                    command = "todo " + entryText;
                } else if (entryType.equals("D")) {
                    command = "deadline " + entryText;
                } else {
                    command = "event " + entryText;
                }

                reAddTask(command, index);
                index += 1;
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
                    printAllTasks(index);
                    break;
                case ADD:
                    addTask(command);
                    break;
                case BYE:
                    farewellMessage();
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
