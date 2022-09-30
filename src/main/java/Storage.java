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
    private static final Path DIRECTORY_PATH = Paths.get(".","data");
    private static final Path FILE_PATH = Paths.get(".","data", "duke.txt");

    public Storage () {
    }

    private static void checkExists() throws IOException {
        boolean directoryExists = Files.exists(DIRECTORY_PATH);
        boolean fileExists = Files.exists(FILE_PATH);
        if (!directoryExists) {
            Files.createDirectories(DIRECTORY_PATH);
        }
        if (!fileExists) {
            Files.createFile(FILE_PATH);
        }
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
     * @return the list of inputs loaded from file
     */
    public ArrayList<Task> load() throws DukeException {
        File f = new File(String.valueOf(FILE_PATH)); // create a File for the given file path

        if (Files.exists(DIRECTORY_PATH) && Files.exists(FILE_PATH)) {
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
            try {
                checkExists();
            } catch (IOException e) {
                throw new DukeException("☹ OOPS!!! Something went wrong when trying to load");
            }
            return new ArrayList<>();
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

    /**
     * Saves current inputs into file for future use
     *
     * @param list the input list to be saved
     */
    public void save(TaskList list) throws DukeException {
        try {
            checkExists();
        } catch (IOException e) {
            throw new DukeException("Something went wrong when trying to save: " + e.getMessage());
        }
        try {
            FileWriter fw = new FileWriter(FILE_PATH.toString());
            fw.write(saveList(list));
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong when trying to save: " + e.getMessage());
        }
    }
}
