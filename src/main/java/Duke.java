import java.util.Scanner;

public class Duke {

    /**
     * Returns a string with formatted user inputs.
     *
     * @param inputs list of user input.
     * @return Formatted string to print
     */
    public static String formatList(Task[] inputs) {
        String formattedString = "____________________________________________________________\n";
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] == null) {
                break;
            }
            formattedString += (i+1) + ".[" + inputs[i].getStatusIcon() + "] " + inputs[i].getDescription() + "\n";
        }
        formattedString += "____________________________________________________________\n";
        return formattedString;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        Task[] inputs = new Task[100];
        int index = 0;

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Matthew\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")){
                System.out.println(formatList(inputs));
            } else if (line.startsWith("mark")) {
                int input = Integer.valueOf(line.substring(5).strip());
                Task t = inputs[input - 1];
                t.markAsDone();
                System.out.println("____________________________________________________________\n" +
                        "Nice! I've marked this task as done: \n  [" + t.getStatusIcon() + "] " + t.getDescription() +
                        "\n____________________________________________________________\n");
            } else if (line.startsWith("unmark")) {
                int input = Integer.valueOf(line.substring(7).strip());
                Task t = inputs[input - 1];
                t.markAsNotDone();
                System.out.println("____________________________________________________________\n" +
                        "OK, I've marked this task as not done yet: \n  ["
                        + t.getStatusIcon() + "] " + t.getDescription() +
                        "\n____________________________________________________________\n");
            } else {
                System.out.println(
                        "____________________________________________________________\nadded: " +
                        line + "\n" +
                        "____________________________________________________________\n");
                inputs[index] = new Task(line);
                index++;
            }
        }
        System.out.println(
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
