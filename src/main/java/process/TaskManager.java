package process;
import java.io.IOException;
import java.util.ArrayList;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

/**
 * Handles all types of task 
 */
public class TaskManager {
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    public boolean handleTask(String inputText) throws DukeException {
        final String[] splits = inputText.trim().split("\\s+", 2);
        final String[] commandTypeAndParams = splits.length == 2 ? splits : new String[] { splits[0], "" };
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        int index;
        String response;
        switch (commandType) {
        case "todo":
            Todo todo = new Todo(commandArgs);
            taskList.add(todo);
            response = taskList.get(taskList.size()-1).getResponse();
            Ui.printTaskResponse(response, taskList.size());
            updateRestorationFile();
            break;
        case "deadline":
            Deadline deadline = new Deadline(commandArgs);
            taskList.add(deadline);
            response = taskList.get(taskList.size()-1).getResponse();
            Ui.printTaskResponse(response, taskList.size());
            updateRestorationFile();
            break;
        case "event":
            Event event = new Event(commandArgs);
            taskList.add(event);
            response = taskList.get(taskList.size()-1).getResponse();
            Ui.printTaskResponse(response, taskList.size());
            updateRestorationFile();
            break;
        case "list":
            showAllTasks();
            break;
        case "delete":
            index = Integer.valueOf(commandArgs);
            response = taskList.get(index-1).getResponse();
            taskList.remove(index-1);
            Ui.printDeleteResponse(response, taskList.size());
            updateRestorationFile();
            break;
        case "mark":
            index = Integer.valueOf(commandArgs);
            taskList.get(index-1).setStringState(true);
            response = taskList.get(index-1).getResponse();
            Ui.printMarkResponse(response);
            updateRestorationFile();
            break;
        case "unmark":
            index = Integer.valueOf(commandArgs);
            taskList.get(index-1).setStringState(false);
            response = taskList.get(index-1).getResponse();
            Ui.printUnmarkResponse(response);
            updateRestorationFile();
            break;
        case "find":
            printTaskByKeyword(commandArgs);
            break;
        case "bye":
            Ui.showExitMessage();
            return true;
        default:
            throw new DukeException("TaskTypeError");
        }
        return false;
    }

    public void handleRestoration(String restoration) throws DukeException{
        final String[] splits = restoration.split(" \\| ");
        boolean isDone;
        String description;
        String dueTime;
        String eventTime;
        switch (splits[0]) {
        case "T":
            isDone = splits[1].equals("1") ? true : false;
            description = splits[2];
            Todo todo = new Todo(description,isDone);
            taskList.add(todo);
            break;
        case "D":
            isDone = splits[1].equals("1") ? true : false;
            description = splits[2];
            dueTime = splits[3];
            Deadline deadline = new Deadline(description,isDone,dueTime);
            taskList.add(deadline);
            break;
        case "E":
            isDone = splits[1].equals("1") ? true : false;
            description = splits[2];
            eventTime = splits[3];
            Event event = new Event(description,isDone,eventTime);
            taskList.add(event);
            break;
        default:
            throw new DukeException("RestorationFileCorrupted");
        }
    }

    private void updateRestorationFile(){
        String[] writeLines = this.getAllTasks();
        try {
            Storage.writeToFile(writeLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] getAllTasks(){
        String[] tasks = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            tasks[i] = taskList.get(i).getStorageFormat();
        }
        return tasks;
    }

    private void showAllTasks(){
        Ui.printSplitLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + String.valueOf(i+1) + "." + taskList.get(i).getResponse());
        }
        Ui.printSplitLine();
        System.out.println();
    }

    private void printTaskByKeyword(String keyword){
        int taskCount = 0;
        Ui.printSplitLine();
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(keyword)) {
                taskCount++;
                System.out.println("     " + String.valueOf(taskCount) + "." + taskList.get(i).getResponse());
            }
            
        }
        Ui.printSplitLine();
        System.out.println();
    }

}
