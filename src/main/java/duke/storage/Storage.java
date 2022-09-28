package duke.storage;

import duke.task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A class that handles the writing and reading of files in the local storage.
 */
public class Storage {

    private static Path taskDataPath = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");
    private static Path dataDirPath = Paths.get(System.getProperty("user.dir"), "data");
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the file by appending to it.
     *
     * @param task The task to be added.
     * @throws IOException If the file is missing.
     */
    public void writeToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(taskDataPath.toString(), true);
        String textToAdd = taskToText(task);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Method that populates an ArrayList based on the tasks in the file.
     * This method will create the file and its directory if they are missing.
     *
     * @return An ArrayList of tasks in the text file.
     */
    public ArrayList<Task> startReading() {
        try {
            makeMissingDirectory();
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found");
        } catch (IOException e) {
            System.out.println("Some IO error has occurred");
        }
        return tasks;
    }

    /**
     * Reads the file from start to finish and identifies the tasks in it.
     *
     * @throws FileNotFoundException If file is not found.
     */
    private void readFile() throws FileNotFoundException {
        String filePath = taskDataPath.toString();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] input = s.nextLine().split(" \\| ");
            switch (input[0]) {
            case ("T"):
                insertTodo(input, tasks);
                break;
            case ("D"):
                insertDeadline(input, tasks);
                break;
            case ("E"):
                insertEvent(input, tasks);
                break;
            }
        }
    }

    /**
     * Makes the directory if it is missing.
     *
     * @throws IOException if the path is not found.
     */
    private void makeMissingDirectory() throws IOException {
        File dir = new File(dataDirPath.toString());
        if (!dir.exists()) {
            dir.mkdir();
        } else {
            return;
        }
        FileWriter fw = new FileWriter(taskDataPath.toString());
    }

    /**
     * Inserts a Todo object into the list of tasks.
     *
     * @param input The substrings a single line in the text file separated by |.
     * @param tasks The list that contains all tasks in the text file.
     */
    private void insertTodo(String[] input, ArrayList<Task> tasks) {
        Todo todo = new Todo(input[2]);
        if (input[1].equals("1")) {
            todo.setDone(true);
        } else {
            todo.setDone(false);
        }
        tasks.add(todo);
    }

    /**
     * Inserts a Deadline object into the list of tasks.
     *
     * @param input The substrings a single line in the text file separated by |.
     * @param tasks The list that contains all tasks in the text file.
     */
    private void insertDeadline(String[] input, ArrayList<Task> tasks) {
        Deadline deadline = new Deadline(input[2], input[3]);
        if (input[1].equals("1")) {
            deadline.setDone(true);
        } else {
            deadline.setDone(false);
        }
        tasks.add(deadline);
    }

    /**
     * Inserts an Event object into the list of tasks.
     *
     * @param input The substrings a single line in the text file separated by |.
     * @param tasks The list that contains all tasks in the text file.
     */
    private void insertEvent(String[] input, ArrayList<Task> tasks) {
        Event event = new Event(input[2], input[3]);
        if (input[1].equals("1")) {
            event.setDone(true);
        } else {
            event.setDone(false);
        }
        tasks.add(event);
    }

    /**
     * Converts a task into its text representation.
     *
     * @param task The task to be converted.
     * @return The text representation of the task.
     */
    private String taskToText(Task task) {
        if (task.getClass() == Todo.class) {
            return "T | " + task.getStatusInNumber() + " | " + task.getDescription() + "\n";
        } else if (task.getClass() == Deadline.class) {
            return "D | " + task.getStatusInNumber() + " | "
                    + task.getDescription() + " | " + ((Deadline) task).getBy() + "\n";
        } else {
            return "E | " + task.getStatusInNumber() + " | "
                    + task.getDescription() + " | " + ((Event) task).getAt() + "\n";
        }
    }

    /**
     * Deletes everything in the text file.
     *
     * @throws IOException If file is missing.
     */
    public void deleteContent() throws IOException {
        new FileWriter(taskDataPath.toString()).close();
    }

    /**
     * Stores a task into the local text file.
     *
     * @param task The task to be stored.
     * @param storage Object that handles saving the user's task into the local storage.
     */
    public void storeTask(Task task, Storage storage) {
        try {
            storage.writeToFile(task);
        } catch (IOException e) {
            System.out.println("File not found/missing directory.");
        }
    }

    /**
     * Empties the text file and repopulates it with the updated tasks.
     *
     * @param storage Object that handles saving the user's task into the local storage.
     */
    public void rewriteFile(Storage storage) {
        try {
            storage.deleteContent();
            for (Task task : tasks) {
                storage.writeToFile(task);
            }
        } catch (IOException e) {
            System.out.println("File is not found/missing directory.");
        }
    }
}
