import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<String> taskList = new ArrayList<String>(0);
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        newLine();
        welcomeGreeting();
        newLine();
        String input = getInput(in);
        newLine();
        while (!input.equals("bye")){
            if (input.equals("list")){
                printTaskList(taskList);
            } else {
                echo(input);
                taskList.add(input);
            }
            input = getInput(in);
            newLine();
        }

        goodbyeGreeting();
        newLine();
    }

    private static void printTaskList(ArrayList<String> taskList){
        for (int i = 0; i < taskList.size(); i++){
            int listIndex = i+1;
            System.out.println(" "+listIndex+". "+taskList.get(i));
        }
        newLine();
    }

    private static void echo(String input){
        System.out.println(" added: " + input);
        newLine();
    }
    private static String getInput(Scanner in){
        return in.nextLine();
    }


    private static void newLine(){
        for (int i = 0; i < 60; i++) {
            System.out.print('_');
        }
        System.out.print('\n');
    }

    private static void welcomeGreeting(){
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
    }

    private static void goodbyeGreeting(){
        System.out.println(" Bye. Hope to see you again soon!");
    }
}
