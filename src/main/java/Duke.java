import java.util.Scanner;

public class Duke {

    public static final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    public static final String SPACER = "  ";

    private static void markCommand(String input, int count, Task[] tasks) {
        String[] words = input.split(" ");
        int num = Integer.parseInt(words[1]);
        if (num <= (count)) {
            tasks[num - 1].markDone();
            System.out.println("wa so fast done liao\n" + SPACER + tasks[num - 1].toString() + System.lineSeparator()
                    + DIVIDER);
        }
    }

    private static void unmarkCommand(String input, int count, Task[] tasks) {
        String[] words = input.split(" ");
        int num = Integer.parseInt(words[1]);
        if (num <= (count)) {
            tasks[num - 1].markUndone();
            System.out.println("can make up your mind\n" + SPACER + tasks[num - 1].toString() + System.lineSeparator()
                    + DIVIDER);
        }
    }

    private static Deadline deadlineCommand(String input) {
        int endOfDescription = input.indexOf("/") - 1;
        String description = input.substring(9, endOfDescription);
        String by = input.substring(input.indexOf("/") + 1);
        return new Deadline(description, by);
    }

    private static Todo todoCommand(String input) {
        String description = input.substring(5);
        return new Todo(description);
    }

    private static Event eventCommand(String input) {
        int endOfDescription = input.indexOf("/") - 1;
        String description = input.substring(6, endOfDescription);
        String at = input.substring(input.indexOf("/") + 1);
        return new Event(description, at);
    }

    private static void listCommand(Task[] tasks) {
        int counter = 1;
        System.out.println("come uncle show you your tasks");
        for (Task task : tasks) {
            if (task != null) {
                String toBePrinted = task.toString();
                System.out.println(counter + ". " + toBePrinted);
                counter++;
            }
        }
        System.out.println(DIVIDER);
    }

    private static void printStatement(Task[] tasks, int count) {
        System.out.println("add task liao" + System.lineSeparator() + SPACER + tasks[count].toString()
                + System.lineSeparator() + "you still have " + (count + 1) + " tasks left" + System.lineSeparator()
                + DIVIDER);
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String logo2 = "                   _      \n"
                + "                  | |     \n"
                + "  _   _ _ __   ___| | ___ \n"
                + " | | | | '_ \\ / __| |/ _ |\n"
                + " | |_| | | | | (__| |  __/ \n"
                + "  \\__,_|_| |_|\\___|_|\\___| \n";

        System.out.println("Oi I'm\n" + logo2);
        System.out.println("What you want?");

        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int matchCount = 0;
        boolean run = true;
        while (run) {
            String line = in.nextLine();
            //String[] words = line.split(" ");
            String[] input = line.split(" ", 2);
            String mainCommand = input[0];
            switch (mainCommand) {
            case "bye":
                System.out.println("Bye bye!");
                run = false;
                break;
            case "list":
                listCommand(tasks);
                break;
            case "mark":
                markCommand(line, matchCount, tasks);
                break;
            case "unmark":
                unmarkCommand(line, matchCount, tasks);
                break;
            case "deadline":
                tasks[matchCount] = deadlineCommand(line);
                printStatement(tasks, matchCount);
                matchCount += 1;
                break;
            case "todo":
                tasks[matchCount] = todoCommand(line);
                printStatement(tasks, matchCount);
                matchCount += 1;
                break;
            case "event":
                tasks[matchCount] = eventCommand(line);
                printStatement(tasks, matchCount);
                matchCount += 1;
                break;
            default:
                System.out.println("Huh? What saying you?\n" + DIVIDER);
                break;
            }
        }
    }
}
