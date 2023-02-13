/**
 * UI.java class
 * Handles all interactions with the user
 * contains all messages to be printed in different scenarios
 * takes input from user and returns it
 */

import java.util.ArrayList;
import java.util.Scanner;
public class UI {
    public static final String line = "____________________________________________________________";

    /**
     * Contains the welcome message to be printed at the beginning of runtime
     */
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

    /**
     * Contains the exit message to be printed before closing the application
     */
    public static void exitMessage(){
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    /**
     * Contains the message to be printed after a task has been added to the collection
     * @param message specifies the entire command given by the user
     *                it is in the same format as entered by the user in CLI
     * @param taskCount specifies the total number of tasks in the collection after adding the new task
     */
    public static void addMessage(String message, int taskCount){
        System.out.println(line );
        System.out.println("Got it. I've added this task:\n"+message +"\n"+"Now you have "+taskCount+ " tasks in the list.");
        System.out.println(line );

    }

    /**
     * contains the message to be printed after a task has been deleted from the collection
     * @param message Contains details of the task that has been deleted
     *                In the toString() format: [TASK_TYPE][STATUS] DESCRIPTION (DEADLINE_BY/EVENT_AT)
     * @param taskCount specifies the total number of tasks in the collection after deleting the task
     */
    public static void deleteMessage(String message, int taskCount){
        System.out.println(line );
        System.out.println("Noted. I've removed this task:");
        System.out.println(message);
        System.out.println("Now you have "+taskCount+ " tasks in the list.");
        System.out.println(line );

    }

    /**
     * Contains the message to be printed after marking or unmarking a particular task
     * @param status Specifies whether to mark a message or unmark it
     *               Based on this, the relevant mark or unmark message is displayed
     * @param description Gives the description of the task that has to be marked or unmarked
     * @param statusIcon Gives the new status of the task after it has been marked or unmarked
     */
    public static void markMessage(String status, String description, String statusIcon){
        if(status.equals("mark")){
            System.out.println("Nice! I've marked this task as done:");
        }
        if(status.equals("unmark")) {
            System.out.println("OK, I've marked this task as not done yet:");  
        }
        System.out.println("["+ statusIcon+"] "+description);
    }

    /**
     * Prints all the tasks in the list
     * Prints "No Tasks in list" if there are no tasks in the collection
     * @param tasks is a reference to the ArrayList of tasks.
     */
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

    /**
     * gets input from the CLI and trims it
     * @return input given by user in String format
     */

    public static String getInput() {
        Scanner in = new Scanner(System.in);
        String inputText = in.nextLine();
        return inputText.trim();
    }
}
