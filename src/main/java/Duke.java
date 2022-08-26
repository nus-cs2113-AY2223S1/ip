import java.util.Scanner;

public class Duke {

    /**
     *
     */
    private static final int MAXLISTNUM = 100;
    private static Task[] taskList = new Task[MAXLISTNUM];
    private static int listLength = 0;

    public static void main(String[] args) {

        showWelcomeMessage();
        handleUserInput();
    }

    private static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    private static void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        int index;
        while (true) {
            String inputText = scanner.nextLine();
            while (inputText.trim().isEmpty())
                inputText = scanner.nextLine();
            final String[] split = inputText.trim().split("\\s+", 2);
            final String[] commandTypeAndParams = split.length == 2 ? split : new String[] { split[0], "" };
            final String commandType = commandTypeAndParams[0];
            final String commandArgs = commandTypeAndParams[1];
            switch (commandType) {
            case "list":
                System.out.println("    ____________________________________________________________");
                for (int i = 1; i <= listLength; i++) {
                    System.out.println("    " + String.valueOf(i) + "." + "[" + taskList[i - 1].getStatusIcon()
                            + "] " + taskList[i - 1].getDescription());
                }
                ;
                System.out.println("    ____________________________________________________________");
                System.out.println();
                break;
            case "mark":
                index = Integer.valueOf(commandArgs) - 1;
                taskList[index].setStringState(true);
                System.out.println("    ____________________________________________________________");
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println(
                        "      " + "[" + taskList[index].getStatusIcon() + "] " + taskList[index].getDescription());
                System.out.println("    ____________________________________________________________");
                break;
            case "unmark":
                index = Integer.valueOf(commandArgs) - 1;
                taskList[index].setStringState(false);
                System.out.println("    ____________________________________________________________");
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println(
                        "      " + "[" + taskList[index].getStatusIcon() + "]" + taskList[index].getDescription());
                System.out.println("    ____________________________________________________________");
                break;
            case "bye":
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                System.exit(0);
            default:
                taskList[listLength] = new Task(inputText);
                listLength++;
                System.out.println("    ____________________________________________________________");
                System.out.println("    " + "added: " + inputText);
                System.out.println("    ____________________________________________________________");
                System.out.println();
                break;
            }
        }
    }
}
