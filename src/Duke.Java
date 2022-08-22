import java.util.*;

public class Duke {
    public static void greet() {
        String intro = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "Hello! I'm Jackson, your personal chatbot! :)\n"
                + "What service are you looking for?\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        System.out.println(intro);
    }

    public static void exit() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "Leaving already? :( Come back soon!\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public static void create_list() {
        ArrayList<String> tasks = new ArrayList<String>();
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        while (true) {
            if (input.equals("bye")) { // ends if input equals to bye
                break;
            }
            if (input.equals("list")) { // display list of items
                int num = 1;
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                for (String task : tasks) {
                    System.out.println(num + ". " + task + "\n");
                    num++;
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            } else {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                        + "added: " + input + "\n"
                        + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                tasks.add(input);
            }
            input = reader.nextLine(); // get next input indefinitely
        }
    }

    public static void main(String[] args) {
        String jackson = "       _            _                    \n"
                + "      | |          | |                   \n"
                + "      | | __ _  ___| | _____  ___  _ __  \n"
                + "  _   | |/ _` |/ __| |/ / __|/ _ \\| '_ \\ \n"
                + " | |__| | (_| | (__|   <\\__ \\ (_) | | | |\n"
                + "  \\____/ \\__,_|\\___|_|\\_\\___/\\___/|_| |_|\n";
        System.out.println("Hello from\n" + jackson);
        greet();
        create_list();
        exit();

    }
}
