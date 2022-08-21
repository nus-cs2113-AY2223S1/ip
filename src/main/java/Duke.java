import java.util.Scanner;

public class Duke {

    public static void response (String line) {
        System.out.println("  ____________________________________________________________");
        System.out.println("\t" + line);
        System.out.println("  ____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        System.out.println("  ____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("  ____________________________________________________________");

        do {
            line = in.nextLine();
            if (line.equals("bye")){
                System.out.println("  ____________________________________________________________");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("  ____________________________________________________________");
            } else {
                response(line);
            }
        } while (!line.equals("bye"));
    }
}

