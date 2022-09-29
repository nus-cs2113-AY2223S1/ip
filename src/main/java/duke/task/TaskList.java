package duke.task;

import duke.ui.Ui;
import duke.errorhandling.DukeException;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> assignments;
    protected int indexTask;
    protected int countTask;
    protected int indexOfChoice = 0;
    protected final int TASK_DETAIL_INDEX = 1;
    protected final int OFFSET_TO_ARRAY_INDEX = 1;

    public TaskList(ArrayList<Task> assignments, int indexTask, int countTask) {
        this.assignments = assignments;
        this.indexTask = indexTask;
        this.countTask = countTask;
    }

    public ArrayList<Task> getAssignments() {
        return assignments;
    }

    public int getIndexTask() {
        return indexTask;
    }

    public int getCountTask() {
        return countTask;
    }

    public void addTask(Task assignment, ArrayList<Task> assignments) {
        assignments.add(assignment);
        countTask++;
    }

    public void addEventTask(String[] splitCommand, ArrayList<Task> assignments, int index) {
        addTask(new Event(splitCommand[TASK_DETAIL_INDEX]), assignments);
        assignments.get(index).markTypeTask();
        indexTask++;
    }

    public void addDeadlineTask(String[] splitCommand, ArrayList<Task> assignments, int index) {
        addTask(new Deadline(splitCommand[TASK_DETAIL_INDEX]), assignments);
        assignments.get(index).markTypeTask();
        indexTask++;
    }

    public void addToDoTask(String[] splitCommand, ArrayList<Task> assignments, int index) {
        String taskDetail = splitCommand[TASK_DETAIL_INDEX];
        addTask(new ToDo(taskDetail), assignments);
        assignments.get(index).markTypeTask();
        indexTask++;
    }

    public void deleteTask(String[] splitCommand, ArrayList<Task> assignments, Ui ui) {
        countTask--;
        try {
            indexOfChoice = readIndexOfChoice(splitCommand);
            ui.showDeletedTask(assignments, indexOfChoice, countTask);
            assignments.remove(indexOfChoice);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t Stop there! Please delete something that is within the list!");
            countTask++;
            return;
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            countTask++;
            return;
        }
        if (indexTask != 0) {
            indexTask--;
        }
    }

    public void markOrUnmarkTask(String[] splitCommand, ArrayList<Task> assignments, boolean isMark, Ui ui) {
        try {
            indexOfChoice = readIndexOfChoice(splitCommand);
            checkMarkOrUnmark(isMark, assignments);
        } catch (IndexOutOfBoundsException e) {
            ui.showMarkOrUnmarkError(isMark);
            return;
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return;
        }
        ui.showMarkOrUnmarkTask(isMark);
        ui.showTaskDetail(assignments, indexOfChoice);
    }

    public void checkMarkOrUnmark(boolean isMark, ArrayList<Task> assignments) {
        if (isMark) {
            assignments.get(indexOfChoice).markAsDone();
        } else {
            assignments.get(indexOfChoice).unmarkAsDone();
        }
    }

    public int readIndexOfChoice(String[] splitCommand) throws DukeException {
        boolean isNotPositiveDigit = !splitCommand[TASK_DETAIL_INDEX].matches("[0-9]+")
                || splitCommand[TASK_DETAIL_INDEX].startsWith("-");
        if (isNotPositiveDigit) {
            throw new DukeException("\t Hey! Please choose a positive digit"
                    + " that correspondence to the list.");
        }
        int indexOfTask = Integer.parseInt(splitCommand[TASK_DETAIL_INDEX]);
        return indexOfTask - OFFSET_TO_ARRAY_INDEX;
    }

}
