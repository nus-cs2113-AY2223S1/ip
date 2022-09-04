import java.util.Scanner;


public class Duke {
    public enum TaskType {
        LIST,
        MARK,
        ADD,
        ERROR
    }
    public static final int maxLength = 100;
    private static Task[] tasks = new Task[maxLength];
    public static void showWelcomeMessage() {
        // Pikachu logo to show in front
        final String logo =
                        "⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿\n" +
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
        System.out.println("{\\__／}\n" +
                "(• w •）\n" +
                "／ >\uDDA4 Hey! How can I help you?");
    }
    public static void markOrUnmarkTask(String command) {
        String markOrUnmark = command.split(" ")[0];
        int taskNumber = Integer.parseInt(command.split(" ")[1]);
        tasks[taskNumber - 1].setStatusIcon(markOrUnmark);
        tasks[taskNumber - 1].printTask(taskNumber - 1);
    }
    public static void printCompleteMessage(int numOfItem) {
        if (numOfItem > 1) {
            System.out.println("You now have " + numOfItem + " tasks.");
        } else {
            System.out.println("You now have " + numOfItem + " task.");
        }
    }
    public static void addTask(String command, int index) {
        boolean isTodo = command.contains("todo");
        boolean isEvent = command.contains("event");
        boolean isDeadline = command.contains("deadline");


        try {

            if (isTodo) {
                tasks[index] = new Todo(command.replace("todo ", ""));
            } else if (isEvent) {
                tasks[index] = new Event(command.replace("event ", ""));
            } else if (isDeadline) {
                tasks[index] = new Deadline(command.replace("deadline ", ""));
            }
            int numOfItem = index + 1;
            printCompleteMessage(numOfItem);
        } catch (EmptyDescriptionException emptyException) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        }
    }
    public static void farewellMessage() {
        System.out.println("Bye bye!");
    }
    public static void printAllTasks(int index) {
        for (int i = 0; i < index; i += 1) {
            tasks[i].printTask(i);
        }
    }
    public static TaskType identifyTask(String command) {
        boolean isList = command.contains("list");
        boolean isAdd = command.contains("todo") || command.contains("deadline") || command.contains("event");
        boolean isMarkedOrUnmarked = command.contains("mark") || command.contains("unmark");

        if (isList) {
            return TaskType.LIST;
        } else if (isMarkedOrUnmarked) {
            return TaskType.MARK;
        } else if (isAdd) {
            return TaskType.ADD;
        } else {
            return TaskType.ERROR;
        }


    }


    public static void main(String[] args) {
        int index = 0;
        boolean isGoodbye;
        String command;

        showWelcomeMessage();

        Scanner in = new Scanner(System.in);

        // Loop that stops only when user enter "bye"
        do {
            command = in.nextLine();
            isGoodbye = command.contains("bye");

            // If user select list, show all the task
            try {
                TaskType taskType = identifyTask(command);
                switch (taskType) {
                case MARK:
                    markOrUnmarkTask(command);
                    break;
                case LIST:
                    printAllTasks(index);
                    break;
                case ADD:
                    addTask(command, index);
                    index += 1;
                    break;
                case ERROR:
                    throw new IncorrectCommandException();
                }
            } catch (IncorrectCommandException incorrectCommand) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } while (!isGoodbye);
        // Goodbye message
        farewellMessage();
    }
}
