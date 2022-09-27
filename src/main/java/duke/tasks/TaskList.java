package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.*;


/**
 * Add the ability to store whatever text entered by the user and display them back to the user when requested.
 */

public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<>();

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
    public static final String DIGITS_DELIMITER = "\\d+";
    public static final String DEADLINE_DELIMITER = ".+/by.+";
    public static final String EVENT_DELIMITER = ".+/at.+";

    public static final String DIVIDER = "______________________________________";

    /**
     * Constructor
     */

    public TaskList() {
    }

    public static void printAcknowledgement() {
        int taskCount = taskList.size();
        String latestTaskAdded = taskList.get(taskCount - 1).showTask();
        System.out.println("Got it. I've added this task:\n  " +
                latestTaskAdded +
                "\nNow you have " + taskCount + " tasks in the list"
        );
    }

    public static String getAcknowledgement() {
        int taskCount = taskList.size();
        String latestTaskAdded = taskList.get(taskCount - 1).showTask();
        return "Got it. I've added this task:\n  " +
                latestTaskAdded +
                "\nNow you have " + taskCount + " tasks in the list";
    }

    /**
     * Add todo task
     */
    public static void addTodo(String taskDescription, boolean isDone){
        Task newTask = new Todo(taskDescription, isDone);
        taskList.add(newTask);
    }

    public static String addTodo(String commandArgument) throws TodoException {
        if (commandArgument.equals("") || commandArgument.equals("todo")) {
            throw new TodoException();
        }
        Task newTask = new Todo(commandArgument);
        taskList.add(newTask);
        return getAcknowledgement();
    }

    /**
     * Add event task
     */

    public static void addEvent(String taskDescription, String at, boolean isDone) {
        Task newTask = new Event(taskDescription, at, isDone);
        taskList.add(newTask);
    }

    public static String addEvent(String commandArgument) throws EventException {
        if (!commandArgument.matches(EVENT_DELIMITER)) {
            throw new EventException();
        }

        String taskDescription = commandArgument.replaceAll(GET_DESCRIPTION_DELIMITER, "");
        String at = commandArgument.replaceAll(GET_AT_DELIMITER, "");

        Task newTask = new Event(taskDescription, at);
        taskList.add(newTask);
        return getAcknowledgement();
    }

    /**
     * Add deadline task
     */
    public static void addDeadline(String taskDescription, String by, boolean isDone) {
        Task newTask = new Deadline(taskDescription, by, isDone);
        taskList.add(newTask);
    }

    public static String addDeadline(String commandArgument) throws DeadlineException {
        if (!commandArgument.matches(DEADLINE_DELIMITER)) {
            throw new DeadlineException();
        }

        String taskDescription = commandArgument.replaceAll(GET_DESCRIPTION_DELIMITER, "");
        String by = commandArgument.replaceAll(GET_BY_DELIMITER, "");

        Task newTask = new Deadline(taskDescription, by);
        taskList.add(newTask);
        return getAcknowledgement();
    }

    public static String deleteTask(String commandArgument) throws DeleteException, DeleteRangeException {

        if (!commandArgument.matches(DIGITS_DELIMITER)) {
            throw new DeleteException();
        }

        int index = Integer.parseInt(commandArgument) - 1;

        if (index >= taskList.size()) {
            throw new DeleteRangeException();
        }

        String deleteCompleteMessage = "Noted. I've removed this task: "
                + taskList.get(index).showTask();
        taskList.remove(index);
        deleteCompleteMessage += "\n Now you have " + taskList.size()
                + " tasks in the list.";

        return deleteCompleteMessage;
    }


    /**
     * Function that retrieves all
     * tasks in taskList. To check if '\n' is needed
     *
     * @return String of all tasks
     */
    public static String getTaskList() throws EmptyListException {

        if (taskList.size() == 0) {
            throw new EmptyListException();
        }

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

    public static String markAsDone(String commandArguments) throws DoneFormatException, DoneRangeException, DoneAlreadyException {

        if (!commandArguments.matches(DIGITS_DELIMITER)) {
            throw new DoneFormatException();
        }
        int index = Integer.parseInt(commandArguments) - 1;

        if (index < 0 || index >= taskList.size()) {
            throw new DoneRangeException();
        }

        if (taskList.get(index).isDone) {
            throw new DoneAlreadyException();
        }

        taskList.get(index).markDone();

        return "Nice! I've marked this task as done:\n  "
                + taskList.get(index).showTask()
                + "\n";
    }

    public static void doNothing() throws DoNothingException {
        throw new DoNothingException();
    }

    public static String findTask(String commandArguments) throws NotFoundException {
        String found = "Here are the tasks in your list that contains '" + commandArguments + "'";

        int numTasksFound = 0;

        for (int i = 0; i < taskList.size(); i++){
            if (taskList.get(i).getTaskDescription().toLowerCase().contains(commandArguments.toLowerCase())){
                numTasksFound++;
                found += "\n" + (i+1) + ". " + taskList.get(i).showTask();
            }
        }

        if (numTasksFound == 0){
            throw new NotFoundException();
        }
        return found;
    }
}
