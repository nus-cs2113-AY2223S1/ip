package UiPackage;

import TaskPackage.TaskList;

import java.util.Scanner;

public class Ui {
    private final Scanner input;
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
    public static void greet(){
        System.out.println(GREETING);
    }
    public static void showLine(){
        System.out.println("____________________________________________________________");
    }
    public String readCommand(){
        return input.nextLine();
    }
    public static void showError(String errorMessage){
        System.out.println(errorMessage);
    }

    public void printTasks(TaskList tasks){
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.print(i + 1);
            System.out.print(".");
            System.out.println(tasks.getTasks().get(i).toString());
        }
        System.out.println("You have "+tasks.getTasks().size()+" task(s) in the list");
    }
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

    public static void printNumberOfTasksMessage(int numberOfTasks){
        showLine();
        System.out.println("You have " + numberOfTasks +" task(s) in the list.");
        showLine();
    }
    public static void printExitMessage(){
        showLine();
        System.out.println("Bye!");
        showLine();
    }


}
