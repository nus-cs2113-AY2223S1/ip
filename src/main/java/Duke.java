import java.util.Scanner;

public class Duke {

    private static final String HORIZONTAL_LINE = "______________________________";
    private static String[] list;
    private static int numTasks;

    private static void printIntroduction() {
        final String INTRODUCTION =
                "Hihi, my name is Jay!" + System.lineSeparator() + "What can I do for you today?";

        System.out.println(HORIZONTAL_LINE);
        System.out.println(INTRODUCTION);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    private static void printExit() {
        final String EXIT = "Goodbye! Hope to see you again!";

        System.out.println(HORIZONTAL_LINE);
        System.out.println(EXIT);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void handleInput() {
        Scanner in = new Scanner(System.in);
        String input;

        final String EXIT_PHRASE = "bye";
        final String LIST_PHRASE = "list";

        while (true) {
            input = in.nextLine();

            if (input.equals(EXIT_PHRASE)) {
                break;
            } else if (input.equals(LIST_PHRASE)) {
                printList();
            } else {
                addToList(input);
            }
        }
    }

    private static void addToList(String text) {
        final String ADD_PHRASE = "added: ";

        list[numTasks] = text;
        numTasks += 1;

        System.out.println(HORIZONTAL_LINE);
        System.out.println(ADD_PHRASE + text);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printList() {
        System.out.println(HORIZONTAL_LINE);

        for (int i = 0; i < numTasks; i += 1) {
            System.out.println(Integer.toString(i + 1) + ". " + list[i]);
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        final int MAX_NUM_OF_TASKS = 100;
        list = new String[MAX_NUM_OF_TASKS];
        numTasks = 0;

        printIntroduction();
        handleInput();
        printExit();
    }
}
