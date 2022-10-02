package command;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import exception.DukeException;
import storage.Storage;

/**
 * Defines The command class, which has the command type and command arguments
 */
public class Command {
    final protected String TODO = "todo";
    final protected String DEADLINE = "deadline";
    final protected String EVENT = "event";
    final protected String LIST = "list";
    final protected String DELETE = "delete";
    final protected String MARK = "mark";
    final protected String UNMARK = "unmark";
    final protected String FIND = "find";
    final protected String BYE = "bye";
    protected String commandType;
    protected String commandArgs;

    public Command(String commandType, String commandArgs){
        this.commandType = commandType;
        this.commandArgs = commandArgs;
    }

    /**
     * Execute the command with the arguments
     */
    public boolean execute(ArrayList<Task> taskList) throws DukeException {
        int index;
        String response;
        Boolean isProgramEnd = false;
        switch (commandType) {
        case TODO:
            Todo todo = new Todo(commandArgs);
            taskList.add(todo);
            response = taskList.get(taskList.size()-1).getResponse();
            Ui.printTaskResponse(response, taskList.size());
            updateRestorationFile(taskList);
            break;
        case DEADLINE:
            Deadline deadline = new Deadline(commandArgs);
            taskList.add(deadline);
            response = taskList.get(taskList.size()-1).getResponse();
            Ui.printTaskResponse(response, taskList.size());
            updateRestorationFile(taskList);
            break;
        case EVENT:
            Event event = new Event(commandArgs);
            taskList.add(event);
            response = taskList.get(taskList.size()-1).getResponse();
            Ui.printTaskResponse(response, taskList.size());
            updateRestorationFile(taskList);
            break;
        case LIST:
            Ui.showAllTasks(taskList);
            break;
        case DELETE:
            index = getValidIndex(taskList.size(),commandArgs);
            response = taskList.get(index-1).getResponse();
            taskList.remove(index-1);
            Ui.printDeleteResponse(response, taskList.size());
            updateRestorationFile(taskList);
            break;
        case MARK:
            index = getValidIndex(taskList.size(),commandArgs);
            taskList.get(index-1).setStringState(true);
            response = taskList.get(index-1).getResponse();
            Ui.printMarkResponse(response);
            updateRestorationFile(taskList);
            break;
        case UNMARK:
            index = getValidIndex(taskList.size(),commandArgs);
            taskList.get(index-1).setStringState(false);
            response = taskList.get(index-1).getResponse();
            Ui.printUnmarkResponse(response);
            updateRestorationFile(taskList);
            break;
        case FIND:
            Ui.printTaskByKeyword(taskList,commandArgs);
            break;
        case BYE:
            Ui.showExitMessage();
            isProgramEnd = true;
            break;
        default:
            throw new DukeException("TaskTypeError");
        }
        return isProgramEnd;
    }

    private void updateRestorationFile(ArrayList<Task> taskList){
        String[] writeLines = this.getAllTasks(taskList);
        try {
            Storage.writeToFile(writeLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] getAllTasks(ArrayList<Task> taskList){
        String[] tasks = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            tasks[i] = taskList.get(i).getStorageFormat();
        }
        return tasks;
    }

    private int getValidIndex(int taskListLength, String input) throws DukeException{
        Integer index;
        try {
            index = Integer.valueOf(commandArgs);
        } catch (NumberFormatException e) {
            throw new DukeException("IndexParseError");
        }
        if (!isValid(taskListLength,index)) {
            throw new DukeException("IndexOutOfBound");
        }
        return index;
    }

    private boolean isValid(int taskListLength,int index){
        if (index <= taskListLength && index > 0){
            return true;
        }
        return false;
    }
}
