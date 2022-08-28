import java.util.Scanner;

public class Duke {

    public static void markAsDone(Task[] tasks, int markIndex) {
        tasks[markIndex].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  [" + tasks[markIndex].getStatusIcon() + "] ");
        System.out.println(tasks[markIndex].getDescription());
    }

    public static void markAsUndone(Task[] tasks, int unmarkIndex) {
        tasks[unmarkIndex].setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.print("  [" + tasks[unmarkIndex].getStatusIcon() + "] ");
        System.out.println(tasks[unmarkIndex].getDescription());
    }

    public static void printList(Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        int taskIndex = 0;
        while (tasks[taskIndex] != null) {
            System.out.print((taskIndex + 1) + ". [" + tasks[taskIndex].getStatusIcon() +"] ");
            System.out.println(tasks[taskIndex].getDescription());
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
        Task[] tasks = new Task[100];
        int taskIndex = 0;
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                printList(tasks);
            } else if (command.contains("unmark")) {
                command = command.trim();
                command = command.substring(6);
                command = command.trim();
                int unmarkIndex = Integer.parseInt(command) - 1;
                markAsUndone(tasks, unmarkIndex);
            } else if (command.contains("mark")) {
                command = command.trim();
                command = command.substring(4);
                command = command.trim();
                int markIndex = Integer.parseInt(command) - 1;
                markAsDone(tasks, markIndex);
            } else {
                //Add only if input is not blank
                if (!command.isBlank()) {
                    tasks[taskIndex] = new Task(command);
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
