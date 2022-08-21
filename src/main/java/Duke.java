import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        String[] taskList = new String[100];
        int listSize = 0;
        // Greet user
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");

        // Wait for input
        while (true) {
            line = in.nextLine();

            if (line.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                break;
            }
            else if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                    if (listSize > 0) {
                        for (int i = 0; i < listSize; i++) {
                            System.out.printf("%d. %s\n", i + 1, taskList[i]);
                        }
                    }
                    else {
                        System.out.println("No items in list! Type something to add to list.");
                    }
                System.out.println("____________________________________________________________");
            }
            else {
                taskList[listSize] = line;
                listSize++;
                System.out.println("____________________________________________________________\n" +
                        "added: " + line +
                        "\n____________________________________________________________\n");
            }
        }
    }
}
