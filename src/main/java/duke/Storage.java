package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains the methods used to manage the task list data in the file and the file itself.
 */
public class Storage {
    protected static final String PATHNAME = "./data/duke.txt";

    /**
     * Reads and loads the existing data from the file into the program.
     *
     * @return ArrayList<Task> tasks The list of tasks stored in the file.
     * @throws FileNotFoundException if the file is not found in the stated path.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(PATHNAME);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String inputFromFile = s.nextLine();
            String[] details = inputFromFile.split("\\|");
            Task task;
            switch (details[0]) {
            case "T":
                task = new Todo(details[2], details[1].equals("1"));
                tasks.add(task);
                break;
            case "D":
                task = new Deadline(details[2], details[1].equals("1"), details[3]);
                tasks.add(task);
                break;
            case "E":
                task = new Event(details[2], details[1].equals("1"), details[3]);
                tasks.add(task);
                break;
            default:
                System.out.println("Error reading from file");
                break;
            }
        }
        return tasks;
    }

    /**
     * Creates a file in the specified file path and checks if it exists to prevent overwriting
     * of the data in the file.
     */
    public void createFile() {
        try {
            load();
        } catch (FileNotFoundException exception) {
            System.out.println("Error reading data from file: File or directory does not exist.\n");
            try {
                System.out.println("Trying to create file now.");
                File file = new File(PATHNAME);
                if (!file.getParentFile().mkdirs()) {
                    System.out.println("Error: Directory could not be created");
                }
                if (file.createNewFile()) {
                    System.out.println("File is created at: " + PATHNAME);
                } else {
                    System.out.println("File already exists at: " + PATHNAME);
                }
            } catch (IOException ioException) {
                System.out.println("Error: File could not be created");
                ioException.printStackTrace();
            }
        }
    }

    /**
     * Inserts the list of tasks data into the file, so that it can be stored
     * and loaded when the program is rebooted.
     *
     * @param tasks the list of tasks to be formatted and inserted into the file.
     * @throws IOException if there is an error while inserting the data into the file.
     */
    public void insertIntoFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(PATHNAME);
        for (Task task : tasks) {
            fw.write(task.convertToFileFormat());
        }
        fw.close();
    }
}
