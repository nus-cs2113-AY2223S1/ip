package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.Todos;

/**
 * A task manager that contains the list of tasks and other task list related methods.
 */
public class TaskList {
    private static final String END_OF_LINE = "\n____________________";
    private static List<Task> tasks = new ArrayList<Task>();

    /**
     * Adds a new todos task and returns a non-null string to be shown to the user if necessary.
     *
     * @param taskName A name or description given to the task.
     * @param toPrint A boolean to indicate whether a non-null string needs to be returned to be shown to the user.
     * @return A non-null string if success message needs to be shown to user, or null string if not needed.
     */
    public String addNewTodo(String taskName, boolean toPrint) {
        tasks.add(new Todos(taskName));
        if (toPrint) {
            return ("Added new todo task: " + taskName + "\n " + tasks.get(tasks.size() - 1).toString()
                    + "\nYou have " + tasks.size() + " tasks in the list." + END_OF_LINE);
        }
        return null;
    }

    /**
     * Adds a new deadline task and returns a non-null string to be shown to the user if necessary.
     *
     * @param taskName A name or description given to the task.
     * @param toPrint A boolean to indicate whether a non-null string needs to be returned to be shown to the user.
     * @return A non-null string if success message needs to be shown to user, or null string if not needed.
     */
    public String addNewDeadline(String taskName, String toBeDoneBy, boolean toPrint) {
        String[] arrOfBy = toBeDoneBy.split(" ");
        tasks.add(new Deadlines(taskName, toBeDoneBy, LocalDate.parse(arrOfBy[0]), LocalTime.parse(arrOfBy[1])));
        if (toPrint) {
            return ("Added new deadline task: " + taskName + "\n " + tasks.get(tasks.size() - 1).toString()
                    + "\nYou have " + tasks.size() + " tasks in the list." + END_OF_LINE);
        }
        return null;
    }

    /**
     * Adds a new event task and returns a non-null string to be shown to the user if necessary.
     *
     * @param taskName A name or description given to the task.
     * @param toPrint A boolean to indicate whether a non-null string needs to be returned to be shown to the user.
     * @return A non-null string if success message needs to be shown to user, or null string if not needed.
     */
    public String addNewEvent(String taskName, String happeningAt, boolean toPrint) {
        String[] arrOfAt = happeningAt.split(" ");
        tasks.add(new Events(taskName, happeningAt, LocalDate.parse(arrOfAt[0]), LocalTime.parse(arrOfAt[1])));
        if (toPrint) {
            return ("Added new event task: " + taskName + "\n " + tasks.get(tasks.size() - 1).toString()
                    + "\nYou have " + tasks.size() + " tasks in the list." + END_OF_LINE);
        }
        return null;
    }

