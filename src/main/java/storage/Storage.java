package storage;

import process.TaskManager;
import exception.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * manages the storage and restoration of task data
 */
public class Storage {
    static java.nio.file.Path path = java.nio.file.Paths.get("src","main", "data");
    static java.nio.file.Path filePath = java.nio.file.Paths.get("src","main", "data","duke.txt");
    TaskManager taskManager;

    public void restoreFromDisk(TaskManager taskManager) throws DukeException {
        this.taskManager = taskManager;
        boolean directoryExists = java.nio.file.Files.exists(path);
        boolean fileExists = java.nio.file.Files.exists(filePath);
        File file = new File(filePath.toString());
        if (!directoryExists) {
            file.getParentFile().mkdirs();
        }
        if (!fileExists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("CreatFileError");
            }
        } else {
            try {
                readFileAndRestore(file);
            } catch (FileNotFoundException e) {
                throw new DukeException("RestoreFileError");
            }
        }
    }

    static public void writeToFile(String[] writeLines) throws IOException{
        FileWriter fw = new FileWriter(filePath.toString());
        for (String line : writeLines) {
            fw.write(line+System.lineSeparator());
        }
        fw.close();
    }

    private void readFileAndRestore(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if(line.trim().isEmpty())
                continue;
            try {
                this.taskManager.handleRestoration(line);
            } catch (DukeException e) {
                e.handleError();
            }
        }
    }
}
