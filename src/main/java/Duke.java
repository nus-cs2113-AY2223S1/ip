import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        String[] items = new String[100];
        int matchCount = 0;

        while(true) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("list")) {
                int counter = 1;
                for (String item : items) {
                    if (item != null) {
                        System.out.println(counter + ". " + item);
                        counter++;
                    }
                }
            } else {
                System.out.println("added " + line);
                items[matchCount] = line;
                matchCount += 1;
            }
        }
    }
}
