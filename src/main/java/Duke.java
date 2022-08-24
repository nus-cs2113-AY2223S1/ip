import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String dukeLogo =  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String convStart = dukeLogo + "Hello! I'm Duke\n" + "What can I do for you?";
        String convEnd= "Bye. Hope to see you again soon!";

        ListDuke list = new ListDuke();

        printOutput(convStart);

        String lineInput;
        Scanner in = new Scanner(System.in);
        while (true) {
            lineInput = in.nextLine();
            if (lineInput.equals("bye")) {
                break;
            } else if (lineInput.equals("list")) {
                printOutput(list.getInputLists());
            } else {
                list.setInputLists(lineInput);
                printOutput("added: " + lineInput);
            }
        }

        printOutput(convEnd);

    }

    private static void printOutput(String message) {
        String separator = "____________________________________________________________";
        System.out.println(separator);
        System.out.println(message);
        System.out.println(separator);
        System.out.println("");
    }
}
