import java.util.Scanner;
import java.util.Vector;

public class Duke {
    public static void main(String[] args) {
        // Greeting message
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke_HTT. \n What can I do for you?");
        
        // Take user input
        Scanner scan = new Scanner ( System.in ); 
        String inData = scan.nextLine();

        // Processing input - 1st time
        Vector<String> ToDoList = new Vector<>();
        if (inData.equals("bye")){
            System.out.println(" \n Bye. Hope to see you again soon!");
        }
        else if (inData.equals("list")){
            for (String item:ToDoList){
                System.out.println((ToDoList.indexOf(item) + 1) + ". " + item);
                
            }
            
        }
        
        else{
            ToDoList.add(inData);
            System.out.println("added: " + inData);
        }

        
        // Processing input - loop
        Boolean isBye = false;
        while (!isBye) {
            inData = scan.nextLine();
            
            if (inData.equals("bye")){
                isBye = true;
                System.out.println(" \n Bye. Hope to see you again soon!");
            }
            else if (inData.equals("list")){
                for (String item:ToDoList){
                    System.out.println((ToDoList.indexOf(item) + 1) + ". " + item);
                    
                }
            }

            else{
                ToDoList.add(inData);
                System.out.println("added: " + inData);
            }


        }

        
       



        
    
    
    
    
        
    }
}
