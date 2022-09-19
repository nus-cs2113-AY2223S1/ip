package duke.storage;

import duke.exception.DukeException;
import duke.exception.SaveErrorDukeException;
import duke.task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class SaveTasks {
    public static void saveTasks(TaskList taskList, Path dataFile) throws IOException, DukeException {
        FileWriter output = new FileWriter(dataFile.toFile());
        for (Task task : taskList.tasks) {
            String description;
            if (task instanceof ToDo) {
                description = task.getName();
            } else if (task instanceof Deadline) {
                description = String.format(task.getName() + " | " + ((Deadline) task).getDateTime());
            } else if (task instanceof Event) {
                description = String.format(task.getName() + " | " + ((Event) task).getDateTime());
            } else {
                throw new SaveErrorDukeException();
            }
            output.write(String.format("%c | %c | %s",
                    task.taskType(), task.doneIcon(), description) + System.lineSeparator());
        }
        output.close();
    }
}
