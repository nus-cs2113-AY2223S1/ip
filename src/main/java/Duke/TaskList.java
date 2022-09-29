package Duke;

import Duke.Commands.*;
import Duke.Exceptions.InvalidCommandFormatException;
import Duke.Exceptions.TaskListEmptyException;
import Duke.Exceptions.TaskNumberOutOfBoundsException;
import Duke.Exceptions.TaskNumberNotNumberException;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.util.ArrayList;

/**
 * Stores user's tasks in a list and
 * contains methods that can be executed on the list based on user's command.
 */
public class TaskList extends ArrayList<Task> {
    public static ArrayList<Task> tasksList = new ArrayList<>();
    protected static int taskNumberOfInterest;

    /**
     * Returns the number of tasks in the list
     */
    public static int getTasksListSize() {
        return tasksList.size();
    }

    /**
     * Returns the task number of the task that user is looking at for that specific command
     */
    public static int getTaskNumberOfInterest() {
        return taskNumberOfInterest;
    }

    /**
     * Adds user's specified task to the list
     * @param task the task that user specified to be added to the list
     */
    public void addToTasksList(Task task) {
        tasksList.add(task);
    }

    /**
     * Adds user's specified todo task to the list
     * @param toDoInput user's input to add todo task (todo (task))
     * @throws InvalidCommandFormatException if the command is invalid
     */
    public static void addTodoTask(String[] toDoInput) {
        try {
            if (toDoInput.length < 2) {
                throw new InvalidCommandFormatException();
            }
            Todo newTodo = new Todo(toDoInput[1], 'T');
            tasksList.add(newTodo);
            taskNumberOfInterest = getTasksListSize() - 1;
            Ui.printAddTaskText(newTodo);
        } catch (InvalidCommandFormatException e) {
            Ui.printCorrectFormatText(AddTodoCommand.TODO_COMMAND);
        }
    }

    /**
     * Adds user's specified deadline task to the list
     * @param deadlineInput user's input to add deadline task (deadline (task) /by (time))
     */
    public static void addDeadlineTask(String[] deadlineInput) {
        try {
            String[] DescriptionWithTime = deadlineInput[1].split("/by ", 2);
            Deadline newDeadlineTask = new Deadline(DescriptionWithTime[0], 'D', DescriptionWithTime[1]);
            tasksList.add(newDeadlineTask);
            Ui.printAddTaskText(newDeadlineTask);
            taskNumberOfInterest = getTasksListSize() - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printCorrectFormatText(AddDeadlineCommand.DEADLINE_COMMAND);
        }
    }

    /**
     * Adds user's specified event task to the list
     * @param eventInput user's input to add event task (event (task) /at (time))
     */
    public static void addEventTask(String[] eventInput) {
        try {
            String[] DescriptionWithTime = eventInput[1].split("/at ", 2);
            Event newEvent = new Event(DescriptionWithTime[0], 'E', DescriptionWithTime[1]);
            tasksList.add(newEvent);
            Ui.printAddTaskText(newEvent);
            taskNumberOfInterest = getTasksListSize() - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printCorrectFormatText(AddEventCommand.EVENT_COMMAND);
        }
    }

    /**
     * Checks whether the task has been marked or unmarked previously
     * and prints the message accordingly to user's new input.
     * @param newMark boolean value of whether the user wants to mark or unmark the task
     * @throws TaskNumberOutOfBoundsException if the task number specified is not in the list
     */
    public void checkMarkTask(boolean newMark) throws TaskNumberOutOfBoundsException, TaskNumberNotNumberException {
        Ui.printMarkTaskText(newMark);
    }

