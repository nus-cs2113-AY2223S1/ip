package duke;

import duke.command.*;
import duke.exception.InvalidCommandTypeException;

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


    public static Command parse(String rawInput) throws Exception {
        String[] tokens = rawInput.split("[ \t]+");
        String commandType = tokens[0];
        String rawArguments = tokens.length > 1 ? rawInput.substring(commandType.length()).trim() : "";

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
            command = new CommandToDo(rawArguments);
            break;
        case "deadline":
            command = new CommandDeadline(rawArguments);
            break;
        case "event":
            command = new CommandEvent(rawArguments);
            break;
        case "delete":
            command = new CommandDelete(rawArguments);
            break;
        default:
            throw new InvalidCommandTypeException();
        }
        command.verifyAndParse();
        return command;
    }


    public static void main(String[] args) {
        String input, output;
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        output = "Hello! I'm Duckymomo\n"
                + "What can I do for you?";

        formatAndPrint(output);

        input = sc.nextLine();
        while (true) {
            Command command;
            try {
                command = parse(input);
            } catch (Exception e) {
                formatAndPrint(e.toString());
                input = sc.nextLine();
                continue;
            }

            switch (command.getCommandType()) {
            case EXIT:
                formatAndPrint("Byeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                return;
            case LIST:
                output = taskManager.getAllTasks();
                break;
            case TODO:
                output = taskManager.addToDo(((CommandToDo) command).getDescription());
                break;
            case MARK:
                output = taskManager.markTask(((CommandMark) command).getTaskNum());
                break;
            case UNMARK:
                output = taskManager.unmarkTask(((CommandUnmark) command).getTaskNum());
                break;
            case DEADLINE:
                CommandDeadline commandDeadline = (CommandDeadline) command;
                output = taskManager.addDeadline(commandDeadline.getDescription(), commandDeadline.getDate());
                break;
            case EVENT:
                CommandEvent commandEvent = (CommandEvent) command;
                output = taskManager.addEvent(commandEvent.getDescription(), commandEvent.getDate());
                break;
            case DELETE:
                output = taskManager.deleteTask(((CommandDelete) command).getTaskNum());
                break;
            default:
                output = "Error, major bug";
                break;
            }

            formatAndPrint(output);
            input = sc.nextLine();
        }

    }
}
