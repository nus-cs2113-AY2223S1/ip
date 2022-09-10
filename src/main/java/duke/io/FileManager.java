package duke.io;

import duke.Duke;
import duke.error.exceptions.CouldNotSaveException;
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

/**
 * Class for managing loading/saving files. Also handles parsing for text files.
 */
public class FileManager {
    /** Location of the directory where the file sits. */
    private static final String FILE_DIRECTORY_LOCATION = Paths.get(".", "save").toString();

    /** Name of file */
    private static final String FILE_NAME = "duke" + ".txt";

    /** Full (relative) path pointing to file */
    private static final String FILE_PATH = Paths.get(FILE_DIRECTORY_LOCATION, FILE_NAME).toString();

    /** {@link File} item initialized from {@link FileManager#FILE_PATH} */
    private static final File file = new File(FILE_PATH);

    /** Separator to be used for loading/saving files */
    private static final String SEPARATOR = "|";

    /** Boolean flag that triggers if file was loaded. */
    private static boolean wasLoaded = false;

    /**
     * Loads and populates a {@link TaskList} instance if the file exists. If not,
     * returns an empty {@link TaskList} item.
     *
     * @return {@link TaskList} instance, empty if no file exists
     */
    public static TaskList LoadTaskList() {
        if (file.exists()) {
            return parseFile();
        } else {
            File directory = new File(FILE_DIRECTORY_LOCATION);
            directory.mkdir();
            return new TaskList();
        }
    }

    /**
     * Parses text file and loads information into a {@link TaskList} instance.
     * If an error is thrown, just load whatever info was already loaded and
     * delete the original (error-prone) file.
     *
     * @return {@link TaskList} instance
     */
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
            wasLoaded = true;
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

    /**
     * Parses individual row of save file into a {@link Task} subclass.
     *
     * @param input Input string from save file
     * @return a {@link Task} subclass instance
     * @throws SaveFileFormatException If there's any kind of error in the format
     *                                 (or an unrecognised command) in the save file
     */
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

    /**
     * Concatenates strings in a {@link List}, with separators of
     * value {@link FileManager#SEPARATOR} in between.
     *
     * @param strings list of strings to be combined
     * @return Properly formatted string
     */
    public static String formatSeparatedString(List<String> strings) {
        String bufferString = "";
        for (String string : strings) {
            bufferString += string + " " + FileManager.getSeparator() + " ";
        }
        return bufferString.substring(0, bufferString.length() - 3);
    }

    /**
     * Saves {@link TaskList} to specified file path as a {@code txt} file.
     */
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

    /**
     * Getter for file path.
     *
     * @return file path string
     */
    public static String getPath() {
        return FILE_PATH;
    }

    /**
     * Getter for {@link FileManager#wasLoaded} field.
     *
     * @return true if a file was loaded.
     */
    public static boolean wasLoaded() {
        return wasLoaded;
    }
}
