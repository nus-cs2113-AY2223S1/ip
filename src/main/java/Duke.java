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
        String[] args = command.split(" ");
        String action = args[0];
        switch (action) {
        case "bye":
            System.out.println(BYE_MESSAGE);
            System.exit(0);
            // no break needed, the code has already exited
        case "list":
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String checkmark = task.isDone() ? "X" : " ";
                System.out.printf("%d.[%s] %s\n", i + 1, checkmark, task.getDescription());
            }
            break;
        case "mark":
        case "unmark": {
            boolean isDone = action.equals("mark");
            int taskIndex = Integer.parseInt(args[1]) - 1; // add code to handle oob, missing argument

            Task task = tasks.get(taskIndex);
            task.setDone(isDone);
            System.out.println("Okay, dude, I've marked this task as " + (isDone ? "done" : "not done yet") + ": ");
            System.out.println(task.getDescription());
            break;
        }
        default: {
            Task task = new Task(command);
            tasks.add(task);
            System.out.println("added: " + command);
            break;
        }
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
