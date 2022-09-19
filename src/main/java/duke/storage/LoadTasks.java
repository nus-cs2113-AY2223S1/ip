package duke.storage;

import duke.exception.DukeException;
import duke.exception.LoadErrorDukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Represents operation of loading tasks from save file
 */
public class LoadTasks {


    /**
     * Loads tasks from save file into taskList
     * @param dataFile Path of save file
     * @throws FileNotFoundException if save file does not exist
     * @throws DukeException if any task fails to load
     */

    public static void loadTasks(TaskList taskList, Path dataFile) throws FileNotFoundException, DukeException {

        Scanner input = new Scanner(new File(dataFile.toUri()));
        while (input.hasNext()) {
            String[] line = input.nextLine().split(" \\| ");
            String taskType = line[0];
            String doneIcon = line[1];
            Task task;
            switch (taskType) {
            case "T":
                task = new ToDo(line[2]);
                break;
            case "E":
                task = new Event(String.format(line[2] + "/" + line[3]));
                break;
            case "D":
                task = new Deadline(String.format(line[2] + "/" + line[3]));
                break;
            default:
                throw new LoadErrorDukeException();
            }
            if (doneIcon.equals("X")) {
                task.markAsDone();
            }
            taskList.loadTask(task);
        }
        input.close();
    }
}
