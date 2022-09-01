import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        case "unmark": {
            boolean isDone = action.equals("mark");
            if (args.length < 2) {
                System.out.println("Not enough arguments, dude.");
                break;
            }
            int index;
            try {
                index = Integer.parseInt(args[1]) - 1;
            } catch (NumberFormatException e) {
                // user either didn't enter a number or number was too big for integer
                // not a nice way to use exceptions but alternatives are cumbersome
                System.out.println("Invalid argument, dude.");
                break;
            }
            setTaskStatus(index, isDone);
            break;
        }
        default: {
            addTask(command);
            break;
        }
        }
    }

    private static void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("added: " + description);
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
            String checkmark = task.isDone() ? "X" : " ";
            System.out.printf("%d.[%s] %s\n", i + 1, checkmark, task.getDescription());
        }
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
