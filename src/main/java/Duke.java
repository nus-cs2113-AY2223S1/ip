import java.util.Objects;
import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {

        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASK];

        untilBye:
        while (true) {
            try {
                String line = in.nextLine();
                String[] parsedInput = line.split(" ");
                switch (parsedInput[0]) {
                case "list":
                    Task.printTaskList(tasks);
                    break;
                case "mark":
                    markTask(tasks, parsedInput);
                    break;
                case "unmark":
                    unmarkTask(tasks, parsedInput);
                    break;
                case "todo":
                    addTodo(tasks, line);
                    break;
                case "deadline":
                    addDeadline(tasks, line);
                    break;
                case "event":
                    addEvent(tasks, line);
                    break;
                case "bye":
                    printByeMessage();
                    break untilBye;
                default:
                    throw new DukeException();
                }
            }   catch (DukeException e) {
                Task.printHorizontalLine();
                System.out.println("     T_T OOPS!!! I'm sorry, but I don't know what that means :-(");
                Task.printHorizontalLine();
            }
        }
    }

    public static final int MAX_TASK = 100;

    public static void printWelcomeMessage() {
        Task.printHorizontalLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        Task.printHorizontalLine();
    }

    public static void printByeMessage() {
        Task.printHorizontalLine();
        System.out.println("     Bye. Hope to see you again soon!");
        Task.printHorizontalLine();
    }

    private static void addEvent(Task[] tasks, String line) {
        String[] DescriptionAt = line.replaceFirst("event ", "").split(" /at ");
        String eventDescription = DescriptionAt[0];
        String at = DescriptionAt[1];
        tasks[Event.getNumberOfTasks()] = new Event(eventDescription, at);
        int eventId = Task.getNumberOfTasks() - 1;
        tasks[eventId].printNewTask();
    }

    private static void addDeadline(Task[] tasks, String line) {
        String[] DescriptionBy = line.replaceFirst("deadline ", "").split(" /by ");
        String deadlineDescription = DescriptionBy[0];
        String by = DescriptionBy[1];
        tasks[Deadline.getNumberOfTasks()] = new Deadline(deadlineDescription, by);
        int deadlineId = Task.getNumberOfTasks() - 1;
        tasks[deadlineId].printNewTask();
    }

    private static void addTodo(Task[] tasks, String line) {
        String todoDescription = line.replaceFirst("todo ", "");
        tasks[Todo.getNumberOfTasks()] = new Todo(todoDescription);
        int todoId = Todo.getNumberOfTasks() - 1;
        tasks[todoId].printNewTask();
    }

    private static void unmarkTask(Task[] tasks, String[] parsedInput) {
        int unmarkId = Integer.parseInt(parsedInput[1]) - 1;
        tasks[unmarkId].setNotDone();
        tasks[unmarkId].printUnmark();
    }

    private static void markTask(Task[] tasks, String[] parsedInput) {
        int markId = Integer.parseInt(parsedInput[1]) - 1;
        tasks[markId].setDone();
        tasks[markId].printMark();
    }
}



