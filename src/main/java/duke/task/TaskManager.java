package duke.task;

import duke.exceptions.AccessTaskOutOfBoundsException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.exceptions.MissingTaskNumberException;
import duke.exceptions.TaskAlreadyMarkedException;
import duke.exceptions.TaskAlreadyUnmarkedException;
import duke.storage.Storage;
import java.io.IOException;
import java.util.ArrayList;

import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private int tasksCount = 0;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public TaskManager(ArrayList<Task> tasks, int tasksCount) {
        this.tasks = tasks;
        this.tasksCount = tasksCount;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void printAddTaskAcknowledgement() {
        String acknowledgement = "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + " " + tasks.get(tasksCount).toString();
        System.out.println(acknowledgement);
        this.tasksCount++;
        System.out.println("Now you have " + this.tasksCount + " task(s) in the list.");
        System.out.println("____________________________________________________________");
    }

    public void printDeleteTaskAcknowledgement(int taskIndex) {
        String acknowledgement = "____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + " " + tasks.get(taskIndex).toString();
        System.out.println(acknowledgement);
        this.tasksCount--;
        System.out.println("Now you have " + this.tasksCount + " task(s) in the list.");
        System.out.println("____________________________________________________________");
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
        this.printAddTaskAcknowledgement();
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
                this.printDeleteTaskAcknowledgement(taskIndex);
                tasks.remove(taskIndex);
            }
        }
    }

    public void handleInput(String curr) {
        String[] text = curr.split(" ");
        String type = text[0];
        if (type.equals("mark")) {
            try {
                this.handleMarkAsDone(curr);
            } catch (MissingTaskNumberException e) {
                e.printMissingTaskNumberError();
            }
        } else if (type.equals("unmark")) {
            try {
                this.handleMarkAsUndone(curr);
            } catch (MissingTaskNumberException e) {
                e.printMissingTaskNumberError();
            }
        } else if (type.equals("delete")) {
            try {
                this.deleteTask(type, curr);
            } catch (AccessTaskOutOfBoundsException e) {
                e.printAccessTaskOutOfBoundsError();
                printNumberOfTasks();
            } catch (MissingTaskNumberException e) {
                e.printMissingTaskNumberError();
            }
        } else {
            try {
                this.addTask(type, curr);
            } catch (EmptyDescriptionException e) {
                e.printEmptyDescriptionError();
            } catch (UnknownCommandException e) {
                e.printUnknownCommandError();
            }
        }
        try {
            Storage.saveFile(this.getTasks());
        } catch (IOException e) {
            System.out.println("Something went wrong trying to save the file: " + e.getMessage());
        }
    }

    public void printNumberOfTasks() {
        System.out.println("You currently have " + this.tasksCount + " task(s) in your list.\n"
                + "    ____________________________________________________________");
    }
    public void listTasks(){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasksCount; i++){
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
        System.out.println("____________________________________________________________");
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
                e.printAccessTaskOutOfBoundsError();
                printNumberOfTasks();
            } catch (TaskAlreadyMarkedException e) {
                e.printTaskAlreadyMarkedError();
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
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(taskIndex).toString());
                System.out.println("____________________________________________________________");
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
                e.printAccessTaskOutOfBoundsError();
                printNumberOfTasks();
            } catch (TaskAlreadyUnmarkedException e) {
                e.printTaskAlreadyUnmarkedError();
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
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(taskIndex).toString());
                System.out.println("____________________________________________________________");
                return;
            }
            throw new TaskAlreadyUnmarkedException();
        }
    }
}