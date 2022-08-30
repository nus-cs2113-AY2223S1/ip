import java.util.Scanner;
import java.util.ArrayList;
//import java.util.regex.*;

public class Juke {

    // HELPER FUNCTIONS
    public static void listTasks(ArrayList<Task> tasks) {
        String line = "------------------------\n";
        System.out.println(line);
        for(int i = 0; i < tasks.size(); i++){
            int j = i + 1;
            System.out.println(j + ". " + tasks.get(i).getDescription() + tasks.get(i).getStatusIcon() + "\n");
        }
        System.out.println(line);
    }

    // MAIN FUNCTION
    public static void main(String[] args) {
        
        // INITIAL PRINTING
        String line = "------------------------\n";
        System.out.println(line + "Hello! I'm Juke!\nWhat can I do for you?\n" + line);
        
        // VARS
        String userInput = "";
        Scanner scanObj = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        
        // JUKE LOGIC
        while(!userInput.equals("bye")){
            userInput = scanObj.nextLine();  // Read user input
        
            // SWITCH-CASE
            switch(userInput){
            case "list":
                listTasks(tasks);
                break;
            case "bye":
                break;
            case "":
                // do nothing if empty line entered
                break; 
            default:
                // mark or unmark
                if(userInput.matches("mark [0-9]+")){
                    String[] words = userInput.split(" ");
                    int val = Integer.parseInt(words[1]);
                    
                    if(val > tasks.size()){
                        break;
                    } else {
                        tasks.get(val-1).markDone();
                        listTasks(tasks);
                    }

                } else if(userInput.matches("unmark [0-9]+")) {
                    String[] words = userInput.split(" ");
                    int val = Integer.parseInt(words[1]);
                    
                    if(val > tasks.size()){
                        break;
                    } else {
                        tasks.get(val-1).unmark();
                        listTasks(tasks);
                    }

                } else {
                    // keep adding to list of tasks
                    Task latestTask = new Task(userInput);
                    tasks.add(latestTask);
                    System.out.println("Input recorded: " + userInput + "\n");
                }
                break;
            }
        }

        scanObj.close();
        System.out.println("\nSee you again!");
    }
}
