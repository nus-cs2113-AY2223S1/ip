import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static final String LINE_BREAK = "______________________________________________\n";

    public static void printList() { //prints list of tasks
        System.out.println(LINE_BREAK);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println( Integer.toString(i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(LINE_BREAK);
    }

    public static void printMarkedTask(Task markedTask) { //prints that the task is marked
        System.out.println(LINE_BREAK + "Nice! I've marked this task as done: ");
        System.out.println("  " + markedTask.toString());
        System.out.println();
    }

    public static void printUnmarkedTask(Task unmarkedTask) { //prints that the task is not done yet
        System.out.println(LINE_BREAK + "OK, I've marked this task as not done yet: ");
        System.out.println("  " + unmarkedTask.toString());
        System.out.println();
    }

    public static void printAddedTask(Task task) { //prints that the task has been added to list
        System.out.println(LINE_BREAK + "Got it. I've added this task: ");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + Integer.toString(tasks.size()) + " tasks in the list \n" + LINE_BREAK);
    }

    public static ToDo getToDo(String[] inputWords) { //returns a todo object from the user input sentence
        String[] taskNameWords = Arrays.copyOfRange(inputWords, 1, inputWords.length);
        String taskName = String.join(" ", taskNameWords);
        ToDo toDo = new ToDo(taskName);
        return toDo;
    }

    public static Deadline getDeadline(String[] inputWords) { //returns a Deadline object from the user input sentence
        int indexOfBy = Arrays.asList(inputWords).indexOf("/by");
        String deadlineName = String.join(" ", Arrays.copyOfRange(inputWords, 1, indexOfBy));
        String dueDate = String.join(" ", Arrays.copyOfRange(inputWords, indexOfBy + 1, inputWords.length));
        Deadline deadline = new Deadline(deadlineName, dueDate);
        return deadline;
    }

    public static Event getEvent(String[] inputWords) { //returns an event object from the user input sentence
        int indexOfAt = Arrays.asList(inputWords).indexOf("/at");
        String eventName = String.join(" ", Arrays.copyOfRange(inputWords, 1, indexOfAt));
        String timeOfEvent = String.join(" ", Arrays.copyOfRange(inputWords, indexOfAt + 1, inputWords.length));
        Event event = new Event(eventName, timeOfEvent);
        return event;
    }

    public static void printInvalidDeadline() {
        System.out.println('\n' + "Invalid Deadline. Please provide due date!" + '\n');
    }

    public static void printInvalidEvent() {
        System.out.println('\n' + "Invalid Event. Please provide event time!" + '\n');
    }

    public static void markTask(String[] inputWords) { //marks task as done in the list
        int listIndex = Integer.parseInt(inputWords[1]) - 1;
        Task markedTask = tasks.get(listIndex);
        markedTask.setIsCompleted(true);
        printMarkedTask(markedTask);
    }

    public static void unmarkTask(String[] inputWords){ //marks task as not done in the list
        int listIndex = Integer.parseInt(inputWords[1]) - 1;
        Task unmarkedTask = tasks.get(listIndex);
        unmarkedTask.setIsCompleted(false);
        printUnmarkedTask(unmarkedTask);
    }

    public static void addTask(String lineInput) { //adds task to list
        Task task = new Task(lineInput);
        tasks.add(task);
        printAddedTask(task);
    }

    public static void addDeadline(String[] inputWords) { //adds deadline to list of tasks if keyword "/by" is found
        if(!Arrays.asList(inputWords).contains("/by")) {
            printInvalidDeadline();
        }
        else {
            Deadline deadline = getDeadline(inputWords);
            tasks.add(deadline);
            printAddedTask(deadline);
        }
    }

    public static void addEvent(String[] inputWords) { //adds event to list of tasks if keyword "/at" is found
        if(!Arrays.asList(inputWords).contains("/at")) {
            printInvalidEvent();
        }
        else {
            Event event = getEvent(inputWords);
            tasks.add(event);
            printAddedTask(event);
        }
    }

    public static void addToDo(String[] inputWords){ //adds todo to list of tasks
        ToDo toDo = getToDo(inputWords);
        tasks.add(toDo);
        printAddedTask(toDo);
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
                addTask(lineInput);
            }
            lineInput = in.nextLine();
        }
        System.out.println(LINE_BREAK + "Bye. Hope to see see you again soon!" + System.lineSeparator() + LINE_BREAK);
    }
}
