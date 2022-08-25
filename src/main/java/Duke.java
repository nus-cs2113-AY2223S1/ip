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
        Scanner response = new Scanner(System.in);
        String[] lst = new String[100]; 
        int lst_counter = 0;

        while (exit == false){
            System.out.println("---------------------------------------------------");
            System.out.print("You: ");
            String response_str = response.nextLine();
            switch (response_str) {
                case ("list"):
                    System.out.println("---------------------------------------------------");
                    System.out.println("Listing: " + response_str);
                    for (int i=0; i<lst_counter; i++){
                        System.out.println((i + 1) + ": " + lst[i]);
                    }
                    break;
                case ("bye"):
                    exit = true;
                    System.out.println("---------------------------------------------------");
                    System.out.println("Duke: Goodbye!");
                    System.out.println("---------------------------------------------------");
                    break;
                default: //Add to list
                    System.out.println("---------------------------------------------------");
                    System.out.println("Added: " + response_str);
                    lst[lst_counter] = response_str;
                    lst_counter++;
                    break;
            }
        }
    }
}
