import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        Scanner cmd = new Scanner(System.in);

        System.out.println(line + " Hello! I'm Tommy\n" + " What can I do for you?\n" + line);

        String foo = cmd.nextLine();

        switch (foo) {
        case "list":
            System.out.println(line + " list\n" + line);
            foo = cmd.nextLine();
        case "blah":
            System.out.println(line + " blah\n" + line);
            foo = cmd.nextLine();
        case "bye":
            System.out.println(line + " Bye. Hope to see you again soon!\n" + line);
            break;
        default:
            break;
        }
    }
}
