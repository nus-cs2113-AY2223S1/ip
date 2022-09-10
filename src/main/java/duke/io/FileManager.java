package duke.io;

import duke.Duke;
import duke.error.exceptions.SaveFileFormatException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.tasktypes.DeadlineTask;
import duke.tasks.tasktypes.EventTask;
import duke.tasks.tasktypes.ToDoTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


public class FileManager {
    private static final String FILE_DIRECTORY_LOCATION = Paths.get(".", "save").toString();
    private static final String FILE_NAME = "duke" + ".txt";
    private static final String FILE_PATH = Paths.get(FILE_DIRECTORY_LOCATION, FILE_NAME).toString();
    private static final File file = new File(FILE_PATH);
    private static final String SEPARATOR = "|";

    public static TaskList LoadTaskList() {
        if (file.exists()) {
            return parseFile();
        } else {
            return new TaskList();
        }
    }

    private static TaskList parseFile() {
        TaskList tasks = new TaskList();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String row = fileScanner.nextLine();
                tasks.addItem(parseRow(row));
            }
            fileScanner.close();
            return tasks;
        } catch (IOException e) {
            // if file has an issue with opening/closing, return empty task list and delete file
            fileScanner.close();
            file.delete();
            return new TaskList();
        } catch (SaveFileFormatException e) {
            // return whatever is currently available and delete file
            fileScanner.close();
            file.delete();
            return tasks;
        } finally {
            fileScanner.close();
        }
    }

    private static Task parseRow(String input) throws SaveFileFormatException {
        String[] sections = input.split(Pattern.quote(SEPARATOR));
        String command = sections[0];
        if (ToDoTask.isCommand(command)) {
            return ToDoTask.parseToDoTask(input);
        } else if (DeadlineTask.isCommand(command)) {
            return DeadlineTask.parseDeadlineTask(input);
        } else if (EventTask.isCommand(command)) {
            return EventTask.parseEventTask(input);
        } else {
            throw new SaveFileFormatException();
        }
    }

    public static String getSeparator() {
        return SEPARATOR;
    }

    public static String formatSeparatedString(List<String> strings) {
        String bufferString = "";
        for (String string : strings) {
            bufferString += string + " " + FileManager.getSeparator() + " ";
        }
        return bufferString.substring(0, bufferString.length() - 3);
    }

    public static void save() {
        try (Writer fileWriter = new FileWriter(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter.write(Duke.TASK_LIST.getSaveString());
        } catch (IOException e) {
            // If cannot write, just delete file.
            file.delete();
        }
    }

    public static String getPath() {
        return FILE_PATH;
    }
}
