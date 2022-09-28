import Exceptions.MissingDateException;
import Exceptions.InvalidListItemNumberException;
import Exceptions.MissingKeywordException;
import Exceptions.MissingTaskException;
import Exceptions.InvalidCommandException;
import Exceptions.InvalidTodoCommandException;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Objects;

public class Duke {
    public static final int DEADLINE_STRING_LENGTH = 9;     // "deadline "
    public static final int EVENT_STRING_LENGTH = 6;        // "event "
    public static final int SEPARATOR_LENGTH = 4;        // "/at  " and "/by "
    public static final int TODO_STRING_LENGTH = 5;        // "todo  "
    public static final String DATA_FILE_PATH = "data.txt";
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "        ____________________________________________";
    public static ArrayList<Task> list = new ArrayList<Task>();

    public static void initializeFile() {
        File f = new File(DATA_FILE_PATH);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating 'data.txt'");
            }
        }
    }

    public static void populateInitialList(String filePath) {
        File f = new File(filePath);
        Scanner s = getInitialListScanner(f);

        while (s.hasNext()) {
            String nextTask = s.next();
            String[] taskParameters = nextTask.split(" \\| ");

            parseInitialList(taskParameters);
        }
    }

    private static void parseInitialList(String[] taskParameters) {
        boolean isTaskDone;
        switch (taskParameters[0]) {
        case "T":
            isTaskDone = Objects.equals(taskParameters[1], "1");
            list.add(new ToDo(taskParameters[2], isTaskDone));
            break;

        case "D":
            isTaskDone = Objects.equals(taskParameters[1], "1");
            list.add(new Deadline(taskParameters[2], isTaskDone, taskParameters[3]));
            break;

        case "E":
            isTaskDone = Objects.equals(taskParameters[1], "1");
            list.add(new Event(taskParameters[2], isTaskDone, taskParameters[3]));
            break;

        default:
            break;
        }
    }

    private static Scanner getInitialListScanner(File f) {
        Scanner s = null;
        try {
            s = new Scanner(f);
            s.useDelimiter(System.lineSeparator());
        } catch (FileNotFoundException e) {
            System.out.println("'data.txt' not found");
        }
        return s;
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static String taskListToString(ArrayList<Task> list) {
        String outputList = "";
        String tempTaskString = "";

        for (int i = 1; i <= list.size(); i += 1) {
            String taskType = list.get(i - 1).getTaskType();
            String markDoneStatus = list.get(i - 1).isDone() ? "1" : "0";

            switch (taskType) {
            case "ToDo":
                tempTaskString = "T | " + markDoneStatus + " | " + list.get(i - 1).getTaskName() +
                        System.lineSeparator();
                outputList += tempTaskString;
                break;

            case "Deadline":
                String deadline = list.get(i - 1).getTaskDeadline();
                tempTaskString = "D | " + markDoneStatus + " | " + list.get(i - 1).getTaskName() +
                        " | " + deadline + System.lineSeparator();
                outputList += tempTaskString;
                break;

            case "Event":
                String eventTime = list.get(i - 1).getEventTime();
                tempTaskString = "E | " + markDoneStatus + " | " + list.get(i - 1).getTaskName() +
                        " | " + eventTime + System.lineSeparator();
                outputList += tempTaskString;
                break;

            default:
                break;
            }
        }
        return outputList;
    }

    public static void printList(ArrayList<Task> list) {
        System.out.println("        ____________________________________________");

        for (int i = 1; i <= list.size(); i += 1) {
            String taskType = list.get(i - 1).getTaskType();
            String markDoneStatus = list.get(i - 1).isDone() ? "[X]" : "[ ]";

            switch (taskType) {
            case "ToDo":
                System.out.println("        [T]" + markDoneStatus + " " + i + ". " + list.get(i - 1).getTaskName());
                break;

            case "Deadline":
                String deadline = list.get(i - 1).getTaskDeadline();
                System.out.println("        [D]" + markDoneStatus + " " + i + ". " + list.get(i - 1).getTaskName() +
                        " (by: " + deadline + ")");
                break;

            case "Event":
                String eventTime = list.get(i - 1).getEventTime();
                System.out.println("        [E]" + markDoneStatus + " " + i + ". " + list.get(i - 1).getTaskName() +
                        " (at: " + eventTime + ")");
                break;

            default:
                break;
            }
        }
        System.out.println("        ____________________________________________");
    }

    private static void printGoodbyeMessage() {
        System.out.println("____________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________");
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________");
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________");
    }

    private static String getTaskIndicator(String[] line) {
        String taskType = list.get(Integer.parseInt(line[1]) - 1).getTaskType();
        String taskIndicator = "";

        switch (taskType) {
        case "ToDo":
            taskIndicator = "[T]";
            break;

        case "Deadline":
            taskIndicator = "[D]";
            break;

        case "Event":
            taskIndicator = "[E]";
            break;

        default:
            break;
        }
        return taskIndicator;
    }

    public static void handleInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] line = input.split(" ");
        boolean isFirstLineProcessed = false;

        while (!line[0].equals("bye")) {
            if (isFirstLineProcessed) {
                input = in.nextLine();
                line = input.split(" ");
            } else {
                isFirstLineProcessed = true;
            }

            switch (line[0]) {
            case "list":
                printList(list);
                break;

            case "mark":
                markTask(line, "mark");
                break;

            case "unmark":
                markTask(line, "unmark");
                break;

            case "todo":
                addTodo(input, line);
                break;

            case "deadline":
                addDeadline(input);
                break;

            case "event":
                addEvent(input);
                break;

            case "delete":
                deleteTask(line);
                break;

            case "bye":
                break;

            default:
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
    }

    private static void deleteTask(String[] line) { //parser
        try {
            boolean isListNumberTooSmall = (Integer.parseInt(line[1])) > list.size();
            boolean isListNumberTooLarge = (Integer.parseInt(line[1])) < 1;
            if (isListNumberTooSmall || isListNumberTooLarge) {
                throw new InvalidListItemNumberException();
            }

            String taskName = list.get(Integer.parseInt(line[1]) - 1).getTaskName();
            String taskIndicator = getTaskIndicator(line);
            String taskStatus = list.get(Integer.parseInt(line[1]) - 1).isDone() ? "[X]" : "[ ]";

            list.remove(Integer.parseInt(line[1]) - 1);

            System.out.println(DIVIDER + LS + "        Noted. I've removed this task:" + LS + "            "
                    + taskIndicator + taskStatus + " " + taskName + LS + "        Now you have " + list.size()
                    + " tasks in the list." + LS + DIVIDER);

            String newListText = taskListToString(list);
            writeToFile(DATA_FILE_PATH, newListText);
        } catch (InvalidListItemNumberException | NumberFormatException e) {
            System.out.println("OOPS!!! The list item number given is invalid.");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void addEvent(String input) {    //parser
        try {
            int separatorIndex = datedEventErrorChecker(input, "/at");

            String eventName = String.copyValueOf(input.toCharArray(), EVENT_STRING_LENGTH,
                    separatorIndex - 1 - EVENT_STRING_LENGTH);
            String eventTime = String.copyValueOf(input.toCharArray(), separatorIndex + SEPARATOR_LENGTH,
                    input.length() - separatorIndex - SEPARATOR_LENGTH);
            list.add(new Event(eventName, eventTime));

            System.out.println(DIVIDER + LS + "        Got it. I've added this task:" + LS + "        [E][ ] "
                    + eventName + " (at: " + eventTime + ")" + LS + "        Now you have " + list.size()
                    + " tasks in the list." + LS + DIVIDER);


            String newListText = taskListToString(list);
            writeToFile(DATA_FILE_PATH, newListText);
        } catch (MissingKeywordException e) {
            System.out.println("OOPS!!! You did not include '/at'.");
        } catch (MissingTaskException e) {
            System.out.println("OOPS!!! You did not indicate the task.");
        } catch (MissingDateException e) {
            System.out.println("OOPS!!! You did not indicate the event date.");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static int datedEventErrorChecker(String input, String separator) throws MissingKeywordException,
            MissingTaskException, MissingDateException {    //parser
        int separatorIndex = input.indexOf(separator);
        if (separatorIndex == -1) {
            throw new MissingKeywordException();
        } else if (separatorIndex == EVENT_STRING_LENGTH) {
            throw new MissingTaskException();
        } else if (separatorIndex + 2 == input.length() - 1) {
            throw new MissingDateException();
        }
        return separatorIndex;
    }

    private static void addDeadline(String input) { //parser
        try {
            int separatorIndex = datedEventErrorChecker(input, "/by");

            String taskName = String.copyValueOf(input.toCharArray(), DEADLINE_STRING_LENGTH,
                    separatorIndex - 1 - DEADLINE_STRING_LENGTH);
            String taskDate = String.copyValueOf(input.toCharArray(), separatorIndex + SEPARATOR_LENGTH,
                    input.length() - separatorIndex - SEPARATOR_LENGTH);
            list.add(new Deadline(taskName, taskDate));

            System.out.println(DIVIDER + LS + "        Got it. I've added this task:" + LS + "        [D][ ] "
                    + taskName + " (by: " + taskDate + ")" + LS + "        Now you have " + list.size()
                    + " tasks in the list." + LS + DIVIDER);

            String newListText = taskListToString(list);
            writeToFile(DATA_FILE_PATH, newListText);
        } catch (MissingKeywordException e) {
            System.out.println("OOPS!!! You did not include '/by'.");
        } catch (MissingTaskException e) {
            System.out.println("OOPS!!! You did not indicate the task.");
        } catch (MissingDateException e) {
            System.out.println("OOPS!!! You did not indicate the deadline.");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void addTodo(String input, String[] line) {  //parser
        try {
            if (line.length == 1) {
                throw new InvalidTodoCommandException();
            }

            String todoName = String.copyValueOf(input.toCharArray(), TODO_STRING_LENGTH,
                    input.length() - TODO_STRING_LENGTH);
            list.add(new ToDo(todoName));

            System.out.println(DIVIDER + LS + "        Got it. I've added this task:" + LS + "        [T][ ] "
                    + todoName + LS + "        Now you have " + list.size() + " tasks in the list." + LS
                    + DIVIDER);

            String newListText = taskListToString(list);
            writeToFile(DATA_FILE_PATH, newListText);
        } catch (InvalidTodoCommandException e) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void markTask(String[] line, String command) {   //parser
        try {
            boolean isListNumberTooSmall = (Integer.parseInt(line[1])) > list.size();
            boolean isListNumberTooLarge = (Integer.parseInt(line[1])) < 1;
            if (isListNumberTooSmall || isListNumberTooLarge) {
                throw new InvalidListItemNumberException();
            }

            String editRemark = "";
            if (command.equals("mark")) {
                list.get(Integer.parseInt(line[1]) - 1).setDone(true);
                editRemark = "        Nice! I've marked this task as done:";
            } else if (command.equals("unmark")) {
                list.get(Integer.parseInt(line[1]) - 1).setDone(false);
                editRemark = "        Ok. I've marked this task as not done yet:";
            }

            String taskIndicator = getTaskIndicator(line);
            String markDone = list.get(Integer.parseInt(line[1]) - 1).isDone() ? "[X]" : "[ ]";
            System.out.println(DIVIDER + LS + editRemark + LS + "            " + taskIndicator + markDone + " "
                    + list.get(Integer.parseInt(line[1]) - 1).getTaskName() + LS + DIVIDER);

            String newListText = taskListToString(list);
            writeToFile(DATA_FILE_PATH, newListText);
        } catch (InvalidListItemNumberException | NumberFormatException e) {
            System.out.println("OOPS!!! The list item number given is invalid.");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    public static void main(String[] args) throws InvalidCommandException {
        printWelcomeMessage();

        initializeFile();
        populateInitialList(DATA_FILE_PATH);

        handleInput();
        printGoodbyeMessage();
    }
}
