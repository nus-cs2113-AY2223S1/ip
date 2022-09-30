import task.Task;

import java.util.ArrayList;
/**
 * in charge of printing messages
 */
public class Ui {

    /**
     * print the bye message
     */
    public void byeMsg() {
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }

    /**
     * print the welcome message
     */
    public void welcomeMsg() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
    }

    /**
     * print the path where the program is reading message from
     *
     * @param path the path of the file
     */
    public void readMsg(String path) {
        System.out.println("____________________________________________________________\n" +
                " Reading from " + path + "\n"+
                " start loading\n" +
                "____________________________________________________________");
    }

    /**
     * print the message that tells the user that the file is done loading
     */
    public void finishReadMsg() {
        System.out.println("____________________________________________________________\n" +
                " done loading.\n" +
                "____________________________________________________________");
    }

    /**
     * tells the user that the input format for adding the task is wrong
     *
     * @param taskName the name of the task
     */
    public void emptyErrorMsg(String taskName){
        System.out.println( "☹ OOPS!!! The description of a " + taskName + " cannot be empty.");
    }

    /**
     * prints how many tasks currently exist
     *
     * @param length number of tasks in the list
     */
    public void numOfTaskMsg(int length){
        System.out.println("Now you have " + length + " tasks in the list.");
    }

    /**
     * print the message that tells saving failure
     */
    public void failToSaveMsg(){
        System.out.println("failed to save");
    }

    /**
     * print the message that said file or folder cannot be found
     */
    public void fileNotFoundMsg(){
        System.out.println("File or folder not found. \n"
                + "Please create a file named duke.txt under folder data and place the data folder under the same route as Duke");
    }

    /**
     * printed when the program cannot recognize the user's input
     */
    public void unKnownMsg(){
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * printed when the input is empty
     */
    public void emptyMsg(){
        System.out.println("☹ OOPS!!! your input ois empty");
    }
    
    /**
     * printed when the index entered is out of bound
     */
    public void indexOutOfBoundMsg(){
        System.out.println("warning:index out of bound");
    }
    
    /**
     * printed when there is no index inputted as required
     */
    public void indexEmptyMsg(){
        System.out.println("☹ OOPS!!! The index of the task cannot be empty.");
    }


    /**
     * prints all the tasks in the list
     *
     * @param tasks the list that contains all the existing tasks
     */
    public void printList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + "." + tasks.get(i));
        }
    }


    /**
     * prints the task that is going to be removed
     *
     * @param task the task to be removed
     */
    public void printTask(Task task){
        System.out.println("     " + task);
    }


    public void removeTaskMsg(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    /**
     * prints the mark/unmark message
     *
     * @param status true means mark the task, false means unmark the task
     * @param task the task that is manipulated
     */
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

    public void unknownError(){
        System.out.println("unknown error occurred");
    }

    public void formatIncorrect(){
        System.out.println("format incorrect");
    }

    public void findMsg(){
        System.out.println("     Here are the matching tasks in your list:");
    }



}
