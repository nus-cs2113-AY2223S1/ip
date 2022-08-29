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

        switch (commandWord) {
        case "bye":
            action.bye();
            break;
        case "list":
            action.listTasks();
            break;
        case "mark":
            action.markTask(taskLine);
            break;
        case "unmark":
            action.unmarkTask(taskLine);
            break;
        case "todo":
        case "deadline":
        case "event":
            action.addTask(taskLine, commandWord);
            break;
        default:
            action.addTask(line, commandWord);
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

