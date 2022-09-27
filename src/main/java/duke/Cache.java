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

public class Cache {
    private final String filePath;
    public Cache (String path){
        filePath = path;
    }


    public ArrayList<Task> printPath() {
        try {
            File file = new File(System.getProperty("user.dir") + "/data");
            if (!file.exists()) {
                Path path = Paths.get(System.getProperty("user.dir") + "/data");
                Files.createDirectories(path);
                assert (file.exists());
            }
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

    public static ArrayList<Task> retrival(File f) {
        ArrayList<Task> ListOfTasks = new ArrayList<>();
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
                    ListOfTasks.add(Communication.ToDos(description, isDone));
                } else if (commandType.equals("D")) {
                    ListOfTasks.add(Communication.Deadlines(description, isDone));
                } else if (commandType.equals("E")) {
                    ListOfTasks.add(Communication.Events(description, isDone));
                }
            }
            return ListOfTasks;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(ArrayList<Task> ListOfTasks){
        try {
            FileWriter writer = new FileWriter(this.filePath);
            StringBuilder builder = new StringBuilder();
            for (Task task : ListOfTasks) {
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

