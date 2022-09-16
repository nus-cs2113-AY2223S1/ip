package duke.task;

import duke.exceptions.AccessTaskOutOfBoundsException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.exceptions.MissingTaskNumberException;
import duke.exceptions.TaskAlreadyMarkedException;
import duke.exceptions.TaskAlreadyUnmarkedException;
import duke.ui.Ui;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int tasksCount = 0;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks, int tasksCount) {
        this.tasks = tasks;
        this.tasksCount = tasksCount;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getTasksCount() {
        return this.tasksCount;
    }

    public String assembleTaskDescription(String[] text) {
        String description = text[1]; //initialise with first word of task
        for (int i = 2; i < text.length; i++) { //start loop from second word of task
            description = description + " " + text[i];
        }
        return description;
    }

    public void handleTaskWithTime(String type, String description) {
        String[] text = description.split(" ");
        String task = text[0]; //initialise with first word of task
        int indexOfTime = 0;
        for (int i = 1; i < text.length; i++) { //start loop from second word of task
            if (text[i].contains("/")) {
                indexOfTime = i;
                break;
            }
            task = task + " " + text[i];
        }
        indexOfTime++;
        String time = "";
        for (int i = indexOfTime; i < text.length; i++) {
            if (i < text.length-1) { //if it is the last word
                time = time + text[i] + " ";
            } else {
                time += text[i];
            }
        }
        if (type.equals("deadline")) {
            Task newTask = new Deadline(task, time);
            tasks.add(newTask);
        } else if (type.equals("event")) {
            Task newTask = new Event(task, time);
            tasks.add(newTask);
        }
    }

    public void handleTask(String type, String description) {
        if (type.equals("todo")) {
            Task newTask = new Todo(description);
            tasks.add(newTask);
        } else {
            this.handleTaskWithTime(type, description);
        }
    }

    public void addTask(String type, String curr) throws EmptyDescriptionException, UnknownCommandException {
        if (!(type.equals("todo") || type.equals("deadline") || type.equals("event"))) { //type given is invalid
            throw new UnknownCommandException();
        } else {
            String[] text = curr.split(" ");
            if (text.length == 1) { //description is empty
                throw new EmptyDescriptionException();
            } else {
                String description = assembleTaskDescription(text);
                handleTask(type, description);
                Ui.printAddTaskAcknowledgement(this.tasks, this.tasksCount);
                this.tasksCount++;
            }
        }
    }

    public void deleteTask(String type, String curr) throws AccessTaskOutOfBoundsException, MissingTaskNumberException {
        String[] text = curr.split(" ");
        if (text.length == 1) { //user did not specify which task to delete
            throw new MissingTaskNumberException();
        } else {
            int taskNumber = Integer.parseInt(text[1]);
            if (taskNumber > this.tasksCount) {
                throw new AccessTaskOutOfBoundsException();
            } else {
                int taskIndex = taskNumber - 1;
                Ui.printDeleteTaskAcknowledgement(taskIndex, this.tasks, this.tasksCount);
                this.tasksCount--;
                tasks.remove(taskIndex);
            }
        }
    }

    public void handleMarkAsDone(String curr) throws MissingTaskNumberException {
        String[] text = curr.split(" ");
        if (text.length == 1) { //did not specify task number
            throw new MissingTaskNumberException();
        } else {
            int taskNumber = Integer.parseInt(text[1]);
            try {
                markAsDone(taskNumber);
            } catch (AccessTaskOutOfBoundsException e) {
                Ui.printAccessTaskOutOfBoundsError();
                Ui.printNumberOfTasks(this.tasksCount);
            } catch (TaskAlreadyMarkedException e) {
                Ui.printTaskAlreadyMarkedError();
            }
        }
    }

    public void markAsDone(int taskNumber) throws AccessTaskOutOfBoundsException, TaskAlreadyMarkedException {
        if (taskNumber > this.tasksCount || taskNumber < 0) { //task specified is out of bounds
            throw new AccessTaskOutOfBoundsException();
        } else {
            int taskIndex = taskNumber - 1;
            if (!tasks.get(taskIndex).isDone) { //only if task is undone
                tasks.get(taskIndex).isDone = true;
                Ui.printMarkedTaskAsDoneAcknowledgement(taskIndex, tasks);
                return;
            }
            throw new TaskAlreadyMarkedException();
        }
    }

    public void handleMarkAsUndone(String curr) throws MissingTaskNumberException {
        String[] text = curr.split(" ");
        if (text.length == 1) { //did not specify task number
            throw new MissingTaskNumberException();
        } else {
            int taskNumber = Integer.parseInt(text[1]);
            try {
                markAsUndone(taskNumber);
            } catch (AccessTaskOutOfBoundsException e) {
                Ui.printAccessTaskOutOfBoundsError();
                Ui.printNumberOfTasks(this.tasksCount);
            } catch (TaskAlreadyUnmarkedException e) {
                Ui.printTaskAlreadyUnmarkedError();
            }
        }
    }

    public void markAsUndone(int taskNumber) throws AccessTaskOutOfBoundsException, TaskAlreadyUnmarkedException{
        if (taskNumber > this.tasksCount || taskNumber < 0) { //task specified is out of bounds
            throw new AccessTaskOutOfBoundsException();
        } else {
            int taskIndex = taskNumber - 1;
            if (tasks.get(taskIndex).isDone) { //only if task is done
                tasks.get(taskIndex).isDone = false;
                Ui.printMarkedTaskAsUndoneAcknowledgement(taskIndex, tasks);
                return;
            }
            throw new TaskAlreadyUnmarkedException();
        }
    }
}