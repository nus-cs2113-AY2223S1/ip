package duke.storage;

import duke.exception.DukeException;
import duke.exception.SaveErrorDukeException;
import duke.task.*;

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
     * @throws DukeException if any task fails to save
     */

    public static void saveTasks(TaskList taskList, Path dataFile) throws IOException, DukeException {

        FileWriter output = new FileWriter(dataFile.toFile());
        for (Task task : taskList.tasks) {
            String description;
            if (task instanceof ToDo) {
                description = task.getName();
            } else if (task instanceof Deadline) {
                description = String.format(task.getName() + " | " + ((Deadline) task).saveDateTime());
            } else if (task instanceof Event) {
                description = String.format(task.getName() + " | " + ((Event) task).saveDateTime());
            } else {
                throw new SaveErrorDukeException();
            }
            output.write(String.format("%c | %c | %s",
                    task.taskType(), task.doneIcon(), description) + System.lineSeparator());
        }
        output.close();
    }
}
