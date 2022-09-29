package main.duke;
import main.duke.exception.DukeException;
import main.duke.task.Task;
import java.util.ArrayList;

/** Controls all interactions between the user, storage, and the task list
 * Includes adding, deleting, marking, and otherwise editing the list */
public class TaskList {
    private ArrayList<Task> taskList;
    private static Parser parser;

    /** Initializes a new task list with an empty list */
    public TaskList() {
        taskList = new ArrayList<Task>();
        parser = new Parser();
    }

    /** Print a list of the given tasks */
    public void printList() {
        if (Utils.getListIndex() == 0) {
            System.out.print(Utils.INDENT + "Nothing to see here! Type to add to your list.");
            System.out.print(Utils.INDENT + "Here are the correct formats: "
                    + Utils.INDENT + "- todo [task]"
                    + Utils.INDENT + "- deadline [task] /by [date]"
                    + Utils.INDENT + "- event [name] /at [date]");
            return;
        }
        System.out.print(Utils.INDENT + "Here's your current task list:");
        for (int i = 0; i < Utils.getListIndex(); i++) {
            Task task = this.taskList.get(i);
            System.out.print(Utils.INDENT + (i + 1) + "." + task);
        }
    }

    /** Adds a task to the task list */
    public void add(Task task) {
        taskList.add(task);
    }

    /** Adds a task to the task list at a certain index */
    public void add(int index, Task task) {
        taskList.add(index, task);
    }

    /** Get a task from the task list at a certain index */
    public Task get(int i) {
        return taskList.get(i);
    }

    /** Create a task given an input from the user */
    public Task createTask(String input) throws DukeException {
        Task task;
        try {
            if (input.startsWith("deadline")) {
                task = parser.createDeadline(input);
            } else if (input.startsWith("event")) {
                task = parser.createEvent(input);
            } else if (input.startsWith("todo")){
                task = parser.createTodo(input);
            } else {
                throw new DukeException("Uhoh! You haven't told me if this is a deadline, event, or todo.");
            }
        } catch (DukeException dukeException) {
            throw dukeException;
        } catch (Exception e) {
            throw new DukeException("Unknown Error: " + e.getMessage());
        }
        return task;
    }

    /** Delete a task given the user input with an index */
    public void deleteTask(String input) {
        System.out.println(Utils.H_LINE + Utils.INDENT + "Deleting...");
        int deleteIndex = "delete".length();
        String number = input.substring(deleteIndex).replaceAll(" ", "");
        int index = Integer.parseInt(number) - 1;
        if (index >= Utils.getListIndex()) {
            System.out.print(Utils.INDENT + "Trying to delete an item outside of list length? Failed.");
        } else if (index < 0) {
            System.out.print(Utils.INDENT + "Trying to delete an item that is too small? Failed.");
        } else {
            taskList.remove(index);
            System.out.print(Utils.INDENT + "Success!");
            Utils.decrementListIndex();
            printList();
        }
        System.out.println(Utils.H_LINE + "\n");
    }


    /** Mark or unmark a task given a user input with an index */
    public void markOrUnmark(boolean toMark, String input) {
        String markString = toMark ? "Mark" : "Unmark";
        System.out.print(Utils.H_LINE + Utils.INDENT + markString + "ing...");
        int markIndex = toMark ? "mark".length() : "unmark".length();
        String number = input.substring(markIndex).replaceAll(" ", "");
        int index = Integer.valueOf(number) - 1;
        if (index >= Utils.getListIndex()) {
            System.out.print(Utils.INDENT + "Trying to " + markString + " an item outside of list length? Failed.");
        } else if (index < 0) {
            System.out.print(Utils.INDENT + "Trying to " + markString + " an item that is too small? Failed.");
        } else {
            if (toMark) {
                taskList.get(index).mark();
            } else {
                taskList.get(index).unmark();
            }
            System.out.print(Utils.INDENT + "Success!");
            printList();
        }
        System.out.println(Utils.H_LINE + "\n");
    }

}
