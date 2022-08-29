import java.util.Scanner;

public class Duke {
    public static Boolean isRunning = true;

    public static void Greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");
    }

    public static void Echo() {

        while (isRunning) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isRunning = false;
            } else {
                System.out.println(line);
            }
        }
    }

    public static void AddandList() {
        int counter = 0;
        String[] tasks = new String[100];

        while (isRunning) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            switch (line) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                isRunning = false;
                break;
            case "list":
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                break;
            default:
                System.out.println("added: " + line);
                tasks[counter] = line;
                counter++;
                break;
            }

        }
    }

    public static void main(String[] args) {
        Greetings();
        AddandList();

    }
}
