package duke;
import java.util.ArrayList;
import java.util.Arrays;

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
    public void addToDo(String[] inputWords) {
        try {
            ToDo toDo = getToDo(inputWords);
            tasks.add(toDo);
            ui.showAddedTask(toDo, tasks.size());
        } catch (NoDescriptionException e) {
            ui.printInvalidToDo();
        }
    }

    public void deleteTask(String[] inputWords) {
        int listIndex = Integer.parseInt(inputWords[1]) - 1;
        Task deletedTask = tasks.get(listIndex);
        ui.showDeletedTask(deletedTask, tasks.size());
        tasks.remove(listIndex);
    }
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

    public void markTask(String[] inputWords) {
        int listIndex = Integer.parseInt(inputWords[1]) - 1;
        Task markedTask = tasks.get(listIndex);
        markedTask.setIsCompleted(true);
        ui.showMarkedTask(markedTask);
    }
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
