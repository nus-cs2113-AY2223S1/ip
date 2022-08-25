import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        newLine();
        welcomeGreeting();
        newLine();
        String input = getInput(in);
        newLine();
        while (!input.equals("bye")){
            echo(input);
            input = getInput(in);
            newLine();
        }

        goodbyeGreeting();
        newLine();
    }

    private static void echo(String input){
        System.out.println(" " + input);
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
