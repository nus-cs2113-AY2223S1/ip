package duke;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private final String filePath;
    /**
     * Constructs a storage
     * @param path The file path specified by the duke.Duke bot.
     */
    public Storage(String path){
        filePath = path;
    }

    /**
     * @return A recovered list of previous work or a blank list if no cache.
     */
    public ArrayList<TaskList> printPath() {
        try {
            //create path if it did not exist
            File file = new File(System.getProperty("user.dir") + "/data");
            if (!file.exists()) {
                Path path = Paths.get(System.getProperty("user.dir") + "/data");
                Files.createDirectories(path);
                assert (file.exists());
            }
            //create file if it did not exist
            file = new File(filePath);
            if (file.exists()) {
                return retrival(file);
            } else {
                file.createNewFile();
                assert(file.exists());
                return new ArrayList<>();
            }

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Returns an ArrayList of previously recorded tasks.
     * @return A recovered list of previous work or a blank list if no cache.
     */
    public static ArrayList<TaskList> retrival(File f) {
        ArrayList<TaskList> ListOfTasks = new ArrayList<>();
        String[] commands;
        String commandType;
        String description;
        boolean isDone;
        Scanner sc;
        try {
            sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                commands = line.split(" \\| ", 3);
                commandType = commands[0].trim();
                isDone = commands[1].trim().equals("1") ? true : false;
                description = commands[2].trim();


                if (commandType.equals("T")) {
                    ListOfTasks.add(ui.ToDos(description, isDone));
                } else if (commandType.equals("D")) {
                    ListOfTasks.add(ui.Deadlines(description, isDone));
                } else if (commandType.equals("E")) {
                    ListOfTasks.add(ui.Events(description, isDone));
                }
            }
            return ListOfTasks;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Updates the records in cache file once for all before closing duke.Duke.
     * @param ListOfTasks list to be recorded.
     */
    public void update(ArrayList<TaskList> ListOfTasks){
        try {
            FileWriter writer = new FileWriter(this.filePath);
            StringBuilder builder = new StringBuilder();
            for (TaskList task : ListOfTasks) {
                builder.append(task.recordString());
                builder.append("\n");
            }
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

