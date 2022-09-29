import java.util.Scanner;

/**
 * Storage class serve as a storage of all tasks and execute command
 * on the list of tasks
 */

public class UI implements FormatChecker, FileIO {

    private static final String BYE_MESSAGE = "Bye! Always there waiting for you!";
    private static final String GREET_MESSAGE_1 = "Hello! I'm Rae.";
    private static final String GREET_MESSAGE_2 = "How does everything progress now?";
    private static final int LINE_SEPARATOR_LENGTH = 60;
    private static final String LINE_SEPARATOR = "_";
    private static final Scanner in = new Scanner(System.in);

    /**
     * greet prints greet messages
     */
    public void greet() {
        separateLine();
        System.out.println(GREET_MESSAGE_1);
        System.out.println(GREET_MESSAGE_2);
        separateLine();
    }

    /**
     *  print a line separator
     */
    public void separateLine() {
        System.out.println(LINE_SEPARATOR.repeat(LINE_SEPARATOR_LENGTH));
    }

    /**
     * bye prints farewell messages
     */
    public void bye() {
        System.out.println(BYE_MESSAGE);
    }

    /**
     * read the next line of command
     *
     * @return the next command in string
     */
    public String readCommand() {
        return in.nextLine();
    }

}
