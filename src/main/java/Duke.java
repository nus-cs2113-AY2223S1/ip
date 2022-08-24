import java.util.Scanner;

public class Duke {
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

        //Echo
        while(!command.equals("bye")){
            System.out.println(command);
            command = in.nextLine();
        }

        //Exit
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
