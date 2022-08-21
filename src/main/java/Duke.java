import java.util.Scanner;

public class Duke {

    public static void listing (String[] listOfThings, int count) {
        for (int i = 0; i < count; i += 1) {
            int index = i + 1;
            System.out.println("\t" + index + ". " + listOfThings[i]);
        }
    }

    public static int response (String line, String[] listOfThings, int count) {
        System.out.println("  ____________________________________________________________");
        if (line.equals("list")) {
            listing(listOfThings, count);
        } else {
            listOfThings[count] = line;
            System.out.println("\tAdded: " + line);
            count += 1;
        }
        System.out.println("  ____________________________________________________________");
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        String[] listOfThings = new String[100];
        int count = 0;
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
                count = response(line, listOfThings, count);
            }
        } while (!line.equals("bye"));
    }
}

