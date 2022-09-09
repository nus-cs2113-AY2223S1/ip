import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String LINE_BREAK = "______________________________________________\n";

    public static void printList() {
        System.out.println(LINE_BREAK);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println( Integer.toString(i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(LINE_BREAK);
    }

    public static void printMarkedTask(Task markedTask) {
        System.out.println(LINE_BREAK + "Nice! I've marked this task as done: ");
        System.out.println("  " + markedTask.toString());
        System.out.println();
    }

    public static void printUnmarkedTask(Task unmarkedTask) {
        System.out.println(LINE_BREAK + "OK, I've marked this task as not done yet: ");
        System.out.println("  " + unmarkedTask.toString());
        System.out.println();
    }

    public static void printAddedTask(Task task) {
        System.out.println(LINE_BREAK + "Got it. I've added this task: ");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + Integer.toString(tasks.size()) + " tasks in the list \n" + LINE_BREAK);
    }

    public static ToDo getToDo(String[] inputWords) throws NoDescriptionException {
        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        String[] taskNameWords = Arrays.copyOfRange(inputWords, 1, inputWords.length);
        String taskName = String.join(" ", taskNameWords);
        ToDo toDo = new ToDo(taskName);
        return toDo;
    }

    public static Deadline getDeadline(String[] inputWords) throws NoDescriptionException, InvalidTaskFormatException {
        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        if (!Arrays.asList(inputWords).contains("/by")) {
            throw new InvalidTaskFormatException();
        }
        int indexOfBy = Arrays.asList(inputWords).indexOf("/by");
        String deadlineName = String.join(" ", Arrays.copyOfRange(inputWords, 1, indexOfBy));
        String dueDate = String.join(" ", Arrays.copyOfRange(inputWords, indexOfBy + 1, inputWords.length));
        Deadline deadline = new Deadline(deadlineName, dueDate);
        return deadline;
    }

    public static Event getEvent(String[] inputWords) throws NoDescriptionException, InvalidTaskFormatException {
        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        if (!Arrays.asList(inputWords).contains("/at")) {
            throw new InvalidTaskFormatException();
        }
        int indexOfAt = Arrays.asList(inputWords).indexOf("/at");
        String eventName = String.join(" ", Arrays.copyOfRange(inputWords, 1, indexOfAt));
        String timeOfEvent = String.join(" ", Arrays.copyOfRange(inputWords, indexOfAt + 1, inputWords.length));
        Event event = new Event(eventName, timeOfEvent);
        return event;
    }

    public static void printInvalidDeadline() {
        System.out.println(LINE_BREAK + "Invalid Deadline. Please provide due date!\n" + LINE_BREAK);
    }

    public static void printInvalidEvent() {
        System.out.println(LINE_BREAK + "Invalid Event. Please provide event time!\n" + LINE_BREAK);
    }

    public static void markTask(String[] inputWords) {
        int listIndex = Integer.parseInt(inputWords[1]) - 1;
        Task markedTask = tasks.get(listIndex);
        markedTask.setIsCompleted(true);
        printMarkedTask(markedTask);
    }

    public static void unmarkTask(String[] inputWords){
        int listIndex = Integer.parseInt(inputWords[1]) - 1;
        Task unmarkedTask = tasks.get(listIndex);
        unmarkedTask.setIsCompleted(false);
        printUnmarkedTask(unmarkedTask);
    }

    public static void addDeadline(String[] inputWords) {
         try {
             Deadline deadline = getDeadline(inputWords);
             tasks.add(deadline);
             printAddedTask(deadline);
         } catch (NoDescriptionException e) {
             System.out.println(LINE_BREAK + "Oh No! The description of a deadline cannot be empty.\n" + LINE_BREAK);
         } catch (InvalidTaskFormatException e) {
             printInvalidDeadline();
         }
    }

    public static void addEvent(String[] inputWords) {
        try {
            Event event = getEvent(inputWords);
            tasks.add(event);
            printAddedTask(event);
        } catch (NoDescriptionException e) {
            System.out.println(LINE_BREAK + "Oh No! The description of an event cannot be empty.\n" + LINE_BREAK);
        } catch (InvalidTaskFormatException e) {
            printInvalidEvent();
        }
    }

    public static void addToDo(String[] inputWords){
        try {
            ToDo toDo = getToDo(inputWords);
            tasks.add(toDo);
            printAddedTask(toDo);
        } catch (NoDescriptionException e) {
            System.out.println(LINE_BREAK + "Oh No! The description of a todo cannot be empty.\n" + LINE_BREAK);
        }
    }

    public static void printInvalidCommand() {
        System.out.println(LINE_BREAK + "Oh no! I do not understand the command! \n" + LINE_BREAK);
    }
    public static void main(String[] args) {
        System.out.println(LINE_BREAK + " Hello! I'm Duke\n" +
                " What can I do for you?\n" + LINE_BREAK);
        String lineInput;
        Scanner in = new Scanner(System.in);
        lineInput = in.nextLine();

        while (!lineInput.equalsIgnoreCase("bye")) {
            String[] inputWords = lineInput.split("\\s+");
            String action = inputWords[0];

            if (action.equalsIgnoreCase("list")) {
                printList();
            } else if (action.equalsIgnoreCase("mark")) {
                markTask(inputWords);
            } else if (action.equalsIgnoreCase("unmark")) {
                unmarkTask(inputWords);
            } else if (action.equalsIgnoreCase("todo")) {
                addToDo(inputWords);
            } else if (action.equalsIgnoreCase("event")) {
                addEvent(inputWords);
            } else if (action.equalsIgnoreCase("deadline")){
                addDeadline(inputWords);
            } else {
                printInvalidCommand();
            }
            lineInput = in.nextLine();
        }
        System.out.println(LINE_BREAK + "Bye. Hope to see see you again soon!" + System.lineSeparator() + LINE_BREAK);
    }
}
