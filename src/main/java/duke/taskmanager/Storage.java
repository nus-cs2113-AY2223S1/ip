package duke.taskmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the saving and loading of <code>task</code>s.
 */
public class Storage {
    public final String FILE_PATH = "data/duke.txt";
    public ArrayList<String> storedTasks = new ArrayList<>();

    /**
     * Loads previously saved tasks.
     *
     * @throws FileNotFoundException there was no previously saved tasks
     */
    public Storage() throws FileNotFoundException {
        File f = new File(FILE_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String command = s.nextLine();
            storedTasks.add(command);
        }
    }

    /**
     * There are no previously saved task. Create this <code>Storage</code> class to save tasks in the future.
     *
     * @param ignoredForSaving random text
     */
    public Storage(String ignoredForSaving) {
    }

    /**
     * Saves all current tasks by overriding the file.
     *
     * @param toSave       all current tasks formatted as commands
     * @throws IOException failed or interrupted I/O operations
     */
    public void saveTasks(StringBuilder toSave) throws IOException {
        File file = new File("data/duke.txt");
        FileWriter fw;
        if (file.getParentFile().mkdirs()) {
            file.createNewFile();
        }
        fw = new FileWriter(file);
        fw.write(String.valueOf(toSave));
        fw.close();
    }
}
