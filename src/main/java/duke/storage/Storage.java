package duke.storage;

import duke.command.Menu;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void readDukeFile(Menu dukeMenu) {
        if (Files.exists(Paths.get(filePath))) {
            File dukeFile = new File(filePath);
            openDukeFileAndInitialise(dukeMenu, dukeFile);
        }
    }

    private static void openDukeFileAndInitialise(Menu dukeMenu, File dukeFile) {
        try {
            Scanner in = new Scanner(dukeFile);
            initialiseTasksFromDukeFile(dukeMenu, in);
        } catch (FileNotFoundException exception) {
            //dukeMenu.displayErrorMessage();
        } catch (DukeException exception) {
            //dukeMenu.displayErrorMessage();
        }
    }

    private static void initialiseTasksFromDukeFile(Menu dukeMenu, Scanner in) throws DukeException {
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] splits = line.split(" \\| ");
            initialiseTask(splits, dukeMenu);
        }
    }

    public void rewriteDukeFile(Menu dukeMenu) throws IOException {
        if (!Files.exists(Paths.get(filePath))) {
            File dukeFile = new File(filePath);
            dukeFile.createNewFile();
        }

        FileWriter dukeFileWriter = new FileWriter(filePath, false);
        for (Task task : dukeMenu.getTasks()) {
            String output = retrieveTaskInformationForFileStorage(task);
            dukeFileWriter.append(output);
        }
        dukeFileWriter.close();
    }

    public void appendDukeFile(Menu dukeMenu) throws IOException {
        FileWriter dukeFileWriter;
        if (Files.exists(Paths.get(filePath))) {
            dukeFileWriter = new FileWriter(filePath, true);
        } else {
            File dukeFile = new File(filePath);
            dukeFile.createNewFile();
            dukeFileWriter = new FileWriter(filePath, false);
        }

        Task newTask = dukeMenu.getTasks().get(dukeMenu.getTasks().size() - 1);
        String output = retrieveTaskInformationForFileStorage(newTask);
        dukeFileWriter.append(output);
        dukeFileWriter.close();
    }

    private static void initialiseTask(String[] splits, Menu dukeMenu) throws DukeException {
        switch (splits[0]) {
        case "T":
            dukeMenu.addTask("todo", splits[2], true);
            break;
        case "D":
            dukeMenu.addTask("deadline", splits[2] + " /by " + splits[3], true);
            break;
        case "E":
            dukeMenu.addTask("event", splits[2] + " /at " + splits[3], true);
            break;
        default:
            break;
        }
        if (splits[1].equals("1")) {
            dukeMenu.mark(Integer.toString(dukeMenu.getTasks().size()), true);
        }
    }

    private static String retrieveTaskInformationForFileStorage(Task task) {
        String output = "";
        if (task instanceof Todo) {
            output = String.format("%s | %s | %s", "T", task.isDone() ? "1" : "0", task.getTaskName());
        }
        if (task instanceof Deadline) {
            output = String.format("%s | %s | %s | %s", "D", task.isDone() ? "1" : "0",
                    task.getTaskName(), ((Deadline) task).getDeadlineTime());
        }
        if (task instanceof Event) {
            output = String.format("%s | %s | %s | %s", "E", task.isDone() ? "1" : "0",
                    task.getTaskName(), ((Event) task).getEventTime());
        }
        output += System.lineSeparator();
        return output;
    }
}
