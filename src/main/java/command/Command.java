package command;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;
import exception.DukeException;
import java.util.ArrayList;

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
     * Executes the command with the arguments
     * 
     * @param taskList the task list
     * @return the flag whether the program should end
     * @throws DukeException if something wrong happens in the execution
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
            break;
        case DEADLINE:
            Deadline deadline = new Deadline(commandArgs);
            taskList.add(deadline);
            response = taskList.get(taskList.size()-1).getResponse();
            Ui.printTaskResponse(response, taskList.size());
            break;
        case EVENT:
            Event event = new Event(commandArgs);
            taskList.add(event);
            response = taskList.get(taskList.size()-1).getResponse();
            Ui.printTaskResponse(response, taskList.size());
            break;
        case LIST:
            Ui.showAllTasks(taskList);
            break;
        case DELETE:
            index = getValidIndex(taskList.size(),commandArgs);
            response = taskList.get(index-1).getResponse();
            taskList.remove(index-1);
            Ui.printDeleteResponse(response, taskList.size());
            break;
        case MARK:
            index = getValidIndex(taskList.size(),commandArgs);
            taskList.get(index-1).setTaskState(true);
            response = taskList.get(index-1).getResponse();
            Ui.printMarkResponse(response);
            break;
        case UNMARK:
            index = getValidIndex(taskList.size(),commandArgs);
            taskList.get(index-1).setTaskState(false);
            response = taskList.get(index-1).getResponse();
            Ui.printUnmarkResponse(response);
            break;
        case FIND:
            Ui.printTaskByKeyword(taskList,commandArgs);
            break;
        case BYE:
            Ui.showExitMessage();
            isProgramEnd = true;
            break;
        default:
            throw new DukeException(DukeException.TASK_TYPE_ERROR);
        }
        return isProgramEnd;
    }

    /**
     * Gets the valid index as the parameter of some commands
     * 
     * @param taskListLength the length of the task list
     * @param input the command arg where the index is fetched 
     * @return the valid index
     * @throws DukeException if index is invalid
     */
    private int getValidIndex(int taskListLength, String input) throws DukeException{
        Integer index;
        try {
            index = Integer.valueOf(commandArgs);
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.INDEX_PARSE_ERROR);
        }
        if (!isValidIndex(taskListLength,index)) {
            throw new DukeException(DukeException.INDEX_OUT_OF_BOUND_ERROR);
        }
        return index;
    }

    private boolean isValidIndex(int taskListLength,int index){
        if (index <= taskListLength && index > 0){
            return true;
        }
        return false;
    }
}
