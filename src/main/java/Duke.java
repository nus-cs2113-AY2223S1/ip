import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        String GREETING = "____________________________________________________________\n" +
                " Hello! I'm Weng!\n" +
                " What can I do for ya?\n" +
                "____________________________________________________________\n";
        System.out.println("\n" + GREETING);
        String EXIT_MESSAGE = "____________________________________________________________\n" +
                " Bye. Hope to cya again soon!\n" +
                "____________________________________________________________";
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")){
            String output = "____________________________________________________________\n " +
                    input +
                    "\n____________________________________________________________\n";
            System.out.println(output);
            input = sc.nextLine();
        }

        System.out.println(EXIT_MESSAGE);
    }
}
