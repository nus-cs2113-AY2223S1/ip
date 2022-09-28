package parser;

import storage.Storage;
import task.*;
import task.command.*;
import ui.UI;
import exception.DukeException;

public class Parser {

    private final String TODO = "todo";
    private final String LIST = "list";
    private final String DEADLINE = "deadline";
    private final String EVENT = "event";
    private final String BYE = "bye";
    private final String MARK = "mark";
    private final String UNMARK = "unmark";
    private final String DELETE = "delete";
    private final String FIND = "FIND";
    private final int SPLIT_AMOUNT = 2;
    private boolean exit = false;

    public void executeCommands(UI ui, TaskList tasks, Storage storage) {
        while (!exit) {
            try {
                Command command = handleCommand(ui);
                command.execute(tasks, ui, storage);
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

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
            Todo todo = handleTodo(getAction(input));
            return new TodoCommand(todo);
        case (DEADLINE):
            Deadline deadline = handleDeadline(getAction(input));
            return new DeadlineCommand(deadline);
        case (EVENT):
            Event event = handleEvent(getAction(input));
            return new EventCommand(event);
        case (MARK):
            int markIndex = readIndex(getAction(input));
            return new MarkCommand(markIndex);
        case (UNMARK):
            int unmarkIndex = readIndex(getAction(input));
            return new UnmarkCommand(unmarkIndex);
        case (DELETE):
            int deleteIndex = readIndex(getAction(input));
            return new DeleteCommand(deleteIndex);
        default:
            return new InvalidCommand();
        }
    }


    private String getCommand(String input) {
        return input.split(" ", 2)[0].trim();
    }

    private String getAction(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length != 2) {
            if (splitInput[1].equals(TODO) || splitInput[1].equals(DEADLINE) || splitInput[1].equals(EVENT)
                    ||splitInput[1].equals(FIND)) {
                throw new DukeException("Missing input for command, input the command in this format: " +
                        splitInput[0] + "{TASK}");
            } else {
                throw new DukeException("Missing input for command, input the command in this format: " +
                        splitInput[0] + "{ID}");
            }

        }
        return splitInput[1];
    }

    public Todo handleTodo(String input) {
        return new Todo(input);
    }


    public Deadline handleDeadline(String input) throws DukeException {
        String[] splitCommand = splitDeadlineTime(input);
        if (splitCommand.length != SPLIT_AMOUNT) {
            throw new DukeException("Deadline is missing a timing.");
        }
        String description = splitCommand[0];
        String by = splitCommand[1];
        return new Deadline(description, by);
    }

    public Event handleEvent(String input) throws DukeException {
        String[] splitCommand = splitEventTime(input);
        if (splitCommand.length != SPLIT_AMOUNT) {
            throw new DukeException("Event is missing a timing.");
        }
        String description = splitCommand[0];
        String at = splitCommand[1];
        return new Event(description, at);
    }


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

    private String[] splitDeadlineTime(String input) throws DukeException {
        return input.split(" /by ", SPLIT_AMOUNT);
    }

    private String[] splitEventTime(String input) throws DukeException {
        return input.split(" /at ", SPLIT_AMOUNT);
    }

}
