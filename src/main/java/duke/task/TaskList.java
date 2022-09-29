package duke.task;

import duke.ui.Ui;
import duke.errorhandling.DukeException;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskList {
    protected Ui ui = new Ui();
    protected final int INITIAL_INDEX = 0;
    protected final int TASK_TITLE_INDEX = 0;
    protected final int TASK_DETAIL_INDEX = 1;
    protected final int OFFSET_TO_ARRAY_INDEX = 1;
    protected final String DATE_PATTERN = "\\d{4}-\\d{1,2}-\\d{1,2}";
    protected ArrayList<Task> assignments;
    protected int indexTask;
    protected int countTask;
    protected int indexOfChoice = 0;
    protected String taskDetail;
    protected String formatTaskDetail;
    protected boolean isDate = false;



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
        taskDetail = splitCommand[TASK_DETAIL_INDEX];
        addTask(new Event(taskDetail), assignments);
        assignments.get(index).markTypeTask();
        indexTask++;
    }

    public void addDeadlineTask(String[] splitCommand, ArrayList<Task> assignments, int index) {
        taskDetail = splitCommand[TASK_DETAIL_INDEX];
        formatTaskDetail = formatDeadlineTaskDetail(taskDetail);
        addTask(new Deadline(formatTaskDetail), assignments);
        assignments.get(index).markTypeTask();
        indexTask++;
    }

    public void addToDoTask(String[] splitCommand, ArrayList<Task> assignments, int index) {
        taskDetail = splitCommand[TASK_DETAIL_INDEX];
        addTask(new ToDo(taskDetail), assignments);
        assignments.get(index).markTypeTask();
        indexTask++;
    }

    public void deleteTask(String[] splitCommand, ArrayList<Task> assignments) {
        countTask--;
        try {
            indexOfChoice = readIndexOfChoice(splitCommand);
            ui.showDeletedTask(assignments, indexOfChoice, countTask);
            assignments.remove(indexOfChoice);
        } catch (IndexOutOfBoundsException e) {
            ui.showDeleteTaskError();
            countTask++;
            return;
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            countTask++;
            return;
        }
        if (isNotInitialIndex()) {
            indexTask--;
        }
    }

    private boolean isNotInitialIndex() {
        return indexTask != INITIAL_INDEX;
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
        boolean isNotPositiveDigit = isNotPositiveDigit(splitCommand);
        if (isNotPositiveDigit) {
            throw new DukeException("\t Hey! Please choose a positive digit"
                    + " that correspondence to the list.");
        }
        int indexOfTask = Integer.parseInt(splitCommand[TASK_DETAIL_INDEX]);
        return indexOfTask - OFFSET_TO_ARRAY_INDEX;
    }

    public boolean isNotPositiveDigit(String[] splitCommand) {
        boolean isNotPositiveDigit = !splitCommand[TASK_DETAIL_INDEX].matches("[0-9]+")
                || splitCommand[TASK_DETAIL_INDEX].startsWith("-");
        return isNotPositiveDigit;
    }

    public String formatDeadlineTaskDetail(String taskDetail) {
        String formatDetail = taskDetail;
        try {
            String[] splitTaskDetail = getDeadlineDetails(taskDetail);
            String taskTitle = splitTaskDetail[TASK_TITLE_INDEX].trim();
            String detail = splitTaskDetail[TASK_DETAIL_INDEX].trim();
            formatDetail = checkValidDate(detail, formatDetail, taskTitle);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showDeadlineTaskError();
            return formatDetail;
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return formatDetail;
    }

    public String checkValidDate(String detail, String formatDetail, String taskTitle) {
        isDate = detail.matches(DATE_PATTERN);
        if (isDate) {
            formatDetail = formatDate(detail, taskTitle);
        }
        return formatDetail;
    }

    public String formatDate(String detail, String taskTitle) {
        LocalDate date = LocalDate.parse(detail);
        detail = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formatDate = taskTitle + " /by " + detail;
        return formatDate;
    }

    public String[] getDeadlineDetails(String taskDetail) throws DukeException {
        String[] splitTaskDetail = taskDetail.split("/by");
        if (splitTaskDetail[1].isBlank()) {
            throw new DukeException("Please try again and include"
                    + " complete Deadline details.");
        }
        return splitTaskDetail;
    }
}
