package UI;
import TaskManager.Task;
import TaskManager.TaskList;
import java.util.ArrayList;

public class UI {
    private static String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";

    private static String commandList = "Command List:\n"
        + "list <list>: shows all existing tasks\n"
        + "event <event task_name at task_time>: adds a new event task\n"
        + "todo <todo task_name>: adds a new todo task\n"
        + "deadline <deadline task_name by task_deadline>: adds a new deadline task\n"
        + "mark <mark task_name>: marks a task as done\n"
        + "unmark <unmark task_name>: marks a task as not done\n"
        + "delete <delete task_index/task_name>: removes task from list\n"
        + "find <find keyword>: searches for all tasks that includes keyword"
        + "bye <bye>: exits program\n";

    public static void printLine(){
        System.out.println("---------------------------------------------------");
    }

    public static void printTaskInfo(Task task){ 
        String description = task.getDescription();
        String taskType = task.getTaskType();
        String statusIcon = task.getStatusIcon();
        int taskIndex = TaskList.getTaskIndex(description)+1;

        System.out.print(taskIndex +") [" + taskType + "][" + statusIcon + "] " + description);

        if (taskType.equals("T")){
            System.out.println(" ");
        } else if (taskType.equals("E")){
            System.out.println(" (at: " + task.getDateTime() + ")");
        } else if (taskType.equals("D")) {
            System.out.println(" (by: " + task.getDueDate() + ")");
        }
    }

    public static void printTaskInfo(String description){
        Task task = TaskList.searchTask(description);
        String taskType = task.getTaskType();
        String statusIcon = task.getStatusIcon();
        int taskIndex = TaskList.getTaskIndex(description)+1;

        System.out.print(taskIndex +") [" + taskType + "][" + statusIcon + "] " + description);

        if (taskType.equals("T")){
            System.out.println(" ");
        } else if (taskType.equals("E")){
            System.out.println(" (at: " + task.getDateTime() + ")");
        } else if (taskType.equals("D")) {
            System.out.println(" (by: " + task.getDueDate() + ")");
        }
    }

    public static void greetUser(){
        System.out.println("Hello from");
        System.out.println(logo);

        printLine();
        System.out.println(commandList);

        printLine();
        System.out.println("Duke: Hello! What can I do for you today?");
    }

    public static void printMarkAsDone(String description){
        System.out.println("Marked as done:");
        printTaskInfo(description);
    }

    public static void printMarkAsNotDone(String description){
        System.out.println("Marked as not done:");
        printTaskInfo(description);
    }

    public static void printTaskAdded(String description){
        System.out.println("Added:");
        printTaskInfo(description);
        System.out.println("Now you have " + TaskList.getSize() + " tasks in your list!");
    }

    public static void printTaskDeleted(Task tempTask){
        System.out.println("Removed:");
        printTaskInfo(tempTask);
        System.out.println("Now you have " + (TaskList.getSize()-1) + " tasks in your list!");
    }

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
