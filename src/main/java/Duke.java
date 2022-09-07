import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static final String COMMAND_BYE = "bye";
    private static final String MESSAGE_BYE = "Duke: Bye. Hope to see you again soon!";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String MESSAGE_MARK = "The task has already been completed! Try doing another task now :)";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String MESSAGE_UNMARK = "The task hasn't been completed. Do it soon :(";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String ILLEGAL_TODO_INPUT_ERROR = "OOPS! The description of a todo cannot be empty";

    private static void checkInput(String input) throws DukeException {

    }
    public static void main(String[] args) {
        String line;
        ArrayList<Task> tasksList = new ArrayList<Task>();
        int count = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Duke: Hello! I'm Duke\n" + "Duke: What can I do for you?");

        while (true) {
            line = in.nextLine();
            String[] words = line.split(" ");
            switch (words[0]) {
            case COMMAND_BYE:
                System.out.println(MESSAGE_BYE);
                break;
            case COMMAND_LIST:
                System.out.println("Here are the tasks for today: ");
                for (int i = 0; i < tasksList.size(); i++) {
                    System.out.println(tasksList.get(i));
                }
                break;
            case COMMAND_MARK:
                int itemNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                Task toBeChanged = tasksList.get(itemNumber);
                if (toBeChanged.isDone) {
                    System.out.println(MESSAGE_MARK);
                } else {
                    toBeChanged.markAsDone(toBeChanged);
                }
                break;
            case COMMAND_UNMARK:
                itemNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                toBeChanged = tasksList.get(itemNumber);
                if (toBeChanged.isDone) {
                    toBeChanged.markAsUndone(toBeChanged);
                } else {
                    System.out.println(MESSAGE_UNMARK);
                }
                break;
            case COMMAND_TODO:
                try {
                    String description = line.substring(line.indexOf("todo") + 5);
                    if (description.length() < 1) {
                        throw new EmptyTodoDescriptionException(description);
                    } else {
                        Todo td = new Todo(description);
                        tasksList.add(td);
                        System.out.println("Got it. I've added this task: " + System.lineSeparator() +
                                td + System.lineSeparator() + "Now you have " + tasksList.size() + " tasks in the list");
                    }
                } catch (EmptyTodoDescriptionException e){
                    System.out.println(e.getExceptionMessage());
                }
                break;
            case COMMAND_DEADLINE:
                String description = line.substring(line.indexOf("deadline") + 9, line.indexOf("/"));
                String by = line.substring(line.indexOf("by") + 3);
                Deadline d = new Deadline(description, by);
                tasksList.add(d);
                System.out.println("Got it. I've added this task: " + System.lineSeparator() +
                        d + System.lineSeparator() + "Now you have " + tasksList.size() + " tasks in the list");
                break;
            case COMMAND_EVENT:
                description = line.substring(line.indexOf("event") + 6, line.indexOf("/"));
                String time = line.substring(line.indexOf("at") + 3);
                Event e = new Event(description, time);
                tasksList.add(e);
                System.out.println("Got it. I've added this task: " + System.lineSeparator() +
                        e + System.lineSeparator() + "Now you have " + tasksList.size() + " tasks in the list.");
                break;
            default:
                try {
                    String message = "error";
                    throw new UnrecognisedInput(message);
                } catch (UnrecognisedInput f) {
                    System.out.println(f.getExceptionMessage());
                }
                break;
            }
        }
    }
}
