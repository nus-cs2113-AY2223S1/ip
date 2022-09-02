import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String logo = "______________________________________________\n";
        System.out.println(logo+ " Hello! I'm Duke\n" +
                " What can I do for you?\n" + logo);

        String lineInput;
        Scanner in = new Scanner(System.in);
        lineInput = in.nextLine();

        while (!lineInput.equals("bye")) {
            if (lineInput.equals("list")) {
                System.out.println(logo);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println( Integer.toString(i + 1) + "." + tasks.get(i).toString());
                }
                System.out.println(logo);
            } else if (lineInput.startsWith("mark")) {
                int listIndex = Integer.parseInt(lineInput.substring(5));
                Task markedTask = tasks.get(listIndex - 1);
                markedTask.setIsCompleted(true);
                System.out.println(logo + "Nice! I've marked this task as done: ");
                System.out.println("  " + markedTask.toString());
                System.out.println();
            } else if (lineInput.startsWith("unmark")) {
                int listIndex = Integer.parseInt(lineInput.substring(7));
                Task unmarkedTask = tasks.get(listIndex - 1);
                unmarkedTask.setIsCompleted(false);
                System.out.println(logo + "OK, I've marked this task as not done yet: ");
                System.out.println("  " + unmarkedTask.toString());
                System.out.println();
            } else if (lineInput.startsWith("todo")) {
                System.out.println(logo + "Got it. I've added this task: ");
                ToDo toDo = new ToDo(lineInput.substring(5));
                tasks.add(toDo);
                System.out.println(" " + toDo.toString());
                System.out.println("Now you have " + Integer.toString(tasks.size()) + " tasks in the list \n" + logo);
            } else if (lineInput.startsWith("event")) {
                System.out.println(logo + "Got it. I've added this task: ");
                int indexOfSeparator = lineInput.indexOf("/at");
                Event event = new Event(lineInput.substring(6, indexOfSeparator - 1), lineInput.substring(indexOfSeparator + 4));
                tasks.add(event);
                System.out.println(" " + event.toString());
                System.out.println("Now you have " + Integer.toString(tasks.size()) + " tasks in the list \n" + logo);
            } else if (lineInput.startsWith("deadline")){
                System.out.println(logo + "Got it. I've added this task: ");
                int indexOfSeparator = lineInput.indexOf("/by");
                Deadline deadline = new Deadline(lineInput.substring(9, indexOfSeparator - 1), lineInput.substring(indexOfSeparator + 4));
                tasks.add(deadline);
                System.out.println(" " + deadline.toString());
                System.out.println("Now you have " + Integer.toString(tasks.size()) + " tasks in the list \n" + logo);

            } else {
                Task task = new Task(lineInput);
                System.out.println(logo + "Got it. I've added this task: ");
                tasks.add(task);
                System.out.println(" " + task.toString());
                System.out.println("Now you have " + Integer.toString(tasks.size()) + " tasks in the list \n" + logo);
            }
            lineInput = in.nextLine();
        }
        System.out.println(logo + "Bye. Hope to see see you again soon!" + System.lineSeparator() + logo);
    }
}
