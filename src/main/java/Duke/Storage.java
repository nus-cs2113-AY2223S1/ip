package Duke;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private static String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given task list to a specified local file
     * @param filePath Location of file for task list to be saved to
     * @param list Task list to be saved
     * @throws IOException Error
     */
    public static void writeToFile(String filePath, ArrayList<Task> list) {
        try {
            File storedFile = new File(filePath);
            File parent = storedFile.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            if (!storedFile.exists()) {
                storedFile.createNewFile();
            }
            FileWriter writeFile = new FileWriter(storedFile);
            for (Task task : list) {
                Boolean isDone = task.isDone;
                writeFile.write(task.type + "|" + isDone + "|" + task.description +
                        "|" + task.by + System.lineSeparator());
            }
            writeFile.close();
        } catch (IOException e) {
            Ui.showFileWriteError();
        }
    }

    /**
     * Loads task list saved in specified local file to the task list
     * @param filePath Location of file with the saved list
     * @param list Target ArrayList to load task list
     * @throws FileNotFoundException Error if file is not found in the specified location
     */
    public static void getFileContents(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        Scanner s = new Scanner(new FileInputStream((filePath)));
        while (s.hasNext()) {
            String command = s.nextLine();
            String inputCommands[] = command.split("\\|", 4);
            switch (inputCommands[0]) {
            case "T":
                Todo addTodo = new Todo(inputCommands[2]);
                TaskList.checkMarked(addTodo,inputCommands[1]);
                list.add(addTodo);
                break;
            case "D":
                Deadline addDeadline = new Deadline(inputCommands[2], inputCommands[3]);
                TaskList.checkMarked(addDeadline,inputCommands[1]);
                list.add(addDeadline);
                break;
            case "E":
                Event addEvent = new Event(inputCommands[2], inputCommands[3]);
                TaskList.checkMarked(addEvent,inputCommands[1]);
                list.add(addEvent);
                break;
            default:
                throw new FileNotFoundException();
            }
        }
    }
}
