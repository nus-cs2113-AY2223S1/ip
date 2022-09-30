package Tasks;

import java.util.ArrayList;

import Parser.Command;
import Exception.WrongArgumentException;


/**
 * Represents the tasks that are stored in the array list.
 * Tasks include: Deadline, Todo, Event.
 * Actions includes: Mark, Unmark, Delete.
 */
public class TaskList {
    private ArrayList<Task> inputLists = new ArrayList<Task>();

    /**
     * Return the entire Task List in Array List Object.
     *
     * @return Task List in ArrayList Object.
     */
    public ArrayList<Task> getInputLists() {
        return inputLists;
    }

    /**
     * Return the formatted string description of the item given the Task number.
     *
     * @param n Task number.
     * @return Formatted string description of Task.
     */
    public String getItemFromList(int n) {
        String output = "\t";
        output += inputLists.get(n - 1).getCompleteDescription();
        return output;
    }

    /**
     * Return the formatted string description of the entire Task in the list.
     *
     * @return Formatted string description of the all the Tasks.
     */
    public String getCompleteList() {
        String output = "";
        for (int i = 0; i < inputLists.size(); i++) {
            output += Integer.toString(i + 1) + ". " + getItemFromList(i + 1);
        }
        return output;
    }

    /**
     * Returns the index-0 position of the newly added Task in the array list.
     * It also initiates a new task and append it to the existing list.
     *
     * @param input User argument.
     * @param type Type of the command entered.
     * @param isCompleted Task completion status.
     * @return index-0 position of the new Task in the list.
     */

    public int addTaskToList(String input, TaskType type, boolean isCompleted) {
        Task newItem;

        if (type == TaskType.EVENT) {
            int indexOfTime = input.indexOf("/at");
            String description = input.substring(0, indexOfTime).strip();
            String time = input.substring(indexOfTime + "/at ".length()).strip();
            newItem = new Event(description, time, isCompleted);
        } else if (type == TaskType.DEADLINE) {
            int indexOfTime = input.indexOf("/by");
            String description = input.substring(0, indexOfTime).strip();
            String time = input.substring(indexOfTime + "/by ".length()).strip();
            newItem = new Deadline(description, time, isCompleted);
        } else if (type == TaskType.TODO) {
            newItem = new Todo(input, isCompleted);
        } else {
            newItem = new Task(input, isCompleted);
        }
        inputLists.add(newItem);
        return inputLists.size() - 1;
    }

    /**
     * Remove the task from the list given the position in the list.
     *
     * @param n Position of the task in the list.
     */
    public void deleteTaskFromList(int n) {
        inputLists.remove(n - 1);
    }

    /**
     * Mark or Unmark a task as completed based on the position of the task in the list.
     *
     * @param n Position of the task in the list.
     * @param bool Completed or not Completed.
     */
    public void markCompleted(int n, boolean bool) {
        inputLists.get(n - 1).setCompleted(bool);
    }

    /**
     * Return the number of task in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getTaskListSize() {
        return inputLists.size();
    }

    /**
     * Return the task description done on the queried task, for the use of UI to output to user.
     * It will execute the action by reading in the command and the user arguments.
     *
     * @param command Type of Command queried.
     * @param userArgs User input arguments.
     * @return Task description done on the queried task.
     * @throws WrongArgumentException if the command is invalid.
     */
    public String executeCommand(Command command, String userArgs)
            throws WrongArgumentException{
        String message;
        switch(command) {
        case TODO:
            message = doTodoAction(userArgs);
            break;
        case DEADLINE:
            message = doDeadlineAction(userArgs);
            break;
        case EVENT:
            message = doEventAction(userArgs);
            break;
        case DELETE:
            message = deleteAction(userArgs);
            break;
        case MARK:
            message = doMarkAction(userArgs);
            break;
        case UNMARK:
            message = doUnmarkAction(userArgs);
            break;
        case FIND:
            message = doFindAction(userArgs);
            break;
        default:
            throw new WrongArgumentException();
        }
        return message;
    }

    /**
     * Returns the task(s) description for keyword found in the task list.
     *
     * @param item keyword.
     * @return tasks(s) description for the found keyword.
     */
    private String doFindAction(String item) {
        String output = "";
        int count = 1;
        for (Task task : inputLists) {
            if(task.getTaskName().contains(item)) {
                output += Integer.toString(count) + ". \t";
                output += task.getCompleteDescription();
                count += 1;
            }
        }
        return output;
    }

    /**
     * Return the todo task formatted description.
     *
     * @param lineInput Task description.
     * @return String formatted description of the task.
     */
    private String doTodoAction(String lineInput){
        int index = addTaskToList(lineInput, TaskType.TODO, false);
        return getItemFromList(index + 1);
    }

    /**
     * Return the deadline task formatted description.
     *
     * @param lineInput Task description.
     * @return String formatted description of the task.
     */
    private String doDeadlineAction(String lineInput) {
        int index = addTaskToList(lineInput, TaskType.DEADLINE, false);
        return getItemFromList(index + 1);
    }

    /**
     * Return the event task formatted description.
     *
     * @param lineInput Task description.
     * @return String formatted description of the task.
     */
    private String doEventAction(String lineInput) {
        int index = addTaskToList(lineInput, TaskType.EVENT, false);
        return getItemFromList(index + 1);
    }

    /**
     * Return the deleted task formatted description.
     *
     * @param lineInput Task number to delete.
     * @return String formatted description of the deleted task.
     * @throws WrongArgumentException if the lineInput is out of bounds.
     */
    private String deleteAction(String lineInput)
            throws WrongArgumentException {
        int index = Integer.parseInt(lineInput);
        if (index > getTaskListSize()) {
            throw new WrongArgumentException();
        }
        String taskDescription = getItemFromList(index);
        deleteTaskFromList(index);
        return taskDescription;
    }

    /**
     * Return the marked task formatted description.
     *
     * @param lineInput Task to mark completed.
     * @return String formatted description of the marked task.
     * @throws WrongArgumentException if the lineInput is out of bounds.
     */
    private String doMarkAction(String lineInput)
            throws WrongArgumentException {
        int itemNumber = Integer.parseInt(lineInput);
        if (itemNumber > getTaskListSize()) {
            throw new WrongArgumentException();
        }
        markCompleted(itemNumber, true);
        return getItemFromList(itemNumber);
    }

    /**
     * Return the unmarked task formatted description.
     *
     * @param lineInput Task to mark not completed.
     * @return String formatted description of the unmarked task.
     * @throws WrongArgumentException if the lineInput is out of bounds.
     */
    private String doUnmarkAction(String lineInput)
            throws WrongArgumentException {
        int itemNumber = Integer.parseInt(lineInput);
        if (itemNumber > getTaskListSize()) {
            throw new WrongArgumentException();
        }

        markCompleted(itemNumber, false);
        return getItemFromList(itemNumber);
    }
}
