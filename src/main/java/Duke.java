import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke_HTT. \n What can I do for you?");
        
        Scanner scan = new Scanner ( System.in ); // take user input
        String inData = scan.nextLine();

        while (!inData.equals("bye")) {
            System.out.println(inData);
            inData = scan.nextLine();
        }

        
       



        
    
    
    
    
        System.out.println(" \n Bye. Hope to see you again soon!");
    }
}
