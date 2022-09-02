import java.util.Scanner;
public class Duke extends Constants{
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

    public static void taskList(){ // check if get is 1 or 2
        ModifyList modifyList = new ModifyList();
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();

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
                    int unmarkIndex = Integer.parseInt(get[NUMBER]);
                    try {
                        modifyList.unmark(unmarkIndex);
                    } catch (IndexOutOfBoundsException error) {
                        System.out.println(OUT_OF_BOUNDS);
                    }
                    break;
                case MARK:
                    int markIndex = Integer.parseInt(get[NUMBER]);
                    try {
                        modifyList.mark(markIndex);
                    } catch (IndexOutOfBoundsException error) {
                        System.out.println(OUT_OF_BOUNDS);
                    }
                    break;
                default:
                    try {
                        modifyList.task(get[TASK], get[DETAILS]);
                    } catch (ArrayIndexOutOfBoundsException error) {
                        if (get[TASK].equals(DEADLINE)) {
                            System.out.println(DEADLINE_FORMAT_ERROR);
                        } else if (get[TASK].equals(EVENT)) {
                            System.out.println(EVENT_FORMAT_ERROR);
                        }
                    }
                    break;
            }
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
