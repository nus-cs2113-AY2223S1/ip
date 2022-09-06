package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {

    public static final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    public static final String SPACER = "  ";

    private static void markCommand(String input, int count, Task[] tasks) throws DukeException {
        String[] words = input.split(" ");
        int num = Integer.parseInt(words[1]);
        if (num <= (count)) {
            tasks[num - 1].markDone();
            System.out.println("wa so fast done liao\n" + SPACER + tasks[num - 1].toString() + System.lineSeparator()
                    + DIVIDER);
        } else {
            throw new DukeException();
        }
    }

    private static void unmarkCommand(String input, int count, Task[] tasks) throws DukeException {
        String[] words = input.split(" ");
        int num = Integer.parseInt(words[1]);
        if (num <= (count)) {
            tasks[num - 1].markUndone();
            System.out.println("can make up your mind\n" + SPACER + tasks[num - 1].toString() + System.lineSeparator()
                    + DIVIDER);
        } else {
            throw new DukeException();
        }
    }

    private static Deadline deadlineCommand(String input) throws DukeException {
        if (input.length() < 10 || !input.contains("/")) {
            throw new DukeException();
        }
        int endOfDescription = input.indexOf("/") - 1;
        String description = input.substring(9, endOfDescription);
        String by = input.substring(input.indexOf("/") + 1);
        return new Deadline(description, by);
    }

    private static Todo todoCommand(String input) throws DukeException {
        if (input.length() < 6) {
            throw new DukeException();
        }
        String description = input.substring(5);
        return new Todo(description);
    }

    private static Event eventCommand(String input) throws DukeException{
        if (input.length() < 7 || !input.contains("/")) {
            throw new DukeException();
        }
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
                try {
                    markCommand(line, matchCount, tasks);
                } catch(DukeException e) {
                    System.out.println("you only have " + (matchCount) + "tasks");
                }

                break;
            case "unmark":
                try {
                    unmarkCommand(line, matchCount, tasks);
                } catch (DukeException e) {
                    System.out.println("you only have " + (matchCount) + "tasks");
                }
                break;
            case "deadline":
                try {
                    tasks[matchCount] = deadlineCommand(line);
                    printStatement(tasks, matchCount);
                    matchCount += 1;
                } catch(DukeException e) {
                    System.out.println("Please key in a valid deadline input (missing '/' or missing description)");
                }
                break;
            case "todo":
                try {
                    tasks[matchCount] = todoCommand(line);
                    printStatement(tasks, matchCount);
                    matchCount += 1;
                } catch (DukeException e) {
                    System.out.println("aiya todo description cannot be empty.");
                }
                break;
            case "event":
                try {
                    tasks[matchCount] = eventCommand(line);
                    printStatement(tasks, matchCount);
                    matchCount += 1;
                } catch(DukeException e) {
                    System.out.println("Please key in a valid deadline input (missing '/' or missing description)");
                }
                break;
            default:
                System.out.println("Huh? What saying you?\n" + DIVIDER);
                break;
            }
        }
    }
}
