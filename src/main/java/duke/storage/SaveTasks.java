package duke.storage;

import duke.exception.DukeException;
import duke.exception.SaveErrorDukeException;
import duke.task.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Represents operation of saving tasks to save file
 */
public class SaveTasks {

    /**
     * Saves tasks from taskList to save file
     * @param dataFile Path of save file
     * @throws IOException if save file does not exist
     * @throws DukeException if error encountered when saving task
     */
    public static void saveTasks(Path dataFile) throws IOException, DukeException {
        FileWriter output = new FileWriter(dataFile.toFile());
        for (Task task : TaskList.Tasks) {
            String description;
            if (task instanceof ToDo) {
                description = task.getName();
            } else if (task instanceof Deadline) {
                description = String.format(task.getName() + " | " + ((Deadline) task).getDeadlineTime());
            } else if (task instanceof Event) {
                description = String.format(task.getName() + " | " + ((Event) task).getEventTime());
            } else {
                throw new SaveErrorDukeException();
            }
            output.write(String.format("%c | %c | %s",
                    task.taskType(), task.doneIcon(), description) + System.lineSeparator());
        }
        output.close();
    }
}
