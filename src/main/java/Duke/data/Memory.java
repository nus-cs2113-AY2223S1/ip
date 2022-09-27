package Duke.data;

import Duke.data.exception.DukeException;
import Duke.data.tasks.Deadline;
import Duke.data.tasks.Event;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Duke.data.exception.ExceptionMessage;
import Duke.data.tasks.Todo;

/**
 * Class to handle to storing and loading of data
 */
public class Memory {
    private final String filePath;
    private static final int MARK_STATUS_INDEX = 1;
    private static final String EVENT = "E";
    private static final String DEADLINE = "D";
    private static final String TODO = "T";

    /**
     * Constructor for Memory class
     * @param filePath path to file that program will load data from and save data to
     */
    public Memory(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method loads all tasks in text file and stores it in task manager
     * @param myTaskManager object that stores and handle all task
     */
    public void loadFromFile(TaskManager myTaskManager) {
        try {
            String newFilePath = new File(filePath).getAbsolutePath();
            File f = new File(newFilePath);
            Scanner s = new Scanner(f);
            String textFromFile;
            while (s.hasNext()) {
                textFromFile = s.nextLine();
                addToTaskManager(myTaskManager, textFromFile);
            }
        } catch(FileNotFoundException e) {
            System.out.println(ExceptionMessage.LOAD_FILE_ERROR);
        }
    }

    /**
     * This method saves all current task into a text file
     * @param myTaskManager object that stores and handle all task
     */
    public void saveToFile(TaskManager myTaskManager) {
        try {
            ArrayList<String> compiledData = taskToStringArray(myTaskManager);
            writeToFile(filePath, compiledData);
        } catch (IOException e) {
            System.out.println(ExceptionMessage.FILE_ERROR);
        }
    }

    /**
     * This method converts all current task stored in task manager into string and combine it into
     * a list of strings
     * @param myTaskManager object that contain all task
     * @return list of string containing information of all task
     */
    public static ArrayList<String> taskToStringArray(TaskManager myTaskManager) {
        ArrayList<String> textData = new ArrayList<>();
        for (int i = 0; i < myTaskManager.getSize(); i++) {
            textData.add(myTaskManager.getTaskString(i));
        }
        return textData;
    }

    /**
     * This method saves information of all task into a file in the form of string
     * @param filePath path to file that program will write data to
     * @param textToWrite list of string containing information of all task
     * @throws IOException when trying to access a file through a wrong path or when file has issues
     */
    private static void writeToFile(String filePath, ArrayList<String> textToWrite) throws IOException {
        FileWriter fw = new FileWriter(new File(filePath).getAbsolutePath(), false);
        for(String task : textToWrite) {
            fw.write(task + "\n");
        }
        fw.close();
    }

    /**
     * This method processes a string containing information about a task and stores it
     * into a taskManager object
     * @param myTaskManager object that stores all task
     * @param textFromFile string containing information about a task
     */
    private static void addToTaskManager(TaskManager myTaskManager, String textFromFile) {
        String[] splitInputs = textFromFile.split(" ", 3);
        String toInput;
        switch(splitInputs[1].toUpperCase()){
        case DEADLINE:
            try {
                toInput = splitInputs[2].replaceFirst(" by: " , "/by ");
                myTaskManager.addTask(new Deadline(toInput));
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.DEADLINE_INPUT_ERROR);
            }
            break;
        case EVENT:
            try {
                toInput = splitInputs[2].replaceFirst(" at: " , "/at ");
                myTaskManager.addTask(new Event(toInput));
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.EVENT_INPUT_ERROR);
            }
            break;
        case TODO:
            try {
                myTaskManager.addTask(new Todo(splitInputs[2]));
            } catch(DukeException e) {
                System.out.println(ExceptionMessage.TODO_INPUT_ERROR);
            }
            break;
        default:
            System.out.println(ExceptionMessage.UNKNOWN_INPUTS_LOAD);
            break;
        }
        if(textFromFile.charAt(MARK_STATUS_INDEX) != 'X') {
            myTaskManager.markDone(myTaskManager.getSize() - 1);
        }
    }
}
