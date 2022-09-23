package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents the file used to store task list.
 */
public class Storage {
    private String filePath;
    private File storageFile;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.storageFile = new File(filePath);
    }

    /**
     * Loads the data from the storage file and returns it.
     *
     * @return Tasks.
     * @throws IOException If error caused by reading file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<String> fileContent = readFile();
        Parser parser = new Parser();
        ArrayList<Task> taskList = parser.decodeTaskListFromFile(fileContent);
        return taskList;
    }

    private ArrayList<String> readFile() throws IOException {
        if (!storageFile.getParentFile().exists()) {
            storageFile.getParentFile().mkdirs();
        }
        if (!storageFile.exists()) {
            storageFile.createNewFile();
            return new ArrayList<String>();
        }
        return (ArrayList<String>) Files.readAllLines(Path.of(filePath));
    }


    /**
     * Overwrites the storage file.
     *
     * @param tasks tasks used to overwrite
     * @throws IOException If error caused by writing to the storage file occurs.
     */
   public void overWriteDukeTxt(ArrayList<Task> tasks) throws IOException {
        String newText = generateNewText(tasks);
        writeToFile(newText);
    }

    private String generateNewText(ArrayList<Task> tasks) {
        String newText = "";
        for (Task task : tasks) {
            newText += task.toString();
        }
        return newText;
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }

    /**
     * Appends to the storage file.
     *
     * @param textToAppend String to be added.
     * @throws IOException If error caused by writing occurs.
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(textToAppend);
        fileWriter.close();
    }
}
