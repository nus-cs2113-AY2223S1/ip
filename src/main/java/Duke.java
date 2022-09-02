import java.util.Scanner;

public class Duke {

    /**
     * Formats messages to be printed on the terminal
     * @param input Message to be formatted
     */
    public static void formatAndPrint(String input) {
        StringBuilder output = new StringBuilder();
        String[] split = input.split("\n");

        System.out.println("    ____________________________________________________________");
        for (String string: split) {
            output.append(String.format("     %s\n", string));
        }
        System.out.print(output);
        System.out.println("    ____________________________________________________________");
    }


    public static Command parse(String rawInput) throws UnknownCommandException, MissingDescriptionException,
            MissingArgumentException, ExtraArgumentException {
        String[] tokens = rawInput.split("[ \t]+");
        String commandType = tokens[0];
        String rawArguments = tokens.length > 1 ? rawInput.substring(commandType.length()+1) : "";

        Command command;

        switch (commandType) {
        case "bye":
            command = new CommandExit(rawArguments);
            break;
        case "list":
            command = new CommandList(rawArguments);
            break;
        case "mark":
            command = new CommandMark(rawArguments);
            break;
        case "unmark":
            command = new CommandUnmark(rawArguments);
            break;
        case "todo":
            command = new CommandToDo();
            break;
        case "deadline":
            command = new CommandDeadline();
            break;
        case "event":
            command = new CommandEvent();
            break;
        default:
            throw new UnknownCommandException();
        }

        return command;
    }


    public static void main(String[] args) {
        String input, output;
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        output = "Hello! I'm DuckyMoMo\n"
                + "What can I do for you?";
        formatAndPrint(output);

        input = sc.nextLine();
        while (true) {
            String[] arguments = input.split("[ \t]+");

            switch (arguments[0]) {
            case "bye":
                formatAndPrint("Byeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                return;
            case "list":
                output = taskManager.getAllTasks();
                break;
            case "mark":
                output = taskManager.markTask(Integer.parseInt(arguments[1]));
                break;
            case "unmark":
                output = taskManager.unmarkTask(Integer.parseInt(arguments[1]));
                break;
            case "todo":
                output = taskManager.addToDo(input.substring("todo ".length()));
                break;
            case "deadline":
                String description = input.substring("deadline ".length(), input.indexOf("/by") - 1);
                String by = input.substring(input.indexOf("/by") + "/by ".length());
                output = taskManager.addDeadline(description, by);
                break;
            case "event":
                description =  input.substring("event ".length(), input.indexOf("/at") - 1);
                String at = input.substring(input.indexOf("/at") + "/at ".length());
                output = taskManager.addEvent(description, at);
                break;
            default:
                output = "Uh Hello, not a valid input?";
                break;
            }
            formatAndPrint(output);

            input = sc.nextLine();
        }

    }
}
