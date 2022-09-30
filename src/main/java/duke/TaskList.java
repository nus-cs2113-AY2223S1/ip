package duke;

import java.util.ArrayList;

/**
 * Contains the task list
 * and deals with operations in the list eg. add, delete
 */
public class TaskList {
    private final ArrayList<Task> tasks= new ArrayList<>();
    private int taskCounter = 0;

    public TaskList() {
    }

    /**
     * Creates a new task list using contents in file.
     * @param fileContent Contents in file in a single string.
     * @throws DukeException If file format is invalid.
     */
    public TaskList(String fileContent) throws DukeException {
        String[] unformattedTasks = fileContent.split("/");
        for (int i = 0; i < unformattedTasks.length; ++i) {
            String[] items = unformattedTasks[i].split(";");
            if (items.length == 1) {
                throw new DukeException("");
            }
            char taskType = items[0].charAt(0);
            boolean isDone = items[1].trim().charAt(0) == '1';
            String taskName = items[2].trim();
            if (taskType == 'T') {
                tasks.add(taskCounter, new ToDo(taskName, isDone, taskType));
            } else if (taskType == 'D') {
                String taskTime = items[3].trim();
                tasks.add(taskCounter, new Deadline(taskName, isDone, taskType, taskTime));
            } else if (taskType == 'E') {
                String taskTime = items[3].trim();
                tasks.add(taskCounter, new Event(taskName, isDone, taskType, taskTime));
            }
            taskCounter++;
        }
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public int getTaskCounter() {
        return taskCounter;
    }

    /**
     * Changes status of specified task from "not done" to "done".
     * @param taskID User input of task ID.
     * @throws DukeException If task has already been marked as "done".
     */
    public void markTask(int taskID) throws DukeException {
        if (tasks.get(taskID - 1).isDone()) {
            throw new DukeException(":( OOPS!!! Unable to mark as this task has already been done");
        }
        tasks.get(taskID - 1).setDone(true);
    }
    /**
     * Changes status of specified task from "done" to "not done".
     * @param taskID User input of task ID.
     * @throws DukeException If task has already been marked as "not done".
     */
    public void unmarkTask(int taskID) throws DukeException {
        if (!tasks.get(taskID - 1).isDone()) {
            throw new DukeException(":( OOPS!!! Unable to unmark as this task has not been done yet");
        }
        tasks.get(taskID - 1).setDone(false);
    }

    /**
     * Removes a task from the task list.
     * @param taskID User input of task ID.
     */
    public void deleteTask(int taskID) {
        tasks.remove(taskID - 1);
        taskCounter--;
    }

    /**
     * Creates new task, based on type of task (todo, deadline, event)
     * and adds it into the task list.
     * @param fullCommand Entire user input.
     */
    public void createTask(String fullCommand) throws DukeException{
        fullCommand = fullCommand.trim();
        char taskType = fullCommand.toUpperCase().charAt(0);    //first char is the type in uppercase
        fullCommand = Parser.removeTaskType(fullCommand);   //get taskName from command
        String taskDateTime, taskName;
        switch(taskType) {
        case 'T':
            createNewToDo(new ToDo(fullCommand, false, taskType));
            break;
        case 'D':
            try {
                taskDateTime = Parser.getTaskDateTime(fullCommand);
                taskName = Parser.getTaskName(fullCommand);
                createNewDeadline(new Deadline(taskName, false, taskType, taskDateTime));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Deadline tasks should be of the form 'deadline <task_name> /by <task_deadline>'");
            }
            break;
        case 'E':
            try {
                taskDateTime = Parser.getTaskDateTime(fullCommand);
                taskName = Parser.getTaskName(fullCommand);
                createNewEvent(new Event(taskName, false, taskType, taskDateTime));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Event tasks should be of the form 'event <task_name> /at <task_time>'");
            }
            break;
        default:

        }
    }
    private void createNewToDo(ToDo toDo) {
        tasks.add(toDo);
        taskCounter++;
        Ui.showAdded(toDo, taskCounter);
    }
    private void createNewDeadline(Deadline deadline) {
        tasks.add(deadline);
        taskCounter++;
        Ui.showAdded(deadline, taskCounter);
    }
    private void createNewEvent(Event event) {
        tasks.add(event);
        taskCounter++;
        Ui.showAdded(event, taskCounter);
    }
}
