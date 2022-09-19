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
import java.util.InputMismatchException;
import java.util.Scanner;


public class Storage {
    private final String dataFileName;
    private final String dataDirectoryName;
    private final Path dataFilePath;
    private final Path dataDirectoryPath;

    public Storage (String dataFileName, String dataDirectoryName) {
        this.dataFileName = dataFileName;
        this.dataDirectoryName = dataDirectoryName;
        this.dataFilePath = Paths.get(dataDirectoryName + "/" + dataFileName);
        this.dataDirectoryPath = Paths.get(dataDirectoryName);
    }

    public void saveProgramData(String dataToWrite) throws IOException {

        if (!Files.exists(dataDirectoryPath)) {
            Files.createDirectories(dataDirectoryPath);
        }

        if (!Files.exists(dataFilePath)) {
            new File(dataDirectoryName + "/" + dataFileName);
        }

        try (FileWriter fw = new FileWriter(dataDirectoryName + "/" + dataFileName)) {
            fw.write(dataToWrite);
        }
    }


    public ArrayList<Task> loadProgramData() {
        ArrayList<Task> output = new ArrayList<>();

        if (!Files.exists(dataDirectoryPath) || !Files.exists(dataFilePath)) {
            return new ArrayList<>();
        }


        File f = new File(dataDirectoryName + "/" + dataFileName);
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
                    String date = data[3];
                    String time = data [4];
                    Deadline deadline = new Deadline(description, date, time);
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    output.add(deadline);
                    break;
                case "E":
                    date = data[3];
                    time = data [4];
                    Event event = new Event(description, date, time);
                    if (isDone) {
                        event.markAsDone();
                    }
                    output.add(event);
                    break;
                default:
                    throw new InputMismatchException();
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
