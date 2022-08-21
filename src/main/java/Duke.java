import java.util.Scanner;

public class Duke {

    public static void response(String line, TaskManager action) {
        if (line.equals("bye")) {
            action.bye();
        } else if (line.equals("list")) {
            action.listTasks();
        } else if ((line.length() > 5) && (line.substring(0, 5).equals("mark "))) {
            action.markTask(line);
        } else if ((line.length() > 7) && (line.substring(0, 7).equals("unmark "))) {
            action.unmarkTask(line);
        } else {
            action.addTask(line);
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

