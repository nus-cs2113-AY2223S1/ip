package Duke;

import Duke.Exceptions.InvalidCommandFormatException;
import Duke.Exceptions.TaskListEmptyException;
import Duke.Exceptions.TaskNumberOutOfBoundsException;
import Duke.Exceptions.TaskNumberNotNumberException;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasksList = new ArrayList<>();
    protected static int taskNumberOfInterest;

    public static int getTasksListSize() {
        return tasksList.size();
    }

    public static int getTaskNumberOfInterest() {
        return taskNumberOfInterest;
    }

    public void addToTasksList(Task task) {
        tasksList.add(task);
    }

    public void addTodoTask(String[] toDoInput) {
        try {
            if (toDoInput.length < 2) {
                throw new InvalidCommandFormatException();
            }
            Todo newTodo = new Todo(toDoInput[1], 'T');
            tasksList.add(newTodo);
            taskNumberOfInterest = getTasksListSize() - 1;
            Ui.printAddTaskText(newTodo);
        } catch (InvalidCommandFormatException e) {
            Ui.printCorrectFormatText("todo");
        }
    }

    public void addDeadlineTask(String[] deadlineInput) {
        try {
            String[] DescriptionWithTime = deadlineInput[1].split("/by ", 2);
            Deadline newDeadlineTask = new Deadline(DescriptionWithTime[0], 'D', DescriptionWithTime[1]);
            tasksList.add(newDeadlineTask);
            Ui.printAddTaskText(newDeadlineTask);
            taskNumberOfInterest = getTasksListSize() - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printCorrectFormatText("deadline");
        }
    }

    public void addEventTask(String[] eventInput) {
        try {
            String[] DescriptionWithTime = eventInput[1].split("/at ", 2);
            Event newEvent = new Event(DescriptionWithTime[0], 'E', DescriptionWithTime[1]);
            tasksList.add(newEvent);
            Ui.printAddTaskText(newEvent);
            taskNumberOfInterest = getTasksListSize() - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printCorrectFormatText("event");
        }
    }


    public void checkMarkTask(boolean newMark) throws TaskNumberOutOfBoundsException, TaskNumberNotNumberException {
        try {
            Ui.printMarkTaskText(newMark);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberOutOfBoundsException();
        }
    }

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
            Ui.printCorrectFormatText("mark");
        } catch (TaskNumberNotNumberException | TaskListEmptyException | TaskNumberOutOfBoundsException e) {
            System.out.println(e);
        }
    }

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
            Ui.printCorrectFormatText("unmark");
        } catch (TaskNumberNotNumberException | TaskListEmptyException | TaskNumberOutOfBoundsException e) {
            System.out.println(e);
        }
    }

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
            Ui.printCorrectFormatText("delete");
        } catch (TaskListEmptyException | TaskNumberOutOfBoundsException e) {
            System.out.println(e);
        }
    }

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
            Ui.printCorrectFormatText("find");
        } catch (TaskListEmptyException e) {
            System.out.println(e);
        }
    }

    public String loadTaskToDataFile(int taskNumber) {
        return tasksList.get(taskNumber).taskDataFileText() + "\n";
    }

    public void setTaskDoneStatus(int taskNumber, boolean setDone) {
        tasksList.get(taskNumber).setDone(setDone);
    }
}