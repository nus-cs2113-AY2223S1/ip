import org.w3c.dom.events.EventException;

import java.util.ArrayList;

/**
 * Add the ability to store whatever text entered by the user and display them back to the user when requested.
 */

public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Commands
     */
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    /**
     * Parser delimiters
     */

    public static final String GET_DESCRIPTION_DELIMITER = "/.+";
    public static final String GET_AT_DELIMITER = ".+/at ";
    public static final String GET_BY_DELIMITER = ".+/by ";
    public static final String SPACE_DELIMITER = "\\s+";
    public static final String START_LINE_DELIMITER = "^";
//    public static final String DIGITS_DELIMITER = "\\d+";
//    public static final String DEADLINE_DELIMITER = ".+/by.+";
//    public static final String EVENT_DELIMITER = ".+/at.+";

    public static final String DIVIDER = "______________________________________";

    /**
     * Constructor
     */

    public TaskList() {
    }

    /**
     * Parser and command executor
     */
    public static void parseInput(String userInput) {
        String[] delimitedInputArray = userInput.split(SPACE_DELIMITER, 2);
        String command = delimitedInputArray[0];

        userInput = userInput.replaceAll(START_LINE_DELIMITER + command + SPACE_DELIMITER, "");

        if (command.equals(LIST)) {
            String taskList = getTaskList();
            System.out.println(
                    "______________________________________\n" +
                            taskList +
                            "______________________________________"
            );
        } else if (command.equals(DONE)) {
            try {
                markAsDone(userInput);
            } catch (DoneFormatException e) {
                System.out.println("OOPS!!! Done command must follow the format: done <index>");
            } catch (DoneAlreadyCompletedException e) {
                System.out.println("OOPS!!! The task has already been completed!");
            } catch (DoneRangeException e) {
                System.out.println("OOPS!!! The index is out of range for the number of tasks! Please enter a valid index");
            }
        } else if (command.equals(TODO)) {
            try {

                addTodo(userInput);
            } catch (ToDoException e) {
                System.out.println("OOPS!!! Todo command must follow the format: todo <task description>");
            }
        } else if (command.equals(EVENT)) {
            try {
                String taskDescription = userInput.replaceAll(GET_DESCRIPTION_DELIMITER, "");
                String at = userInput.replaceAll(GET_AT_DELIMITER, "");
                addEvent(taskDescription, at);
            } catch (EventException e) {
                System.out.println("OOPS!!! Event command must follow the format: <task description> /at <time/date>");
            }
        } else if (command.equals(DEADLINE)) {
            try {
                String taskDescription = userInput.replaceAll(GET_DESCRIPTION_DELIMITER, "");
                String by = userInput.replaceAll(GET_BY_DELIMITER, "");
                addDeadline(taskDescription, by);
            } catch (DeadlineException e) {
                System.out.println("OOPS!!! Deadline command must follow the format: <task description> /by <time/date>");
            }
        }
    }


    public static void printAcknowledgement() {
        int taskCount = taskList.size();
        String latestTaskAdded = taskList.get(taskCount - 1).showTask();
        System.out.println("Got it. I've added this task:\n  " +
                latestTaskAdded +
                "\nNow you have " + taskCount + " tasks in the list"
        );
    }

    /**
     * Add todo task
     */
    public static void addTodo(String taskDescription) throws ToDoException {
        Task newTask = new Todo(taskDescription);
        taskList.add(newTask);
        System.out.println(DIVIDER);
        printAcknowledgement();
        System.out.println(DIVIDER);
    }

    /**
     * Add event task
     */

    public static void addEvent(String taskDescription, String at) throws EventException {
        Task newTask = new Event(taskDescription, at);
        taskList.add(newTask);
        System.out.println(DIVIDER);
        printAcknowledgement();
        System.out.println(DIVIDER);
    }

    /**
     * Add deadline task
     */
    public static void addDeadline(String taskDescription, String by) throws DeadlineException {
        Task newTask = new Deadline(taskDescription, by);
        taskList.add(newTask);
        System.out.println(DIVIDER);
        printAcknowledgement();
        System.out.println(DIVIDER);
    }

    /**
     * Function that retrieves all
     * tasks in taskList. To check if '\n' is needed
     *
     * @return String of all tasks
     */
    public static String getTaskList() {
        StringBuilder listOutput = new StringBuilder();


        for (int i = 0; i < taskList.size(); i++) {
            listOutput.append(i + 1);
            listOutput.append(". ");
            listOutput.append(taskList.get(i).showTask());
            listOutput.append("\n");
        }
        return listOutput.toString();
    }

    /**
     * Function sets task in task-list to be done
     */
    public static void markAsDone(String userInput) throws DoneFormatException, DoneRangeException, DoneAlreadyCompletedException {
        int index = Integer.parseInt(userInput) - 1;
        taskList.get(index).markDone();
        System.out.println(
                DIVIDER + "\n" +
                        "Nice! I've marked this task as done:\n  " +
                        taskList.get(index).showTask() +
                        "\n" +
                        DIVIDER
        );
    }

}
