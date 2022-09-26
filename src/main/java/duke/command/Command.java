package duke.command;

import duke.Message;
import duke.task.TaskList;
import duke.task.model.Deadline;
import duke.task.model.Event;
import duke.task.model.Todo;

public class Command {
    private String command;
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

    public void execute(CommandMenu commandMenu, TaskList taskList) {
        switch(command) {
        case CommandMenu.HELP_COMMAND:
            commandMenu.display();
            break;
        case CommandMenu.LIST_COMMAND:
            taskList.list();
            break;
        case CommandMenu.EXIT_COMMAND:
            this.isExit = true;
            break;
        case CommandMenu.MARK_COMMAND:
            taskList.markTaskAsDone(taskNumber);
            break;
        case CommandMenu.UNMARK_COMMAND:
            taskList.markTaskAsUndone(taskNumber);
            break;
        case CommandMenu.DELETE_COMMAND:
            taskList.delete(taskNumber);
            break;
        case CommandMenu.ADD_TODO_COMMAND:
            Todo todo = new Todo(description);
            taskList.add(todo);
            break;
        case CommandMenu.ADD_DEADLINE_COMMAND:
            Deadline deadline = new Deadline(description, datetime);
            taskList.add(deadline);
            break;
        case CommandMenu.ADD_EVENT_COMMAND:
            Event event = new Event(description, datetime);
            taskList.add(event);
            break;
        case "":
            System.out.println(Message.EMPTY_INPUT_MESSAGE + " " + Message.HELP_MESSAGE);
            break;
        case CommandMenu.INVALID_COMMAND:
            System.out.println(Message.INVALID_COMMAND_MESSAGE + " " + Message.HELP_MESSAGE);
            break;
        }
    }

    public boolean isExit() {
        return this.isExit;
    }
}
