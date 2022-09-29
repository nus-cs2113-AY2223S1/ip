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

    public Storage(String filePath) {
        this.filePath = filePath;
        this.newDirName = "data";
        this.dataFile = new File(filePath);
        this.dataDir = new File(newDirName);
    }

    /**
     * Gets the data file if it does not exist or inform the User that it already exist.
     *
     */
    public void dataFileStatus(Ui ui) {
        boolean isNoDir = dataDir.mkdir();
        createFileOrFolder(ui, isNoDir);
    }

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

    public void createDir(Ui ui) {
        ui.showDataFolderStatus();
        try {
            createDataFile(ui);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public void createDataFile(Ui ui) throws DukeException {
        try {
            getFileStatus(ui);
        } catch (IOException e) {
            throw new DukeException("\n\t Error in trying to locate or make data file");
        }
    }

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

    public void writeToFile(String taskDetail) throws IOException {
        FileWriter addTask = new FileWriter(filePath);
        addTask.write(taskDetail);
        addTask.close();
    }

    public void dataToFile(String taskDetail) {
        try {
            writeToFile(taskDetail);
        } catch (IOException e) {
            Ui.showDataToFileError();
        }
    }

    public void saveToFile(int countTask, ArrayList<Task> assignments) {
        StringBuilder taskList = new StringBuilder();
        for(int i = 0; i < countTask; i++) {
            addToTaskList(taskList, assignments, i);
        }
        dataToFile(taskList.toString());
    }

    public void addToTaskList(StringBuilder taskList, ArrayList<Task> assignments, int index) {
        taskList.append(getTaskDetail(assignments, index)).append("\n");
    }

    public String getTaskDetail(ArrayList<Task> assignments , int index) {
        String statusOfTypeTask = assignments.get(index)
                .getStatusOfTypeTask();
        String changeStatusOfDone = changeStatusOfDone(assignments
                .get(index).getStatusOfDone());
        String changeTaskDetails = changeDetailDisplay(assignments, index);
        return statusOfTypeTask + " | " + changeStatusOfDone + " | " + changeTaskDetails;
    }

    public String changeStatusOfDone(String statusOfDone) {
        boolean isChecked = statusOfDone.contains("X");
        if (isChecked) {
            statusOfDone = "1";
        } else {
            statusOfDone = "0";
        }
        return statusOfDone;
    }

    private String changeDetailDisplay(ArrayList<Task> assignments, int index) {
        String changeTaskDetails = assignments.get(index).getDescription();
        checkStoredInputStatus(assignments, index);
        changeTaskDetails = modifyEventOrDeadlineDetails(assignments, index, changeTaskDetails);
        return changeTaskDetails;
    }

    public void checkStoredInputStatus(ArrayList<Task> assignments, int index) {
        isStoredInputOfEvent = assignments.get(index)
                .getStatusOfTypeTask().contains("E");
        isStoredInputOfDeadline = assignments.get(index)
                .getStatusOfTypeTask().contains("D");
    }

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

    public ArrayList<String> storeDataFileContents() {
        ArrayList<String> contentList = new ArrayList<>();
        try {
            contentList = getDataFileContents();
        } catch (FileNotFoundException e) {
            Ui.showFileError();
        }
        return contentList;
    }

    public ArrayList<String> getDataFileContents() throws FileNotFoundException {
        Scanner s = new Scanner(dataFile);
        ArrayList<String> contentList = new ArrayList<>();
        while (s.hasNext()) {
            contentList.add(s.nextLine());
        }
        return contentList;
    }

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

    private void addTaskIndex(int lastIndex, int i) {
        if (i != lastIndex) {
            indexTask++;
        }
    }

    public void checkEmptyTask(int numOfTask) {
        if (numOfTask == NO_TASK) {
            isEmptyTask = true;
        }
    }

    public void addTaskFromFile(String[] task, ArrayList<Task> tasks) {
        checkDataFileCommand(task);
        addTypeOfTaskFromFile(task, tasks);
        checkTaskStatusFromFile(tasks);
    }

    public void checkDataFileCommand(String[] task) {
        isToDoFromDataFile = task[COMMAND_INDEX].contains("T");
        isDeadlineFromDateFile = task[COMMAND_INDEX].contains("D");
        isEventFromDataFile = task[COMMAND_INDEX].contains("E");
        isMarkFromDataFile = task[STATUS_INDEX].contains("1");
    }

    public void addTypeOfTaskFromFile(String[] task, ArrayList<Task> tasks) {
        if (isToDoFromDataFile) {
            addToDoTaskFromFile(task, tasks);
        } else if (isDeadlineFromDateFile) {
            addDeadlineTaskFromFile(task, tasks);
        } else if (isEventFromDataFile) {
            addEventTaskFromFile(task, tasks);
        }
    }

    public void checkTaskStatusFromFile(ArrayList<Task> tasks) {
        if (isMarkFromDataFile) {
            tasks.get(indexTask).markAsDone();
        }
    }

    public void addEventTaskFromFile(String[] task, ArrayList<Task> tasks) {
        String taskDetail = task[TASK_TITLE_INDEX].trim()
                + " /at" + task[EVENT_OR_DEADLINE_DETAIL];
        Task assignment = new Event(taskDetail);
        loadTaskFromFile(assignment, tasks);
        tasks.get(indexTask).markTypeTask();
    }

    public void addDeadlineTaskFromFile(String[] task, ArrayList<Task> tasks) {
        String taskDetail = task[TASK_TITLE_INDEX].trim()
                + " /by" + task[EVENT_OR_DEADLINE_DETAIL];
        Task assignment = new Deadline(taskDetail);
        loadTaskFromFile(assignment, tasks);
        tasks.get(indexTask).markTypeTask();
    }

    public void addToDoTaskFromFile(String[] task, ArrayList<Task> tasks) {
        Task assignment = new ToDo(task[TASK_TITLE_INDEX].trim());
        loadTaskFromFile(assignment, tasks);
        tasks.get(indexTask).markTypeTask();
    }

    public static void loadTaskFromFile(Task assignment, ArrayList<Task> tasks) {
        tasks.add(assignment);
    }


}
