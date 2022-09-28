import TaskManager.Task;
import TaskManager.TaskList;
import UI.FileHandler;
import UI.Parser;
import UI.UI;
import java.util.ArrayList;

public class Commands {
    static String description;

    public static void runList(){
        UI.printLine();
        TaskList.printList();
    }

    public static void runExit(){
        UI.printLine();
        System.out.println("Duke: Goodbye!");
        UI.printLine();
    }

    public static void runMark(){
        Task temp;
        int taskIndex = -1;

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
        TaskList.markTask(taskIndex);
        FileHandler.markAsDone(taskIndex);
        UI.printMarkAsDone(description);
    }

    public static void runUnmark(){
        Task temp;
        int taskIndex = -1;

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
        } else {
            System.out.println("Sorry, seems like you already have a task with the same name!");
        }
    }

    public static void runDeadline(){
        UI.printLine();
        int byPosition = Parser.getByPosition();
        String dueDate;
        
        // Handle invalid input
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
        } else {
            System.out.println("Sorry, seems like you already have a task with the same name!");
        }
    }

    public static void runEvent(){
        UI.printLine();
        int atPosition = Parser.getAtPosition();
        String dateTime;

        //Handle invalid input
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
        } else {
            System.out.println("Sorry, seems like you already have a task with the same name!");
        }
    }

    public static void runDelete(){
        Task temp;
        int taskIndex = -1;

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

    public static void runFind(){
        String keyword;

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



