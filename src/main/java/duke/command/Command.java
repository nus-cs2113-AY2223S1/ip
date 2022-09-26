package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Todo;

public class Command {
    private final String command;
    private int taskNumber;
    private String description;
    private String datetime;
    private boolean isExit;

    public Command() {
        this.command = "test";
        this.isExit = false;
    }

    public Command(String command) {
        this.command = command;
        this.isExit = false;
    }

    public Command(String command, int taskNumber) {
        this.command = command;
        this.taskNumber = taskNumber;
        this.isExit = false;
    }

    public Command(String command, String description) {
        this.command = command;
        this.description = description;
        this.isExit = false;
    }

    public Command(String command, String description, String datetime) {
        this.command = command;
        this.description = description;
        this.datetime = datetime;
        this.isExit = false;
    }

    public void execute(TaskList taskList, Ui ui) {
        switch(command) {
        case CommandMenu.HELP_COMMAND:
            CommandMenu.display(ui);
            break;
        case CommandMenu.LIST_COMMAND:
            taskList.list(ui);
            break;
        case CommandMenu.EXIT_COMMAND:
            this.isExit = true;
            break;
        case CommandMenu.MARK_COMMAND:
            taskList.markTaskAsDone(taskNumber, ui);
            break;
        case CommandMenu.UNMARK_COMMAND:
            taskList.markTaskAsUndone(taskNumber, ui);
            break;
        case CommandMenu.DELETE_COMMAND:
            taskList.delete(taskNumber, ui);
            break;
        case CommandMenu.ADD_TODO_COMMAND:
            Todo todo = new Todo(description);
            taskList.add(todo, ui);
            break;
        case CommandMenu.ADD_DEADLINE_COMMAND:
            Deadline deadline = new Deadline(description, datetime);
            taskList.add(deadline, ui);
            break;
        case CommandMenu.ADD_EVENT_COMMAND:
            Event event = new Event(description, datetime);
            taskList.add(event, ui);
            break;
        case "":
            ui.showEmptyInputErrorMessage();
            break;
        case CommandMenu.INVALID_COMMAND:
            ui.showInvalidCommandErrorMessage();
            break;
        }
    }

    public boolean isExit() {
        return this.isExit;
    }
}
