import java.util.Scanner;

public class Duke {

    public static void markAsDone(Task[] task, int markIndex){
        task[markIndex].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [" + task[markIndex].getStatusIcon() + "] " + task[markIndex].getDescription());
    }

    public static void markAsUndone(Task[] task, int unmarkIndex){
        task[unmarkIndex].setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [" + task[unmarkIndex].getStatusIcon() + "] " + task[unmarkIndex].getDescription());
    }

    public static void printList(Task[] task){
        System.out.println("Here are the tasks in your list:");
        int taskIndex = 0;
        while(task[taskIndex] != null){
            System.out.println((taskIndex + 1) + ". [" + task[taskIndex].getStatusIcon() +"] "
                    + task[taskIndex].getDescription());
            taskIndex++;
        }
    }

    public static void main(String[] args) {

        String logo = "    ,---,                        ,-.            \n"
                + "  .'  .' `\\                  ,--/ /|            \n"
                + ",---.'     \\          ,--, ,--. :/ |            \n"
                + "|   |  .`\\  |       ,'_ /| :  : ' /             \n"
                + ":   : |  '  |  .--. |  | : |  '  /      ,---.   \n"
                + "|   ' '  ;  :,'_ /| :  . | '  |  :     /     \\  \n"
                + "'   | ;  .  ||  ' | |  . . |  |   \\   /    /  | \n"
                + "|   | :  |  '|  | ' |  | | '  : |. \\ .    ' / | \n"
                + "'   : | /  ; :  | : ;  ; | |  | ' \\ \\'   ;   /| \n"
                + "|   | '` ,/  '  :  `--'   \\'  : |--' '   |  / | \n"
                + ";   :  .'    :  ,      .-./;  |,'    |   :    | \n"
                + "|   ,.'       `--`----'    '--'       \\   \\  /  \n"
                + "'---'                                  `----'   \n";

        //Greet
        System.out.println("____________________________________________________________");
        System.out.println(" Hello I'm Duke\n" + logo);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        String command;                         //variable to store line (input)
        Scanner in = new Scanner(System.in);    //create object that reads input
        command = in.nextLine();                //read input

        //Add
        Task[] task = new Task[100];
        int taskIndex = 0;
        while(!command.equals("bye")){
            if(command.equals("list")){
                printList(task);
            } else if (command.contains("unmark")) {
                command = command.trim();
                command = command.substring(6);
                command = command.trim();
                int unmarkIndex = Integer.parseInt(command) - 1;
                markAsUndone(task, unmarkIndex);
            } else if (command.contains("mark")) {
                command = command.trim();
                command = command.substring(4);
                command = command.trim();
                int markIndex = Integer.parseInt(command) - 1;
                markAsDone(task, markIndex);
            } else {
                //Add only if input is not blank
                if(!command.isBlank()) {
                    task[taskIndex] = new Task(command);
                    System.out.println("added: " + command);
                    taskIndex++;
                }
            }
            command = in.nextLine();
        }

        //Exit
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
