package duke;

import java.util.Scanner;
import duke.task.Task;
import duke.task.TaskFactory;

public class Duke {
    private static final String INDENT = "    ";
    private static final String HORIZONTAL_RULE = "____________________________________________________________\n";
    private static final String DUKE_LOGO = String.join("\n", " ____        _        ", "|  _ \\ _   _| | _____ ",
            "| | | | | | | |/ / _ \\", "| |_| | |_| |   <  __/", "|____/ \\__,_|_|\\_\\___|");
    private static final String[] GREETING = { "Hello! I'm Duke", "What can I do for you?" };
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private static ListManager listManager = new ListManager();

    public static void displayMessage(String[] lines) {
        System.out.println(INDENT + HORIZONTAL_RULE);
        for (String line : lines) {
            System.out.println(INDENT + " " + line);
        }
        System.out.println(INDENT + HORIZONTAL_RULE);
    }

    public static void displayMessage(String line) {
        displayMessage(line.split("\n"));
    }

    public static void processInput(String input) {
        // command types: read, return, list
        try {
            if (input.startsWith("list")) {
                displayMessage(listManager.toString());
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring("mark".length()).trim());
                listManager.markDone(index);
                displayMessage(String.format("Nice! I've marked this task as done:\n %s", listManager.getItem(index)));
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring("unmark".length()).trim());
                listManager.markUndone(index);
                displayMessage(
                        String.format("OK, I've marked this task as not done yet:\n %s", listManager.getItem(index)));
            } else {
                Task task = TaskFactory.createTask(input);
                listManager.addItem(task);
                displayMessage("added: " + task);
            }
        } catch (DukeException e) {
            displayMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println(DUKE_LOGO);
        displayMessage(GREETING);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            processInput(input.trim());
        }
        displayMessage(EXIT_MESSAGE);
        scanner.close();
    }
}
