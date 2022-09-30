import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final Path directoryPath = Paths.get(".","data");
    private static final Path filePath = Paths.get(".","data", "duke.txt");

    public Storage () {
    }

    private Task loadTasks (String[]  line) throws DukeException {
        Task newTask;
        if (line[0].equals("T")) {
            newTask = new Todo(line[2]);
        } else if (line[0].equals("D")) {
            newTask = new Deadline(line[2], line[3]);
        } else {
            newTask = new Event(line[2], line[3]);
        }
        return newTask;
    }

    /**
     * Loads inputs from file when program is restarted
     */
    public ArrayList<Task> load() throws DukeException {
        if (Files.exists(directoryPath) && Files.exists(filePath)) {
            File f = new File(String.valueOf(filePath)); // create a File for the given file path
            Scanner s;
            try {
                s = new Scanner(f); // create a Scanner using the File as the source
            } catch (FileNotFoundException e) {
                throw new DukeException("☹ OOPS!!! Something went wrong when trying to load");
            }
            ArrayList<Task> inputs = new ArrayList<>();
            while (s.hasNext()) {
                String[] line = s.nextLine().split("\\|");
                Task newTask = loadTasks(line);
                if (newTask != null) {
                    if (line[1].equals("1")) {
                        newTask.markAsDone();
                    } else {
                        newTask.markAsNotDone();
                    }
                    inputs.add(newTask);
                } else {
                    throw new DukeException("☹ OOPS!!! Something went wrong when trying to load");
                }
            }
            return inputs;
        } else {
            throw new DukeException("☹ OOPS!!! Something went wrong when trying to load");
        }
    }

    private static String saveList(TaskList list) {
        String formattedString = "";
        for (int i = 0; i < list.getList().size(); i++) {
            if (list.getList().get(i) == null) {
                break;
            }
            formattedString += list.getList().get(i).toSaveString() + "\n";
        }
        return formattedString;
    }

    private static void checkExists() throws IOException {
        boolean directoryExists = Files.exists(directoryPath);
        boolean fileExists = Files.exists(filePath);
        if (!directoryExists) {
            Files.createDirectories(directoryPath);
        }
        if (!fileExists) {
            Files.createFile(filePath);
        }
    }

    /**
     * Saves current inputs into file for future use
     */
    public void save(TaskList list) throws DukeException {
        try {
            checkExists();
        } catch (IOException e) {
            throw new DukeException("Something went wrong when trying to save: " + e.getMessage());
        }
        String pathName = "./data/duke.txt";
        try {
            FileWriter fw = new FileWriter(pathName);
            fw.write(saveList(list));
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong when trying to save: " + e.getMessage());
        }
    }
}
