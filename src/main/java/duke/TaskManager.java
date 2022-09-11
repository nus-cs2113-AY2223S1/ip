package duke;

import duke.tasks.Task;

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
        tasks = new ArrayList<Task>();
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

    public int getTotalNumberOfTasks(){
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + (getTotalNumberOfTasks()) + " task(s) in the list.");
    }

    public void markTaskAsDone(int taskNumber) {
        if (taskNumber >= getTotalNumberOfTasks() || taskNumber < 0) {
            System.out.println("\tInvalid input! Please key in an existing task number!");
        } else if (tasks.get(taskNumber).isDone()) {
            System.out.println("\tThis task has already been marked as done!");
        } else {
            System.out.println("\tNice! I've marked this task as done: ");
            tasks.get(taskNumber).markAsDone();
            System.out.println("\t  " + tasks.get(taskNumber));
        }
    }

    public void markTaskAsUndone(int taskNumber) {
        if (taskNumber >= getTotalNumberOfTasks() || taskNumber < 0) {
            System.out.println("\tInvalid input! Please key in an existing task number!");
        } else if (!tasks.get(taskNumber).isDone()) {
            System.out.println("\tThis task has already been marked as not done!");
        } else {
            System.out.println("\tOK, I've marked this task as not done yet: ");
            tasks.get(taskNumber).markAsUndone();
            System.out.println("\t  " + tasks.get(taskNumber));
        }
    }

    public void listTasks() {
        if (getTotalNumberOfTasks() == 0) {
            System.out.println("\tNothing in list right now!");
            return;
        }
        System.out.println("\tHere are the tasks in your list: ");
        for (int taskNumber = 0; taskNumber < getTotalNumberOfTasks(); taskNumber++) {
            System.out.println("\t" + (taskNumber + 1) + ". " + tasks.get(taskNumber));
        }
    }

}
