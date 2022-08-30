import java.util.Locale;
import java.util.Scanner;

public class Duke {

    private static final String COMMAND_BYE = "bye";
    private static final String BYE_MESSAGE = "Bye! Always there waiting for you!";
    private static final String GREET_MESSAGE_1 = "Hello! I'm Rae.";
    private static final String GREET_MESSAGE_2 = "How does everything progress now?";
    private static final int LINE_SEPARATOR_LENGTH = 60;
    private static final String LINE_SEPARATOR = "_";
    private static final String NULL_STRING = "";

    public static void separateLine() {
        String lineSeparator = NULL_STRING;
        for (int i = 0; i < LINE_SEPARATOR_LENGTH; i++) {
            lineSeparator += LINE_SEPARATOR;
        }
        System.out.println(lineSeparator);
    }

    public static void greet() {
        separateLine();
        System.out.println(GREET_MESSAGE_1);
        System.out.println(GREET_MESSAGE_2);
    }

    public static void bye() {
        separateLine();
        System.out.println(BYE_MESSAGE);
        separateLine();
    }


    public static void echo(String line) {
        separateLine();
        System.out.println(line);
        separateLine();
    }


    public static void main(String[] args) {
        greet();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Storage storage = new Storage();
        while (!line.toLowerCase().equals(COMMAND_BYE)) {
            separateLine();
            storage.execute(line);
            separateLine();
            line = in.nextLine();
        }
        bye();
    }
}
