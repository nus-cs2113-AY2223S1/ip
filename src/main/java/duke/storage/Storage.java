package duke.storage;

import duke.errorhandling.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    protected String newDirName;
    protected File dataFile;
    protected File dataDir;
    protected int indexTask = 0;
    protected int countTask = 0;
    protected final int COMMAND_INDEX = 0;
    protected final int NO_TASK = 0;
    protected final int STATUS_INDEX = 1;

    protected final int TASK_TITLE_INDEX = 2;
    protected final int EVENT_OR_DEADLINE_DETAIL = 3;
    protected boolean isStoredInputOfEvent = false;
    protected boolean isStoredInputOfDeadline = false;
    protected boolean isToDoFromDataFile = false;
    protected boolean isDeadlineFromDateFile = false;
    protected boolean isEventFromDataFile = false;
    protected boolean isMarkFromDataFile = false;
    protected boolean isEmptyTask = false;

    /**
     * Initialises object variables to create the dataFile.
     *
     * @param filePath which is the address of the file that is set before initialisation.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.newDirName = "data";
        this.dataFile = new File(filePath);
        this.dataDir = new File(newDirName);
    }

    /**
     * Gets the data file if it does not exist or inform the User that it already exist.
     */
    public void dataFileStatus(Ui ui) {
        boolean isNoDir = dataDir.mkdir();
        createFileOrFolder(ui, isNoDir);
    }

    /**
     * Creates the file or the folder based on what is missing before the main program starts.
     *
     * @param ui which is taken from the class Ui to display messages.
     * @param isNoDir which is true when the specific directory is not found.
     */
    public void createFileOrFolder(Ui ui, boolean isNoDir) {
        if (isNoDir) {
           createDir(ui);
        } else {
            try {
                createDataFile(ui);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Creates a path for the directory and also checks that if it exists.
     *
     * @param ui which is taken from the class Ui to display messages.
     */
    public void createDir(Ui ui) {
        ui.showDataFolderStatus();
        try {
            createDataFile(ui);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Creates the dataFile and ensure that the file is valid before the program starts.
     *
     * @param ui which is taken from the class Ui to display messages.
     * @throws DukeException which is an error to show that there is some problem
     * generating or creating the Datafile.
     */
    public void createDataFile(Ui ui) throws DukeException {
        try {
            getFileStatus(ui);
        } catch (IOException e) {
            throw new DukeException("\n\t Error in trying to locate or make data file");
        }
    }

    /**
     * Gets the file status to see if the file have been successfully created.
     *
     * @param ui which is taken from the class Ui to display messages.
     * @throws IOException set of errors that is used to govern the creation of
     * a new file.
     */
    public void getFileStatus(Ui ui) throws IOException {
        boolean isFileCreated = dataFile.createNewFile();
        ui.showDataFileStatus(isFileCreated);
    }

    public int getCountTask() {
        return countTask;
    }

    public boolean isInitialEmpty() {
        return isEmptyTask;
    }

    /**
     * Writes the taskDetails that is taken from the taskList and be written to the dataFile.
     *
     * @param taskDetail which is the detail of the task from the TaskList.
     * @throws IOException set of errors that is used to govern the creation of
     * a new file.
     */
    public void writeToFile(String taskDetail) throws IOException {
        FileWriter addTask = new FileWriter(filePath);
        addTask.write(taskDetail);
        addTask.close();
    }

    /**
     * Gets the data from the TaskList which is the taskDetail
     * and puts the data in the dataFile.
     *
     * @param taskDetail which is the detail of the task from the TaskList.
     */
    public void getDataToFile(String taskDetail) {
        try {
            writeToFile(taskDetail);
        } catch (IOException e) {
            Ui.showDataToFileError();
        }
    }

    /**
     * Saves the data that is present to the file. Use for updating.
     *
     * @param countTask which keeps tracks of the number of task that have been added.
     * @param assignments that an array of tasks taken from the class TaskList.
     */
    public void saveToFile(int countTask, ArrayList<Task> assignments) {
        StringBuilder taskList = new StringBuilder();
        for(int i = 0; i < countTask; i++) {
            addToTaskList(taskList, assignments, i);
        }
        getDataToFile(taskList.toString());
    }

    /**
     * Adds the data that is present in the dataFile to the TaskList which is
     * the local memory when the program is running.
     *
     * @param taskList which is used to store the data from the dataFile
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param index which is used to access the specific task detail.
     */
    public void addToTaskList(StringBuilder taskList, ArrayList<Task> assignments, int index) {
        taskList.append(getTaskDetail(assignments, index)).append("\n");
    }

    /**
     * Gets the Task Detail from the taskList and format it so that it can
     * be stored in the dataFile.
     *
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param index which is used to access the specific task detail.
     * @return taskDetail that has been formatted.
     */
    public String getTaskDetail(ArrayList<Task> assignments , int index) {
        String statusOfTypeTask = assignments.get(index)
                .getStatusOfTypeTask();
        String changeStatusOfDone = changeStatusOfDone(assignments
                .get(index).getStatusOfDone());
        String changeTaskDetails = changeDetailDisplay(assignments, index);
        return statusOfTypeTask + " | " + changeStatusOfDone + " | " + changeTaskDetails;
    }

    /**
     * Changes the statusOfDone from "X" to "1" so that it can be stored into the dataFile.
     *
     * @param statusOfDone which is denoted by "X" to tell the user that the task is completed.
     * @return statusOfDone which is formatted.
     */
    public String changeStatusOfDone(String statusOfDone) {
        boolean isChecked = statusOfDone.contains("X");
        if (isChecked) {
            statusOfDone = "1";
        } else {
            statusOfDone = "0";
        }
        return statusOfDone;
    }

    /**
     * Changes the detail of the task that is then used to be stored in the dataFile.
     *
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param index which is used to access the specific task detail.
     * @return changeTaskDetail which is formatted to suit the dataFile format.
     */
    private String changeDetailDisplay(ArrayList<Task> assignments, int index) {
        String changeTaskDetails = assignments.get(index).getDescription();
        checkStoredInputStatus(assignments, index);
        changeTaskDetails = modifyEventOrDeadlineDetails(assignments, index, changeTaskDetails);
        return changeTaskDetails;
    }

    /**
     * Checks the stored input status in the dataFile to see if it is a deadline or event task.
     *
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param index which is used to access the specific task detail.
     */
    public void checkStoredInputStatus(ArrayList<Task> assignments, int index) {
        isStoredInputOfEvent = assignments.get(index)
                .getStatusOfTypeTask().contains("E");
        isStoredInputOfDeadline = assignments.get(index)
                .getStatusOfTypeTask().contains("D");
    }

    /**
     * Modifies the event or deadline details so that it can be stored in the dataFile.
     *
     * @param assignments that an array of tasks taken from the class TaskList.
     * @param index which is used to access the specific task detail.
     * @param changeTaskDetails which is formatted to suit the dataFile format.
     * @return changeTaskDetails which is formatted to suit the dataFile format.
     */
    public String modifyEventOrDeadlineDetails(ArrayList<Task> assignments, int index, String changeTaskDetails) {
        if (isStoredInputOfEvent) {
            changeTaskDetails = assignments.get(index)
                    .getDescription().replace("/at", "|");
        } else if (isStoredInputOfDeadline) {
            changeTaskDetails = assignments.get(index)
                    .getDescription().replace("/by", "|");
        }
        return changeTaskDetails;
    }

    /**
     * Stores the dataFile Contents into an array list for later usage.
     *
     * @return contentList which an array of string that gets the data from the dataFile.
     */
    public ArrayList<String> storeDataFileContents() {
        ArrayList<String> contentList = new ArrayList<>();
        try {
            contentList = getDataFileContents();
        } catch (FileNotFoundException e) {
            Ui.showFileError();
        }
        return contentList;
    }

    /**
     * Gets the data File contents from the dataFile.
     *
     * @return contentList which an array of string that gets the data from the dataFile.
     * @throws FileNotFoundException which is an error if the file is not found.
     */
    public ArrayList<String> getDataFileContents() throws FileNotFoundException {
        Scanner s = new Scanner(dataFile);
        ArrayList<String> contentList = new ArrayList<>();
        while (s.hasNext()) {
            contentList.add(s.nextLine());
        }
        return contentList;
    }

    /**
     * Loads the tasks from the dataFile and into the taskList.
     *
     * @param ui which is taken from the class Ui to display messages.
     * @return TaskList which is initialises the class.
     */
    public TaskList loadTasksFromFile(Ui ui) {
        dataFileStatus(ui);
        ArrayList<Task> tasks = new ArrayList<>();
        int numOfTask = storeDataFileContents().size();
        int lastIndex = numOfTask - 1;
        checkEmptyTask(numOfTask);
        countTask = numOfTask;
        ArrayList<String> taskList = storeDataFileContents();
        for(int i = 0; i < numOfTask; i++) {
            String[] task = taskList.get(i).split("\\|");
            addTaskFromFile(task,tasks);
            addTaskIndex(lastIndex, i);
        }
        return new TaskList(tasks, indexTask, countTask);
    }

    /**
     * Adds taskIndex incrementally as long it is not the last index.
     *
     * @param lastIndex which is the last index of an array.
     * @param i the increment in regard to the loop.
     */
    private void addTaskIndex(int lastIndex, int i) {
        if (i != lastIndex) {
            indexTask++;
        }
    }

    /**
     * Checks if the taskList is empty or not.
     *
     * @param numOfTask which is the number of task in the taskList.
     */
    public void checkEmptyTask(int numOfTask) {
        if (numOfTask == NO_TASK) {
            isEmptyTask = true;
        }
    }

    /**
     * Adds the tasks from the file to the taskList.
     *
     * @param task which is the task details that is obtained from the dataFile.
     * @param tasks an array of assignments that is from taskList.
     */
    public void addTaskFromFile(String[] task, ArrayList<Task> tasks) {
        checkDataFileCommand(task);
        addTypeOfTaskFromFile(task, tasks);
        checkTaskStatusFromFile(tasks);
    }

    /**
     * Checks the dataFile commands in so to classify them according to type of tasks.
     *
     * @param task task which is the task details that is obtained from the dataFile.
     */
    public void checkDataFileCommand(String[] task) {
        isToDoFromDataFile = task[COMMAND_INDEX].contains("T");
        isDeadlineFromDateFile = task[COMMAND_INDEX].contains("D");
        isEventFromDataFile = task[COMMAND_INDEX].contains("E");
        isMarkFromDataFile = task[STATUS_INDEX].contains("1");
    }

    /**
     * Adds the type of tasks to the taskList from the file.
     *
     * @param task task which is the task details that is obtained from the dataFile.
     * @param tasks an array of assignments that is from taskList.
     */
    public void addTypeOfTaskFromFile(String[] task, ArrayList<Task> tasks) {
        if (isToDoFromDataFile) {
            addToDoTaskFromFile(task, tasks);
        } else if (isDeadlineFromDateFile) {
            addDeadlineTaskFromFile(task, tasks);
        } else if (isEventFromDataFile) {
            addEventTaskFromFile(task, tasks);
        }
    }

    /**
     * Checks the task status that is obtained from the dataFile to check if
     * the task has been completed or not.
     *
     * @param tasks which is the task details that is obtained from the dataFile.
     */
    public void checkTaskStatusFromFile(ArrayList<Task> tasks) {
        if (isMarkFromDataFile) {
            tasks.get(indexTask).markAsDone();
        }
    }

    /**
     * Adds the events task to the taskList from the dataFile.
     *
     * @param task task which is the task details that is obtained from the dataFile.
     * @param tasks an array of assignments that is from taskList.
     */
    public void addEventTaskFromFile(String[] task, ArrayList<Task> tasks) {
        String taskDetail = task[TASK_TITLE_INDEX].trim()
                + " /at" + task[EVENT_OR_DEADLINE_DETAIL];
        Task assignment = new Event(taskDetail);
        loadTaskFromFile(assignment, tasks);
        tasks.get(indexTask).markTypeTask();
    }

    /**
     * Adds the deadline task to the taskList from the dataFile.
     *
     * @param task task which is the task details that is obtained from the dataFile.
     * @param tasks an array of assignments that is from taskList.
     */
    public void addDeadlineTaskFromFile(String[] task, ArrayList<Task> tasks) {
        String taskDetail = task[TASK_TITLE_INDEX].trim()
                + " /by" + task[EVENT_OR_DEADLINE_DETAIL];
        Task assignment = new Deadline(taskDetail);
        loadTaskFromFile(assignment, tasks);
        tasks.get(indexTask).markTypeTask();
    }

    /**
     * Adds the Todo task to the taskList from the dataFile.
     *
     * @param task task which is the task details that is obtained from the dataFile.
     * @param tasks an array of assignments that is from taskList.
     */
    public void addToDoTaskFromFile(String[] task, ArrayList<Task> tasks) {
        Task assignment = new ToDo(task[TASK_TITLE_INDEX].trim());
        loadTaskFromFile(assignment, tasks);
        tasks.get(indexTask).markTypeTask();
    }

    /**
     * Loads the task from the dataFile and store it into the taskList.
     *
     * @param assignment which is a specific task that is obtained from the taskList.
     * @param tasks an array of assignments that is from taskList.
     */
    public static void loadTaskFromFile(Task assignment, ArrayList<Task> tasks) {
        tasks.add(assignment);
    }


}
