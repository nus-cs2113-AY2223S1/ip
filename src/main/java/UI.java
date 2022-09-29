import java.util.ArrayList;
import java.util.Scanner;
public class UI {
    public static final String line = "____________________________________________________________";

    public static void welcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line );
    }
    public static void exitMessage(){
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void addMessage(String message, int taskCount){
        System.out.println(line );
        System.out.println("Got it. I've added this task:\n"+message +"\n"+"Now you have "+taskCount+ " tasks in the list.");
        System.out.println(line );

    }

    public static void deleteMessage(String message, int taskCount){
        System.out.println(line );
        System.out.println("Noted. I've removed this task:");
        System.out.println(message);
        System.out.println("Now you have "+taskCount+ " tasks in the list.");
        System.out.println(line );

    }
    
    public static void markMessage(String status, String description, String statusIcon){
        if(status.equals("mark")){
            System.out.println("Nice! I've marked this task as done:");
        }
        if(status.equals("unmark")) {
            System.out.println("OK, I've marked this task as not done yet:");  
        }
        System.out.println("["+ statusIcon+"] "+description);
    }

    public static void listMessage(ArrayList<Task> tasks){
        System.out.println(line );
        if(tasks.size()==0){
            System.out.println("No Tasks in list");
        } else{
            for (Task task : tasks) {
                System.out.println(task.toString());
            }
        }
        System.out.println(line );
    }

    public static String getInput() {
        Scanner in = new Scanner(System.in);
        String inputText = in.nextLine();
        return inputText.trim();
    }
}
