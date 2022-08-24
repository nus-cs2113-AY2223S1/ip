import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

//        String[] toDoList = new String[100];

        List<String> toDoList = new ArrayList<String>();
        do {
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }

            if (line.equals("list")) {
                System.out.println("\t_____________________");
                for (int i = 1; i <= toDoList.size(); i += 1) {
                    System.out.println("\t" + i + ": " + toDoList.get(i - 1));
                }
                System.out.println("\t_____________________\n");
            } else {
                toDoList.add(line);
                System.out.println("\t_____________________");
                System.out.println("\t" + "added: " + line);
                System.out.println("\t_____________________\n");
            }

        } while (!line.equals("bye"));
    }
}
