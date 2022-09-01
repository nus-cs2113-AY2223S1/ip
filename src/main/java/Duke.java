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
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(Integer.toString(i + 1) + "." + tasks.get(i).getCheckBox()
                            + " "+ tasks.get(i).getName());
                }
                System.out.println();
            } else if (lineInput.startsWith("mark")) {
                int listIndex = Integer.parseInt(lineInput.substring(5));
                Task markedTask = tasks.get(listIndex - 1);
                markedTask.setIsCompleted(true);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  " + markedTask.getCheckBox() + " " + markedTask.getName());
                System.out.println();
            } else if (lineInput.startsWith("unmark")) {
                int listIndex = Integer.parseInt(lineInput.substring(7));
                Task unmarkedTask = tasks.get(listIndex - 1);
                unmarkedTask.setIsCompleted(false);
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println("  " + unmarkedTask.getCheckBox() + " " + unmarkedTask.getName());
                System.out.println();
            } else {
                Task task = new Task(lineInput);
                tasks.add(task);
                System.out.println("added: " + lineInput + System.lineSeparator());
            }
            lineInput = in.nextLine();
        }
        System.out.println(logo + "Bye. Hope to see see you again soon!" + System.lineSeparator() + logo);
    }
}
