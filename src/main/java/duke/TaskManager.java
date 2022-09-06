package duke;

import duke.tasks.Task;

public class TaskManager {
    Task[] tasks;
    int numTasks;
    static final int MAX_NUM_TASKS = 100;

    static final String[] LIST_OF_COMMANDS = {"help", "todo", "mark", "unmark", "list", "bye", "deadline", "event"};

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
            " \n\tmark a specific task as not done; format 'unmark' [number of task]" +
            "\n\n\tlist" +
            "\n\t----" +
            " \n\tlist out all tasks and their completion status" +
            "\n\n\tbye" +
            "\n\t---" +
            " \n\texit the program";

    public TaskManager() {
        tasks = new Task[MAX_NUM_TASKS];
        numTasks = -1;
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

    public Task[] getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks[++numTasks] = task;
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + (numTasks + 1) + " task(s) in the list.");
    }

    public void markTaskAsDone(int taskNumber) {
        if (taskNumber > numTasks || taskNumber < 0) {
            System.out.println("\tInvalid input! Please key in an existing task number!");
        } else if (tasks[taskNumber].isDone()) {
            System.out.println("\tThis task has already been marked as done!");
        } else {
            System.out.println("\tNice! I've marked this task as done: ");
            tasks[taskNumber].markAsDone();
            System.out.println("\t  " + tasks[taskNumber]);
        }
    }

    public void markTaskAsUndone(int taskNumber) {
        if (taskNumber > numTasks || taskNumber < 0) {
            System.out.println("\tInvalid input! Please key in an existing task number!");
        } else if (!tasks[taskNumber].isDone()) {
            System.out.println("\tThis task has already been marked as not done!");
        } else {
            System.out.println("\tOK, I've marked this task as not done yet: ");
            tasks[taskNumber].markAsUndone();
            System.out.println("\t  " + tasks[taskNumber]);
        }
    }

    public void listTasks() {
        if (numTasks == -1) {
            System.out.println("\tNothing in list right now!");
            return;
        }
        System.out.println("\tHere are the tasks in your list: ");
        for (int i = 0; i <= numTasks; i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks[i]);
        }
    }

}
