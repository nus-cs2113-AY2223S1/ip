package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task> tasks;

    static final String[] LIST_OF_COMMANDS = {"help", "todo", "mark", "unmark", "list", "bye", "deadline", "event", "delete"};

    public static final String DEADLINE_SEPERATOR = " /by ";
    public static final String EVENT_SEPERATOR = " /at ";

    public static final String HELP_MESSAGE
            = "\tlist of valid commands" +
            "\n\t----------------------" +
            "\n\n\ttodo " +
            "\n\t----" +
            "\n\trecord a generic task without a deadline; format: 'todo' [name of task]" +
            "\n\n\tdeadline " +
            "\n\t--------" +
            "\n\trecord a task with a specific deadline; format: 'deadline' [name of task] '/by' [deadline]" +
            "\n\n\tevent " +
            "\n\t-----" +
            "\n\trecord a task happening at a specific time; format: 'event' [name of event] '/at' [time]" +
            "\n\n\tmark " +
            "\n\t----" +
            "\n\tmark a specific task as done; format: 'mark' [number of task]" +
            "\n\n\tunmark" +
            "\n\t------" +
            "\n\tmark a specific task as not done; format: 'unmark' [number of task]" +
            "\n\n\tdelete" +
            "\n\t------" +
            "\n\tdelete a specific task from the list of tasks; format: 'delete' [number of task]" +
            "\n\n\tlist" +
            "\n\t----" +
            " \n\tlist out all tasks and their completion status" +
            "\n\n\tbye" +
            "\n\t---" +
            " \n\texit the program";

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }


    public static boolean isValidCommand(String command) {
        for (String validCommand : LIST_OF_COMMANDS) {
            if (command.equals(validCommand))
                return true;
        }
        return false;
    }

    public void printHelpMessage() {
        System.out.println(HELP_MESSAGE);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTotalNumberOfTasks() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + (getTotalNumberOfTasks()) + " task(s) in the list.");
    }

    public void removeTask(int taskNumber) {
        if (taskNumber >= getTotalNumberOfTasks() || taskNumber < 0) {
            System.out.println("\tInvalid input! Please key in an existing task number!");
            return;
        }
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);

        if (isEmpty()) {
            System.out.println("Nothing in list right now!");
        } else {
            System.out.println("\tNow you have " + (getTotalNumberOfTasks()) + " task(s) in the list.");
        }
    }


    public void loadTask(Task task) {
        tasks.add(task);
    }

    public void markTaskAsDone(int taskNumber) {
        if (taskNumber >= getTotalNumberOfTasks() || taskNumber < 0) {
            System.out.println("\tInvalid input! Please key in an existing task number!");
            return;
        }

        if (tasks.get(taskNumber).isDone()) {
            System.out.println("\tThis task has already been marked as done!");
            return;
        }

        System.out.println("\tNice! I've marked this task as done: ");
        tasks.get(taskNumber).markAsDone();
        System.out.println("\t  " + tasks.get(taskNumber));
    }

    public void markTaskAsUndone(int taskNumber) {
        if (taskNumber >= getTotalNumberOfTasks() || taskNumber < 0) {
            System.out.println("\tInvalid input! Please key in an existing task number!");
            return;
        }

        if (!tasks.get(taskNumber).isDone()) {
            System.out.println("\tThis task has already been marked as not done!");
            return;
        }

        System.out.println("\tOK, I've marked this task as not done yet: ");
        tasks.get(taskNumber).markAsUndone();
        System.out.println("\t  " + tasks.get(taskNumber));

    }

    public void listTasks() {
        if (isEmpty()) {
            System.out.println("\tNothing in list right now!");
            return;
        }
        System.out.println("\tHere are the tasks in your list: ");
        for (int taskNumber = 0; taskNumber < getTotalNumberOfTasks(); taskNumber++) {
            System.out.println("\t" + (taskNumber + 1) + ". " + tasks.get(taskNumber));
        }
    }

    public static Task initialiseTaskFromParameters(String taskType, String description, String status, String time) {
        Task task;
        switch (taskType) {
        case Todo.TASK_TYPE:
            task = new Todo(description);
            break;
        case Event.TASK_TYPE:
            task = new Event(description, time);
            break;
        case Deadline.TASK_TYPE:
            task = new Deadline(description, time);
            break;
        default:
            System.out.println("Error in data file!");
            Duke.exit(-1);
            return null;
        }
        if (status.equals(Task.getDoneIcon())) {
            task.markAsDone();
        }
        return task;
    }

}
