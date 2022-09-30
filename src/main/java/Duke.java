import java.util.Scanner;
public class Duke {
    public static void printWelcome() {
        String line = ("____________________________________________________________");
        System.out.println(line + "\n" + "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n" + line);
    }
    public static void main(String[] args) {
        printWelcome();
        Scanner input = new Scanner(System.in);
        String command;

        while (true) {
            command = input.nextLine();
            if(command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("    " + command);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
