import java.util.Scanner;

public class Duke {
    public static String[] list = new String[100];
    public static int position = 0;

    public static void printDivider() {
        System.out.println("    ----------------------------------------------------");
    }

    public static void printGreeting() {
        printDivider();
        System.out.println("    Hello! I'm Albot, your new best friend! ᕙ(`▿´)ᕗ");
        System.out.println("    What can I do for you today, friend? ʕ•́ᴥ•̀ʔっ♡");
        System.out.println();
        printDivider();
    }

    public static void printExitMessage() {
        printDivider();
        System.out.println("    Aww, you're leaving already? I'm gonna miss you (╥﹏╥)");
        System.out.println("    But anyways bye! Hope to see you again soon, friend! (ɔ◔‿◔)ɔ ♥");
        System.out.println();
        printDivider();
    }

    public static void addToList(String line) {
        list[position] = line;
        position++;

        printDivider();
        System.out.println("    added: " + line);
        System.out.println();
        printDivider();
    }

    public static void printList() {
        printDivider();
        for (int i = 0; i < position; i++) {
            System.out.println("    " + String.valueOf(i + 1) + ". " + list[i]);
        }
        System.out.println();
        printDivider();
    }

    public static void main(String[] args) {
        printGreeting();

        String userInput = "";

        while (!userInput.equals("bye")) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.equals("list")) {
                printList();
            } else {
                addToList(userInput);
            }
        }

        printExitMessage();
    }
}
