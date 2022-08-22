import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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

    private static boolean isContainString(String source, String subItem) {
        String pattern = "\\b" + subItem + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }

    private static void markOrNot(char a, ArrayList<String> tasks, int number) {
        String str = tasks.get(number - 1);
        StringBuilder mod = new StringBuilder(str);
        mod.setCharAt(1, a); // fill with X
        str = mod.toString();
        tasks.set(number - 1, str); // replace with marked task
        if (a == 'X') {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                    + "The following task been marked as completed:\n"
                    + tasks.get(number - 1) + "\n"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                    + "The following task been marked as not done yet:\n"
                    + tasks.get(number - 1) + "\n"
                    + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }
    }

    public static void modifyList() {
        ArrayList<String> tasks = new ArrayList<String>();
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        String sub1 = "mark";
        String sub2 = "unmark";
        while (true) {
            if (input.equals("bye")) { // ends if input equals to bye
                break;
            }

            else if (input.equals("list")) { // display list of items
                int num = 1;
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                System.out.println("Here are your tasks!:\n");
                for (String task : tasks) {
                    System.out.println(num + "." + task + "\n");
                    num++;
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            }

            else if (isContainString(input, sub2)) { // remove x
                int number = input.charAt(input.length() - 1) - '0'; // get index of task
                if (number > tasks.size()) {
                    System.out.println("Sorry, there is no such task. Please input a correct number");
                } else {
                    markOrNot(' ', tasks, number);
                }
            }

            else if (isContainString(input, sub1)) { // put x
                int number = input.charAt(input.length() - 1) - '0'; // get index of task
                if (number > tasks.size()) {
                    System.out.println("Sorry, there is no such task. Please input a correct number");
                } else {
                    markOrNot('X', tasks, number);
                }
            }

            else { // default adding of tasks; empty checkbox
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                        + "added: " + input + "\n"
                        + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                String box = "[ ] " + input;
                tasks.add(box);
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
        modifyList();
        exit();

    }
}
