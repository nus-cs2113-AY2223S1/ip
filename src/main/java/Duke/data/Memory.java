package Duke.data;

import Duke.data.exception.DukeException;
import Duke.data.tasks.Deadline;
import Duke.data.tasks.Event;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Duke.data.exception.ExceptionMessage;
import Duke.data.tasks.Todo;

public class Memory {
    private final String filePath;
    private static final int MARK_STATUS_INDEX = 1;
    private static final String EVENT = "E";
    private static final String DEADLINE = "D";
    private static final String TODO = "T";

    public Memory(String filePath) {
        this.filePath = filePath;
    }

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

    public void saveToFile(TaskManager myTaskManager) {
        try {
            ArrayList<String> compiledData = taskToStringArray(myTaskManager);
            appendToFile(filePath, compiledData);
        } catch (IOException e) {
            System.out.println(ExceptionMessage.FILE_ERROR);
        }
    }

    public static ArrayList<String> taskToStringArray(TaskManager myTaskManager) {
        ArrayList<String> textData = new ArrayList<>();
        for (int i = 0; i < myTaskManager.getSize(); i++) {
            textData.add(myTaskManager.getTaskString(i));
        }
        return textData;
    }

    private static void appendToFile(String filePath, ArrayList<String> textToAppend) throws IOException {
        FileWriter fw = new FileWriter(new File(filePath).getAbsolutePath(), false);
        for(String task : textToAppend) {
            fw.write(task + "\n");
        }
        fw.close();
    }

    private static void addToTaskManager(TaskManager myTaskManager, String textFromFile) {
        String[] splitInputs = textFromFile.split(" ", 3);
        String toInput;
        switch(splitInputs[1].toUpperCase()){
        case DEADLINE:
            try {
                toInput = splitInputs[2].replaceFirst("by :" , "/by");
                myTaskManager.addTasks(new Deadline(toInput));
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.DEADLINE_INPUT_ERROR);
            }
            break;
        case EVENT:
            try {
                toInput = splitInputs[2].replaceFirst("at :" , "/at");
                myTaskManager.addTasks(new Event(toInput));
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.EVENT_INPUT_ERROR);
            }
            break;
        case TODO:
            try {
                myTaskManager.addTasks(new Todo(splitInputs[2]));
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
