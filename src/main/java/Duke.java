import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskList list = new TaskList();
        Scanner in = new Scanner(System.in);
        list.start();
        while (in.hasNextLine()) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                list.end();
                break;
            } else if (input.equals("list")) {
                list.printList();
            } else if (input.contains("unmark")) {
                list.unmarkStatus(input);
            } else if (input.contains("mark")) {
                list.markStatus(input);
            }
            else {
                list.addTask(input);
            }
        }
    }
}
