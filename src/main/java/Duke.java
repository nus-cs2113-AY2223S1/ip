import java.util.Scanner;
public class Duke {

    public static void greetUser(){
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void echo(String userInput){
        System.out.println(userInput);
    }

    public static boolean toExit(String userInput) {
        if (userInput.equals("bye")){
            System.out.println("Bye!!!! See you again :D");
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        greetUser();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while(!toExit(userInput)){
            echo(userInput);
            userInput = sc.nextLine();
        }
    }
}
