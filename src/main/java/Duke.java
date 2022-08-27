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
        System.out.println();
    }

    private static void handleInput(TaskManager taskManager) {
        Scanner in = new Scanner(System.in);
        String input;

        final String EXIT_PHRASE = "bye";
        final String LIST_PHRASE = "list";
        final String MARK_PHRASE = "mark";
        final String UNMARK_PHRASE = "unmark";
        final String TODO_PHRASE = "todo";
        final String DEADLINE_PHRASE = "deadline";
        final String EVENT_PHRASE = "event";

        while (true) {
            input = in.nextLine();

            // handle exit
            if (input.equals(EXIT_PHRASE)) {
                break;
            } else {
                System.out.println(HORIZONTAL_LINE);

                // handle list, mark, unmark, add
                if (input.equals(LIST_PHRASE)) {
                    taskManager.printList();
                } else if (input.startsWith(MARK_PHRASE)) {
                    int taskNum = Integer.parseInt(input.substring(MARK_PHRASE.length() + 1));
                    taskManager.markTask(taskNum - 1);
                } else if (input.startsWith(UNMARK_PHRASE)) {
                    int taskNum = Integer.parseInt(input.substring(UNMARK_PHRASE.length() + 1));
                    taskManager.unmarkTask(taskNum - 1);
                } else if (input.startsWith(TODO_PHRASE)) {
                    taskManager.addTask(TODO_PHRASE, input);
                } else if (input.startsWith(DEADLINE_PHRASE)) {
                    taskManager.addTask(DEADLINE_PHRASE, input);
                } else if (input.startsWith(EVENT_PHRASE)) {
                    taskManager.addTask(EVENT_PHRASE, input);
                } else {
                    taskManager.addTask("", input);
                }

                System.out.println(HORIZONTAL_LINE);
                System.out.println();
            }
        }

    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        printIntroduction();
        handleInput(taskManager);
        printExit();
    }
}
