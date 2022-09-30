package duke.task;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidDeadlineInputException;
import duke.exception.InvalidEventInputException;
import duke.exception.InvalidTaskDescriptionException;
import duke.parser.Parser;

import java.util.ArrayList;

import static duke.Ui.*;

// contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {

    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        Storage.loadInputFile(this.tasks);
    }


    public static void addTodo(TaskList tasks, String fullCommand) throws InvalidTaskDescriptionException {
        String description;
        description = Parser.getTaskDescription(fullCommand).trim();
        Task t = new Todo(description);
        tasks.addTask(t);
        Ui.printSuccessfulAdd(tasks);
    }

    public static void addDeadline(TaskList tasks, String fullCommand) {
            try {
                String[] description = Parser.parseDeadlineDescription(fullCommand);
                String deadlineName = description[0].trim();
                String deadlineDetails = description[1].trim();
                Task t = new Deadline(deadlineName, deadlineDetails);
                tasks.addTask(t);
                Ui.printSuccessfulAdd(tasks);
            } catch (InvalidDeadlineInputException e) {
                Ui.showInvalidDeadlineInputExceptionMessage();
            }

    }

    public static void addEvent(TaskList tasks, String fullCommand){
            try {
                String[] description = Parser.parseEventDescription(fullCommand);
                String eventName = description[0].trim();
                String eventDetails = description[1].trim();
                Task t = new Event(eventName, eventDetails);
                tasks.addTask(t);
                Ui.printSuccessfulAdd(tasks);
            } catch (InvalidEventInputException e) {
                Ui.showInvalidEventInputExceptionMessage();
            }
    }

    public static void deleteTask(TaskList tasks, String line) throws DukeException {
        int taskId = Parser.getTaskId(line);
        Task taskToBeDeleted = tasks.get(taskId);
        int taskSize = tasks.size() - 1;
        System.out.println("\t_____________________");
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + taskToBeDeleted);

        if (taskSize == 1 || taskSize == 0) {
            System.out.println("\tNow you have " + taskSize + " task in the list.");
        } else {
            System.out.println("\tNow you have " + taskSize + " tasks in the list.");
        }
        System.out.println("\t_____________________");
        tasks.removeTask(taskId);
    }

    public static void markTask(TaskList tasks, String line) throws DukeException {
        int taskId = Parser.getTaskId(line);
        printMark(tasks, taskId);
    }

    public static void unmarkTask(TaskList tasks, String line) throws DukeException {
        int taskId = Parser.getTaskId(line);
        printUnmark(tasks, taskId);
    }
    
    public static void totalTask(TaskList tasks, String fullCommand) {
        int taskSize = tasks.size();
        printTotalNumberOfItems(tasks, taskSize);
    }

    // get size of tasks
    public int size() {
        return tasks.size();
    }

    // get task from tasks
    public Task get(int index) {
        return tasks.get(index);
    }

    // add task to tasks
    public void addTask(Task task) {
        tasks.add(task);
    }

    // remove task from tasks
    public void removeTask(int index) {
        tasks.remove(index);
    }





}
