package duke.command;

import duke.TaskList;
import duke.exception.EmptyDescriptionException;


public class AddCommand extends Command {


    public AddCommand(String[] commands) throws EmptyDescriptionException {
        super();
        executeCommand(commands);
    }


    @Override
    public void executeCommand(String[] commands) throws EmptyDescriptionException {
        boolean isEmpty = commands.length == 1;
        if (isEmpty) {
            throw new EmptyDescriptionException();
        } else {
            String commandType = commands[0];
            boolean isTodo = commandType.equals("todo");
            boolean isDeadline = commandType.equals("deadline");
            boolean isEvent = commandType.equals("event");

            String command = commands[1];
            if (isTodo) {
                TaskList.addTodo(command);
            } else if (isDeadline) {
                TaskList.addDeadline(command);
            } else if (isEvent) {
                TaskList.addEvent(command);
            }
        }
    }






}
