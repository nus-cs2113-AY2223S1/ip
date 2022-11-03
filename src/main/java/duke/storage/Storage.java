package duke.storage;

import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * storage class that handles all local memory storage such as reading and writing
 * to files
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * read from saved local file, creates a new file in new directory if necessary
     *
     * @return an ArrayList of tasks from the data read
     * @throws FileNotFoundException the exception is thrown when the file cannot be found/created
     */
    public ArrayList<Task> readFile() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        String directory = filePath.substring(0, filePath.lastIndexOf("/"));
        new File(directory).mkdir();
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String toBeConverted = s.nextLine();
            Task task = stringToTask(toBeConverted);
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * append task as string to file
     *
     * @param textToAppend string to be appended to file
     * @throws IOException the exception thrown when file cannot be written to
     */
    public void appendTask(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    /**
     * converts string from file to task to be added into taskList
     *
     * @param toBeConverted string to be converted to task
     * @return Task which is either Todo, Event, Deadline
     */
    private Task stringToTask(String toBeConverted) {
        String[] words = toBeConverted.split("\\|");
        Task convertedTask = null;
        switch (words[0]) {
        case "T":
            convertedTask = new Todo(words[2]);
            break;
        case "E":
            convertedTask = new Event(words[2], words[3]);
            break;
        case "D":
            convertedTask = new Deadline(words[2], words[3]);
            break;
        default:
            break;
        }
        if (words[1].equals("1")) {
            convertedTask.markDone();
        } else if (words[1].equals("0")) {
            convertedTask.markUndone();
        }
        return convertedTask;
    }

    /**
     * writes Duke's task list to file in local storage
     *
     * @param tasks Duke's task list
     * @throws IOException the exception thrown when file cannot be written to
     */
    public void writeFile(TaskList tasks) throws IOException {
        if (tasks.getSize() == 0) {
            new FileWriter(filePath, false).close();
        } else {
            StringBuilder toWrite = new StringBuilder(tasks.findTask(0).taskToString());
            toWrite.append(System.lineSeparator());
            for (int i = 1; i < tasks.getSize(); i += 1) {
                Task taskToAppend = tasks.findTask(i);
                String stringToAppend = taskToAppend.taskToString();
                toWrite.append(stringToAppend).append(System.lineSeparator());
            }
            String directory = filePath.substring(0, filePath.lastIndexOf("/"));
            new File(directory).mkdir();
            FileWriter fw = new FileWriter(filePath);
            fw.write(toWrite.toString());
            fw.close();
        }
    }
}
