package duke.data;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private static final int INDENT_COUNT = 4;
    private static final String INDENT_SPACE = "    ";
    private static final String EMPTY_STRING = "";
    private static final String DELIMITER = " | ";
    private static final String DONE = "X";
    private static final String NUMERIC_DONE = "1";
    private static final String NUMERIC_NOT_DONE = "0";
    private static final String TASK_TYPE_TODO = "T";
    private static final String TASK_TYPE_EVENT = "E";
    private static final String TASK_TYPE_DEADLINE = "D";

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task addTodo(String title, boolean isDone) {
        Todo todo = new Todo(title, isDone);
        tasks.add(todo);
        return todo;
    }

    public Task addDeadline(String title, String dueBy, boolean isDone) {
        Deadline deadline = new Deadline(title, dueBy, isDone);
        tasks.add(deadline);
        return deadline;
    }

    public Task addEvent(String title, String time, boolean isDone) {
        Event event = new Event(title, time, isDone);
        tasks.add(event);
        return event;
    }

    public String markTask(int taskIndex, boolean isDone) {
        tasks.get(taskIndex).setStatus(isDone);
        String taskDetails = tasks.get(taskIndex).getTaskDetails();
        return taskDetails;
    }

    public String deleteTask(int taskIndex) {
        String taskDetails = tasks.get(taskIndex).getTaskDetails();
        tasks.remove(taskIndex);
        return taskDetails;
    }

    public String getTaskFileEntry(Task task) {
        String taskStatus = task.getStatusIcon().equals(DONE) ? NUMERIC_DONE : NUMERIC_NOT_DONE;
        String taskTitle = task.getTitle();
        String taskFileEntry = TASK_TYPE_TODO + DELIMITER + taskStatus + DELIMITER + taskTitle;
        if (task instanceof Deadline) {
            taskFileEntry = TASK_TYPE_DEADLINE + DELIMITER + taskStatus + DELIMITER + taskTitle
                    + DELIMITER + task.getDueBy();
        } else if (task instanceof Event) {
            taskFileEntry = TASK_TYPE_EVENT + DELIMITER + taskStatus + DELIMITER + taskTitle
                    + DELIMITER + task.getTime();
        }
        return taskFileEntry;
    }

    public String listTasks() {
        String tasksList = EMPTY_STRING;
        for (Task task : tasks) {
            tasksList += INDENT_SPACE + task.getTaskDetails() + System.lineSeparator();
        }
        if (!tasksList.equals(EMPTY_STRING)) {
            tasksList = tasksList.substring(INDENT_COUNT, tasksList.length() - 1);
        }
        return tasksList;
    }
}
