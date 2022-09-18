package duke.task;

import duke.exceptions.AccessTaskOutOfBoundsException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.exceptions.MissingTaskNumberException;
import duke.exceptions.TaskAlreadyMarkedException;
import duke.exceptions.TaskAlreadyUnmarkedException;
import duke.exceptions.MissingKeywordException;
import duke.ui.Ui;
import java.util.ArrayList;

/**
 * Represents a task manager to encapsulate the user's list of tasks and execute operations that retrieve,
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int tasksCount = 0;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks, int tasksCount) {
        this.tasks = tasks;
        this.tasksCount = tasksCount;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getTasksCount() {
        return this.tasksCount;
    }

    /**
     * Assembles individual words of the task's description into a combined string
     *
     * @param text The array of individual words that make up the full description of the task being handled.
     * @return One combined string containing the full description of the task.
     */
    public String assembleTaskDescription(String[] text) {
        String description = text[1]; //initialise with first word of task
        for (int i = 2; i < text.length; i++) { //start loop from second word of task
            description = description + " " + text[i];
        }
        return description;
    }

    /**
     * Processes user commands concerning tasks that have a time-related attribute. Creates new Deadline or Event
     * types of tasks by extracting their description and time-related attribute ie deadline for Deadline
     * and dayAndTime for Event. The newly created task is then added to the user's list of tasks.
     *
     * @param type The type of task being handled by the program.
     * @param description The full description of the task being handled.
     */

    public void handleTaskWithTime(String type, String description) {
        String[] text = description.split(" ");
        String task = text[0]; //initialise with first word of task
        int indexOfTime = 0;
        for (int i = 1; i < text.length; i++) { //start loop from second word of task
            if (text[i].contains("/")) {
                indexOfTime = i;
                break;
            }
            task = task + " " + text[i];
        }
        indexOfTime++;
        String time = "";
        for (int i = indexOfTime; i < text.length; i++) {
            if (i < text.length-1) { //if it is the last word
                time = time + text[i] + " ";
            } else {
                time += text[i];
            }
        }
        if (type.equals("deadline")) {
            Task newTask = new Deadline(task, time);
            tasks.add(newTask);
        } else if (type.equals("event")) {
            Task newTask = new Event(task, time);
            tasks.add(newTask);
        }
    }

    /**
     * Processes user commands concerning tasks, irrespective of whether they have a time-related attribute.
     * After processing, the task is created and added to the user's list of tasks.
     *
     * @param type The type of task being handled by the program.
     * @param description The full description of the task being handled.
     */
    public void handleTask(String type, String description) {
        if (type.equals("todo")) {
            Task newTask = new Todo(description);
            tasks.add(newTask);
        } else {
            this.handleTaskWithTime(type, description);
        }
    }

    /**
     * Processes the user input to the program and adds the processed task to the user's list of tasks if valid.
     *
     * @param type The type of task being handled by the program.
     * @param curr The string representing the task currently being handled, including its type and description (if any).
     * @throws EmptyDescriptionException if the user did not enter a description along with the type.
     * @throws UnknownCommandException if the user provided an invalid type of task that the program cannot recognise.
     */
    public void addTask(String type, String curr) throws EmptyDescriptionException, UnknownCommandException {
        boolean isTodo = type.equals("todo");
        boolean isDeadline = type.equals("deadline");
        boolean isEvent = type.equals("event");
        if (isTodo || isDeadline || isEvent) {
            String[] text = curr.split(" ");
            if (text.length == 1) { //description is empty
                throw new EmptyDescriptionException();
            } else {
                String description = assembleTaskDescription(text);
                handleTask(type, description);
                Ui.printAddTaskAcknowledgement(this.tasks, this.tasksCount);
                this.tasksCount++;
            }
        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Processes the user input to the program and deletes the processed task from the user's list of tasks if valid.
     *
     * @param type The type of task being handled by the program.
     * @param curr The string representing the task currently being handled, including its number (if any).
     * @throws AccessTaskOutOfBoundsException if the user specified an invalid task number to delete.
     * @throws MissingTaskNumberException if the user did not specify a task number.
     */
    public void deleteTask(String type, String curr) throws AccessTaskOutOfBoundsException, MissingTaskNumberException {
        String[] text = curr.split(" ");
        if (text.length == 1) { //user did not specify which task to delete
            throw new MissingTaskNumberException();
        } else {
            int taskNumber = Integer.parseInt(text[1]);
            if (taskNumber > this.tasksCount) {
                throw new AccessTaskOutOfBoundsException();
            } else {
                int taskIndex = taskNumber - 1;
                Ui.printDeleteTaskAcknowledgement(taskIndex, this.tasks, this.tasksCount);
                this.tasksCount--;
                tasks.remove(taskIndex);
            }
        }
    }

    /**
     * Processes the user input to the program and finds the tasks which match the keyword if valid.
     *
     * @param text The string representing the user input to the program.
     * @throws MissingKeywordException if the user did not specify a keyword.
     */
    public void findTasks(String[] text) throws MissingKeywordException {
        if (text.length == 1) {
            throw new MissingKeywordException();
        } else {
            String keyword = text[1];
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (Task task : tasks) {
                String taskDescriptionLowerCase = task.getDescription().toLowerCase();
                String keywordLowerCase = keyword.toLowerCase();
                boolean hasKeyword = taskDescriptionLowerCase.contains(keywordLowerCase);
                if (hasKeyword) {
                    matchingTasks.add(task);
                }
            }
            TaskList matchingTaskList = new TaskList(matchingTasks, matchingTasks.size());
            Ui.printMatchingTasks(matchingTaskList);
        }
    }

    /**
     * Processes the user input to the program and marks the processed task as done if valid.
     *
     * @param curr The string representing the task currently being handled, including its number (if any).
     * @throws MissingTaskNumberException if the user did not specify a task number.
     */
    public void handleMarkAsDone(String curr) throws MissingTaskNumberException {
        String[] text = curr.split(" ");
        if (text.length == 1) { //did not specify task number
            throw new MissingTaskNumberException();
        } else {
            int taskNumber = Integer.parseInt(text[1]);
            try {
                markAsDone(taskNumber);
            } catch (AccessTaskOutOfBoundsException e) {
                e.printErrorMessage();
                Ui.printNumberOfTasks(this.tasksCount);
            } catch (TaskAlreadyMarkedException e) {
                e.printErrorMessage();
            }
        }
    }

    /**
     * Marks the task as done if valid.
     *
     * @param taskNumber The chronological number of the task being marked as done.
     * @throws AccessTaskOutOfBoundsException if the user did not specify a task number.
     * @throws TaskAlreadyMarkedException if the user tries to mark an already marked task.
     */
    public void markAsDone(int taskNumber) throws AccessTaskOutOfBoundsException, TaskAlreadyMarkedException {
        if (taskNumber > this.tasksCount || taskNumber < 0) { //task specified is out of bounds
            throw new AccessTaskOutOfBoundsException();
        } else {
            int taskIndex = taskNumber - 1;
            if (!tasks.get(taskIndex).isDone) { //only if task is undone
                tasks.get(taskIndex).isDone = true;
                Ui.printMarkedTaskAsDoneAcknowledgement(taskIndex, tasks);
                return;
            }
            throw new TaskAlreadyMarkedException();
        }
    }

    /**
     * Processes the user input to the program and marks the processed task as undone if valid.
     *
     * @param curr The string representing the task currently being handled, including its number (if any).
     * @throws MissingTaskNumberException if the user did not specify a task number.
     */
    public void handleMarkAsUndone(String curr) throws MissingTaskNumberException {
        String[] text = curr.split(" ");
        if (text.length == 1) { //did not specify task number
            throw new MissingTaskNumberException();
        } else {
            int taskNumber = Integer.parseInt(text[1]);
            try {
                markAsUndone(taskNumber);
            } catch (AccessTaskOutOfBoundsException e) {
                e.printErrorMessage();
                Ui.printNumberOfTasks(this.tasksCount);
            } catch (TaskAlreadyUnmarkedException e) {
                e.printErrorMessage();
            }
        }
    }

    /**
     * Marks the task as undone if valid.
     *
     * @param taskNumber The chronological number of the task being marked as undone.
     * @throws AccessTaskOutOfBoundsException if the user did not specify a task number.
     * @throws TaskAlreadyUnmarkedException if the user tries to unmark an already unmarked task.
     */
    public void markAsUndone(int taskNumber) throws AccessTaskOutOfBoundsException, TaskAlreadyUnmarkedException{
        if (taskNumber > this.tasksCount || taskNumber < 0) { //task specified is out of bounds
            throw new AccessTaskOutOfBoundsException();
        } else {
            int taskIndex = taskNumber - 1;
            if (tasks.get(taskIndex).isDone) { //only if task is done
                tasks.get(taskIndex).isDone = false;
                Ui.printMarkedTaskAsUndoneAcknowledgement(taskIndex, tasks);
                return;
            }
            throw new TaskAlreadyUnmarkedException();
        }
    }
}