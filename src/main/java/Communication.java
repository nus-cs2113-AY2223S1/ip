import java.util.ArrayList;

public class Communication {
    public static void greet(){
        System.out.println("Hello! I'm Duke ");
        System.out.println("What can I do for you? \n");
    }

    public static void bye(){
        System.out.println(" Bye. Hope to see you again soon! \n");
    }

    public static void copy(String userInput){
        System.out.println(userInput);
    }

    public static void list(ArrayList<Task> ListOfTasks) {
        for (Task task : ListOfTasks) {
            System.out.println(task.index+".[" + task.getStatusIcon() +"] "+ task.name);
        }
    }
    public static void mark(Task task){
        System.out.println("Nice! I've marked this task as done:");
        task.UpdateStatus();
        System.out.println(task.printSelf());

    }
    public static void unmark(Task task){
        System.out.println("OK, I've marked this task as not done yet:");
        task.UpdateStatus();
        System.out.println(task.printSelf());

    }

}
