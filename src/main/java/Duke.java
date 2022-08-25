import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greet
        System.out.println("---------------------------------------------------");
        System.out.println("Duke: Hello! What can I do for you today?");

        boolean exit = false;
        //Echo
        Scanner response = new Scanner(System.in);
        while (exit == false){
            System.out.println("---------------------------------------------------");
            System.out.print("You: ");
            String response_str = response.nextLine();
            if (!response_str.equals("bye")){
                System.out.println("---------------------------------------------------");
                System.out.println("Duke: " + response_str);
            } else {
                exit = true;
                System.out.println("---------------------------------------------------");
                System.out.println("Duke: Goodbye!");
                System.out.println("---------------------------------------------------");
            }
        }
    }
}
