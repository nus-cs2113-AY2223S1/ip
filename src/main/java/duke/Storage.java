package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    private static final String TASK_ATTRIBUTE_DELIMITER = " \\| ";

    public ArrayList<Task> loadTasks() {
        accessDukeFile();
        return tasks;
    }
    private void accessDukeFile() {
        File dukeFile = new File(filePath);
        if (dukeFile.exists()) {
            try {
                retrieveFileData(dukeFile);
            } catch (FileNotFoundException e) {
                Ui.showMessage("Duke data file cannot be accessed");
                Ui.showException(e);
            } catch (InvalidTaskFormatException e) {
                Ui.showMessage("Invalid task found in Duke file. Task is omitted from the list.");
                Ui.showException(e);
            }
        } else {
            try {
                createDukeFile(dukeFile);
            } catch (IOException e) {
                Ui.showMessage("Duke file creation failed.");
                Ui.showMessage(e.getMessage());
            }
        }
    }
    private void createDukeFile(File dukeFile) throws IOException {
        dukeFile.getParentFile().mkdir();
        dukeFile.createNewFile();
    }

    private void retrieveFileData(File dukeFile) throws FileNotFoundException, InvalidTaskFormatException {
        Scanner scanner = new Scanner(dukeFile);
        while (scanner.hasNext()) {
            String taskData = scanner.nextLine();
            String[] taskAttributes = taskData.split(TASK_ATTRIBUTE_DELIMITER);
            String taskType = taskAttributes[0];
            String taskName = taskAttributes[2];
            boolean isTaskCompleted = Boolean.valueOf(taskAttributes[1]);
            switch (taskType) {
            case "T":
                ToDo toDo = new ToDo(taskName);
                toDo.setIsCompleted(isTaskCompleted);
                tasks.add(toDo);
                break;
            case "D":
                String dueDate = taskAttributes[3];
                Deadline deadline = new Deadline(taskName, dueDate);
                deadline.setIsCompleted(isTaskCompleted);
                tasks.add(deadline);
                break;
            case "E":
                String timeOfEvent = taskAttributes[3];
                Event event = new Event(taskName, timeOfEvent);
                event.setIsCompleted(isTaskCompleted);
                tasks.add(event);
                break;
            default:
                throw new InvalidTaskFormatException();
            }
        }
    }


    private void writeTasksToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : tasks) {
            if (task instanceof ToDo) {
                ToDo toDo = (ToDo) task;
                fileWriter.write(toDo.getFileInput());
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                fileWriter.write(deadline.getFileInput());
            } else if (task instanceof Event) {
                Event event = (Event) task;
                fileWriter.write(event.getFileInput());
            }
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    public void saveTasksData(ArrayList<Task> tasks) {
        try {
            writeTasksToFile(tasks);
        } catch (IOException e) {
            Ui.showMessage("Unable to write task to file: ");
            Ui.showException(e);
        }
    }
}