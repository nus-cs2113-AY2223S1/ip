package duke.task;

import duke.Ui;
import duke.exception.EmptyDescriptionException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addDeadline (String input, Ui ui) throws EmptyDescriptionException, StringIndexOutOfBoundsException {
        String [] splitInput = input.split(" ");
        if (splitInput.length == 1) {
            throw new EmptyDescriptionException();
        }
        String description = input.substring(input.indexOf(" ") + 1, input.indexOf(" /by "));
        String by = input.substring(input.indexOf("/by ") + 4);
        Task d = new Deadline(description, by);
        tasks.add(d);
        // print to CLI
        ui.addTaskMessage(tasks, d);
    }


    public void addTodo (String input, Ui ui) throws EmptyDescriptionException, StringIndexOutOfBoundsException {
        String [] splitInput = input.split(" ");
        if (splitInput.length == 1) {
            throw new EmptyDescriptionException();
        }
        String description = input.substring(input.indexOf(" ") + 1);
        Task td = new Todo(description);
        tasks.add(td);
        // print to CLI
        ui.addTaskMessage(tasks, td);
    }

    public void addEvent (String input, Ui ui) throws EmptyDescriptionException, StringIndexOutOfBoundsException {
        String [] splitInput = input.split(" ");
        if (splitInput.length == 1) {
            throw new EmptyDescriptionException();
        }
        String description = input.substring(input.indexOf(" ") + 1, input.indexOf(" /at "));
        String at = input.substring(input.indexOf("/at ") + 4);
        Task e = new Event(description, at);
        tasks.add(e);
        // print to CLI
        ui.addTaskMessage(tasks, e);
    }
    
    public void deleteTask (String input, Ui ui) {
        String[] splitInput = input.split(" ");
        //print to CLI before deleting
        ui.deleteTaskMessage(tasks, Integer.parseInt(splitInput[1]) - 1);
        //delete
        tasks.remove(Integer.parseInt(splitInput[1]) - 1);

    }

    public void markTask (String input, Ui ui) {
        // mark x commands duke to mark the corresponding task as completed
        String numericString = input.substring(input.indexOf(" ") + 1);
        int markedNum = Integer.parseInt(numericString) - 1;
        tasks.get(markedNum).setStatus(Boolean.TRUE);
        //print to CLI
        ui.markTaskMessage(tasks,markedNum);


    }

    public void unmarkTask (String input, Ui ui) {
        // unmark x commands duke to mark the corresponding task as uncompleted
        // Exceptions could occur
        String numericString = input.substring(input.indexOf(" ") + 1);
        int markedNum = Integer.parseInt(numericString) - 1;
        tasks.get(markedNum).setStatus(Boolean.FALSE);
        //print to CLI
        ui.unmarkTaskMessage(tasks,markedNum);

    }



    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void removeTask(int i) {
        tasks.remove(i);
    }

}
