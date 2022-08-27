import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String GREETING = "Hello! I'm Timmy\n What can I do for you?\n \n";
        final String GOODBYE = "Bye. Hope to see you again soon\n";

        TaskManager myManager = new TaskManager();

        System.out.println(GREETING);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (true) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                myManager.print();
            } else if (input.startsWith("mark")){
                myManager.markAsDone(input);
            } else if (input.startsWith("unmark")) {
                myManager.removeMark(input);
            } else if (input.startsWith("todo")){
                myManager.addTodo(input);
            } else if (input.startsWith("deadline")) {
                myManager.addDeadline(input);
            } else if (input.startsWith("event")) {
                myManager.addEvent(input);
            }
            System.out.println();
            input = in.nextLine();
        }

        System.out.println(GOODBYE);
    }
}

