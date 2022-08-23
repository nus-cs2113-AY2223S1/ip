import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        int count = 0;
        while (count < 5 && !line.equals("bye")) {
            System.out.println(line + "\n");
            line = in.nextLine();
            count++;
        }


        System.out.println("Bye good friend! Hope to see you again soon!\n" + logo);
    }
}
