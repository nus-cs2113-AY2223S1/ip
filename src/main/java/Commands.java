import TaskManager.Task;
import TaskManager.TaskList;
import UI.FileHandler;
import UI.Parser;
import UI.UI;

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
        UI.printLine();
        try{
            description = Parser.getDescription();
            TaskList.searchTask(description).markAsDone();
            FileHandler.markAsDone(TaskList.getTaskIndex(description));
        } catch (NullPointerException e){ //invalid 
            System.out.println("Please enter a valid task!");
            return;
        } catch (IllegalArgumentException e){ //empty
            System.out.println("Task cannot be empty!");
            return;
        }

        UI.printMarkAsDone(description);
    }

    public static void runUnmark(){
        UI.printLine();

        //Handle empty/invalid task
        try{
            description = Parser.getDescription();
            TaskList.searchTask(description).markAsNotDone();
            FileHandler.markAsNotDone(TaskList.getTaskIndex(description));
        } catch (NullPointerException e){
            System.out.println("Please enter a valid task! ");
            return;
        } catch (IllegalArgumentException e){ //empty
            System.out.println("Task cannot be empty!");
            return;
        }
        
        //Printing result
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
            System.out.println("Invalid input: task/date not given!");
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
            System.out.println("Invalid input: task/date not given!");
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
            taskIndex = Parser.getTaskIndex()-1;
        } catch (NumberFormatException e){
            description = Parser.getDescription();
            taskIndex = TaskList.getTaskIndex(description);
        }

        try{
            temp = TaskList.getTaskAtIndex(taskIndex);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Sorry, index out of range!");
            return;
        }
        TaskList.deleteTask(taskIndex);
        FileHandler.deleteTask(taskIndex);
        UI.printTaskDeleted(temp);
    }
}



