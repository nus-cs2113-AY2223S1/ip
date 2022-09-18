package duke.data;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;
import duke.exception.*;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui();
    }

    public TaskList(TaskList taskList) {
        tasks = taskList.getTasks();
        ui = new Ui();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return this.getTasks().size();
    }

    public Task addTodo(String taskName) {
        Todo todo = new Todo(taskName);
        tasks.add(todo);
        return todo;
    }

    public Task addDeadline(String taskName, String deadlineTime) {
        Deadline deadline = new Deadline(taskName, deadlineTime);
        tasks.add(deadline);
        return deadline;
    }

    public Task addEvent(String taskName, String eventTime) {
        Event event = new Event(taskName, eventTime);
        tasks.add(event);
        return event;
    }

    public String listTasks() {
        String listContent = "";
        for (int i = 0; i < tasks.size(); i++) {
            listContent += String.format("%d.%s", i + 1, tasks.get(i).getTaskFullDetails());
            listContent += System.lineSeparator();
        }
        listContent += "There are a total of " + tasks.size() + " tasks.";
        return listContent;
    }

    public TaskList findTasks(String query) {
        TaskList tempTaskList = new TaskList();

        for (int i = 0; i < tasks.size(); i++) {
            Task tempTask = tasks.get(i);
            if (tempTask.getTaskName().contains(query)){
                tempTaskList.tasks.add(tempTask);
            }
        }
        return tempTaskList;
    }

    public String deleteTask(int taskIndex) {
        String taskDetail = tasks.get(taskIndex - 1).getTaskFullDetails();
        tasks.remove(taskIndex - 1);
        return taskDetail;
    }

    public Task markTask(int taskIndex) throws DukeException {
        tasks.get(taskIndex - 1).setDone(true);
        return tasks.get(taskIndex - 1);
    }

    public Task unmarkTask(int taskIndex) throws DukeException {
        tasks.get(taskIndex - 1).setDone(false);
        return tasks.get(taskIndex - 1);
    }
}
