package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.InvalidArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Duke {
    static final String LOGO = "$$$$$$$\\                  $$\\           \n" +
            "$$  __$$\\                 $$ |          \n" +
            "$$ |  $$ |$$\\   $$\\  $$$$$$$ | $$$$$$\\  \n" +
            "$$ |  $$ |$$ |  $$ |$$  __$$ |$$  __$$\\ \n" +
            "$$ |  $$ |$$ |  $$ |$$ /  $$ |$$$$$$$$ |\n" +
            "$$ |  $$ |$$ |  $$ |$$ |  $$ |$$   ____|\n" +
            "$$$$$$$  |\\$$$$$$  |\\$$$$$$$ |\\$$$$$$$\\ \n" +
            "\\_______/  \\______/  \\_______| \\_______|";
    static final String GREET_MESSAGE = "I am Dude.\n" +
            "Type something in, dude.";
    static final String BYE_MESSAGE = "Catch you later, dude.";

    static List<Task> tasks = new ArrayList<>();

    public static void runCommand(String command) {
        String[] args = command.split(" ", 2);
        String action = args[0];
        switch (action) {
        case "bye":
            System.out.println(BYE_MESSAGE);
            System.exit(0);
            // no break needed, the code has already exited
        case "list":
            listTasks();
            break;
        case "mark":
            // fallthrough
        case "unmark":
            boolean isDone = action.equals("mark");
            if (args.length < 2) {
                System.out.println("Not enough arguments, dude.");
                break;
            }
            int index;
            try {
                index = Integer.parseInt(args[1].trim()) - 1;
                setTaskStatus(index, isDone);
            } catch (NumberFormatException exception) {
                // user either didn't enter a number or number was too big for integer
                // not a nice way to use exceptions but alternatives are cumbersome
                System.out.println("That's not even a number, dude.");
            }
            break;
        case "todo":
            // fallthrough
        case "deadline":
            // fallthrough
        case "event":
            if (args.length < 2) {
                System.out.println("Not enough arguments, dude.");
                break;
            }
            String taskData = args[1];
            addTask(action, taskData);
            break;
        case "delete":
            try {
                index = Integer.parseInt(args[1].trim()) - 1;
                deleteTask(index);
            } catch (NumberFormatException exception) {
                System.out.println("That's not even a number, dude.");
            }
            break;
        default:
            System.out.println("I don't recognise that command, dude.");
            break;
        }
    }

    private static void addTask(String taskType, String taskData) {
        Task task;
        try {
            switch (taskType) {
            case "todo":
                task = createTodo(taskData);
                break;
            case "deadline":
                task = createDeadline(taskData);
                break;
            case "event":
                task = createEvent(taskData);
                break;
            default:
                System.out.println("How did we get here?"); // this should have been validated by runCommand()
                return; // instead of break, we want to stop function execution
            }
        } catch (InvalidArgumentException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        tasks.add(task);
        System.out.println("OK, dude, I've added this task: ");
        System.out.println(task);
        System.out.println("You have " + tasks.size() + " tasks in the list.");
    }

    private static Todo createTodo(String taskData) {
        return new Todo(taskData);
    }

    private static Task createEvent(String taskData) throws InvalidArgumentException {
        if (!taskData.contains("/at")) {
            throw new InvalidArgumentException("Missing /at parameter");
        }
        String[] inputArr = taskData.split("/at");
        String description = inputArr[0].trim();
        String at = inputArr[1].trim();
        return new Event(description, at);
    }


    private static Deadline createDeadline(String taskData) throws InvalidArgumentException {
        if (!taskData.contains("/by")) {
            throw new InvalidArgumentException("Missing /by parameter");
        }
        String[] inputArr = taskData.split("/by");
        String description = inputArr[0].trim();
        String by = inputArr[1].trim();
        return new Deadline(description, by);
    }

    private static void setTaskStatus(int taskIndex, boolean isDone) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            System.out.println("There's no task with that number, dude.");
            return;
        }

        Task task = tasks.get(taskIndex);
        task.setDone(isDone);
        System.out.println("Okay, dude, I've marked this task as " + (isDone ? "done" : "not done yet") + ": ");
        System.out.println(task.getDescription());
    }

    private static void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.%s\n", i + 1, task);
        }
    }

    private static void deleteTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            System.out.println("There's no task with that number, dude.");
            return;
        }

        Task task = tasks.get(taskIndex);
        System.out.println("Deleting task: " + task.getDescription());
        tasks.remove(taskIndex);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {

        System.out.println(LOGO);
        System.out.println();
        System.out.println(GREET_MESSAGE);
        System.out.println();

        Scanner input = new Scanner(System.in);

        while (true) {
            runCommand(input.nextLine());
        }

    }
}
