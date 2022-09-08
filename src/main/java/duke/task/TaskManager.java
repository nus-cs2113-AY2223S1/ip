package duke.task;

import duke.*;
import duke.task.exception.IllegalTaskNumberInputException;
import duke.task.exception.TaskCountExceedMaximumException;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Todo;

public class TaskManager {
    public static final int MAX_TASK = 100;
    private final Task[] tasks;
    private int taskCount;

    public TaskManager() {
        tasks = new Task[MAX_TASK];
        taskCount = 0;
    }

    private void addTask(Task task) throws TaskCountExceedMaximumException {
        if (taskCount >= MAX_TASK) {
            throw new TaskCountExceedMaximumException();
        }
        else {
            tasks[taskCount++] = task;
            System.out.println("Task added: " + task);
        }
    }

    public void listTasks() {
        if (taskCount > 0) {
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("%d. %s\n", i + 1, tasks[i]);
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
            tasks[taskIndex].markAsDone();
            System.out.printf("Marked as done: %s\n", tasks[taskIndex]);
        } catch (NullPointerException e) {
            System.out.println(Message.WRONG_TASK_NUMBER_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get("mark").syntax);
        } catch (ArrayIndexOutOfBoundsException e) {
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
            tasks[taskIndex].unmarkDone();
            System.out.printf("Unmarked done: %s\n", tasks[taskIndex]);
        } catch (NullPointerException e) {
            System.out.println(Message.WRONG_TASK_NUMBER_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get("unmark").syntax);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Message.WRONG_TASK_NUMBER_RANGE_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get("unmark").syntax);
        }
    }

    public void addTodoTask(String input) {
        try {
            String[] parameters = Todo.extractParameters(input);
            String description = parameters[0].trim();
            addTask(new Todo(description));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Message.INVALID_ADD_TODO_FORMAT_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get("todo").syntax);
        } catch (TaskCountExceedMaximumException e) {
            System.out.println(Message.MAXIMUM_TASKS_REACHED_ERROR_MESSAGE);
        }
    }

    public void addDeadlineTask(String input) {
        try {
            String[] parameters = Deadline.extractParameters(input);
            String description = parameters[0].trim();
            String deadlineDate = parameters[1].trim();
            addTask(new Deadline(description, deadlineDate));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Message.INVALID_ADD_DEADLINE_FORMAT_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get("deadline").syntax);
        } catch (TaskCountExceedMaximumException e) {
            System.out.println(Message.MAXIMUM_TASKS_REACHED_ERROR_MESSAGE);
        }
    }

    public void addEventTask(String input) {
        try {
            String[] parameters = Event.extractParameters(input);
            String description = parameters[0].trim();
            String datetime = parameters[1].trim();
            addTask(new Event(description, datetime));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Message.INVALID_ADD_EVENT_FORMAT_ERROR_MESSAGE);
            System.out.println("Syntax: " + Duke.COMMANDS.get("event").syntax);
        } catch (TaskCountExceedMaximumException e) {
            System.out.println(Message.MAXIMUM_TASKS_REACHED_ERROR_MESSAGE);
        }
    }
}
