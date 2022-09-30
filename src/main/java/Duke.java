import java.util.Scanner;
public class Duke {
    public static void printWelcome() {
        String line = ("    ____________________________________________________________");
        System.out.println(line + "\n" + "    Hello! I'm Duke" + "\n" + "    What can I do for you?" + "\n" + line);
    }
    public static void main(String[] args) {
        printWelcome();
        Scanner input = new Scanner(System.in);
        String command;
        String[] tasks = new String[100];
        int numberOfTasks = 0;

        while (true) {
            command = input.nextLine();
            if(command.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int counter = 0; counter < numberOfTasks; counter++) {
                    System.out.println("    " + (counter + 1) + ". " + tasks[counter]);
                }
                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("    ____________________________________________________________");
                tasks[numberOfTasks++] = command;
                System.out.println("    added: " + command);
                System.out.println("    ____________________________________________________________");
            }
        }
    }
}
