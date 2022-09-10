package duke.task;
import duke.DukeException;
import java.util.ArrayList;

public class TaskManager {

    protected ArrayList<Task> myTasks;
    protected int taskCount;

    private final int START_INDEX_TODO = 5;
    private final int START_INDEX_DEADLINE = 9;
    private final int START_INDEX_EVENT = 6;
    private final String DEADLINE_FORMAT = "deadline {task name} /by {task deadline}";
    private final String EVENT_FORMAT = "event {task name} /at {task time}";


    public TaskManager() {
        myTasks = new ArrayList<>();
        taskCount = 0;
    }

    public void handleInput(String input, String command) throws DukeException {
        switch (command) {
        case "list":
            print();
            break;
        case "mark":
            markAsDone(input);
            break;
        case "unmark":
            removeMark(input);
            break;
        case "todo":
            addTodo(input);
            break;
        case "deadline":
            addDeadline(input);
            break;
        case "event":
            addEvent(input);
            break;
        case "delete":
            deleteTask(input);
            break;
        default:
            throw new DukeException();
        }
    }

    public void addTodo(String input) {
        try {
            String myTask = input.substring(START_INDEX_TODO);
            Todo newTodo = new Todo(myTask);
            myTasks.add(newTodo);
            System.out.println("Added: " + newTodo);
            taskCount++;
            System.out.println("Total tasks = " + taskCount);
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void addDeadline(String input) {
        try {
            int indexSlash = input.indexOf('/');
            String myTask = input.substring(START_INDEX_DEADLINE, indexSlash-1);
            String by = input.substring(indexSlash+4);
            Deadline newDeadline = new Deadline(myTask, by);
            myTasks.add(newDeadline);
            System.out.println("Added: " + newDeadline);
            taskCount++;
            System.out.println("Total tasks = " + taskCount);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Deadline format: " + DEADLINE_FORMAT);
        }
    }

    public void addEvent(String input) {
        try {
            int indexSlash = input.indexOf('/');
            String myTask = input.substring(START_INDEX_EVENT, indexSlash-1);
            String at = input.substring(indexSlash+4);
            Event newEvent = new Event(myTask, at);
            System.out.println("Added: " + newEvent);
            taskCount++;
            System.out.println("Total tasks = " + taskCount);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Event format: " + EVENT_FORMAT);
        }
    }

    public void markAsDone(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        taskNum--;
        if (taskNum >= 0 && taskNum < taskCount) {
            myTasks.get(taskNum).isDone = true;
            System.out.println("Marked: ");
            System.out.println(myTasks.get(taskNum).toString());
        }
    }

    public void removeMark(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        taskNum--;
        if (taskNum >= 0 && taskNum < taskCount) {
            myTasks.get(taskNum).isDone = false;
            System.out.println("Unmarked: ");
            System.out.println(myTasks.get(taskNum).toString());
        }
    }

    public void print() {
        for (int i = 0; i < myTasks.size(); i++) {
            System.out.print(i+1);
            System.out.print(". ");
            System.out.println(myTasks.get(i).toString());
        }
    }

    public void deleteTask(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        taskNum--;
        if (taskNum >= 0 && taskNum < taskCount) {
            System.out.println("Deleting task:");
            System.out.println(myTasks.get(taskNum).toString());
            myTasks.remove(taskNum);
            taskCount--;
            System.out.println("Remaining task count " + myTasks.size());
        }
    }
}
