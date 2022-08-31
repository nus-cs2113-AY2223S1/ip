import java.util.Scanner;

public class UI {
    public static void welcomeUser() {
        String welcomeText = "-------------------------------------\n"
                + "Hello! I am not Duke.\n"
                + "Here are a list of commands: \n"
                + "\ttodo <string> : adds a Todo to the list\n"
                + "\tdeadline <string> /by <string> : adds a deadline and its deadline...\n"
                + "\tevent <string> /at <string> : adds an event and its time...\n"
                + "\tmark <task id> : marks a task \n"
                + "\tunmark <task id> : unmarks a task \n"
                + "\tlist : lists out all your tasks \n"
                + "\tbye : exits the program \n"
                + "I WILL CRASH IF YOU DO NOT COMPLY!!!\n"
                + "-------------------------------------";
        System.out.println(welcomeText);
    }

    public static String getInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input.trim();
    }
}
