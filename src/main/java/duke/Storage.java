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
    private final String filePath;
    private final File storageFile;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.storageFile = new File(filePath);
    }

    /**
     * Loads the data from the storage file and returns it.
     *
     * @return Tasks.
     * @throws DukeException If error caused by reading file.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<String> fileContent = readFile();
            Parser parser = new Parser();
            return parser.decodeTaskListFromFile(fileContent);
        } catch (IOException e) {
            throw new DukeException("Error loading file");
        }

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
     * @throws DukeException If error caused by writing to the storage file occurs.
     */
   public void overWriteDukeTxt(ArrayList<Task> tasks) throws DukeException {
       try {
           String newText = generateNewText(tasks);
           writeToFile(newText);
       } catch (IOException e) {
           throw new DukeException("Error writing to file");
       }
    }

    private String generateNewText(ArrayList<Task> tasks) {
        StringBuilder newText = new StringBuilder();
        for (Task task : tasks) {
            newText.append(task.toString());
        }
        return newText.toString();
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
     * @throws DukeException If error appending to file.
     */
    public void appendToFile(String textToAppend) throws DukeException{
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            fileWriter.write(textToAppend);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error appending to file");
        }
    }
}
