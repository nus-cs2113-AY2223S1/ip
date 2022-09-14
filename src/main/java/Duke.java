import java.util.Scanner;

public class Duke {

    private static final String COMMAND_BYE = "bye";
    private static final String BYE_MESSAGE = "Bye! Always there waiting for you!";
    private static final String GREET_MESSAGE_1 = "Hello! I'm Rae.";
    private static final String GREET_MESSAGE_2 = "How does everything progress now?";
    private static final int LINE_SEPARATOR_LENGTH = 60;
    private static final String LINE_SEPARATOR = "_";

    public static void separateLine() {
        System.out.println(LINE_SEPARATOR.repeat(LINE_SEPARATOR_LENGTH));
    }

    /**
     * greet prints greet messages
     */
    public static void greet() {
        separateLine();
        System.out.println(GREET_MESSAGE_1);
        System.out.println(GREET_MESSAGE_2);
    }

    /**
     * bye prints farewell messages
     */
    public static void bye() {
        separateLine();
        System.out.println(BYE_MESSAGE);
        separateLine();
    }

    /**
     * echo print input string with line breaker after it
     *
     * @param line the line of string input
     */
    public static void echo(String line) {
        separateLine();
        System.out.println(line);
        separateLine();
    }


    public static void main(String[] args) {
        greet();
        Storage storage = new Storage();
        storage.loadSave();
        separateLine();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equalsIgnoreCase(COMMAND_BYE)) {
            separateLine();
            try {
                storage.execute(line);
            } catch (UnknownCommandException e) {
                System.out.format("Exception: Unknown command%n" +
                        "%s is not a valid command%n", line.split(" ")[0]);
            } catch (NullCommandException e) {
                System.out.format("Exception: Null command%n" +
                        "No command is entered%n");
            }
            separateLine();
            line = in.nextLine();
        }
        bye();
    }
}
