public class DukeManager {
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

    public static void print(String string) {
        System.out.println(HORIZONTAL_LINE + string + "\n" + HORIZONTAL_LINE);
    }

    public static void list() {
        print(formatList(inputs));
    }

    public static void unmark(String line) {
        int input = -1;
        try {
            input = Integer.valueOf(line.replace("unmark", "").strip());
        } catch (NumberFormatException e) {
            print("☹ OOPS!!! You must specify a number in the list to unmark. Format: unmark #");
            return;
        }
        if (input >= 0 && input < index) {
            print("☹ OOPS!!! You must specify a number in the list.");
            return;
        }
        Task task = inputs[input - 1];
        task.markAsNotDone();
        print("OK, I've marked this task as not done yet: \n  " + task);
    }

    public static void mark(String line) {
        int input = -1;
        try {
            input = Integer.valueOf(line.replace("mark", "").strip());
        } catch (NumberFormatException e) {
            print("☹ OOPS!!! You must specify a number in the list to mark. Format: mark #");
            return;
        }
        if (input < 0 || input > index) {
            print("☹ OOPS!!! You must specify a number in the list.");
            return;
        }
        Task task = inputs[input - 1];
        task.markAsDone();
        print("Nice! I've marked this task as done: \n  " + task);
    }

    public static void createTodo(String line) {
        String description = line.replace("todo", "").strip();
        Todo todo = null;
        try {
            todo = new Todo(description);
        } catch (DukeException e) {
            print("☹ OOPS!!! The description of a todo cannot be empty.");
            return;
        }
        inputs[index] = todo;
        index++;
        print(ADD_CONFIRMATION_MESSAGE + "\n  " + todo + "\nNow you have "+ index + " tasks in the list.");
    }

    public static void createEvent(String line) {
        String removedCommand = line.replace("event", "");
        int timeIndex = removedCommand.indexOf("/at");
        String description = "";
        try {
            description = removedCommand.substring(0, timeIndex).strip();
        } catch (StringIndexOutOfBoundsException e) {
            print("☹ OOPS!!! ensure that you include /at. Format: event (description) /at (date)");
            return;
        }

        String date = removedCommand.substring(timeIndex + "/at".length()).strip();
        Event event = null;
        try {
            event = new Event(description, date);
        } catch (DukeException e) {
            print("☹ OOPS!!! The description and date of an event cannot be empty.");
            return;
        }
        inputs[index] = event;
        index++;
        print(ADD_CONFIRMATION_MESSAGE + "\n  " + event + "\nNow you have "+ index + " tasks in the list.");
    }

    public static void createDeadline(String line) {
        String removedCommand = line.replace("deadline", "");
        int timeIndex = removedCommand.indexOf("/by");
        String description = "";
        try {
            description = removedCommand.substring(0, timeIndex).strip();
        } catch (StringIndexOutOfBoundsException e) {
            print("☹ OOPS!!! ensure that you include /by. Format: deadline (description) /by (date)");
            return;
        }
        if (description.isBlank()) {
            print("☹ OOPS!!! The description of a deadline cannot be empty.");
            return;
        }
        String date = removedCommand.substring(timeIndex + "/by".length()).strip();
        Deadline deadline = null;
        try {
            deadline = new Deadline(description, date);
        } catch (DukeException e) {
            print("☹ OOPS!!! The date and description of a deadline cannot be empty.");
            return;
        }
        inputs[index] = deadline;
        index++;
        print(ADD_CONFIRMATION_MESSAGE + "\n  " + deadline + "\nNow you have "+ index + " tasks in the list.");
    }
}
