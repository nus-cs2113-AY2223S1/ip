package UI;
import TaskManager.Task;
import TaskManager.TaskList;

import java.util.ArrayList;

public class Commands {
    static String description; //Represents name of tasks
    static Task temp;
    static int taskIndex = -1;
    static String keyword;

    /*
     * List function:
     * Lists all existing tasks
     */
    public static void runList(){
        UI.printLine();
        TaskList.printList();
    }

    /*
     * Exit function:
     * Exits program
     */
    public static void runExit(){
        UI.printLine();
        System.out.println("Duke: Goodbye!");
        UI.printLine();
    }

    /*
     * Mark function:
     * Marks a task as done
     * Task specified by index/name
     */
    public static void runMark(){
        UI.printLine();

        //Handle empty task
        try{
            taskIndex = Parser.getTaskIndex();
            description = TaskList.getTaskAtIndex(taskIndex).getDescription();
        } catch (NumberFormatException e){
            description = Parser.getDescription();
            taskIndex = TaskList.getTaskIndex(description);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Task index/name cannot be empty!");
            return;
        }

        //Handle when a task not in list is given
        try{
            temp = TaskList.getTaskAtIndex(taskIndex);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Task does not exist, please give a valid task index/name!");
            return;
        }
        
        //Printing result
        TaskList.markTask(taskIndex);
        FileHandler.markAsDone(taskIndex);
        UI.printMarkAsDone(description);
    }

    /*
     * Unmark function:
     * Marks a task as not done
     * Task specified by index/name
     */
    public static void runUnmark(){
        UI.printLine();

        //Handle empty/invalid task
        try{
            taskIndex = Parser.getTaskIndex();
            description = TaskList.getTaskAtIndex(taskIndex).getDescription();
        } catch (NumberFormatException e){
            description = Parser.getDescription();
            taskIndex = TaskList.getTaskIndex(description);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Task index/name cannot be empty!");
            return;
        }

        try{
            temp = TaskList.getTaskAtIndex(taskIndex);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Task does not exist, please give a valid task index/name!");
            return;
        }
        
        //Printing result
        TaskList.unmarkTask(taskIndex);
        FileHandler.markAsNotDone(taskIndex);
        UI.printMarkAsNotDone(description);
    }

    /*
     * ToDo function:
     * Adds a ToDo task to list
     */
    public static void runToDo(){
        UI.printLine();

        try{
            description = Parser.getDescription();
        } catch (IllegalArgumentException e){
            System.out.println("Task cannot be empty!");
            return;
        }

        //Printing result
        if (TaskList.searchTask(description) == null){
            TaskList.addToDo(description);
            FileHandler.addTask(TaskList.getTaskAtIndex(TaskList.getSize()-1));
            UI.printTaskAdded(description);
        } else { //Handle duplicate task
            System.out.println("Sorry, seems like you already have a task with the same name!");
        }
    }

    /*
     * Deadline function:
     * Adds a Deadline task to list
     */
    public static void runDeadline(){
        UI.printLine();
        int byPosition = Parser.getByPosition();
        String dueDate;
        
        // Handle empty task/date
        try {
            description = Parser.getDescription(byPosition);
            dueDate = Parser.getDate(byPosition);
        } catch (IllegalArgumentException e){
            System.out.println("Task/date cannot be empty!");
            return;
        }

        //Printing result
        if (TaskList.searchTask(description) == null){
            TaskList.addDeadline(description, dueDate);
            FileHandler.addTask(TaskList.getTaskAtIndex(TaskList.getSize()-1));
            UI.printTaskAdded(description);
        } else { //Handle duplicate task
            System.out.println("Sorry, seems like you already have a task with the same name!");
        }
    }

    /*
     * Event function:
     * Adds an Event task to list
     */
    public static void runEvent(){
        UI.printLine();
        int atPosition = Parser.getAtPosition();
        String dateTime;

        // Handle empty task/date
        try{
            description = Parser.getDescription(atPosition);
            dateTime = Parser.getDate(atPosition);
        } catch (IllegalArgumentException e){
            System.out.println("Task/date cannot be empty!");
            return;
        }

        //Printing result
        if (TaskList.searchTask(description) == null){
            TaskList.addEvent(description, dateTime);
            FileHandler.addTask(TaskList.getTaskAtIndex(TaskList.getSize()-1));
            UI.printTaskAdded(description);
        } else { //Handle duplicate task
            System.out.println("Sorry, seems like you already have a task with the same name!");
        }
    }

    /*
     * Delete:
     * Removes a task from list
     * Task specified by name/index
     */
    public static void runDelete(){
        UI.printLine();

        try{
            taskIndex = Parser.getTaskIndex();
        } catch (NumberFormatException e){
            description = Parser.getDescription();
            taskIndex = TaskList.getTaskIndex(description);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Task index/name cannot be empty!");
            return;
        }

        try{
            temp = TaskList.getTaskAtIndex(taskIndex);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Task does not exist, please give a valid task index/name!");
            return;
        }
        UI.printTaskDeleted(temp);
        TaskList.deleteTask(taskIndex);
        FileHandler.deleteTask(taskIndex);
    }

    /*
     * Find function
     * Searches for all tasks containing the keyword within its name/duedate
     */
    public static void runFind(){
        UI.printLine();
        try{
            keyword = Parser.getDescription();
        } catch (IllegalArgumentException e){
            System.out.println("Keyword cannot be empty!");
            return;
        }
        ArrayList<Task> result = TaskList.find(keyword);
        UI.printFind(result);
    }
}



