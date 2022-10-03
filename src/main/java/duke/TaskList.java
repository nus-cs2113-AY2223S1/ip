package duke;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * TaskList implements the collection ArrayList and is used for storing objects with type Task.
 * TaskList contains Task-specific methods to add, delete, mark, unmark and find tasks. TaskList requires class
 * attributes ArrayList<Task> to store tasks and Ui to interact with the user.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_DELIMITER = "/at";
    private static final int RIGHT_SHIFT_INDEX = 1;
    private static final int TASK_START_INDEX = 1;
    public TaskList (ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }
    public ArrayList<Task> getTaskList() {
        return tasks;
    }
    public void addTask(Task task) {
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
    }

    /**
     * Handles adding a deadline task from user input to task list. Searches for possible exceptions
     * caused by invalid user input.
     *
     * @param  inputWords Array of words extracted from user input
     */
    public void addDeadline(String[] inputWords) {
        try {
            checkForTaskExceptions(inputWords, DEADLINE_DELIMITER);
            Deadline deadline = getDeadline(inputWords);
            addTask(deadline);
        } catch (NoDescriptionException e) {
            ui.printEmptyDeadlineDescription();
        } catch (InvalidTaskFormatException e) {
            ui.printInvalidDeadline();
        }
    }

    /**
     * Handles adding an event task from user input to task list. Searches for possible exceptions
     * caused by invalid user input.
     *
     * @param  inputWords Array of words extracted from user input
     */
    public void addEvent(String[] inputWords) {
        try {
            checkForTaskExceptions(inputWords, EVENT_DELIMITER);
            Event event = getEvent(inputWords);
            addTask(event);
        } catch (NoDescriptionException e) {
            ui.printEmptyEventDescription();
        } catch (InvalidTaskFormatException e) {
            ui.printInvalidEvent();
        }
    }

    /**
     * Handles adding a ToDo task from user input to task list. Catches exception when no description is provided
     * for ToDo task.
     *
     * @param  inputWords Array of words extracted from user input
     */
    public void addToDo(String[] inputWords) {
        try {
            ToDo toDo = getToDo(inputWords);
            tasks.add(toDo);
            ui.showAddedTask(toDo, tasks.size());
        } catch (NoDescriptionException e) {
            ui.printInvalidToDo();
        }
    }

    /**
     * Deletes task from task list based on index of task in the list
     *
     * @param  inputWords Array of words extracted from user input
     */
    public void deleteTask(String[] inputWords) {
        int listIndex = Integer.parseInt(inputWords[1]) - 1;
        Task deletedTask = tasks.get(listIndex);
        ui.showDeletedTask(deletedTask, tasks.size());
        tasks.remove(listIndex);
    }

    /**
     * Finds tasks in the task list which contain the keywords provided by the user.
     *
     * @param  inputWords Array of words extracted from user input
     */
    public void findTasks(String[] inputWords) {
        String[] keywordsArray = Arrays.copyOfRange(inputWords, RIGHT_SHIFT_INDEX, inputWords.length);
        String keywords = String.join(" ", keywordsArray);
        ArrayList<Task> tasksFound = new ArrayList<Task>();
        for (Task task : tasks) {
            if(task.getName().contains(keywords)) {
                tasksFound.add(task);
            }
        }
        ui.showFoundTasks(tasksFound);
    }

    private String getTaskName(String[] inputWords, int index) {
        String[] taskNameArray = Arrays.copyOfRange(inputWords, TASK_START_INDEX, index);
        String taskName = String.join(" ", taskNameArray);
        return taskName;
    }
    private String getTaskDate(String[] inputWords, int index) {
        String[] taskDateArray = Arrays.copyOfRange(inputWords, index + RIGHT_SHIFT_INDEX, inputWords.length);
        String taskDate = String.join(" ", taskDateArray);
        return taskDate;
    }

    private ToDo getToDo(String[] inputWords) throws NoDescriptionException {
        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        String[] taskNameWords = Arrays.copyOfRange(inputWords, TASK_START_INDEX, inputWords.length);
        String taskName = String.join(" ", taskNameWords);
        ToDo toDo = new ToDo(taskName);
        return toDo;
    }
    private Deadline getDeadline(String[] inputWords) throws NoDescriptionException, InvalidTaskFormatException {
        int index = getIndexOfDelimiter(inputWords, DEADLINE_DELIMITER);
        String deadlineName = getTaskName(inputWords, index);
        String dueDate = getTaskDate(inputWords, index);
        Deadline deadline = new Deadline(deadlineName, dueDate);
        return deadline;
    }
    private Event getEvent(String[] inputWords) throws NoDescriptionException, InvalidTaskFormatException {
        int index = getIndexOfDelimiter(inputWords, EVENT_DELIMITER);
        String eventName = getTaskName(inputWords, index);
        String timeOfEvent = getTaskDate(inputWords, index);
        Event event = new Event(eventName, timeOfEvent);
        return event;
    }

    /**
     * Marks task as done in the task list using the index of task in the list.
     *
     * @param  inputWords Array of words extracted from user input
     */
    public void markTask(String[] inputWords) {
        int listIndex = Integer.parseInt(inputWords[1]) - 1;
        Task markedTask = tasks.get(listIndex);
        markedTask.setIsCompleted(true);
        ui.showMarkedTask(markedTask);
    }

    /**
     * Unmarks task as done in the task list using the index of task in the list.
     *
     * @param  inputWords Array of words extracted from user input
     */
    public void unmarkTask(String[] inputWords) {
        int listIndex = Integer.parseInt(inputWords[1]) - 1;
        Task unmarkedTask = tasks.get(listIndex);
        unmarkedTask.setIsCompleted(false);
        ui.showUnmarkedTask(unmarkedTask);
    }
    private void checkForTaskExceptions(String[] inputWords, String delimiter) throws NoDescriptionException, InvalidTaskFormatException {
        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        if (!Arrays.asList(inputWords).contains(delimiter)) {
            throw new InvalidTaskFormatException();
        }
    }
    private int getIndexOfDelimiter(String[] inputWords, String delimiter) {
        int index = Arrays.asList(inputWords).indexOf(delimiter);
        return index;
    }
}
