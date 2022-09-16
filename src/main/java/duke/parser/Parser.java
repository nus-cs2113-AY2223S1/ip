package duke.parser;

import duke.exceptions.AccessTaskOutOfBoundsException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.MissingTaskNumberException;
import duke.exceptions.UnknownCommandException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents an entity to handle and interpret user commands in order to execute the corresponding methods to reflect the program's intended behaviour.
 */
public class Parser {
    private Ui ui;
    private boolean isProgramFinished;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Resolves the user command to interpret its type and executes the relevant operations to modify the user's list of tasks according to the type.
     *
     * @param command The full line of input that the user provided to the program.
     * @param taskList The task manager handling the user's current list of tasks.
     */
    public void handleInput(String command, TaskList taskList) {
        String[] text = command.split(" ");
        String type = text[0];
        if (type.equals("mark")) {
            try {
                taskList.handleMarkAsDone(command);
            } catch (MissingTaskNumberException e) {
                ui.printMissingTaskNumberError();
            }
        } else if (type.equals("unmark")) {
            try {
                taskList.handleMarkAsUndone(command);
            } catch (MissingTaskNumberException e) {
                ui.printMissingTaskNumberError();
            }
        } else if (type.equals("delete")) {
            try {
                taskList.deleteTask(type, command);
            } catch (AccessTaskOutOfBoundsException e) {
                ui.printAccessTaskOutOfBoundsError();
                ui.printNumberOfTasks(taskList.getTasksCount());
            } catch (MissingTaskNumberException e) {
                ui.printMissingTaskNumberError();
            }
        } else if (type.equals("find")) {
            String keyword = text[1];
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for(Task task : taskList.getTasks()) {
                if(task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    matchingTasks.add(task);
                }
            }
            TaskList matchingTaskList = new TaskList(matchingTasks, matchingTasks.size());
            ui.printMatchingTasks(matchingTaskList);
        } else {
            try {
                taskList.addTask(type, command);
            } catch (EmptyDescriptionException e) {
                ui.printEmptyDescriptionError();
            } catch (UnknownCommandException e) {
                ui.printUnknownCommandError();
            }
        }
        try {
            Storage.saveFile(taskList.getTasks());
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
        }
    }

    /**
     * Detects if the user has indicated to terminate the program or print their current list of tasks.
     * If other types of user commands were given, the user command is resolved further and executed thereafter.
     *
     * @param command The full line of input that the user provided to the program.
     * @param taskList The task manager handling the user's current list of tasks.
     */
    public void parse(String command, TaskList taskList) {
        if(command.equals("bye")) {
            this.isProgramFinished = true;
        } else if(command.equals("list")) {
            ui.printTasks(taskList);
        } else {
            handleInput(command, taskList);
        }
    }

    public boolean getTerminationStatus() {
        return this.isProgramFinished;
    }
}
