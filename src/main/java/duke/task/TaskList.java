package duke.task;

import duke.Ui;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeUnknownInputException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor of TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor of TaskList when tasks has already been saved previously
     *
     * @param tasks Arraylist of Tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add Deadline Task to Tasks
     *
     * @param input User input line
     * @param ui Instance of Ui
     * @throws DukeEmptyDescriptionException when no description is inputted
     */
    public void addDeadline (String input, Ui ui) throws DukeEmptyDescriptionException {
        try {
            String [] splitInput = input.split(" ");
            if (splitInput.length == 1) {
                throw new DukeEmptyDescriptionException();
            }
            String description = input.substring(input.indexOf(" ") + 1, input.indexOf(" /by "));
            String by = input.substring(input.indexOf("/by ") + 4);
            Task d = new Deadline(description, by);
            tasks.add(d);
            // print to CLI
            ui.addTaskMessage(tasks, d);
        } catch (StringIndexOutOfBoundsException e) {
            //when /by is not used in the user command
            System.out.println("OOPS!!! You must have a /by ' for deadlines!!\n");
        }
    }

    /**
     * Add Todo Task to Tasks
     *
     * @param input User input line
     * @param ui Instance of UI
     * @throws DukeEmptyDescriptionException when no description is inputted
     */
    public void addTodo (String input, Ui ui) throws DukeEmptyDescriptionException {
        String [] splitInput = input.split(" ");
        if (splitInput.length == 1) {
            throw new DukeEmptyDescriptionException();
        }
        String description = input.substring(input.indexOf(" ") + 1);
        Task td = new Todo(description);
        tasks.add(td);
        // print to CLI
        ui.addTaskMessage(tasks, td);
    }

    /**
     * Add Event Task to Tasks
     *
     * @param input User input line
     * @param ui Instance of Ui
     * @throws DukeEmptyDescriptionException when no description is inputted
     */
    public void addEvent (String input, Ui ui) throws DukeEmptyDescriptionException {
        try {
            String [] splitInput = input.split(" ");
            if (splitInput.length == 1) {
                throw new DukeEmptyDescriptionException();
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

    /**
     * Delete a Task from Tasks
     *
     * @param input User input line
     * @param ui Instance of Ui
     */
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

    /**
     * Mark a Task as completed
     *
     * @param input User input line
     * @param ui Instance of Ui
     */
    public void markTask (String input, Ui ui) {
        try {
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

    /**
     * Unmark an initially completed Task as uncompleted
     *
     * @param input User input line
     * @param ui Instance of Ui
     * @throws DukeEmptyDescriptionException when no description is inputted
     */
    public void unmarkTask (String input, Ui ui) {
        try {
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

    /**
     * Find matched tasks in Tasks whose description match the search phrase
     *
     * @param input User input line
     * @param ui Instance of Ui
     * @throws DukeEmptyDescriptionException when no description is inputted
     */
    public void findTask (String input, Ui ui) throws DukeEmptyDescriptionException {
        try {
            String [] splitInput = input.split(" ");
            if (splitInput.length == 1) {
                throw new DukeEmptyDescriptionException();
            } else if (splitInput.length > 2) {
                throw new DukeUnknownInputException();
            }
            // print to CLI
            ui.printMatchedTasks(tasks, splitInput[1]);
        } catch (DukeUnknownInputException e) {
            System.out.println("OOPS!! Duke can only handle one search word!!\n");
        }
    }

    /**
     * Returns tasks' size
     *
     * @return Integer of the Size of Task
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Return a Task
     * @param i index of the Task to be returned
     * @return Task
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

}
