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
    protected static ArrayList<Task> tasksList = new ArrayList<>();
    protected static int taskNumberOfInterest;

    public int getTasksListSize() {
        return tasksList.size();
    }

    public int getTaskNumberOfInterest() {
        return taskNumberOfInterest;
    }

    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void printAddTaskText(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.taskStatusWithDescriptionText());
        System.out.println("Now you have " + getTasksListSize() + " tasks in the list.");
        printHorizontalLine();
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
            printAddTaskText(newTodo);
            taskNumberOfInterest = getTasksListSize() - 1;
        } catch (InvalidCommandFormatException e) {
            System.out.println("The command should be 'todo (task name)'.");
        }
    }

    public void addDeadlineTask(String[] deadlineInput) {
        try {
            String[] DescriptionWithTime = deadlineInput[1].split("/by ", 2);
            Deadline newDeadlineTask = new Deadline(DescriptionWithTime[0], 'D', DescriptionWithTime[1]);
            tasksList.add(newDeadlineTask);
            printAddTaskText(newDeadlineTask);
            taskNumberOfInterest = getTasksListSize() - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            String correctFormatMessage = "The command should be 'deadline (task name) /by (deadline)'.";
            System.out.println(correctFormatMessage);
        }
    }

    public void addEventTask(String[] eventInput) {
        try {
            String[] DescriptionWithTime = eventInput[1].split("/at ", 2);
            Event newEvent = new Event(DescriptionWithTime[0], 'E', DescriptionWithTime[1]);
            tasksList.add(newEvent);
            printAddTaskText(newEvent);
            taskNumberOfInterest = getTasksListSize() - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            String correctFormatMessage = "The command should be 'event (task name) /by (event date)'.";
            System.out.println(correctFormatMessage);
        }
    }


    public void checkMarkTask(int taskNumber, boolean newMark) throws TaskNumberOutOfBoundsException, TaskNumberNotNumberException {
        try {
            String previousIcon = tasksList.get(taskNumber).getStatusIcon();
            if (previousIcon == "X") {
                if (newMark) {
                    System.out.println("This task has already been marked!");
                } else {
                    System.out.println("OK, I've marked this task as not done yet:");
                }
            } else {
                if (!newMark) {
                    System.out.println("This task has already been unmarked!");
                } else {
                    System.out.println("Nice! I've marked this task as done:");
                }
            }
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
            checkMarkTask(taskNumber, true);
            setTaskDoneStatus(taskNumber, true);
            String newIcon = tasksList.get(taskNumber).getStatusIcon();
            System.out.println("[" + newIcon + "] " + tasksList.get(taskNumber).description);
            printHorizontalLine();
        } catch (NumberFormatException e) {
            System.out.println("Task Number should be an integer!");
        } catch (InvalidCommandFormatException e) {
            String correctFormatMessage = "The command should be 'mark (task number)'.";
            System.out.println(correctFormatMessage);
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
            checkMarkTask(taskNumber, false);
            setTaskDoneStatus(taskNumber, false);
            String newIcon = tasksList.get(taskNumber).getStatusIcon();
            System.out.println("[" + newIcon + "] " + tasksList.get(taskNumber).description);
            printHorizontalLine();
        } catch (NumberFormatException e) {
            System.out.println("Task Number should be an integer!");
        } catch(InvalidCommandFormatException e) {
            String correctFormatMessage = "The command should be 'unmark (task number)'.";
            System.out.println(correctFormatMessage);
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
            printDeleteTaskText(taskToBeRemoved);
        } catch (NumberFormatException e) {
            System.out.println("Task Number should be an integer!");
        } catch (InvalidCommandFormatException e) {
            System.out.println("The command should be 'delete (task number)'.");
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
            String taskToFind = findInput[1];
            int count = 1;
            for (Task task : tasksList) {
                if (task.description.contains(taskToFind)) {
                    System.out.println(count + ". " + task.taskStatusWithDescriptionText());
                    count += 1;
                }
            }
            if (count == 1 ) {
                System.out.println("There is no related task in your list.");
            }
            printHorizontalLine();
        } catch (InvalidCommandFormatException e) {
            System.out.println("The command should be 'find (task)'.");
        } catch (TaskListEmptyException e) {
            System.out.println(e);
        }
    }

    public void printTaskList() {
        try {
            if (getTasksListSize() == 0) {
                throw new TaskListEmptyException();
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < getTasksListSize(); i++) {
                    System.out.println((i + 1) + "." + tasksList.get(i).taskStatusWithDescriptionText());
                }
            }
            printHorizontalLine();
        } catch (TaskListEmptyException e) {
            System.out.println(e);
        }
    }


    public void printDeleteTaskText(Task task) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task.taskStatusWithDescriptionText());
        System.out.println("Now you have " + getTasksListSize() + " tasks in the list.");
        printHorizontalLine();
    }

    public String printTaskToDataFile(int taskNumber) {
        return tasksList.get(taskNumber).taskDataFileText() + "\n";
    }

    public void setTaskDoneStatus(int taskNumber, boolean setDone) {
        tasksList.get(taskNumber).setDone(setDone);
    }
}