package Duke.Tasks;

import Duke.Duke;
import Duke.Exceptions.ArguementNotFoundException;
import Duke.Exceptions.WrongArgumentException;

public class TaskManager {

    public static final String TASK_ADDED = "Got it. I've added this task:\n";
    public static final int MAX_NUMBER_OF_TASKS = 100;
    public Tasks[] taskList = new Tasks[MAX_NUMBER_OF_TASKS];
    public int numOfTasks = 0;

    public String checkCommandLength(String description) throws ArguementNotFoundException {
        if (description.isEmpty()) {
            throw new ArguementNotFoundException();
        }
        return description;
    }

    public int checkTaskNumber(int input) throws WrongArgumentException {
        if (input < 0 || input > numOfTasks) {
            throw new WrongArgumentException();
        }
        return input;
    }

    private static int getTaskNumber(String command) {
        return Integer.parseInt(command.substring(command.length() - 1));
    }

    /**
     * Add a task into the list and prints an indication it has been added successfully
     *
     * @param task task to add
     */
    public void addTask(Tasks task) {
        taskList[numOfTasks] = task;
        numOfTasks++;
        System.out.println(Duke.PRINT_LINE
                + TASK_ADDED
                + task + "\n"
                + "Now you have " + numOfTasks + " in the list.\n"
                + Duke.PRINT_LINE
        );
    }

    /**
     * Adds a todo type task to the list
     *
     * @param input input of user
     */
    public void addToDo(String input) {
        String todoTask = input.substring("todo".length(), input.length());
        try {
            checkCommandLength(todoTask);
            ToDo todo = new ToDo(todoTask);
            addTask(todo);
        } catch (ArguementNotFoundException e) {
            e.ArgumentNotFoundMessage();
        }


    }

    /**
     * Adds an event type task to the list
     *
     * @param input input of user
     */
    public void addEvent(String input) {
        try {
            checkCommandLength(input.substring("event".length()));
            String eventTask = input.substring("event".length(), input.indexOf("/") - 1);
            String eventDate = input.substring(input.indexOf("/at ") + "/at ".length());
            Event event = new Event(eventTask, eventDate);
            addTask(event);
        } catch (ArguementNotFoundException e) {
            e.ArgumentNotFoundMessage();
        }


    }

    /**
     * Adds a deadline type task to the list
     *
     * @param input input of user
     */
    public void addDeadline(String input) {
        try {
            checkCommandLength(input.substring("deadline".length()));
            String deadlineTask = input.substring("deadline".length(), input.indexOf("/") - 1);
            String deadlineDate = input.substring(input.indexOf("/by ") + "/by ".length());
            Deadline deadline = new Deadline(deadlineTask, deadlineDate);
            addTask(deadline);
        } catch (ArguementNotFoundException e) {
            e.ArgumentNotFoundMessage();
        }

    }


    /**
     * mark a task in the list
     *
     * @param input position of item to mark in the list
     */
    public void markTask(String input) {
        try {
            checkCommandLength(input.substring("mark".length()));
            int taskNum = getTaskNumber(input);
            checkTaskNumber(taskNum);
            taskList[taskNum - 1].markAsDone();
        } catch (ArguementNotFoundException e) {
            e.ArgumentNotFoundMessage();
        } catch (WrongArgumentException e) {
            e.WrongArguementMessage();
        }
    }

    /**
     * unmark a task in the list
     *
     * @param input position of item to unmark in the list
     */
    public void unmarkTask(String input) {
        try {
            checkCommandLength(input.substring("unmark".length()));
            int taskNum = getTaskNumber(input);
            checkTaskNumber(taskNum);
            taskList[taskNum - 1].markAsNotDone();
        } catch (ArguementNotFoundException e) {
            e.ArgumentNotFoundMessage();
        } catch (WrongArgumentException e) {
            e.WrongArguementMessage();
        }
    }

    /**
     * Print List in chronological order
     */
    public void printList() {
        System.out.println(
                Duke.PRINT_LINE
                        + "Here are the tasks in your list:"
        );
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println(
                    (i + 1) + "."
                            + taskList[i]
            );
        }
        System.out.println(
                Duke.PRINT_LINE
        );
    }
}
