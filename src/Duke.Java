import java.util.Scanner;
public class Duke extends Constants{
    public static String line() {
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
            line() +
            "Hello! I'm Jackson, your personal chat-bot! :)\n" +
            "What service are you looking for?\n" +
            line()
        );
    }

    public static void exit() {
        System.out.println(
            line() +
            "Leaving already? :( Come back soon!\n" +
            line()
        );
    }

    public static void taskList() throws Error{ // check if get is 1 or 2
        ModifyList modifyList = new ModifyList();
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine(); //.toLowerCase()?

        while (!input.equals(BYE)) {
            String[] get = input.split(" ", 2);
            if (get.length == 1) {
                try {
                    switch (get[TASK]) {
                        case LIST:
                            modifyList.list();
                            break;
                        case UNMARK:
                            throw new Error(UNMARK_ERROR);
                        case MARK:
                            throw new Error(MARK_ERROR);
                        case DEADLINE:
                            throw new Error(DELETE_ERROR);
                        default:
                            throw new Error(INVALID_COMMAND);
                    }
                }
                catch (Error error) {
                    System.out.println(error.getMessage());
                }
            }
        else {
            switch (get[TASK]) { //if input more than 1
                case LIST:
                    System.out.println("Do you mean 'list'?\n");
                    modifyList.list();
                    break;
                case UNMARK:
                    try {
                        int unmarkIndex = Integer.parseInt(get[NUMBER]);
                        modifyList.unmark(unmarkIndex);
                    }
                    catch (IndexOutOfBoundsException error) {
                        System.out.println(OUT_OF_BOUNDS);
                    }
                    catch (NumberFormatException error) {
                        System.out.println(NOT_INTEGER);
                    }
                    break;

                case MARK:
                    try {
                        int markIndex = Integer.parseInt(get[NUMBER]);
                        modifyList.mark(markIndex);
                    }
                    catch (IndexOutOfBoundsException error) {
                        System.out.println(OUT_OF_BOUNDS);
                    }
                    catch (NumberFormatException error) {
                        System.out.println(NOT_INTEGER);
                    }
                    break;

                case DELETE:
                    try {
                        int deleteIndex = Integer.parseInt(get[NUMBER]);
                        modifyList.delete(deleteIndex);
                    }
                    catch (IndexOutOfBoundsException error) {
                        System.out.println(OUT_OF_BOUNDS);
                    }
                    catch (NumberFormatException error) {
                        System.out.println(NOT_INTEGER);
                    }
                    break;

                case DEADLINE:
                case EVENT:
                case TODO:
                    try {
                        modifyList.task(get[TASK], get[DETAILS]);
                    }
                    catch (Error error) {
                        System.out.println(error.getMessage());
                    }
                    break;
                default:
                    System.out.println(INVALID_COMMAND);
                    break;
            }
        }
            input = reader.nextLine(); // get next input indefinitely
        }
    }
    public static void main(String[] args) throws Error {
        greet();
        taskList();
        exit();

    }
}
