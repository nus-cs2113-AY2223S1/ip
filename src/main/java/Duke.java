import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        TaskList task_list = new TaskList();

        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greet
        System.out.println("---------------------------------------------------");
        System.out.println("Duke: Hello! What can I do for you today?");

        
        while (isExit == false){
            //Getting Input
            System.out.println("---------------------------------------------------");
            System.out.print("You: ");
            String raw_input = scanner.nextLine();
            String[] input_list = raw_input.split(" ");
            String cmd = input_list[0];

            //Responding
            switch (cmd) {
                case ("list"):
                    System.out.println("---------------------------------------------------");
                    System.out.println("Tasks: ");
                    task_list.printList();
                    break;
                case ("bye"):
                    isExit = true;
                    System.out.println("---------------------------------------------------");
                    System.out.println("Duke: Goodbye!");
                    System.out.println("---------------------------------------------------");
                    break;
                case ("mark"):
                    System.out.println("---------------------------------------------------");
                    System.out.println("Marked as done:");

                    String task_description = String.join(" ", Arrays.copyOfRange(input_list, 1, input_list.length));
                    task_list.searchTask(task_description).markAsDone();

                    System.out.print("[X] ");
                    System.out.println(task_description);
                    break;
                default: //Add to list
                    System.out.println("---------------------------------------------------");
                    System.out.println("Added: " + raw_input);
                    task_list.addTask(raw_input);
                    break;
            }
        }
    }
}