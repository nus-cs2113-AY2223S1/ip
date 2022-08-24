import java.util.Scanner;

public class Duke {

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String linebreak = "____________________________________________________________";

    static void startSession() {
        System.out.println("Hello from\n" + logo);
        System.out.println(linebreak);
        System.out.println("Hello! I'm Duke Nukem");
        System.out.println("What can I do for you today? Let's rock!");
        System.out.println(linebreak);
    }

    static void endSession() {
        System.out.println(linebreak);
        System.out.println("Bye. Hope to see you again soon! Groovy!");
        System.out.println(linebreak);
    }

    static void echo(String line) {
        System.out.println(linebreak);
        System.out.println(line);
        System.out.println(linebreak);
    }

    public static void main(String[] args) {
        startSession();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            echo(line);
            line = in.nextLine();
        }

        endSession();
    }
}
