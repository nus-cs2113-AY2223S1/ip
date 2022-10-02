package ui;

import task.Task;
import java.util.ArrayList;

/**
 * Handles UI-related work
 */
public class Ui {
    public static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void showExitMessage(){
        printSplitLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printSplitLine();
    }

    public static void printSplitLine(){
        System.out.println("    ____________________________________________________________");
    }
    
    public static void printTaskResponse(String response, int listLength){
        printSplitLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       "+response);
        System.out.println("     Now you have "+listLength+" tasks in the list.");
        printSplitLine();
    }
    
    public static void printDeleteResponse(String response, int listLength){
        printSplitLine();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("     "+response);
        System.out.println("     Now you have "+listLength+" tasks in the list.");
        printSplitLine();
    }

    public static void printMarkResponse(String response){
        printSplitLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("     "+response);
        printSplitLine();
    }

    public static void printUnmarkResponse(String response){
        printSplitLine();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("     "+response);
        printSplitLine();
    }

    public static void printErrorMessage(String errorMessage){
        printSplitLine();
        System.out.println(errorMessage);
        printSplitLine();
    }

    public static void showAllTasks(ArrayList<Task> taskList){
        Ui.printSplitLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + String.valueOf(i+1) + "." + taskList.get(i).getResponse());
        }
        Ui.printSplitLine();
        System.out.println();
    }

    public static void printTaskByKeyword(ArrayList<Task> taskList, String keyword){
        int taskCount = 0;
        Ui.printSplitLine();
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(keyword)) {
                taskCount++;
                System.out.println("     " + String.valueOf(taskCount) + "." + taskList.get(i).getResponse());
            }
        }
        Ui.printSplitLine();
        System.out.println();
    }

}