    /**
     * Marks the specified task of given task number that user inputs
     * @param taskInput user's input to mark the specified task (mark (task number))
     * @throws InvalidCommandFormatException if the command format is invalid
     * @throws TaskListEmptyException if the task list is empty, and hence no task to mark
     * @throws TaskNumberOutOfBoundsException if the task number specified is not in the list
     */
    public void doMarkTask(String[] taskInput) {
        try {
            if (taskInput.length == 1) {
                throw new InvalidCommandFormatException();
            } if (getTasksListSize() == 0) {
                throw new TaskListEmptyException();
            }
            int taskNumber =  Integer.parseInt(taskInput[1]) - 1;
            taskNumberOfInterest = taskNumber;
            if (taskNumber >= getTasksListSize()) {
                throw new TaskNumberOutOfBoundsException();
            }
            checkMarkTask(true);
            setTaskDoneStatus(taskNumber, true);
            Ui.printUpdatedTaskMarkAndDescription();
            Ui.printHorizontalLine();
        } catch (NumberFormatException e) {
            Ui.printTaskNumberNotIntegerError();
        } catch (InvalidCommandFormatException e) {
            Ui.printCorrectFormatText(MarkTaskCommand.MARK_COMMAND);
        } catch (TaskNumberNotNumberException | TaskListEmptyException | TaskNumberOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    /**
     * Unmarks the specified task of given task number that user inputs
     * @param taskInput user's input to unmark the specified task (unmark (task number))
     * @throws InvalidCommandFormatException if the command format is invalid
     * @throws TaskListEmptyException if the task list is empty, and hence no task to mark
     * @throws TaskNumberOutOfBoundsException if the task number specified is not in the list
     */
    public void doUnmarkTask(String[] taskInput) {
        try {
            if (taskInput.length == 1) {
                throw new InvalidCommandFormatException();
            }
            if (getTasksListSize() == 0) {
                throw new TaskListEmptyException();
            }
            int taskNumber = Integer.parseInt(taskInput[ 1 ]) - 1;
            taskNumberOfInterest = taskNumber;
            if (taskNumber >= getTasksListSize()) {
                throw new TaskNumberOutOfBoundsException();
            }
            checkMarkTask(false);
            setTaskDoneStatus(taskNumber, false);
            Ui.printUpdatedTaskMarkAndDescription();
            Ui.printHorizontalLine();
        } catch (NumberFormatException e) {
            Ui.printTaskNumberNotIntegerError();
        } catch(InvalidCommandFormatException e) {
            Ui.printCorrectFormatText(UnmarkTaskCommand.UNMARK_COMMAND);
        } catch (TaskNumberNotNumberException | TaskListEmptyException | TaskNumberOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    /**
     * Deletes the specified task of given task number that user inputs
     * @param deleteInput user's input to delete the specified task (delete (task number))
     * @throws InvalidCommandFormatException if the command format is invalid
     * @throws TaskListEmptyException if the task list is empty, and hence no task to mark
     * @throws TaskNumberOutOfBoundsException if the task number specified is not in the list
     */
    public void doDeleteTask(String[] deleteInput) {
        try {
            if (deleteInput.length < 2) {
                throw new InvalidCommandFormatException();
            }
            if (getTasksListSize() == 0) {
                throw new TaskListEmptyException();
            }
            int taskNumber = Integer.parseInt(deleteInput[ 1 ]) - 1;
            taskNumberOfInterest = taskNumber;
            if (taskNumber >= getTasksListSize()) {
                throw new TaskNumberOutOfBoundsException();
            }
            Task taskToBeRemoved = tasksList.get(taskNumber);
            tasksList.remove(taskNumber);
            Ui.printDeleteTaskText(taskToBeRemoved);
        } catch (NumberFormatException e) {
            Ui.printTaskNumberNotIntegerError();
        } catch (InvalidCommandFormatException e) {
            Ui.printCorrectFormatText(DeleteCommand.DELETE_COMMAND);
        } catch (TaskListEmptyException | TaskNumberOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    /**
     * Finds tasks with relevant keyword that user inputs
     * @param findInput user's keyword input to find tasks (find (keyword))
     * @throws InvalidCommandFormatException if the command format is invalid
     * @throws TaskListEmptyException if the task list is empty, and hence no task to mark
     * @throws TaskNumberOutOfBoundsException if the task number specified is not in the list
     */
    public void findTask(String[] findInput) {
        try {
            if (findInput.length < 2) {
                throw new InvalidCommandFormatException();
            }
            if (getTasksListSize() == 0) {
                throw new TaskListEmptyException();
            }
            String keyword = findInput[1];
            Ui.printTasksWithKeyword(keyword);
            Ui.printHorizontalLine();
        } catch (InvalidCommandFormatException e) {
            Ui.printCorrectFormatText(FindCommand.FIND_COMMAND);
        } catch (TaskListEmptyException e) {
            System.out.println(e);
        }
    }

    /**
     * Loads task from task list to data file
     * @param taskNumber task number of the task to be loaded to data file
     */
    public String loadTaskToDataFile(int taskNumber) {
        return tasksList.get(taskNumber).taskDataFileText() + "\n";
    }

    /**
     * Sets the done status of the task
     * @param taskNumber task number of the task to be set
     * @param setDone boolean value of whether the task is to be set to done or not done
     */
    public void setTaskDoneStatus(int taskNumber, boolean setDone) {
        tasksList.get(taskNumber).setDone(setDone);
    }
}