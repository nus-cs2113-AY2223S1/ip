import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "___________.__                ___.     \n"
                + "\\_   _____/|  | _____    _____|  |__  \n"
                + " |    __)  |  | \\__  \\  /  ___/  |  \\ \n"
                + " |     \\   |  |__/ __ \\_\\___ \\|   Y  \\\n"
                + " \\___  /   |____(____  /____  >___|  /\n"
                + "     \\/              \\/     \\/     \\/ \n";
        System.out.println("Hello from\n" + logo);
        String lineDivider = "____________________________________________________________\n";
        System.out.println("\t" + lineDivider
                + "\t Hello! I'm Flash\n"
                + "\t What can I do for you?\n"
                + "\t" + lineDivider);

        String userInput;
        Scanner in = new Scanner(System.in);
        do{
            userInput = in.nextLine();
            System.out.print("\t" + lineDivider);
            if(!userInput.equals("bye")) {
                System.out.println("\t " + userInput);
            }
            else{
                System.out.println("\t Bye. Hope to see you again soon!");
            }
            System.out.println("\t" + lineDivider);
        }
        while(!userInput.equals("bye"));

    }
}
