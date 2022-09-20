package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.NoSpecficTimeException;
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

    public static void addTodo(String command) {
        try {
            tasks.add(new Todo(command));
            Ui.addCompleteMessage("todo");
            Storage.appendFile("todo");
        } catch (EmptyDescriptionException e) {
            System.out.println("The description cannot be empty. :/");
            System.out.println("Please re-enter the same command but with a description");
        } catch (NoSpecficTimeException e) {
            System.out.println("Please re-enter and specify the time. :/");
        } catch (NoSpecificDeadlineException e) {
            System.out.println("Please re-enter and specify the deadline time. :/");
        }

    }

    public static void addDeadline(String command) {
        try {
            tasks.add(new Deadline(command));
            Ui.addCompleteMessage("deadline");
            Storage.appendFile("deadline");
        } catch (EmptyDescriptionException e) {
            System.out.println("The description cannot be empty. :/");
            System.out.println("Please re-enter the same command but with a description");
        } catch (NoSpecficTimeException e) {
            System.out.println("Please re-enter and specify the time. :/");
        } catch (NoSpecificDeadlineException e) {
            System.out.println("Please re-enter and specify the deadline time. :/");
        }

    }

    public static void addEvent(String command) {
        try {
            tasks.add(new Event(command));
            Ui.addCompleteMessage("event");
            Storage.appendFile("event");
        } catch (EmptyDescriptionException e) {
            System.out.println("The description cannot be empty. :/");
            System.out.println("Please re-enter the same command but with a description");
        } catch (NoSpecficTimeException e) {
            System.out.println("Please re-enter and specify the time. :/");
        } catch (NoSpecificDeadlineException e) {
            System.out.println("Please re-enter and specify the deadline time. :/");
        }

    }

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
