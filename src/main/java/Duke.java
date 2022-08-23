import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<String> list = new ArrayList<String>();

    public static void handleInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        if (line.equals("bye")) {
            return;
        } else {
            switch (line) {
            case "list":
                System.out.println("        ____________________________________________");
                for (int i = 1; i <= list.size(); i += 1) {
                    System.out.println("        " + i + ". " + list.get(i-1));
                }
                System.out.println("        ____________________________________________");
                break;
            default:
                list.add(line);
                System.out.println("        ____________________________________________");
                System.out.println("        " + line);
                System.out.println("        ____________________________________________");
                break;
            }

            handleInput();
        }

        return;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________");
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________");
        handleInput();

        System.out.println("____________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________");
    }
}
