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

public class Parser {
    private Ui ui;
    private boolean isProgramFinished;

    public Parser(Ui ui) {
        this.ui = ui;
    }

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
