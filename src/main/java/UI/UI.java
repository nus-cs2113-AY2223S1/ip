package UI;
import TaskManager.Task;
import TaskManager.TaskList;
import java.util.ArrayList;

public class UI {
    /*
     * Logo to be printed at start of program
     */
    private static String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";

    /*
     * List of commands followed by syntax
     */
    private static String commandList = "Command List:\n"
        + "list <list>: shows all existing tasks\n"
        + "event <event task_name at task_time>: adds a new event task\n"
        + "todo <todo task_name>: adds a new todo task\n"
        + "deadline <deadline task_name by task_deadline>: adds a new deadline task\n"
        + "mark <mark task_name/task_index>: marks a task as done\n"
        + "unmark <unmark task_name/task_index>: marks a task as not done\n"
        + "delete <delete task_name/task_index>: removes task from list\n"
        + "find <find keyword>: searches for all tasks that includes keyword\n"
        + "bye <bye>: exits program\n";

    /*
     * Prints a line
     */
    public static void printLine(){
        System.out.println("---------------------------------------------------");
    }

    /*
     * Prints TaskIndex, TaskStatus, TaskName, TaskDateTime(only for event/deadline tasks)
     * Takes in a task as input
     */
    public static void printTaskInfo(Task task){ 
        /*
         * Get task information required
         */
        String description = task.getDescription();
        String taskType = task.getTaskType();
        String statusIcon = task.getStatusIcon();
        int taskIndex = TaskList.getTaskIndex(description)+1;

        System.out.print(taskIndex +") [" + taskType + "][" + statusIcon + "] " + description);

        /*
         * Add date/time for Event/Deadline tasks
         */
        if (taskType.equals("T")){
            System.out.println(" ");
        } else if (taskType.equals("E")){
            System.out.println(" (at: " + task.getDateTime() + ")");
        } else if (taskType.equals("D")) {
            System.out.println(" (by: " + task.getDueDate() + ")");
        }
    }

    /*
     * Prints TaskIndex, TaskStatus, TaskName, TaskDateTime(only for event/deadline tasks)
     * Takes in a description as input
     */
    public static void printTaskInfo(String description){
        /*
         * Get task information required
         */
        Task task = TaskList.searchTask(description);
        String taskType = task.getTaskType();
        String statusIcon = task.getStatusIcon();
        int taskIndex = TaskList.getTaskIndex(description)+1;

        System.out.print(taskIndex +") [" + taskType + "][" + statusIcon + "] " + description);

        /*
         * Add date/time for Event/Deadline tasks
         */
        if (taskType.equals("T")){
            System.out.println(" ");
        } else if (taskType.equals("E")){
            System.out.println(" (at: " + task.getDateTime() + ")");
        } else if (taskType.equals("D")) {
            System.out.println(" (by: " + task.getDueDate() + ")");
        }
    }

    /*
     * Prints Logo, Welcome message and Command List
     * Run at start of program
     */
    public static void greetUser(){
        System.out.println("Hello from");
        System.out.println(logo);

        printLine();
        System.out.println(commandList);

        printLine();
        System.out.println("Duke: Hello! What can I do for you today?");
    }

    /*
     * Message to be printed at end of successful Mark function
     */
    public static void printMarkAsDone(String description){
        System.out.println("Marked as done:");
        printTaskInfo(description);
    }
 
    /*
     * Message to be printed at end of successful Unmark function
     */
    public static void printMarkAsNotDone(String description){
        System.out.println("Marked as not done:");
        printTaskInfo(description);
    }

    /*
     * Message to be printed at end of successful ToDo/Event/Deadline function
     */
    public static void printTaskAdded(String description){
        System.out.println("Added:");
        printTaskInfo(description);
        System.out.println("Now you have " + TaskList.getSize() + " tasks in your list!");
    }

    /*
     * Message to be printed at end of successful Delete function
     */
    public static void printTaskDeleted(Task tempTask){
        System.out.println("Removed:");
        printTaskInfo(tempTask);
        System.out.println("Now you have " + (TaskList.getSize()-1) + " tasks in your list!");
    }

    /*
     * Message to be printed at end of successful Find function
     * Takes in list of Tasks found by Find function as input
     */
    public static void printFind(ArrayList<Task> result){
        if (result.size() == 0){
            System.out.println("Sorry, there are no tasks matching your search!");
        } else {
            System.out.println("Here are the tasks matching your search: ");
            for (int i=0; i<result.size(); i++){
                Task tempTask = result.get(i);
                printTaskInfo(tempTask);
            }
        }
    }
}