    /**
     * Lists out tasks in the task list in index order.
     *
     * @return List as a string to be shown to the user.
     */
    public String listTasks() {
        if (tasks.size() == 0) {
            return Printables.NO_TASKS_IN_LIST_MESSAGE;
        }

        String list = "Here is your list of tasks:\n";
        for (int i = 0; i < tasks.size(); i++) {
            list += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return list + END_OF_LINE;
    }

    /**
     * Marks or unmarks an indicated task in the task list.
     *
     * @param toMark A boolean such that true means to mark and false means to unmark.
     * @param taskIndex Displayed index of the task in the task list to be marked or unmarked.
     * @param toPrint A boolean to indicate whether a non-null string needs to be returned to be shown to the user.
     * @return A non-null string if success message needs to be shown to user, or null string if not needed.
     * @throws DukeException.IllegalMarkTargetException If index is out of range.
     */
    public String markTasks(boolean toMark, int taskIndex, boolean toPrint)
            throws DukeException.IllegalMarkTargetException {
        String responseLine;
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new DukeException.IllegalMarkTargetException();
        }

        if (toMark) {
            if (tasks.get(taskIndex - 1).getIsDone()) {
                return Printables.ALREADY_MARKED_MESSAGE;
            }
            tasks.get(taskIndex - 1).setIsDone();
            responseLine = tasks.get(taskIndex - 1).getTaskName()
                    + " has been marked as done!" + END_OF_LINE;
        } else {
            if (!tasks.get(taskIndex - 1).getIsDone()) {
                return Printables.ALREADY_UNMARKED_MESSAGE;
            }
            tasks.get(taskIndex - 1).setIsNotDone();
            responseLine = tasks.get(taskIndex - 1).getTaskName()
                    + " has been unmarked!" + END_OF_LINE;
        }

        if (toPrint) {
            return responseLine;
        }
        return null;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Creates and returns a string representing the data from the current task list to be saved.
     *
     * @return Data to be written into the text file to save.
     */
    public String saveTaskList() {
        String data = "";
        for (Task task : tasks) {
            String type = task.getType();
            if (type.equals("T")) {
                Todos temp = (Todos) task;
                data += (type + "|" + temp.getTaskName() + "|" + (temp.getIsDone() ? "1" : "0") + "\n");
            } else if (type.equals("D")) {
                Deadlines temp = (Deadlines) task;
                data += (type + "|" + temp.getTaskName() + "|" + (temp.getIsDone() ? "1" : "0") + "|"
                        + temp.getToBeDoneBy() + "\n");
            } else {
                Events temp = (Events) task;
                data += (type + "|" + temp.getTaskName() + "|" + (temp.getIsDone() ? "1" : "0") + "|"
                        + temp.getHappeningAt() + "\n");
            }
        }
        return data;
    }

    /**
     * Deletes a specified task from the task list.
     *
     * @param taskIndex Displayed index of the task in the task list to be deleted.
     * @return A response to the user to indicate successful deletion of task.
     * @throws DukeException.IllegalDeleteTargetException If index is out of range.
     */
    public String deleteTask(int taskIndex) throws DukeException.IllegalDeleteTargetException {
        String response;
        String taskDescription;
        try {
            taskDescription = tasks.get(taskIndex - 1).toString();
            tasks.remove(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException.IllegalDeleteTargetException();
        }
        response = "Deleted: " + taskDescription  + "\nYou have " + tasks.size()
                + " tasks in the list." + END_OF_LINE;
        return response;
    }

    /**
     * Finds tasks containing keyword in the task list.
     *
     * @param keyword Keyword to find in the task list.
     * @return A list of tasks that contains the keyword.
     */
    public String findTasksWithKeyphrase(String keyword) {
        String list = Printables.TASK_SEARCH_INIT_STRING;
        for (Task task : tasks) {
            list += (checkMatchingTask(task.getTaskName(), keyword)
                    ? (task + "\n") : "");
        }
        return (list.equals(Printables.TASK_SEARCH_INIT_STRING) ? Printables.EMPTY_TASK_SEARCH_RESULT_MESSAGE : list);
    }

    private boolean checkMatchingTask(String taskName, String keyword) {
        String[] arrOfTaskName = taskName.split(" ");
        for (String word : arrOfTaskName) {
            if (word.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds events and deadlines that are due or happening at specified date.
     *
     * @param dateString Date to be searched.
     * @return A list of events and deadlines due or happening on specified date.
     */
    public String checkoutDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        String list = Printables.DATE_SEARCH_INIT_STRING;

        for (Task task : tasks) {
            if (task instanceof Deadlines) {
                Deadlines temp = (Deadlines) task;
                list += (temp.getDate().equals(date) ? (temp + "\n") : "");
            } else if (task instanceof Events) {
                Events temp = (Events) task;
                list += (temp.getDate().equals(date) ? (temp + "\n") : "");
            }
        }
        return (list.equals(Printables.DATE_SEARCH_INIT_STRING) ? Printables.EMPTY_DATE_SEARCH_RESULT_MESSAGE : list);
    }
}