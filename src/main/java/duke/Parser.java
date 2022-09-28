package duke;

public class Parser {

    public Command parseCommand(String userInput) {
        Ui ui = new Ui();
        String[] details = userInput.split(" ");
        String command = details[0];
        switch (command) {
        case "bye":
            return prepareGoodbye();
        case "deadline":
            return prepareInsertDeadlineTask();
        case "todo":
            return prepareInsertToDoTask();
        case "event":
            return prepareInsertEventTask();
        case "mark":
            return prepareMarkTask();
        case "unmark":
            return prepareUnmarkTask();
        case "list":
            return preparePrintTaskList();
        case "delete":
            return prepareDeleteTask();
        case "exit":
            return prepareToExit();
        case "find":
            return prepareToFind();
        default:
            return prepareInvalidCommand();
        }
    }

    private Command prepareToFind() {
        return Command.FIND;
    }

    private Command prepareInvalidCommand() {
        return Command.INVALID;
    }

    private static Command prepareToExit() {
        return Command.EXIT;
    }

    private static Command prepareDeleteTask() {
        return Command.DELETE;
    }

    private static Command preparePrintTaskList() {
        return Command.LIST;
    }

    private static Command prepareUnmarkTask() {
        return Command.UNMARK;
    }

    private static Command prepareMarkTask() {
        return Command.MARK;
    }

    private static Command prepareInsertEventTask() {
        return Command.EVENT;
    }

    private static Command prepareInsertToDoTask() {
        return Command.TODO;
    }

    private static Command prepareInsertDeadlineTask() {
        return Command.DEADLINE;
    }

    private static Command prepareGoodbye() {
        return Command.EXIT;
    }

}
