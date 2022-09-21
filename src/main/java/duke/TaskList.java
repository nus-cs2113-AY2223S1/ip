package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.NoSpecificTimeException;
import duke.exception.NoSpecificDeadlineException;
import duke.exception.TaskNumberExceedException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a Todo Task.
     *
     * @param description The description that will be added into Todo task.
     */
    public static void addTodo(String description) {
        try {
            tasks.add(new Todo(description));
            Ui.addCompleteMessage("todo");
            Storage.appendFile("todo");
        } catch (EmptyDescriptionException e) {
            System.out.println("The description cannot be empty. :/");
            System.out.println("Please re-enter the same command but with a description");
        } catch (NoSpecificTimeException e) {
            System.out.println("Please re-enter and specify the time. :/");
        } catch (NoSpecificDeadlineException e) {
            System.out.println("Please re-enter and specify the deadline time. :/");
        }

    }

    /**
     * Adds a Deadline Task.
     * @param description The description that will be added into Deadline task.
     */
    public static void addDeadline(String description) {
        try {
            tasks.add(new Deadline(description));
            Ui.addCompleteMessage("deadline");
            Storage.appendFile("deadline");
        } catch (EmptyDescriptionException e) {
            System.out.println("The description cannot be empty. :/");
            System.out.println("Please re-enter the same command but with a description");
        } catch (NoSpecificTimeException e) {
            System.out.println("Please re-enter and specify the time. :/");
        } catch (NoSpecificDeadlineException e) {
            System.out.println("Please re-enter and specify the deadline time. :/");
        }

    }

    /**
     * Adds an Event Task.
     *
     * @param description The description that will be added into Event task.
     */
    public static void addEvent(String description) {
        try {
            tasks.add(new Event(description));
            Ui.addCompleteMessage("event");
            Storage.appendFile("event");
        } catch (EmptyDescriptionException e) {
            System.out.println("The description cannot be empty. :/");
            System.out.println("Please re-enter the same command but with a description");
        } catch (NoSpecificTimeException e) {
            System.out.println("Please re-enter and specify the time. :/");
        } catch (NoSpecificDeadlineException e) {
            System.out.println("Please re-enter and specify the deadline time. :/");
        }

    }

    /**
     * Marks a task indicated by the user.
     *
     * @param taskNumber The task number that will be marked.
     * @throws TaskNumberExceedException When the number indicated exceed the size of the task.
     * @throws IOException When there is an error writing to the file.
     */
    public static void markTask(int taskNumber) throws TaskNumberExceedException, IOException {
        boolean isValidTaskNumber = taskNumber <= TaskList.tasks.size();
        if (!isValidTaskNumber) {
            throw new TaskNumberExceedException();
        } else {
            int index = taskNumber - 1;
            TaskList.tasks.get(index).setStatusIcon("mark");
            Ui.printTask(index);
            Storage.updateLine("mark", index);
        }
    }

    /**
     * Deletes a task from the list of task.
     *
     * @param taskNumber The task number that will be deleted from the list.
     * @throws TaskNumberExceedException When the number indicated exceed the size of the task.
     * @throws IOException When there is an error writing to the file.
     */
    public static void deleteTask(int taskNumber) throws TaskNumberExceedException, IOException {
        boolean isValidTaskNumber = taskNumber <= TaskList.tasks.size();
        if (!isValidTaskNumber) {
            throw new TaskNumberExceedException();
        } else {
            int index = taskNumber - 1;
            Ui.printDeletedTask(index);
            tasks.remove(index);
            Ui.showNumberOfTask();
            Storage.deleteLine(index);
        }

    }

    /**
     * Unmarks a task indicated by the user.
     *
     * @param taskNumber The task number that will be unmarked.
     * @throws TaskNumberExceedException When the number indicated exceed the size of the task.
     * @throws IOException When there is an error writing to the file.
     */
    public static void unmarkTask(int taskNumber) throws TaskNumberExceedException, IOException {
        boolean isValidTaskNumber = taskNumber <= TaskList.tasks.size();
        if (!isValidTaskNumber) {
            throw new TaskNumberExceedException();
        } else {
            int index = taskNumber - 1;
            TaskList.tasks.get(index).setStatusIcon("unmark");
            Ui.printTask(index);
            Storage.updateLine("unmark", index);
        }
    }

}
