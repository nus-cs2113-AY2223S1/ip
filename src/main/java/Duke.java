import java.util.Scanner;
public class Duke {
    public static void printLine () {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
        while(in.hasNextLine()) {
            input = in.nextLine();
            boolean isSame = input.equals("bye");
            if (isSame) {
                printLine();
                break;
            }
            printLine();
            System.out.println(input);
            printLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
