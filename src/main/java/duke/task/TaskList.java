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



    /**
     * Initialises the class TaskList.
     *
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param indexTask which is the index of the task.
     * @param countTask which tracks the number of task that the user has added.
     */
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

    /**
     * Adds a specific task to the taskList.
     *
     * @param assignment which is a specific task that is obtained from the taskList.
     * @param assignments that an array of tasks taken from the class TaskList.
     */
    public void addTask(Task assignment, ArrayList<Task> assignments) {
        assignments.add(assignment);
        countTask++;
    }

    /**
     * Adds an event task to the taskList.
     *
     * @param splitCommand command that have been separated into their respective words.
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param index which is the increment value in the loop to access the assignments.
     */
    public void addEventTask(String[] splitCommand, ArrayList<Task> assignments, int index) {
        taskDetail = splitCommand[TASK_DETAIL_INDEX];
        addTask(new Event(taskDetail), assignments);
        assignments.get(index).markTypeTask();
        indexTask++;
    }

    /**
     * Adds the deadline task into the taskList.
     *
     * @param splitCommand command that have been separated into their respective words.
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param index which is the increment value in the loop to access the assignments.
     */
    public void addDeadlineTask(String[] splitCommand, ArrayList<Task> assignments, int index) {
        taskDetail = splitCommand[TASK_DETAIL_INDEX];
        formatTaskDetail = formatDeadlineTaskDetail(taskDetail);
        addTask(new Deadline(formatTaskDetail), assignments);
        assignments.get(index).markTypeTask();
        indexTask++;
    }

    /**
     * Adds the todo task into the taskList.
     *
     * @param splitCommand command that have been separated into their respective words.
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param index which is the increment value in the loop to access the assignments.
     */
    public void addToDoTask(String[] splitCommand, ArrayList<Task> assignments, int index) {
        taskDetail = splitCommand[TASK_DETAIL_INDEX];
        addTask(new ToDo(taskDetail), assignments);
        assignments.get(index).markTypeTask();
        indexTask++;
    }

    /**
     * Deletes a task and removes it from the taskList.
     *
     * @param splitCommand command that have been separated into their respective words.
     * @param assignments that an array of tasks taken from the class TaskList.
     */
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

    /**
     * Marks or unmark a task that is in the taskList.
     *
     * @param splitCommand command that have been separated into their respective words.
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param isMark which checks if the task has been marked or not.
     * @param ui which is taken from the Ui class to display messages to the user.
     */
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

    /**
     * Checks the mark or unmark status of the task to distinct that it is completed or not.
     *
     * @param isMark which checks if the task has been marked or not.
     * @param assignments that an array of tasks taken from the class TaskList.
     */
    public void checkMarkOrUnmark(boolean isMark, ArrayList<Task> assignments) {
        if (isMark) {
            assignments.get(indexOfChoice).markAsDone();
        } else {
            assignments.get(indexOfChoice).unmarkAsDone();
        }
    }

    /**
     * Reads the index of choice that the user has input.
     *
     * @param splitCommand command that have been separated into their respective words.
     * @return indexOfChoice
     * @throws DukeException an error that is thrown if the user does not input a valid digit.
     */
    public int readIndexOfChoice(String[] splitCommand) throws DukeException {
        boolean isNotPositiveDigit = isNotPositiveDigit(splitCommand);
        if (isNotPositiveDigit) {
            throw new DukeException("\t Hey! Please choose a positive digit"
                    + " that correspondence to the list.");
        }
        int indexOfTask = Integer.parseInt(splitCommand[TASK_DETAIL_INDEX]);
        return indexOfTask - OFFSET_TO_ARRAY_INDEX;
    }

    /**
     * Checks that the user in inputting a positive digit. Else, the command would be invalid.
     *
     * @param splitCommand command that have been separated into their respective words.
     * @return isNotPositiveDigit
     */
    public boolean isNotPositiveDigit(String[] splitCommand) {
        boolean isNotPositiveDigit = !splitCommand[TASK_DETAIL_INDEX].matches("[0-9]+")
                || splitCommand[TASK_DETAIL_INDEX].startsWith("-");
        return isNotPositiveDigit;
    }

    /**
     * Formats the deadline Task detail to display the data in a particular format.
     *
     * @param taskDetail which is taken from the TaskList
     * @return formatDetail
     */
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

    /**
     * Checks that the date is input in the correct format by the user.
     *
     * @param detail the detail of the TASK_TITLE_INDEX.
     * @param formatDetail which is the formatted detail to display the date.
     * @param taskTitle which is the name of the task.
     * @return formatDetail
     */
    public String checkValidDate(String detail, String formatDetail, String taskTitle) {
        isDate = detail.matches(DATE_PATTERN);
        if (isDate) {
            formatDetail = formatDate(detail, taskTitle);
        }
        return formatDetail;
    }

    /**
     * Formats the input date to a specified format "MMM d YYYY".
     *
     * @param detail the detail of the TASK_TITLE_INDEX.
     * @param taskTitle which is the name of the task.
     * @return formatDate
     */
    public String formatDate(String detail, String taskTitle) {
        LocalDate date = LocalDate.parse(detail);
        detail = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formatDate = taskTitle + " /by " + detail;
        return formatDate;
    }

    /**
     *
     * @param taskDetail which is the detail of the task that is obtained from taskList.
     * @return splitTaskDetail
     * @throws DukeException an error is made if the deadline details are not completely or
     * correctly input by the user.
     */
    public String[] getDeadlineDetails(String taskDetail) throws DukeException {
        String[] splitTaskDetail = taskDetail.split("/by");
        if (splitTaskDetail[1].isBlank()) {
            throw new DukeException("Please try again and include"
                    + " complete Deadline details.");
        }
        return splitTaskDetail;
    }
}
