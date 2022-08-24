import java.util.Scanner;

public class Duke {
    public static void printList(String[] task){
        int taskIndex = 0;
        while(task[taskIndex] != null){
            System.out.println(task[taskIndex]);
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

        //Echo -> Add
        String[] task = new String[100];
        int taskIndex = 0;
        while(!command.equals("bye")){
            if(command.equals("list")){
                printList(task);
            }
            else {
                //Add only if input is not blank
                if(!command.isBlank()) {
                    System.out.println("added: " + command);
                    task[taskIndex] = (taskIndex + 1) + ". " + command;
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
