package parser;

import storage.Storage;
import task.*;
import task.command.*;
import ui.UI;
import exception.DukeException;

/**
 * An object that deals with anything that the user inputs so that logic
 * can be performed on them.
 */
public class Parser {

    private final String TODO = "todo";
    private final String LIST = "list";
    private final String DEADLINE = "deadline";
    private final String EVENT = "event";
    private final String BYE = "bye";
    private final String MARK = "mark";
    private final String UNMARK = "unmark";
    private final String DELETE = "delete";
    private final String FIND = "find";
    private final int SPLIT_AMOUNT = 2;
    private boolean exit = false;

    /**
     * This method calls the execute method of the commands that the user inputs.
     *
     * @param ui Object that handles all user interaction.
     * @param tasks Object that handles and tracks all tasks that the user has added.
     * @param storage Object that handles saving the user's task into the local storage.
     */
    public void executeCommands(UI ui, TaskList tasks, Storage storage) {
        while (!exit) {
            try {
                Command command = handleCommand(ui);
                command.execute(tasks, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * The method that identifies the command that the user is inputting.
     *
     * @param ui Object that handles all user interaction.
     * @return Command object based on what the user inputs.
     * @throws DukeException If user enters an invalid input.
     */
    public Command handleCommand(UI ui) throws DukeException {
        String input = ui.getInput();
        String command = getCommand(input);
        switch (command) {
        case (BYE):
            exit = true;
            return new ByeCommand();
        case (LIST):
            return new ListCommand();
        case (TODO):
            Todo todo = handleTodo(getArgument(input));
            return new TodoCommand(todo);
        case (DEADLINE):
            Deadline deadline = handleDeadline(getArgument(input));
            return new DeadlineCommand(deadline);
        case (EVENT):
            Event event = handleEvent(getArgument(input));
            return new EventCommand(event);
        case (MARK):
            int markIndex = readIndex(getArgument(input));
            return new MarkCommand(markIndex);
        case (UNMARK):
            int unmarkIndex = readIndex(getArgument(input));
            return new UnmarkCommand(unmarkIndex);
        case (DELETE):
            int deleteIndex = readIndex(getArgument(input));
            return new DeleteCommand(deleteIndex);
        case (FIND):
            String target = getArgument(input);
            return new FindCommand(target);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Gets the specific command the user inputs.
     *
     * @param input What the user inputs in the CLI.
     * @return The first word, which is the command that the user inputs.
     */
    private String getCommand(String input) {
        return input.split(" ", 2)[0].trim().toLowerCase();
    }

    /**
     * Gets the argument that the user inputs.
     *
     * @param input What the user inputs in the CLI.
     * @return The string after the first word, which is the argument.
     * @throws DukeException If the user did not input the command.
     */
    private String getArgument(String input) throws DukeException {
        String[] splitInput = input.split(" ", SPLIT_AMOUNT);
        if (splitInput.length != SPLIT_AMOUNT) {
            if (splitInput[0].equals(TODO) || splitInput[0].equals(DEADLINE) || splitInput[0].equals(EVENT)
                    || splitInput[0].equals(FIND)) {
                throw new DukeException("Missing input for command, input the command in this format: " +
                        splitInput[0] + " {TASK}");
            } else {
                throw new DukeException("Missing input for command, input the command in this format: " +
                        splitInput[0] + " {ID}");
            }
        }
        return splitInput[1];
    }

    /**
     * Creates a Todo object from the user's argument.
     *
     * @param input The argument that the user inputs.
     * @return A new Todo object based on user inputs.
     */
    public Todo handleTodo(String input) {
        return new Todo(input);
    }

    /**
     * Creates a Deadline object from the user's argument by identifying the description and timing.
     * @param input The argument the user inputs.
     * @return A new Deadline object based on the user's input.
     * @throws DukeException If the user does not input the proper argument.
     */
    public Deadline handleDeadline(String input) throws DukeException {
        String[] splitCommand = splitDeadlineTime(input);
        if (splitCommand.length != SPLIT_AMOUNT) {
            throw new DukeException("Deadline is missing a timing.");
        }
        String description = splitCommand[0];
        String by = splitCommand[1];
        return new Deadline(description, by);
    }

    /**
     * Creates an Event object from the user's argument by identifying the description and timing.
     * @param input The argument the user inputs.
     * @return A new Event object based on the user's input.
     * @throws DukeException If the user does not input the proper argument.
     */
    public Event handleEvent(String input) throws DukeException {
        String[] splitCommand = splitEventTime(input);
        if (splitCommand.length != SPLIT_AMOUNT) {
            throw new DukeException("Event is missing a timing.");
        }
        String description = splitCommand[0];
        String at = splitCommand[1];
        return new Event(description, at);
    }

    /**
     * Converts the user argument into an integer.
     *
     * @param input The argument the user inputs.
     * @return An integer based on user's input.
     * @throws DukeException If the user's argument cannot be converted into an integer.
     */
    public int readIndex(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            if (index < 1 || index > TaskList.getTaskCount()) {
                throw new DukeException("Index out of bound.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new DukeException("A valid numeric number must be entered.");
        }
    }

    /**
     * Splits the user's deadline argument into its description and time.
     *
     * @param input The argument the user inputs.
     * @return An array of strings that contain the deadline's description and time respectively.
     */
    private String[] splitDeadlineTime(String input) {
        return input.split(" /by ", SPLIT_AMOUNT);
    }

    /**
     * Splits the user's event argument into its description and time.
     *
     * @param input The argument the user inputs.
     * @return An array of strings that contain the event's description and time respectively.
     */
    private String[] splitEventTime(String input) {
        return input.split(" /at ", SPLIT_AMOUNT);
    }

}
