package UiPackage;

import TaskPackage.*;

import java.util.Scanner;
/*
Deals with interaction with user, and displaying relevant information to user.
 */
public class Ui {
    private final Scanner input;
    /*
    Initialisation of Ui
     */
    public Ui(){
        input = new Scanner(System.in);
    }
    private static String GREETING = "____________________________________________________________\n" +
                " __      __                       \n" +
                "/  \\    /  \\ ____   ____    ____  \n" +
                "\\   \\/\\/   // __ \\ /    \\  / ___\\ \n" +
                " \\        /\\  ___/|   |  \\/ /_/  >\n" +
                "  \\__/\\  /  \\___  >___|  /\\___  / \n" +
                "       \\/       \\/     \\//_____/  " +
                "\n Hello! I'm Weng!\n" +
                " What can I do for ya?\n" +
                "____________________________________________________________\n";
    /*
    Prints greeting message
     */
    public static void greet(){
        System.out.println(GREETING);
    }
    /*
    Prints a line
     */
    public static void showLine(){
        System.out.println("____________________________________________________________");
    }
    /*
    Prints a single Task
     */
    public static void printTask(Task task){
        System.out.println(task.toString());
    }
    /*
    reads input from user and returns it
     */
    public String readCommand(){
        return input.nextLine();
    }
    /*
    takes in errorMessage and prints it
     */
    public static void showError(String errorMessage){
        System.out.println(errorMessage);
    }

    /*
    Print all tasks in a TaskList
     */
    public void printTasks(TaskList tasks){
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.print(i + 1);
            System.out.print(".");
            System.out.println(tasks.getTasks().get(i).toString());
        }
        System.out.println("You have "+tasks.getTasks().size()+" task(s) in the list");
    }
    /*
    Print all tasks which match a keyword
     */
    public void printMatched(TaskList tasks, String keyword){
        int numberOfMatches = 0;
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            if(tasks.getTasks().get(i).getDescription().contains(keyword)){
                numberOfMatches++;
                System.out.print(i + 1);
                System.out.print(".");
                System.out.println(tasks.getTasks().get(i).toString());
            }
        }
        System.out.println("There are " + numberOfMatches + " match(es) in the list");
    }

    /*
    Print message indicating number of tasks
     */
    public static void printNumberOfTasksMessage(int numberOfTasks){
        System.out.println("You have " + numberOfTasks +" task(s) in the list.");
    }

    /*
    Print exit message
     */
    public static void printExitMessage(){
        showLine();
        System.out.println("Bye!");
        showLine();
    }


}
