import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        while (!command.matches("bye")) {
            echo(command);
            command = input.nextLine();
        }
        exit(); 
    }

    public static void greet() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------------------------");
        
    }

    public static void echo(String command) {
        System.out.println("------------------------------------------------------------");
        System.out.println(command);
        System.out.println("------------------------------------------------------------");
    }

    public static void exit() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------------------------");
    }
}
