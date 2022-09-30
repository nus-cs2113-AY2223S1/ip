package duke.data;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadlines;
import duke.task.Events;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

import javax.sound.midi.SysexMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class to load and save tasks into a file
 *
 * FILE_DIRECTORY refers to the data folder
 * FILE_PATH refers to the duke.txt file
 */
public class Storage {
    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_PATH = "data/duke.txt";

    public Storage() {

    }

    /**
     * A method to save task to the file whenever there is changes to the ArrayList of tasks
     *
     * @param Tasks is an arraylist of tasks to be called to save the tasks into the file
     * @throws IOException If error occurs during file operation
     */
    public static void saveTask(ArrayList<Task> Tasks) throws IOException {
        Path fileDirectory = Paths.get(FILE_DIRECTORY);

        if (Files.notExists(fileDirectory)) {
            Files.createDirectory(fileDirectory);
        }

        Path file = Paths.get(FILE_PATH);

        if (Files.notExists(file)) {
            Files.createFile(file);
        }
        FileWriter writer = new FileWriter(FILE_PATH);

        for(Task task: Tasks) {
            try {
                writer.write(task.SaveAsString() + "\n");
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        writer.close();
    }

    /**
     * Write the tasks stored in the duke.txt file into the Arraylist of tasks in the program
     *
     * @return The arraylist of tasks that is being updated after the loading from the duke.txt file
     */
    public Tasklist loadTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                char action = data.charAt(1);
                String description = data.substring(7);
                switch (action) {
                    case 'T':
                        Todo todo = new Todo(description);
                        if (data.charAt(4) == 'X') {
                            todo.setisDone(true);
                        }
                        tasks.add(todo);
                        break;
                    case 'D':
                        String deadlineDescription = description.split("/by ")[0];
                        String deadlineDate = description.split("/by ")[1];
                        Deadlines deadline = new Deadlines(deadlineDescription, deadlineDate);
                        if (data.charAt(4) == 'X') {
                            deadline.setisDone(true);
                        }
                        tasks.add(deadline);
                        break;
                    case 'E':
                        String EventDescription = description.split("/at ")[0];
                        String EventDate = description.split("/at ")[1];
                        Events event = new Events(EventDescription, EventDate);
                        if (data.charAt(4) == 'X') {
                            event.setisDone(true);
                        }
                        tasks.add(event);
                        break;
                    default:
                        break;
                }

            }
        } catch (FileNotFoundException e) {
            Ui.showError(e.getMessage());
        }
        return new Tasklist(tasks);
    }

}
