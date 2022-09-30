import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> inputs;
    private static final String ADD_CONFIRMATION_MESSAGE = "Got it. I've added this task:";

    public TaskList(ArrayList<Task> load) {
        inputs = load;
    }
    public TaskList() {
        inputs = new ArrayList<>();
    }

    /**
     * Retrieves tasks
     *
     * @return the tasks
     */
    public ArrayList<Task> getList() {
        return inputs;
    }

    /**
     * Deletes entry from list
     *
     * @param line integer that specifies the input to delete
     */
    public String delete(String line) throws DukeException {
        int input;
        try {
            input = Integer.valueOf(line.replace("delete", "").strip());
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! You must specify a number in the list to unmark. Format: unmark #");
        }
        try {
            Task task = inputs.get(input - 1);
            inputs.remove(input - 1);
            return "Noted. I've removed this task:\n" + task +"\nNow you have " + inputs.size() + " tasks in the list.";

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! You must specify a number in the list to delete. Format: delete #");
        }
    }

    /**
     * Unmarks an entry from list
     *
     * @param line integer that specifies the input to unmark
     */
    public String unmark(String line) throws DukeException {
        int input;
        try {
            input = Integer.valueOf(line.replace("unmark", "").strip());
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! You must specify a number in the list to unmark. Format: unmark #");
        }
        if (input <= 0 || input > inputs.size()) {
            throw new DukeException("☹ OOPS!!! You must specify a number in the list.");
        }
        Task task = inputs.get(input - 1);
        task.markAsNotDone();
        return "OK, I've marked this task as not done yet: \n  " + task;
    }

    /**
     * Marks entry from list
     *
     * @param line integer that specifies the input to mark
     */
    public String mark(String line) throws DukeException {
        int input;
        try {
            input = Integer.valueOf(line.replace("mark", "").strip());
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! You must specify a number in the list to mark. Format: mark #");
        }
        if (input <= 0 || input > inputs.size()) {
            throw new DukeException("☹ OOPS!!! You must specify a number in the list.");
        }
        Task task = inputs.get(input - 1);
        task.markAsDone();
        return "Nice! I've marked this task as done: \n  " + task;
    }

    /**
     * Creates a to-do
     *
     * @param line to-do (description)
     */
    public String createTodo(String line) throws DukeException {
        String description = line.replace("todo", "").strip();
        Todo todo;
        todo = new Todo(description);
        inputs.add(todo);
        return ADD_CONFIRMATION_MESSAGE + "\n  " + todo + "\nNow you have "+ inputs.size() + " tasks in the list.";
    }

    /**
     * Creates an event
     *
     * @param line event (description) /at (date)
     */
    public String createEvent(String line) throws DukeException {
        String removedCommand = line.replace("event", "");
        int timeIndex = removedCommand.indexOf("/at");
        String description;
        try {
            description = removedCommand.substring(0, timeIndex).strip();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! ensure that you include /at. Format: event (description) /at (date)");
        }

        String date = removedCommand.substring(timeIndex + "/at".length()).strip();
        Event event;
        event = new Event(description, date);

        inputs.add(event);
        return ADD_CONFIRMATION_MESSAGE + "\n  " + event + "\nNow you have "+ inputs.size() + " tasks in the list.";
    }

    /**
     * Creates a deadline
     *
     * @param line deadline (description) /by (date)
     */
    public String createDeadline(String line) throws DukeException {
        String removedCommand = line.replace("deadline", "");
        int timeIndex = removedCommand.indexOf("/by");
        String description = "";
        try {
            description = removedCommand.substring(0, timeIndex).strip();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! ensure that you include /by. Format: deadline (description) /by (date)");
        }

        String date = removedCommand.substring(timeIndex + "/by".length()).strip();
        Deadline deadline;
        deadline = new Deadline(description, date);

        inputs.add(deadline);
        return ADD_CONFIRMATION_MESSAGE + "\n  " + deadline + "\nNow you have "+ inputs.size() + " tasks in the list.";
    }

    /**
     * Finds inputs that have the specified search term and outputs the list
     *
     * @param line the search term
     */
    public String find(String line) {
        ArrayList<Task> matchedTerms = new ArrayList<>();
        String searchTerm = line.replace("find", "").strip();
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i).getDescription().contains(searchTerm)) {
                matchedTerms.add(inputs.get(i));
            }
        }
        return "Here are the matching tasks in your list:\n" + formatList(matchedTerms);
    }

    private static String formatList(ArrayList<Task> inputs) {
        String formattedString = "";
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i) == null) {
                break;
            }
            formattedString += (i+1) + "." + inputs.get(i).toString() + "\n";
        }
        formattedString = formattedString.strip();
        return formattedString;
    }

    /**
     * composes list of inputs for list command
     */
    public String list() {
        return formatList(inputs);
    }
}
