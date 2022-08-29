import java.util.Scanner;



public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        showWelcomeMsg();
        showEchoMsg();
    }

    private static void showSeparator() {
        System.out.println("===================================================");
    }

    private static void showWelcomeMsg() {
        showSeparator();
        System.out.println("Hello! I'm Duke ^_^");
        System.out.println("What can I do for you?");
        showSeparator();
    }

    private static void showGoodbyeMsg() {
        System.out.println("Bye. Hope to see you again soon! qwq");
        showSeparator();
    }

    private static void showEchoMsg() {
        Scanner sc = new Scanner(System.in);
        String input = new String();
        input = sc.next();
        String endCommand = "bye";
        while (!input.equals(endCommand)) {
            System.out.println(input + "~~~");
            showSeparator();
            input = sc.next();
        }
        showGoodbyeMsg();
    }
 }
