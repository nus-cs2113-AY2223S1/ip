package duke.taskmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public final String FILE_PATH = "data/duke.txt";
    public ArrayList<String> storedTasks = new ArrayList<>();
    public Storage() throws FileNotFoundException {
        File f = new File(FILE_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String command = s.nextLine();
            storedTasks.add(command);
        }
    }
    public Storage(String forSaving) {

    }
    public void saveTasks(StringBuilder toSave) throws IOException {
        File file = new File("data/duke.txt");
        FileWriter fw;
        file.getParentFile().mkdirs();
        file.createNewFile();
        fw = new FileWriter(file);
        fw.write(String.valueOf(toSave));
        fw.close();
    }
}
