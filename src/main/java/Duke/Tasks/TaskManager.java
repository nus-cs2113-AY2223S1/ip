package Duke.Tasks;

import Duke.Exceptions.ArguementNotFoundException;
import Duke.Exceptions.WrongArgumentException;
import Duke.UI.UI;

import java.util.ArrayList;

/**
 * TaskManager handles all task operations ie. adding, deleting, finding etc.
 */
public class TaskManager extends ArrayList<Tasks> {
    public static final String TASK_ADDED = "Got it. I've added this task:\n";
    public static ArrayList<Tasks> taskList = new ArrayList<>();
    public static int numOfTasks = 0;

    public ArrayList<Tasks> getTasks() {
        return taskList;
    }

    /**
     * Checks if a task description has been specified in user input.
     *
     * @param description task description
     * @return task description
     * @throws ArguementNotFoundException no task description found
     */
    public static String checkCommandLength(String description) throws ArguementNotFoundException {
        if (description.isEmpty()) {
            throw new ArguementNotFoundException();
        }
        return description;
    }

    /**
     * Checks if task number specified is within the list's range.
     *
     * @param input task number
     * @return task number
     * @throws WrongArgumentException invalid task number outside of list
     */
    public static int checkTaskNumber(int input) throws WrongArgumentException {
        if (input > 0 && input <= taskList.size()) {
            return input;
        } else {
            throw new WrongArgumentException();
        }

    }

    /**
     * Obtains specified task number from user input.
     *
     * @param command user input
     * @param action  type of task
     * @return task number
     */
    private static int getTaskNumber(String command, String action) {
        return Integer.parseInt(command.substring(action.length(), command.length()));
    }

    /**
     * Add a task into the list and prints an indication it has been added successfully
     *
     * @param task task to add
     */
    public static void addTask(Tasks task) {

        taskList.add(numOfTasks, task);
        numOfTasks++;
        System.out.println(UI.PRINT_LINE
                + TASK_ADDED
                + task + "\n"
                + "Now you have " + numOfTasks + " task(s) in the list.\n"
                + UI.PRINT_LINE
        );
    }

    /**
     * Adds a todo type task to the list
     *
     * @param input input of user
     */
    public static void addToDo(String input) {
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
    public static void addEvent(String input) {
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
    public static void addDeadline(String input) {
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
     * mark a task in the list.
     *
     * @param input position of item to mark in the list.
     */
    public static void markTask(String input) {
        try {
            checkCommandLength(input.substring("mark".length()));
            int taskNum = getTaskNumber(input, "mark ");
            checkTaskNumber(taskNum);
            taskList.get(taskNum - 1).markAsDone();
        } catch (ArguementNotFoundException e) {
            e.ArgumentNotFoundMessage();
        } catch (WrongArgumentException e) {
            e.WrongArguementMessage();
        }
    }

    /**
     * unmark a task in the list.
     *
     * @param input user input position of item to unmark in the list.
     */
    public static void unmarkTask(String input) {
        try {
            checkCommandLength(input.substring("unmark".length()));
            int taskNum = getTaskNumber(input, "unmark ");
            checkTaskNumber(taskNum);
            taskList.get(taskNum - 1).markAsNotDone();
        } catch (ArguementNotFoundException e) {
            e.ArgumentNotFoundMessage();
        } catch (WrongArgumentException e) {
            e.WrongArguementMessage();
        }
    }

    /**
     * Deletes a specific task in the list.
     *
     * @param input user input with position of item to delete in the list.
     */
    public static void deleteTask(String input) {
//        int taskNum = getTaskNumber(input);

        try {
            checkCommandLength(input.substring("delete".length()));
            int taskNum = getTaskNumber(input, "delete ");
            checkTaskNumber(taskNum);
            numOfTasks -= 1;
            System.out.println(
                    UI.PRINT_LINE
                            + "Noted. I've removed this task:\n"
                            + taskList.get(taskNum - 1) + "\n"
                            + "Now you have " + numOfTasks + " task(s) in the list.\n"
                            + UI.PRINT_LINE
            );
            taskList.remove(taskNum - 1);

        } catch (ArguementNotFoundException e) {
            e.ArgumentNotFoundMessage();
        } catch (WrongArgumentException e) {
            e.WrongArguementMessage();
        }
    }

    /**
     * Finds a specific task in the list.
     *
     * @param input user input with keywords to look for in list.
     */
    public static void findTask(String input) {
        try {
            ArrayList<Tasks> matchingTasks = new ArrayList<>();
            int numOfMatchingTasks = 0;

            checkCommandLength(input.substring("find".length()));
            String keyword = input.substring("find".length(), input.length());
            keyword = keyword.toLowerCase();

            for (Tasks task : taskList) {
                if (task.getTaskDescription().toLowerCase().contains(keyword)) {
                    matchingTasks.add((task));
                    numOfMatchingTasks += 1;
                }
            }
            if (numOfMatchingTasks == 0) {
                System.out.println(
                        UI.PRINT_LINE
                                + "No matching tasks found in list.\n"
                                + UI.PRINT_LINE
                );
            } else {
                printMatchedTasks(matchingTasks);
            }

        } catch (ArguementNotFoundException e) {
            e.ArgumentNotFoundMessage();
        }
    }

    /**
     * Prints all tasks found in list after searching for keyword.
     *
     * @param matchingTasks array with matching tasks in chronological order.
     */
    public static void printMatchedTasks(ArrayList<Tasks> matchingTasks) {
        System.out.println(
                UI.PRINT_LINE
                        + "Here are the matching task(s) in your list:"
        );
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println(
                    (i + 1) + "."
                            + matchingTasks.get(i)
            );
        }
        System.out.println(
                UI.PRINT_LINE
        );
    }

    /**
     * Print List in chronological order
     */
    public static void printList() {
        System.out.println(
                UI.PRINT_LINE
                        + "Here are the task(s) in your list:"
        );
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(
                    (i + 1) + "."
                            + taskList.get(i)
            );
        }
        System.out.println(
                UI.PRINT_LINE
        );
    }

}
