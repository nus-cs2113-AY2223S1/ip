import java.util.Scanner;

public class Duke {

    private static final String HORIZONTAL_LINE = "______________________________";

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

    private static void echoInput() {
        final String EXIT_PHRASE = "bye";

        Scanner in = new Scanner(System.in);
        String input;

        input = in.nextLine();

        while (!input.equals(EXIT_PHRASE)) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println(input);
            System.out.println(HORIZONTAL_LINE);

            input = in.nextLine();
        }
    }

    public static void main(String[] args) {
        printIntroduction();
        echoInput();
        printExit();
    }
}
