import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DukeManager.print(" Hello! I'm Matthew\n" + " What can I do for you?");
        DukeManager.load();
        while (true) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                DukeManager.list();
            } else if (line.startsWith("mark")) {
                DukeManager.mark(line);
            } else if (line.startsWith("unmark")) {
                DukeManager.unmark(line);
            } else if (line.startsWith("todo")) {
                DukeManager.createTodo(line);
            } else if (line.startsWith("event")) {
                DukeManager.createEvent(line);
            } else if (line.startsWith("deadline")) {
                DukeManager.createDeadline(line);
            } else if (line.startsWith("delete")) {
                DukeManager.delete(line);
            } else {
                DukeManager.print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                continue;
            }
            DukeManager.save();
        }
        DukeManager.print("Bye. Hope to see you again soon!");
    }
}
