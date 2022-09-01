import java.util.Scanner;

public class Duke {
    private static void markCommand(String input, int count, Task[] tasks) {
        String[] words = input.split(" ");
        int num = Integer.parseInt(words[1]);
        if (num <= (count)) {
            tasks[num - 1].markDone();
            System.out.println(tasks[num - 1].toString());
        }
    }

    private static void unmarkCommand(String input, int count, Task[] tasks) {
        String[] words = input.split(" ");
        int num = Integer.parseInt(words[1]);
        if (num <= (count)) {
            tasks[num - 1].markUndone();
            System.out.println(tasks[num - 1].toString());
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
        System.out.println("Here are the tasks in your list");
        for (Task task : tasks) {
            if (task != null) {
                String toBePrinted = task.toString();
                System.out.println(counter + ". " + toBePrinted);
                counter++;
            }
        }
    }

    private static void printStatement(Task[] tasks, int count) {
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + tasks[count].toString()
                + System.lineSeparator() + "Now you have " + (count + 1) + " in the list");
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

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
                System.out.println("Bye. Hope to see you again soon!");
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
                System.out.println("Enter a valid command");
                break;
            }
        }
    }
}
