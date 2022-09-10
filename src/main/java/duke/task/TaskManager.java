package duke.task;
import duke.DukeException;
import duke.FileSaver;
import java.io.File;
import java.io.IOException;

public class TaskManager {
    public Task[] tasks;
    protected int taskCount;

    private final int START_INDEX_TODO = 5;
    private final int START_INDEX_DEADLINE = 9;
    private final int START_INDEX_EVENT = 6;
    private final String DEADLINE_FORMAT = "deadline {task name} /by {task deadline}";
    private final String EVENT_FORMAT = "event {task name} /at {task time}";


    public TaskManager() {
        tasks = new Task[100];
        taskCount = 0;
    }

    public void handleInput(String input) {
        String command;
        if (input.contains(" ")) {
            command = input.substring(0, input.indexOf(" "));
        } else {
            command = input;
        }
        try {
            handleInput(input, command);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
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
        default:
            throw new DukeException();
        }
    }

    public void addTodo(String input) {
        try {
            String myTask = input.substring(START_INDEX_TODO);
            tasks[taskCount] = new Todo(myTask);
            System.out.println("Added: " + tasks[taskCount].toString());
            taskCount++;
            System.out.println("Total tasks = " + taskCount);
            FileSaver.updateFile(tasks, taskCount);
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void addDeadline(String input) {
        try {
            int indexSlash = input.indexOf('/');
            String myTask = input.substring(START_INDEX_DEADLINE, indexSlash-1);
            String by = input.substring(indexSlash+4);
            tasks[taskCount] = new Deadline(myTask, by);
            System.out.println("Added: " + tasks[taskCount].toString());
            taskCount++;
            System.out.println("Total tasks = " + taskCount);
            FileSaver.updateFile(tasks, taskCount);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Deadline format: " + DEADLINE_FORMAT);
        }
    }

    public void addEvent(String input) {
        try {
            int indexSlash = input.indexOf('/');
            String myTask = input.substring(START_INDEX_EVENT, indexSlash-1);
            String at = input.substring(indexSlash+4);
            tasks[taskCount] = new Event(myTask, at);
            System.out.println("Added: " + tasks[taskCount].toString());
            taskCount++;
            System.out.println("Total tasks = " + taskCount);
            FileSaver.updateFile(tasks, taskCount);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Event format: " + EVENT_FORMAT);
        }
    }

    public void markAsDone(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        taskNum--;
        if (taskNum >= 0 && taskNum < taskCount) {
            tasks[taskNum].isDone = true;
            System.out.println("Marked: ");
            System.out.println(tasks[taskNum].toString());
            FileSaver.updateFile(tasks, taskCount);
        }
    }

    public void removeMark(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        taskNum--;
        if (taskNum >= 0 && taskNum < taskCount) {
            tasks[taskNum].isDone = false;
            System.out.println("Unmarked: ");
            System.out.println(tasks[taskNum].toString());
            FileSaver.updateFile(tasks, taskCount);
        }
    }

    public void print() {
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i+1);
            System.out.print(". ");
            System.out.println(tasks[i].toString());
        }
    }

}
