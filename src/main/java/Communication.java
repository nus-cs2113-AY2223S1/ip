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
            System.out.println(task.index+": " + task.name);
        }
    }

}
