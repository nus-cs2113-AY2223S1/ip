package duke.task;

import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.exception.UnknownInputException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addDeadline (String input, Ui ui) throws EmptyDescriptionException {
        try {
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
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! You must have a /by ' for deadlines!!\n");
        }

    }


    public void addTodo (String input, Ui ui) throws EmptyDescriptionException {
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

    public void addEvent (String input, Ui ui) throws EmptyDescriptionException {
        try {
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
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! You must have a /at ' for events!!\n");
        }

    }
    
    public void deleteTask (String input, Ui ui) {
        try {
            String[] splitInput = input.split(" ");
            //print to CLI before deleting
            ui.deleteTaskMessage(tasks, Integer.parseInt(splitInput[1]) - 1);
            //delete
            tasks.remove(Integer.parseInt(splitInput[1]) - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! You must input the right delete index number\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! You must input the right delete index number\n");
        }

    }

    public void markTask (String input, Ui ui) {
        try {
            // mark x commands duke to mark the corresponding task as completed
            String numericString = input.substring(input.indexOf(" ") + 1);
            int markedNum = Integer.parseInt(numericString) - 1;
            tasks.get(markedNum).setStatus(Boolean.TRUE);
            //print to CLI
            ui.markTaskMessage(tasks,markedNum);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! You must input the right mark index number\n");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! You must input an index number \n");
        }



    }

    public void unmarkTask (String input, Ui ui) {
        try {
            // unmark x commands duke to mark the corresponding task as uncompleted
            String numericString = input.substring(input.indexOf(" ") + 1);
            int markedNum = Integer.parseInt(numericString) - 1;
            tasks.get(markedNum).setStatus(Boolean.FALSE);
            //print to CLI
            ui.unmarkTaskMessage(tasks,markedNum);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! You must input the right unmark index number\n");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! You must input an index number \n");
        }
    }


    public void findTask (String input, Ui ui) throws EmptyDescriptionException {
        try {
            String [] splitInput = input.split(" ");
            if (splitInput.length == 1) {
                throw new EmptyDescriptionException();
            } else if (splitInput.length > 2) {
                throw new UnknownInputException();
            }
            // print to CLI
            ui.printMatchedTasks(tasks, splitInput[1]);
        } catch (UnknownInputException e) {
            System.out.println("OOPS!! Duke can only handle one search word!!\n");
        }
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
