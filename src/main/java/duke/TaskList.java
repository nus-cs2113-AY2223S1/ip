package duke;

import duke.exceptions.DukeException;
import duke.exceptions.MarkedTaskException;
import duke.exceptions.UnmarkedTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

/**
 * Class of task manager that manages the commands and tasks
 */

public class TaskList {

    protected ArrayList<Task> myTasks;

    //numbers to help parse the input
    private final int START_INDEX_TODO = 5;
    private final int START_INDEX_DEADLINE = 9;
    private final int START_INDEX_EVENT = 6;
    private final int START_INDEX_FIND = 5;
    //proper format to guide the user on the input
    private final String DEADLINE_FORMAT = "deadline {task name} /by {task deadline}";
    private final String EVENT_FORMAT = "event {task name} /at {task time}";
    private final String NO_TASKS = "No current tasks";


    public TaskList() {
        myTasks = new ArrayList<>();
    }

    /**
     * Method to add a Task of type "todo"
     * @param input the user input
     */
    public void addTodo(String input) {
        try {
            String myTask = input.substring(START_INDEX_TODO);
            Todo newTodo = new Todo(myTask);
            myTasks.add(newTodo);
            System.out.println("Added todo: " + newTodo);
            System.out.println("Total tasks = " + myTasks.size());
            Storage.updateFile(myTasks);
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Method to add a Task of type "deadline"
     * @param input the user input
     */
    public void addDeadline(String input) {
        try {
            int indexSlash = input.indexOf('/');
            String myTask = input.substring(START_INDEX_DEADLINE, indexSlash-1);
            String by = input.substring(indexSlash+4);
            Deadline newDeadline = new Deadline(myTask, by);
            myTasks.add(newDeadline);
            System.out.println("Added deadline: " + newDeadline);
            System.out.println("Total tasks = " + myTasks.size());
            Storage.updateFile(myTasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Deadline format: " + DEADLINE_FORMAT);
        }
    }

    /**
     * Method to add a Task of type "event"
     * @param input the user input
     */
    public void addEvent(String input) {
        try {
            int indexSlash = input.indexOf('/');
            String myTask = input.substring(START_INDEX_EVENT, indexSlash-1);
            String at = input.substring(indexSlash+4);
            Event newEvent = new Event(myTask, at);
            myTasks.add(newEvent);
            System.out.println("Added event: " + newEvent);
            System.out.println("Total tasks = " + myTasks.size());
            Storage.updateFile(myTasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Event format: " + EVENT_FORMAT);
        }
    }

    /**
     * Method to mark a task as done
     * @param input the user input
     */
    public void markAsDone(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        taskNum--;
        try {
            if (myTasks.get(taskNum).isDone) {
                throw new MarkedTaskException();
            }
            myTasks.get(taskNum).isDone = true;
            System.out.println("Marked: ");
            System.out.println(myTasks.get(taskNum).toString());
            Storage.updateFile(myTasks);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Task ID not within range");
        } catch (NullPointerException e) {
            System.out.println("Task ID does not exist");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a numerical value for Task ID");
        } catch (MarkedTaskException e) {
            System.out.println("Task already marked");
        }
    }

    /**
     * Method to unmark a task
     * @param input the user input
     */
    public void removeMark(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        taskNum--;
        try {
            if (!myTasks.get(taskNum).isDone) {
                throw new UnmarkedTaskException();
            }
            myTasks.get(taskNum).isDone = false;
            System.out.println("Unmarked: ");
            System.out.println(myTasks.get(taskNum).toString());
            Storage.updateFile(myTasks);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Task ID not within range");
        } catch (NullPointerException e) {
            System.out.println("Task ID does not exist");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a numerical value for Task ID");
        } catch (UnmarkedTaskException e) {
            System.out.println("Task already unmarked");
        }
    }

    /**
     * Method to delete a task
     * @param input the user input
     */
    public void deleteTask(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        taskNum--;
        try {
            System.out.println("Deleting task:");
            System.out.println(myTasks.get(taskNum).toString());
            myTasks.remove(taskNum);
            System.out.println("Remaining task count " + myTasks.size());
            Storage.updateFile(myTasks);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Task ID not within range");
        } catch (NullPointerException e) {
            System.out.println("Task ID does not exist");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a numerical value for Task ID");
        }
    }

    /**
     * Method to find a task
     * @param input the user input containing the keyword to search for
     */
    public void findTask(String input) {
        try {
            String keyword = input.substring(START_INDEX_FIND);
            int counter = 1;
            for (Task myTask : myTasks) {
                if (myTask.description.contains(keyword)) {
                    System.out.println(counter + ": " + myTask.toString());
                    counter++;
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of find cannot be empty.");
        }
    }

    /**
     * Method to print all the tasks in the taskList
     */
    public void print() {
        if (myTasks.size() == 0) {
            System.out.println(NO_TASKS);
        }
        for (int i = 0; i < myTasks.size(); i++) {
            System.out.print(i+1);
            System.out.print(". ");
            System.out.println(myTasks.get(i).toString());
        }
    }


}
