package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represent a data file with actions to be perform to read or edit the contents in the file.
 */
public class Storage {
    private String filePath;
    private File data;

    public Storage(String filePath) {
        this.filePath = filePath;
        createFile();
    }

    public void createFile() {
        this.data = new File(filePath);
        try {
            data.createNewFile();
            System.out.println("File have been created.");
        } catch (Exception e) {
            System.out.println("File creation failed with exception.");
        }
    }

    /**
     * Check whether the data file is empty. If it is not empty, proceed with
     * loading the history data in the file into the task list to account for the previous inputs.
     *
     * @param tasks the list of tasks to be used in Duke
     */
    public void load(ArrayList<Task> tasks) {
        try {
            if (data.length() != 0) {
                readFile(tasks);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Reading the data file with a scanner line by line, with each line processed and decoded separately.
     * Line is split by '/' as the data stored in file is separated by '/' for the detailed information on each task.
     *
     * @param tasks the list of tasks to be used in Duke
     * @throws FileNotFoundException
     */
    public void readFile(ArrayList<Task> tasks) throws FileNotFoundException {
        Scanner s = new Scanner(data);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parts = line.split("/");
            decodeLine(parts, tasks);
        }
    }

    /**
     * Represent the steps to make sense of a line of the contents in the storage data file.
     * Followed by the addition of the respective task into the task list.
     * Lastly set the completion status of the task.
     *
     * @param parts the different parts of a line split by '/'
     * @param tasks the list of tasks to be used in Duke
     */
    public void decodeLine(String[] parts, ArrayList<Task> tasks) {
        String taskType = parts[0];
        String taskState = parts[1];
        String taskDescription = parts[2];
        String taskAdditional = "invalid";
        if (parts.length > 3) {
            taskAdditional = parts[3];
        }

        if (taskType.equals("T")) {
            tasks.add(new Todo(taskDescription));
        } else if (taskType.equals("D")) {
            tasks.add(new Deadline(taskDescription, taskAdditional));
        } else if (taskType.equals("E")) {
            tasks.add(new Event(taskDescription, taskAdditional));
        }
        boolean isDone = true;
        if (taskState.equals(" ")) {
            isDone = false;
        }
        int taskIndex = tasks.size() - 1;
        tasks.get(taskIndex).setCompletion(isDone);
    }

    /**
     * Convert a task to a standardised format with each part separated by a '/' to be added into data file.
     *
     * @param t the task in list to be inputted into the data file
     * @param index the index of the task in the task list
     */
    public void addToFile(Task t, int index) {
        String state;
        if (t.getStatus().equals("[X]")) {
            state = "X";
        } else {
            state = " ";
        }
        String type = t.getTaskType().substring(1, 2);
        String addedInfo = t.getAddedInfo();
        if (addedInfo.length() > 6) {
            int i = t.getAddedInfo().indexOf(")");
            addedInfo = t.getAddedInfo().substring(6, i);
        }
        try {
            writeToFile(type + "/" + state + "/" + t.getDescription() + "/" + addedInfo, index + 1);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Writing a new line to the end of the data file with the line being the task data to be added.
     *
     * @param textToAdd the task to add in a line of standardised format
     * @param n the line number that the task will be added on
     * @throws IOException
     */
    public void writeToFile(String textToAdd, int n) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        if (n == 1) {
            fw.write(textToAdd);
        } else {
            fw.write(System.lineSeparator() + textToAdd);
        }
        fw.close();
    }

    /**
     * Action called with intention to delete or edit a certain line in the data file.
     *
     * @param tasks the task list containing all the tasks
     */
    public void updateFile(ArrayList<Task> tasks) {
        try {
            editionToFile(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Edit the data file by first clearing the content of the file followed by calling the
     * adding action to add each of the tasks in the updated task list to the data file one by one.
     *
     * @param tasks the task list containing all the tasks
     * @throws IOException
     */
    public void editionToFile(ArrayList<Task> tasks) throws IOException {
        new FileWriter(filePath, false).close();
        int n = 0;
        for (Task t : tasks) {
            addToFile(t, n);
            n += 1;
        }
    }
}