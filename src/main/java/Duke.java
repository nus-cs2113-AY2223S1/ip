import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "     ____.  _____ ______________   ____.___  _________\n"
                + "    |    | /  _  \\\\______   \\   \\ /   /|   |/   _____/\n"
                + "    |    |/  /_\\  \\|       _/\\   Y   / |   |\\_____  \\ \n"
                + "/\\__|    /    |    \\    |   \\ \\     /  |   |/        \\\n"
                + "\\________\\____|__  /____|_  /  \\___/   |___/_______  /\n"
                + "                 \\/       \\/                       \\/ \n";

        String greeting = "------------------------------------------------\n"
                + "Hello! I'm\n"
                + logo
                + "What can I do for you?\n"
                + "------------------------------------------------\n";
        String parting = "------------------------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "------------------------------------------------\n";

        System.out.println(greeting);
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        while(!command.equals("bye")){
            System.out.println("------------------------------------------------\n");
            System.out.println(command);
            System.out.println("------------------------------------------------\n");
            command = in.nextLine();
        }
        System.out.println(parting);
    }
}
