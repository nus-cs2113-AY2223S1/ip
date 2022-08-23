import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        Scanner cmd = new Scanner(System.in);
        String[] words = new String[100];
        int cnt = 0;
        Boolean last = false;

        System.out.println(line + " Hello! I'm Tommy\n" + " What can I do for you?\n" + line);

        do {
            String foo = cmd.nextLine();
            switch (foo) {
            case "list":
                System.out.println(line);
                for (int i = 0; i < cnt; i++) {
                    System.out.println(i + 1 + ". " + words[i]);
                }
                System.out.println(line);
                break;
            case "blah":
                System.out.println(line + " blah\n" + line);
                break;
            case "bye":
                last = true;
                break;
            default:
                words[cnt] = foo;
                cnt++;
                System.out.println(line + "added: " + foo + "\n" + line);
                break;
            }
        } while (last==false);
        System.out.println(line + " Bye. Hope to see you again soon!\n" + line);
    }
}
