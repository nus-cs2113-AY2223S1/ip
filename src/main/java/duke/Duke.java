package duke;

import duke.exceptions.EmptyCommandException;

import java.util.Scanner;

public class Duke {

    public static void response(String line, TaskManager action) {
        String[] words = line.split(" ");
        String commandWord = words[0];
        int length = commandWord.length();
        String taskLine = " ";
        if (line.length() > length) {
            taskLine = line.substring(length + 1);
        }

        try {
            specificCase(line, action, commandWord, taskLine);
        } catch (EmptyCommandException e) {
            System.out.println("  ____________________________________________________________");
            System.out.println("\tSorry what are you trying to say?");
            System.out.println("  ____________________________________________________________");
        }
    }

    private static void specificCase(String line, TaskManager action, String commandWord,
            String taskLine) throws EmptyCommandException {
        String status = "start";
        switch (commandWord) {
        case "bye":
            action.bye();
            break;
        case "list":
            action.listTasks();
            break;
        case "mark":
        case "unmark":
            status = action.markOrUnmark(taskLine, commandWord);
            break;
        case "todo":
        case "deadline":
        case "event":
            status = action.addTask(taskLine, commandWord);
            break;
        default:
            throw new EmptyCommandException();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        TaskManager action = new TaskManager();

        action.greet();
        do {
            line = in.nextLine();
            response(line, action);
        } while (!line.equals("bye"));
    }
}

