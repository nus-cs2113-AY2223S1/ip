package Parser;

import Exceptions.MissingDateException;
import Exceptions.InvalidListItemNumberException;
import Exceptions.MissingKeywordException;
import Exceptions.MissingTaskException;
import Exceptions.InvalidCommandException;
import Exceptions.InvalidTodoCommandException;

import Tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import Ui.TextUi;

import TaskList.TaskList;

public class Parser {
    public static final int DEADLINE_STRING_LENGTH = 9;
    public static final int EVENT_STRING_LENGTH = 6;
    public static final int SEPARATOR_LENGTH = 4;
    public static final int TODO_STRING_LENGTH = 5;
    public static final String DATA_FILE_PATH = "data.txt";
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "        ____________________________________________";

    private TextUi ui = new TextUi();
    public Parser() {

    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void parseCommand(String input, String[] splitInput, String command, ArrayList<Task> list) {
        switch (command) {
        case "list":
            ui.printList(list);
            break;

        case "mark":
            markTask(splitInput, "mark", list);
            break;

        case "unmark":
            markTask(splitInput, "unmark", list);
            break;

        case "todo":
            addTodo(input, splitInput, list);
            break;

        case "deadline":
            addDeadline(input, list);
            break;

        case "event":
            addEvent(input, list);
            break;

        case "delete":
            deleteTask(splitInput, list);
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
    public void parseInitialList(ArrayList<Task> list, String[] taskParameters) {
        boolean isTaskDone;
        switch (taskParameters[0]) {
        case "T":
            isTaskDone = Objects.equals(taskParameters[1], "1");
            TaskList.addTodo(taskParameters[2], isTaskDone);
            break;

        case "D":
            isTaskDone = Objects.equals(taskParameters[1], "1");
            TaskList.addDeadline(taskParameters[2], isTaskDone, taskParameters[3]);
            break;

        case "E":
            isTaskDone = Objects.equals(taskParameters[1], "1");
            TaskList.addEvent(taskParameters[2], isTaskDone, taskParameters[3]);
            break;

        default:
            break;
        }
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

    private static String getTaskIndicator(String[] line, ArrayList<Task> list) {
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

    private static void deleteTask(String[] line, ArrayList<Task> list) {
        try {
            boolean isListNumberTooSmall = (Integer.parseInt(line[1])) > list.size();
            boolean isListNumberTooLarge = (Integer.parseInt(line[1])) < 1;
            if (isListNumberTooSmall || isListNumberTooLarge) {
                throw new InvalidListItemNumberException();
            }

            String taskName = list.get(Integer.parseInt(line[1]) - 1).getTaskName();
            String taskIndicator = getTaskIndicator(line, list);
            String taskStatus = list.get(Integer.parseInt(line[1]) - 1).isDone() ? "[X]" : "[ ]";

            TaskList.deleteTask(Integer.parseInt(line[1]) - 1);

            System.out.println(DIVIDER + LS + "        Noted. I've removed this task:" + LS + "            "
                    + taskIndicator + taskStatus + " " + taskName + LS + "        Now you have " + list.size()
                    + " tasks in the list." + LS + DIVIDER);

            String newListText = taskListToString(list);
            writeToFile(DATA_FILE_PATH, newListText);
        } catch (InvalidListItemNumberException | NumberFormatException e) {
            System.out.println("OOPS!!! The list item number given is invalid.");
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void addEvent(String input, ArrayList<Task> list) {
        try {
            int separatorIndex = datedEventErrorChecker(input, "/at");

            String taskName = String.copyValueOf(input.toCharArray(), EVENT_STRING_LENGTH,
                    separatorIndex - 1 - EVENT_STRING_LENGTH);
            String taskDate = String.copyValueOf(input.toCharArray(), separatorIndex + SEPARATOR_LENGTH,
                    input.length() - separatorIndex - SEPARATOR_LENGTH);
            TaskList.addEvent(taskName, taskDate);

            System.out.println(DIVIDER + LS + "        Got it. I've added this task:" + LS + "        [E][ ] "
                    + taskName + " (at: " + taskDate + ")" + LS + "        Now you have " + list.size()
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
            MissingTaskException, MissingDateException {
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

    private static void addDeadline(String input, ArrayList<Task> list) {
        try {
            int separatorIndex = datedEventErrorChecker(input, "/by");

            String taskName = String.copyValueOf(input.toCharArray(), DEADLINE_STRING_LENGTH,
                    separatorIndex - 1 - DEADLINE_STRING_LENGTH);
            String taskDate = String.copyValueOf(input.toCharArray(), separatorIndex + SEPARATOR_LENGTH,
                    input.length() - separatorIndex - SEPARATOR_LENGTH);
            TaskList.addDeadline(taskName, taskDate);

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

    private void addTodo(String input, String[] line, ArrayList<Task> list) {
        try {
            if (line.length == 1) {
                throw new InvalidTodoCommandException();
            }

            String todoName = String.copyValueOf(input.toCharArray(), TODO_STRING_LENGTH,
                    input.length() - TODO_STRING_LENGTH);
            TaskList.addTodo(todoName);

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

    private static void markTask(String[] line, String command, ArrayList<Task> list) {
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

            String taskIndicator = getTaskIndicator(line, list);
            String markDone = list.get(Integer.parseInt(line[1]) - 1).isDone() ? "[X]" : "[ ]";
            System.out.println(DIVIDER + LS + editRemark + LS + "            " + taskIndicator + markDone + " "
                    + list.get(Integer.parseInt(line[1]) - 1).getTaskName() + LS + DIVIDER);

            String newListText = taskListToString(list);
            writeToFile(DATA_FILE_PATH, newListText);
        } catch (InvalidListItemNumberException | NumberFormatException e) {
            System.out.println("OOPS!!! The list item number given is invalid.");
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
