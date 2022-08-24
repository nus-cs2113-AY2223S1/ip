import java.util.Scanner;
public class Duke {
    public static void printLine () {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String[] list = new String[100];
        int index = 0;
        Scanner in = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
        while(in.hasNextLine()) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                printLine();
                break;
            }
            else if (input.equals("list")) {
                printLine();
                for (int i = 0; i < index; i++) {
                    System.out.println(i + ". " + list[i]);
                }
                printLine();
            }
            else {
                list[index] = input;
                index++;
                printLine();
                System.out.println("added: " + input);
                printLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
