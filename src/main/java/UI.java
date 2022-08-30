import java.util.Scanner;

public class UI {
    public static void welcomeUser() {
        String welcomeText = "-------------------------------------\n"
                + "Hello! I am not Duke.\n"
                + "Here are a list of commands: \n"
                + "\tadd <string> : adds a task to the list\n"
                + "\tlist : shows list of tasks\n"
                + "\tmark <task id> : marks a task \n"
                + "\tunmark <task id> : unmarks a task \n"
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
