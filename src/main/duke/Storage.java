package main.duke;
import main.duke.exception.DukeException;
import main.duke.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/** Controls how tasks are saved into the data file and loaded out of the data file */
public class Storage {

    private TaskList taskList;
    private boolean isFileLoaded = false;
    private static Parser parser;

    /** Initializes a new instance of Storage */
    public Storage() {
        parser = new Parser();
    }

    /** Executes loading tasks from a saved data file and handles error cases */
    public void loadTasksOrError() {
        try {
            loadTasks();
            isFileLoaded = true;
        } catch (DukeException dukeException) {
            System.out.println(Utils.INDENT
                    + dukeException.getMessage() + Utils.INDENT + "No tasks loaded." + Utils.H_LINE + "\n");
        }

        if (isFileLoaded) {
            System.out.print(Utils.INDENT + "Loaded tasks from data file successfully!");
            taskList.printList();
            System.out.println(Utils.H_LINE + "\n");
        }
    }

    /** Returns if a file was loaded in (i.e. there were existing tasks loaded into the program) */
    public boolean getIsFileLoaded() {
        return isFileLoaded;
    }

    /** Returns the current task list after it is loaded */
    public TaskList getTaskList() {
        return taskList;
    }

    /** Updates the current task list for saving purposes */
    public void updateTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    private void loadTasks() throws DukeException {
        if (foundTaskFile()) {
            System.out.print(Utils.H_LINE + Utils.INDENT + "Found a data file! Loading it in...");
            try {
                File taskFile = new File("./data/tasks.txt");
                Scanner scanner = new Scanner(taskFile);
                if (!scanner.hasNext()) {
                    throw new DukeException("Found an empty file!");
                }
                String firstText = scanner.nextLine();
                if (!firstText.equals("start")) {
                    throw new DukeException("File is not formatted correctly (could not find start).");
                }
                this.taskList = new TaskList();
                String taskString = scanner.nextLine();
                while (!taskString.equals("finish")) {
                    convertStringToTask(taskString);
                    if (Utils.getListIndex() >= Utils.TASK_LIMIT) {
                        throw new DukeException("File is not formatted correctly (could not find end). Or, tried to add too many tasks.");
                    }
                    taskString = scanner.nextLine();
                }
            } catch (FileNotFoundException fileException) {
                throw new DukeException("Unable to load the data text file.");
            }
        } else {
            System.out.print(Utils.H_LINE);
            throw new DukeException("No previous data found!");
        }
    }

    private void convertStringToTask(String taskString) throws DukeException {
        Pattern taskPattern = Pattern.compile("^\\[[TDE]\\]\\[[X\\s]\\]\\s+.+$");
        boolean matchesTask = taskPattern.matcher(taskString).find();
        if (!matchesTask) {
            throw new DukeException("File is not formatted correctly (could not properly read tasks) for task: " + taskString);
        }
        Task task;
        String taskType = taskString.substring(1, 2);
        String mark = taskString.substring(4, 5);
        int descriptionIndex = "[T][ ] ".length();

        switch (taskType) {
        case "T":
            task = parser.createTodo("todo " + taskString.substring(descriptionIndex));
            break;
        case "D":
            task = parser.createDeadline("deadline " + taskString.substring(descriptionIndex));
            break;
        case "E":
            task = parser.createEvent("event " + taskString.substring(descriptionIndex));
            break;
        default:
            throw new DukeException("An incorrectly formatted task was passed in to the data: " + taskString);
        }

        switch (mark) {
        case "X":
            task.mark();
            break;
        case " ":
            task.unmark();
            break;
        default:
            throw new DukeException("An incorrectly marked task was passed in to the data: " + taskString);
        }

        taskList.add(task);
        Utils.incrementListIndex();
    }

    private boolean foundTaskFile() {
        File dataFolder = new File(Utils.FOLDER_PATH);
        if (!dataFolder.exists()) {
            return false;
        }
        File taskFile = new File(Utils.FILE_PATH);
        if (!taskFile.exists()) {
            return false;
        }
        return true;
    }

    /** Executes saving tasks to a data file and handles error cases */
    public void saveTasksOrError() {
        try {
            saveTasks();
        } catch (DukeException dukeException) {
            System.out.println(Utils.H_LINE + Utils.INDENT
                    + dukeException.getMessage() + Utils.INDENT + "Unable to save tasks." + Utils.H_LINE + "\n");
        }

        System.out.println(Utils.H_LINE + Utils.INDENT + "Saved tasks to data file successfully!");
    }

    private void saveTasks() throws DukeException {
        try {
            File taskFile = ensureExists();
            FileWriter fw = new FileWriter(taskFile);
            fw.write("start" + System.lineSeparator());
            for (int i = 0; i < Utils.getListIndex(); i++) {
                fw.write(this.taskList.get(i).dataString() + System.lineSeparator());
            }
            fw.write("finish");
            fw.close();
        } catch (DukeException dukeException) {
            throw dukeException;
        } catch (IOException e) {
            throw new DukeException("Unknown error with saving tasks: " + e.getMessage());
        }
    }

    private static File ensureExists() throws DukeException {
        File dataFolder = new File(Utils.FOLDER_PATH);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        File taskFile = new File(Utils.FILE_PATH);
        if (!taskFile.exists()) {
            try {
                taskFile.createNewFile();
            } catch (Exception e) {
                throw new DukeException("Unable to create a data text file.");
            }
        }
        return taskFile;
    }

}
