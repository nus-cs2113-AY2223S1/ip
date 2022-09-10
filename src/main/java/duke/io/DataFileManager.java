package duke.io;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class DataFileManager {
    private final String DATA_FILE_NAME = "dukeData.txt";
    private final String DATA_DIRECTORY_NAME = "data";
    private final Path DATA_FILE_PATH = Paths.get(DATA_DIRECTORY_NAME + "/" + DATA_FILE_NAME);
    private final Path DATA_DIRECTORY_PATH = Paths.get(DATA_DIRECTORY_NAME);

    public DataFileManager() {
    }

    public void saveProgramData(String dataToWrite) throws IOException {

        if (!Files.exists(DATA_DIRECTORY_PATH)) {
            Files.createDirectories(DATA_DIRECTORY_PATH);
        }

        if (!Files.exists(DATA_FILE_PATH)) {
            new File(DATA_DIRECTORY_NAME + "/" + DATA_FILE_NAME);
        }

        try (FileWriter fw = new FileWriter(DATA_DIRECTORY_NAME + "/" + DATA_FILE_NAME)) {
            fw.write(dataToWrite);
        }
    }


    public ArrayList<Task> loadProgramData() {
        ArrayList<Task> output = new ArrayList<>();

        if (!Files.exists(DATA_DIRECTORY_PATH) || !Files.exists(DATA_FILE_PATH)) {
            return new ArrayList<>();
        }


        File f = new File(DATA_DIRECTORY_NAME + "/" + DATA_FILE_NAME);
        Scanner sc;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read data file, initialising program with empty TODO list");
            return new ArrayList<>();
        }


        try {
            while (sc.hasNextLine()) {
                //@@Author Raman Sahasi
                //Reused from https://stackoverflow.com/questions/41953388/java-split-and-trim-in-one-shot
                //with minor modifications
                String[] data = sc.nextLine().split("\\s*[|]\\s*");
                //@@author

                String taskType = data[0];
                boolean isDone = data[1].equals("1");
                String description = data[2];

                switch (taskType) {
                case "T":
                    ToDo todo = new ToDo(description);
                    if (isDone) {
                        todo.markAsDone();
                    }
                    output.add(todo);
                    break;
                case "D":
                    String by = data[3];
                    Deadline deadline = new Deadline(description, by);
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    output.add(deadline);
                    break;
                case "E":
                    String at = data[3];
                    Event event = new Event(description, at);
                    if (isDone) {
                        event.markAsDone();
                    }
                    output.add(event);
                    break;
                default:
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            System.out.println("Data file data/dukeData.txt is corrupted. Initialising program with empty TODO list.");
            return new ArrayList<>();
        }
        sc.close();
        return output;

    }


}
