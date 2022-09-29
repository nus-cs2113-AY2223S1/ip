import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Storage implements FileIO {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * read the saved data file and load it to software
     *
     * @return the task list saved in data file
     */
    public ArrayList<Task> loadSave() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks = FileIO.loadFile(filePath);
        } catch (CorruptedDataFileException e) {
            System.out.println("The saved file is corrupted.");
        } catch (FileNotFoundException e) {
            System.out.println("The saved file is not found");
        }
        System.out.println("Saved file is successfully loaded!");
        for (Task task : tasks) {
            System.out.println(task);
        }
        return tasks;
    }

    /**
     * Write the current task list to storage
     *
     * @param tasks the current task list
     */
    public void writeSave(ArrayList<Task> tasks) {
        try {
            FileIO.writeFile(tasks, filePath);
        } catch (IOException e) {
            System.out.format("IO exception occurs:%n%s", e.getMessage());
        }
    }
}
