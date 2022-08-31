import java.util.Scanner;
public class Duke {
    protected static final String BYE = "bye";
    protected static final String LIST = "list";
    protected static final String MARK = "mark";
    protected static final String UNMARK = "unmark";
    protected static final String TODO = "todo";
    protected static final String DEADLINE = "deadline";
    protected static final String EVENT = "event";
    protected static final int TASK = 0;
    protected static final int NUMBER = 1;
    protected static final int DETAILS = 1;


    public static String lineSeparator() {
        return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    }

    public static String botLogo() {
        return "       _            _                    \n"
                + "      | |          | |                   \n"
                + "      | | __ _  ___| | _____  ___  _ __  \n"
                + "  _   | |/ _` |/ __| |/ / __|/ _ \\| '_ \\ \n"
                + " | |__| | (_| | (__|   <\\__ \\ (_) | | | |\n"
                + "  \\____/ \\__,_|\\___|_|\\_\\___/\\___/|_| |_|\n";
    }

    public static void greet() {
        System.out.println(
                "Hello from\n" +
                        botLogo() +
                        lineSeparator() +
                        "Hello! I'm Jackson, your personal chat-bot! :)\n" +
                        "What service are you looking for?\n" +
                        lineSeparator());
    }

    public static void exit() {
        System.out.println(
                lineSeparator() +
                        "Leaving already? :( Come back soon!\n" +
                        lineSeparator());
    }

    public static void taskList() {
        ModifyList modifyList = new ModifyList();
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();

        while (!input.equals(BYE)) {
            String[] get = input.split(" ", 2);
//            System.out.println(get[NUMBER]);
            switch (get[TASK]) {
                case BYE:
                    break;
                case LIST:
                    modifyList.list();
                    break;
                case UNMARK:
                    int unmarkIndex = Integer.parseInt(get[NUMBER]);
                    modifyList.unmark(unmarkIndex);
                    break;
                case MARK:
                    int markIndex = Integer.parseInt(get[NUMBER]);
                   modifyList.mark(markIndex);
                    break;
                case TODO:
                    modifyList.todo(get[DETAILS]);
                    break;
                case DEADLINE:
                    modifyList.deadline(get[DETAILS]);
                    break;
                case EVENT:
                    modifyList.event(get[DETAILS]);
                    break;
            }
            input = reader.nextLine(); // get next input indefinitely
        }
    }

    public static void main(String[] args) {
        greet();
        taskList();
        exit();

    }
}
