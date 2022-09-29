import task.Task;

import java.util.ArrayList;

public class Ui {

    public static void byeMsg() {
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }

    public static void welcomeMsg() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
    }

    public static void readMsg(String path) {
        System.out.println("____________________________________________________________\n" +
                " Reading from " + path + "\n"+
                " start loading\n" +
                "____________________________________________________________");
    }

    public static void finishReadMsg() {
        System.out.println("____________________________________________________________\n" +
                " done loading.\n" +
                "____________________________________________________________");
    }

    public static void emptyErrorMsg(String taskName){
        System.out.println( "☹ OOPS!!! The description of a " + taskName + " cannot be empty.");
    }

    public static void numOfTaskMsg(int length){
        System.out.println("Now you have " + length + " tasks in the list.");
    }

    public static void failToSaveMsg(){
        System.out.println("failed to save");
    }

    public static void fileNotFoundMsg(){
        System.out.println("File or folder not found");
    }

    public static void unKnownMsg(){
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void indexOutOfBoundMsg(){
        System.out.println("warning:index out of bound");
    }

    public static void indexEmptyMsg(){
        System.out.println("☹ OOPS!!! The index of the task cannot be empty.");
    }

    public static void printList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void removeTaskMsg(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    public static void invalidIndexMsg(){
        System.out.println("no index found");
    }

    public void markTaskMsg(boolean status, Task task){
        if(status){
            System.out.println("Nice! I've marked this task as done:");
        }else {
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println(task);
    }

    public void separatorMsg(){
        System.out.println("____________________________________________________________");
    }

}
