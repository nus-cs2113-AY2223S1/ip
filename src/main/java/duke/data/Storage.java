package duke.data;

import duke.Parser;
import duke.TaskManager;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_PATH = "data/duke.txt";

    /**
     * Save the tasks in the existing arraylist to a .txt file duke in the data folder
     *
     * @param tasks The tasks currently in the list in the program
     * @throws IOException If errors occur during file operations
     */
    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
        Path fileDir = Paths.get(FILE_DIRECTORY);

        if (Files.notExists(fileDir)) {
            Files.createDirectories(fileDir);
            System.out.println("Creating save file location!");
        }

        Path file = Paths.get(FILE_PATH);

        if (Files.notExists(file)) {
            Files.createFile(file);
            System.out.println("Autosaving for the first time!");
        }


        FileWriter writer = new FileWriter(FILE_PATH);

        for (Task task : tasks) {
            String type = task.getType();
            try {
                writer.write(task.toSaveString() + "\n");
            } catch (IOException e) {
                // Error when writing to the file
                e.printStackTrace();
            }
        }
        writer.close();
    }

    /**
     * Write the tasks stored in the duke.txt file in folder data into the current list of tasks in the program
     *
     * @param tasks The tasks currently in the list
     */
    public static void loadTasks(ArrayList<Task> tasks) {
        Path fileDir = Paths.get(FILE_DIRECTORY);
        if (Files.notExists(fileDir)) {
            System.out.println("You have no save folder!");
        }
        Path file = Paths.get(FILE_PATH);
        if (Files.notExists(file)) {
            System.out.println("You have no save file! Hardcore mode?");
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    String taskType = Parser.parseSaveTaskType(line);
                    switch (taskType) {
                    case "T":
                        loadTodo(line);
                        break;
                    case "D":
                        loadDeadline(line);
                        break;
                    case "E":
                        loadEvent(line);
                        break;
                    default:
                        System.out.println("Alien encounter! Get a flamethrower or Ellen Ripley!");
                        break;
                    }
                }
                System.out.println("Lock and Loaded your save!");
            } catch (IOException e) {
                // Error when reading the file
                e.printStackTrace();
            }
        }
    }

    /**
     * Write the todo stored in the duke.txt file in folder data into the current list of tasks in the program
     *
     * @param line The line in the save file that contains the pertinent information of the todo
     */
    private static void loadTodo(String line) {
        Boolean isDone = Parser.parseSaveTaskStatus(line);
        String taskDescription = Parser.parseSaveTodo(line);
        Todo todo = new Todo(taskDescription);
        if (isDone) {
            todo.setDone();
        }
        TaskManager.addFromStorage(todo);
    }

    /**
     * Write the deadline stored in the duke.txt file in folder data into the current list of tasks in the program
     *
     * @param line The line in the save file that contains the pertinent information of the deadline
     */
    private static void loadDeadline(String line) {
        Boolean isDone = Parser.parseSaveTaskStatus(line);
        String deadlineDescription = Parser.parseSaveDeadline(line);
        String dateDeadline = Parser.parseDeadlineDate(line);
        Deadline deadline = new Deadline(deadlineDescription, dateDeadline);
        if (isDone) {
            deadline.setDone();
        }
        TaskManager.addFromStorage(deadline);
    }

    /**
     * Write the event stored in the duke.txt file in folder data into the current list of tasks in the program
     *
     * @param line The line in the save file that contains the pertinent information of the event
     */
    private static void loadEvent(String line) {
        Boolean isDone = Parser.parseSaveTaskStatus(line);
        String eventDescription = Parser.parseSaveEvent(line);
        String dateEvent = Parser.parseEventDate(line);
        Event event = new Event(eventDescription, dateEvent);
        if (isDone) {
            event.setDone();
        }
        TaskManager.addFromStorage(event);
    }
}
