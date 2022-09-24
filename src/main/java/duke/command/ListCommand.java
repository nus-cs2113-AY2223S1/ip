package duke.command;

import duke.Ui;

public class ListCommand extends Command {

    /**
     * Initialises default state of list command and execute the command.
     * @param commands User inputted command containing list command.
     */
    public ListCommand(String[] commands) {
        super();
        executeCommand(commands);
    }

    /**
     * Prints all the tasks store in the list of task.
     *
     * @param commands User inputted command conmtaining list command.
     */
    @Override
    public void executeCommand(String[] commands) {
        Ui.printAllTasks();
    }
}
