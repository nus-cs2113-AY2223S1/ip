package duke;

import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;

public class FileAccess {

    private static final Path dataDirectory = Paths.get("src/main/java/duke/data/");
    private static final Path dataFile = Paths.get("src/main/java/duke/data/data.txt");

    public static void createDataFile() throws IOException {
        if(!Files.exists(dataDirectory)) {
            Files.createDirectory(dataDirectory);
        }
        Files.createFile(dataFile);
    }

    public static void loadTasks() throws FileNotFoundException, DukeException {
        Scanner input = new Scanner(new File(dataFile.toUri()));
        while (input.hasNext()) {
            String[] line = input.nextLine().split(" \\| ");
            switch (line[0]) {
            case "T":
                Task.tasksCount++;
                Task toDo = new ToDo(line[2]);
                if (line[1].equals("X")) {
                    toDo.markAsDone();
                }
                TaskManager.loadTask(toDo);
                break;
            case "E":
                Task.tasksCount++;
                Task event = new Event(String.format(line[2] + "/" + line[3]));
                if (line[1].equals("X")) {
                    event.markAsDone();
                }
                TaskManager.loadTask(event);
                break;
            case "D":
                Task.tasksCount++;
                Task deadline = new Deadline(String.format(line[2] + "/" + line[3]));
                if (line[1].equals("X")) {
                    deadline.markAsDone();
                }
                TaskManager.loadTask(deadline);
                break;
            default:
                throw new DukeException(ExceptionType.LOAD_ERROR);
            }
        }
        input.close();
    }

    public static void saveTasks() throws IOException {
        FileWriter output = new FileWriter(dataFile.toFile());
        for (int taskNumber = 1; taskNumber <= Task.tasksCount; taskNumber++) {
            String description = "null";
            if (TaskManager.Tasks[taskNumber] instanceof ToDo) {
                description = TaskManager.Tasks[taskNumber].taskDescription();
            } else if (TaskManager.Tasks[taskNumber] instanceof Deadline) {
                description = String.format(TaskManager.Tasks[taskNumber].taskDescription() + " | " + ((Deadline) TaskManager.Tasks[taskNumber]).getDeadlineTime());
            } else if (TaskManager.Tasks[taskNumber] instanceof Event) {
                description = String.format(TaskManager.Tasks[taskNumber].taskDescription() + " | " + ((Event) TaskManager.Tasks[taskNumber]).getEventTime());
            }
            output.write(String.format("%c | %c | %s", TaskManager.Tasks[taskNumber].taskType(), TaskManager.Tasks[taskNumber].doneIcon(), description) + System.lineSeparator());
        }
        output.close();
    }

}
