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
                    tryMarkTask(tasks, line);
                    break;
                case "unmark":
                    tryUnmarkTask(tasks, line);
                    break;
                case "todo":
                    tryAddTodo(tasks, line);
                    break;
                case "deadline":
                    tryAddDeadline(tasks, line);
                    break;
                case "event":
                    tryAddEvent(tasks, line);
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

    private static void tryAddEvent(Task[] tasks, String line){
        try{
            if(line.replaceFirst("event", "").trim().equals("")){
                throw new DukeException();
            }
            addEvent(tasks, line);
        } catch (DukeException e){
            Task.printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of an event cannot be empty.");
            Task.printHorizontalLine();
        }
    }
    private static void addEvent(Task[] tasks, String line) {
        String[] DescriptionAt = line.replaceFirst("event ", "").split(" /at ");
        String eventDescription = DescriptionAt[0];
        String at = DescriptionAt[1];
        tasks[Event.getNumberOfTasks()] = new Event(eventDescription, at);
        int eventId = Task.getNumberOfTasks() - 1;
        tasks[eventId].printNewTask();
    }
    private static void tryAddDeadline(Task[] tasks, String line){
        try{
            if(line.replaceFirst("deadline", "").trim().equals("")){
                throw new DukeException();
            }
            addDeadline(tasks, line);
        } catch (DukeException e){
            Task.printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of a deadline cannot be empty.");
            Task.printHorizontalLine();
        }
    }
    private static void addDeadline(Task[] tasks, String line) {
        String[] descriptionBy = line.replaceFirst("deadline ", "").split(" /by ");
        String deadlineDescription = descriptionBy[0];
        String by = descriptionBy[1];
        tasks[Deadline.getNumberOfTasks()] = new Deadline(deadlineDescription, by);
        int deadlineId = Task.getNumberOfTasks() - 1;
        tasks[deadlineId].printNewTask();
    }

    private static void tryAddTodo(Task[] tasks, String line){
        try{
            if(line.replaceFirst("todo", "").trim().equals("")){
                throw new DukeException();
            }
            addTodo(tasks, line);
        } catch (DukeException e){
            Task.printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of a todo cannot be empty.");
            Task.printHorizontalLine();
        }
    }

    private static void addTodo(Task[] tasks, String line) {
        String todoDescription = line.replaceFirst("todo ", "");
        tasks[Todo.getNumberOfTasks()] = new Todo(todoDescription);
        int todoId = Todo.getNumberOfTasks() - 1;
        tasks[todoId].printNewTask();
    }
    private static void tryUnmarkTask(Task[] tasks, String line){
        try{
            if(line.replaceFirst("unmark", "").trim().equals("")){
                throw new DukeException();
            }
            unmarkTask(tasks, line.split(" "));
        } catch (DukeException e){
            Task.printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of an unmark cannot be empty.");
            Task.printHorizontalLine();
        }
    }
    private static void unmarkTask(Task[] tasks, String[] parsedInput) {
        int unmarkId = Integer.parseInt(parsedInput[1]) - 1;
        tasks[unmarkId].setNotDone();
        tasks[unmarkId].printUnmark();
    }

    private static void tryMarkTask(Task[] tasks, String line){
        try{
            if(line.replaceFirst("mark", "").trim().equals("")){
                throw new DukeException();
            }
            markTask(tasks, line.split(" "));
        } catch (DukeException e){
            Task.printHorizontalLine();
            System.out.println("     T_T OOPS!!! The description of a mark cannot be empty.");
            Task.printHorizontalLine();
        }
    }

    private static void markTask(Task[] tasks, String[] parsedInput) {
        int markId = Integer.parseInt(parsedInput[1]) - 1;
        tasks[markId].setDone();
        tasks[markId].printMark();
    }
}



