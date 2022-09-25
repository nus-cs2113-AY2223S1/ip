package duke.task;

import duke.Duke;
import duke.Message;
import duke.Storage;
import duke.task.exception.IllegalTaskNumberInputException;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Todo;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    public static final String FILE_STRING_SEPARATOR = " // ";

    private final Storage storage;

    public TaskList() {
        storage = new Storage();
        tasks = new ArrayList<>(storage.loadTasks());
    }

    public void add(Task task) {
        tasks.add(task);
        System.out.println("Task added: " + task);
        save();
    }

    public void list() {
        if (tasks.size() > 0) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }
        } else {
            System.out.println(Message.NO_TASKS_MESSAGE + " " + Message.HELP_MESSAGE);
        }
    }

    private int getTaskIndex(String input) throws IllegalTaskNumberInputException {
        String[] inputWords = input.split(" ", 2);
        try {
            return Integer.parseInt(inputWords[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Message.MISSING_TASK_NUMBER_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get(inputWords[0]).syntax);
            throw new IllegalTaskNumberInputException();
        } catch (NumberFormatException e) {
            System.out.println(Message.WRONG_TASK_NUMBER_FORMAT_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get(inputWords[0]).syntax);
            throw new IllegalTaskNumberInputException();
        }
    }

    public void markTaskAsDone(String input) {
        int taskIndex;
        try {
            taskIndex = getTaskIndex(input);
        } catch (IllegalTaskNumberInputException e) {
            return;
        }

        try {
            tasks.get(taskIndex).markAsDone();
            System.out.printf("Marked as done: %s\n", tasks.get(taskIndex));
            save();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Message.WRONG_TASK_NUMBER_RANGE_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get("mark").syntax);
        }
    }

    public void markTaskAsUndone(String input) {
        int taskIndex;
        try {
            taskIndex = getTaskIndex(input);
        } catch (IllegalTaskNumberInputException e) {
            return;
        }

        try {
            tasks.get(taskIndex).unmarkDone();
            System.out.printf("Unmarked done: %s\n", tasks.get(taskIndex));
            save();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Message.WRONG_TASK_NUMBER_RANGE_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get("unmark").syntax);
        }
    }

    public void addTodoTask(String input) {
        try {
            String[] parameters = Todo.extractParameters(input);
            String description = parameters[0].trim();
            add(new Todo(description));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Message.INVALID_ADD_TODO_FORMAT_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get("todo").syntax);
        }
    }

    public void addDeadlineTask(String input) {
        try {
            String[] parameters = Deadline.extractParameters(input);
            String description = parameters[0].trim();
            String deadlineDate = parameters[1].trim();
            add(new Deadline(description, deadlineDate));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Message.INVALID_ADD_DEADLINE_FORMAT_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get("deadline").syntax);
        }
    }

    public void addEventTask(String input) {
        try {
            String[] parameters = Event.extractParameters(input);
            String description = parameters[0].trim();
            String datetime = parameters[1].trim();
            add(new Event(description, datetime));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Message.INVALID_ADD_EVENT_FORMAT_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get("event").syntax);
        }
    }

    public void delete(String input) {
        int taskIndex;
        try {
            taskIndex = getTaskIndex(input);
        } catch (IllegalTaskNumberInputException e) {
            return;
        }

        try {
            tasks.remove(taskIndex);
            System.out.printf("Task %d deleted\n", taskIndex + 1);
            save();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Message.WRONG_TASK_NUMBER_RANGE_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get("delete").syntax);
        }
    }

    public void save() {
        storage.saveTasks(tasks);
    }

    private String[] parseFileLine(String line) {
        return line.split(FILE_STRING_SEPARATOR);
    }
}
