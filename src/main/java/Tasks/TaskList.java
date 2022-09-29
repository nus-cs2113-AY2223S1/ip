package Tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Exception.DataCorruptedException;
import Parser.Command;
import Exception.WrongArgumentException;

public class TaskList {
    private final String FILE_PATH = "./data/data.md";
    private ArrayList<Task> inputLists = new ArrayList<Task>();

    public ArrayList<Task> getInputLists() {
        return inputLists;
    }

    public String getItemFromList(int n) {
        String output = "\t";
        output += inputLists.get(n - 1).getCompleteDescription();
        return output;
    }

    public String getCompleteList() {
        String output = "";
        for (int i = 0; i < inputLists.size(); i++) {
            output += Integer.toString(i + 1) + ". " + getItemFromList(i + 1);
        }
        return output;
    }

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

    public void deleteTaskFromList(int n) {
        inputLists.remove(n - 1);
    }

    public void markCompleted(int n, boolean bool) {
        inputLists.get(n - 1).setCompleted(bool);
    }

    public int getTaskListSize() {
        return inputLists.size();
    }

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

    private String doTodoAction(String lineInput){
        int index = addTaskToList(lineInput, TaskType.TODO, false);
        return getItemFromList(index + 1);
    }

    private String doDeadlineAction(String lineInput) {
        int index = addTaskToList(lineInput, TaskType.DEADLINE, false);
        return getItemFromList(index + 1);
    }

    private String doEventAction(String lineInput) {
        int index = addTaskToList(lineInput, TaskType.EVENT, false);
        return getItemFromList(index + 1);
    }

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

    private String doMarkAction(String lineInput)
            throws WrongArgumentException {
        int itemNumber = Integer.parseInt(lineInput);
        if (itemNumber > getTaskListSize()) {
            throw new WrongArgumentException();
        }
        markCompleted(itemNumber, true);
        return getItemFromList(itemNumber);
    }

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
