package consoleCommands;

import exception.InvalidFileDataException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class is used to read/write data to/from text file and ArrayList of tasks
 * Class also has method of converting tasks to string to be stored as data
 */
public class Storage {
    public static final String IS_MARKED = "1";
    public static final String IS_UNMARKED = "0";
    public static final String TODO = "T";
    public static final String DEADLINE = "D";
    public static final String EVENT = "E";
    public static final String LINE_SEPARATOR = " / ";

    /**
     * Method is used to read string from text file, and convert it into task objects to be inputted into ArrayList
     * @param filePath is the relative filePath of the file to be read
     * @param taskList is the ArrayList of tasks, to read data from text file
     */
    public void readFromFile (String filePath, ArrayList<Task> taskList)
            throws FileNotFoundException, InvalidFileDataException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String data = s.nextLine();
            String[] taskData = data.split(" / ");
            if (taskData[0].equals(TODO)) {
                taskList.add(new Todo(taskData[2]));
            } else if (taskData[0].equals(DEADLINE)) {
                taskList.add(new Deadline(taskData[2],taskData[3]));
            } else if (taskData[0].equals(EVENT)) {
                taskList.add(new Event(taskData[2],taskData[3]));
            } else {
                throw new InvalidFileDataException();
            }
            if (taskData[1].equals(IS_MARKED)) {
                taskList.get(taskList.size()-1).isDone = true;
            }
        }
    }

    /**
     * Method is used to convert task objects into string for writing to text file at the end of programme
     * Method creates a temp file and inputs the string converted from ArrayList
     * @param tempFilePath is the relative filePath of the temporary file to be written to
     * @param taskList is the ArrayList of tasks, to write data to text file
     */
    public static void arrayToText (String tempFilePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(tempFilePath, true);
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            String textToAppend = currentTask.getTaskClass();
            if (currentTask.isDone) {
                textToAppend += LINE_SEPARATOR + IS_MARKED ;
            } else {
                textToAppend += LINE_SEPARATOR + IS_UNMARKED;
            }
            textToAppend += LINE_SEPARATOR + currentTask.getDescription();
            if (currentTask.getTaskClass().equals(EVENT) || currentTask.getTaskClass().equals(DEADLINE)) {
                textToAppend += LINE_SEPARATOR + currentTask.getDetails();
            }
            fw.write(textToAppend + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Method is used to write data from ArrayList to text file
     * Method calls .arrayToText() to create a temporary text file, with data from ArrayList written to it
     * Method then copies data from the temporary file to the main text file, then deletes the temporary file
     * @param filePath is the relative filePath of the file to be written to
     * @param taskList is the ArrayList of tasks, to write data to text file
     * @param tempFilePath is the relative filePath of the temporary file to be written to
     */
    public void writeToFile (String filePath, String tempFilePath, ArrayList<Task> taskList)
            throws IOException {
        try {
            arrayToText(tempFilePath, taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        Path tempPath = Paths.get(tempFilePath);
        Files.copy(tempPath, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(tempPath);
    }
}
