import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final String ADD_CONFIRMATION_MESSAGE = "Got it. I've added this task:";
    private static Task[] inputs = new Task[100];
    private static int index = 0;

    /**
     * Returns a string with formatted user inputs.
     *
     * @param inputs list of user input.
     * @return Formatted string to print
     */
    public static String formatList(Task[] inputs) {
        String formattedString = HORIZONTAL_LINE;
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] == null) {
                break;
            }
            formattedString += (i+1) + "." + inputs[i].toString() + "\n";
        }
        formattedString += HORIZONTAL_LINE;
        return formattedString;
    }

    private static void unmark(String line){
        int input = Integer.valueOf(line.replace("unmark", "").strip());
        Task task = inputs[input - 1];
        task.markAsNotDone();
        System.out.println(HORIZONTAL_LINE + "OK, I've marked this task as not done yet: \n  "
                + task + "\n" + HORIZONTAL_LINE);
    }

    private static void mark(String line) {
        int input = Integer.valueOf(line.replace("mark", "").strip());
        Task task = inputs[input - 1];
        task.markAsDone();
        System.out.println(HORIZONTAL_LINE + "Nice! I've marked this task as done: \n  "
                + task + "\n"+ HORIZONTAL_LINE);
    }

    private static void createTodo(String line){
        String description = line.replace("todo", "").strip();
        Todo todo = new Todo(description);
        inputs[index] = todo;
        index++;
        System.out.println(HORIZONTAL_LINE + ADD_CONFIRMATION_MESSAGE + "\n  " + todo
                + "\nNow you have "+ index + " tasks in the list.\n" + HORIZONTAL_LINE);
    }

    private static void createEvent(String line){
        String removedCommand = line.replace("event", "");
        String description = removedCommand.substring(0, removedCommand.indexOf("/at")).strip();
        String date = removedCommand.substring(removedCommand.indexOf("/at") + "/at".length()).strip();
        Event event = new Event(description, date);
        inputs[index] = event;
        index++;
        System.out.println(HORIZONTAL_LINE + ADD_CONFIRMATION_MESSAGE + "\n  " + event
                + "\nNow you have "+ index + " tasks in the list.\n" + HORIZONTAL_LINE);
    }

    private static void createDeadline(String line) {
        String removedCommand = line.replace("deadline", "");
        String description = removedCommand.substring(0, removedCommand.indexOf("/by")).strip();
        String date = removedCommand.substring(removedCommand.indexOf("/by") + "/by".length()).strip();
        Deadline deadline = new Deadline(description, date);
        inputs[index] = deadline;
        index++;
        System.out.println(HORIZONTAL_LINE + ADD_CONFIRMATION_MESSAGE + "\n  " + deadline
                + "\nNow you have "+ index + " tasks in the list.\n" + HORIZONTAL_LINE);
    }

    private static void createTask(String line) {
        System.out.println(HORIZONTAL_LINE + "added: " + line + "\n" + HORIZONTAL_LINE);
        inputs[index] = new Task(line);
        index++;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;

        System.out.println(HORIZONTAL_LINE + " Hello! I'm Matthew\n" + " What can I do for you?\n" + HORIZONTAL_LINE);

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")){
                System.out.println(formatList(inputs));
            } else if (line.startsWith("mark")) {
                mark(line);
            } else if (line.startsWith("unmark")) {
                unmark(line);
            } else if (line.startsWith("todo")) {
                createTodo(line);
            } else if (line.startsWith("event")) {
                createEvent(line);
            } else if (line.startsWith("deadline")) {
                createDeadline(line);
            }  else {
                createTask(line);
            }
        }
        System.out.println(HORIZONTAL_LINE + "Bye. Hope to see you again soon!\n" + HORIZONTAL_LINE);
    }
}
