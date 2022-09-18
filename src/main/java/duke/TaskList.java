package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks= new ArrayList<>();
    private int taskCounter = 0;

    public TaskList() {
    }
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
    public void markTask(int taskID) throws DukeException {
        if (tasks.get(taskID - 1).isDone()) {
            throw new DukeException(":( OOPS!!! Unable to mark as this task has already been done");
        }
        tasks.get(taskID - 1).setDone(true);
    }
    public void unmarkTask(int taskID) throws DukeException {
        if (!tasks.get(taskID - 1).isDone()) {
            throw new DukeException(":( OOPS!!! Unable to unmark as this task has not been done yet");
        }
        tasks.get(taskID - 1).setDone(false);
    }
    public void deleteTask(int taskID) throws DukeException {
        tasks.remove(taskID - 1);
        taskCounter--;
    }
    public void createTask(String fullCommand) {
        fullCommand = fullCommand.trim();
        char taskType = fullCommand.toUpperCase().charAt(0);    //first char is the type in uppercase
        try {
            fullCommand = Parser.removeTaskType(fullCommand);   //get taskName from command
        } catch (DukeException e) {
            Ui.showError(e.getErrorMessage());
            return;
        }
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
                System.out.println(":( OOPS!!! Deadline tasks should be of the form 'deadline <task_name> /by <task_deadline>'");
            }
            break;
        case 'E':
            try {
                taskDateTime = Parser.getTaskDateTime(fullCommand);
                taskName = Parser.getTaskName(fullCommand);
                createNewEvent(new Event(taskName, false, taskType, taskDateTime));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(":( OOPS!!! Event tasks should be of the form 'event <task_name> /at <task_time>'");
            }
            break;
        default:
            Ui.drawLine();
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
