import java.util.Scanner;

public class Duke {




    public static void showWelcomeMessage() {
        // Pikachu logo to show in front
        String logo =   "⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿\n";

        System.out.println(logo);
        System.out.println("Harrlo! This is Wilson. What do you want?");
    }
    public static void markOrUnmarkTask(Task[] tasks, String command) {
        String markOrUnmark = command.split(" ")[0];
        int taskNumber = Integer.parseInt(command.split(" ")[1]);
        tasks[taskNumber - 1].setStatusIcon(markOrUnmark);
    }

    public static void addTask(String command, Task[] tasks, int index) {
        boolean isTodo = command.contains("todo");
        boolean isEvent = command.contains("event");
        boolean isDeadline = command.contains("deadline");

        if (isTodo) {
            tasks[index] = new Todo(command.replace("todo ", ""));
        } else if (isEvent) {
            tasks[index] = new Event(command.replace("event ", ""));
        } else if (isDeadline) {
            tasks[index] = new Deadline(command.replace("deadline ", ""));
        }

        index += 1;
        if (index > 1) {
            System.out.println("You now have " + index + " tasks.");
        } else {
            System.out.println("You now have " + index + " task.");
        }

    }
    public static void farewellMessage() {
        System.out.println("Bye bye!");
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int index = 0;
        String command = "";
        boolean isList = command.contains("list");
        boolean isAdd = command.contains("todo") || command.contains("deadline") || command.contains("event");
        boolean isGoodbye = command.contains("bye");
        boolean isMarkedOrUnmarked = command.contains("mark") || command.contains("unmark");




        showWelcomeMessage();

        Scanner in = new Scanner(System.in);

        // Loop that stops only when user enter "bye"
        do{
            command = in.nextLine();
            isList = command.contains("list");
            isAdd = command.contains("todo") || command.contains("deadline") || command.contains("event");
            isGoodbye = command.contains("bye");
            isMarkedOrUnmarked = command.contains("mark") || command.contains("unmark");


            // If user select list, show all the task
            if (isList) {
                for (int i = 0; i < index; i += 1) {
                    tasks[i].printTask(i);
                }
            } else if (isMarkedOrUnmarked) {
                markOrUnmarkTask(tasks, command);
            } else if (isAdd) {
                addTask(command, tasks, index);
            }

        } while (!isGoodbye);
        // Goodbye message
        farewellMessage();
    }
}
