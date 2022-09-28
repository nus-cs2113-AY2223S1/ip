package duke;

/**
 * The class which contains methods which serve
 * to interpret the inputs given by the user.
 */
public class Parser {

    /**
     * Takes in the user's input and interprets it to determine which command to execute.
     *
     * @param userInput input that is keyed into the program by the user.
     * @return Command a specified command interpreted from the user's input.
     */
    public Command parseCommand(String userInput) {
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

    private Command prepareToExit() {
        return Command.EXIT;
    }

    private Command prepareDeleteTask() {
        return Command.DELETE;
    }

    private Command preparePrintTaskList() {
        return Command.LIST;
    }

    private Command prepareUnmarkTask() {
        return Command.UNMARK;
    }

    private Command prepareMarkTask() {
        return Command.MARK;
    }

    private Command prepareInsertEventTask() {
        return Command.EVENT;
    }

    private Command prepareInsertToDoTask() {
        return Command.TODO;
    }

    private Command prepareInsertDeadlineTask() {
        return Command.DEADLINE;
    }

    private Command prepareGoodbye() {
        return Command.EXIT;
    }

}
