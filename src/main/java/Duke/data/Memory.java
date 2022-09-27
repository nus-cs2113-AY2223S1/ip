package Duke.data;

import Duke.data.tasks.Task;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

import Duke.data.exception.ExceptionMessage;

public class Memory {
    protected static String filePath;

    public Memory(String filePath) {
        this.filePath = filePath;
    }

    public static void saveToFile(TaskManager myTaskManager) {
        try {
            ArrayList<String> compiledData = taskToStringArray(myTaskManager);
            appendToFile(filePath, compiledData);
        } catch (IOException e) {
            System.out.println(ExceptionMessage.FILE_IO_ERROR);
        }
    }

    public static ArrayList<String> taskToStringArray(TaskManager myTaskManager) {
        ArrayList<String> textData = new ArrayList<>();
        for (int i = 0; i < myTaskManager.getSize(); i++) {
            textData.add(myTaskManager.getTaskString(i));
        }
        return textData;
    }
;
    private static void appendToFile(String filePath, ArrayList<String> textToAppend) throws IOException {
        FileWriter fw = new FileWriter(new File(filePath).getAbsolutePath(), false); // create a FileWriter in append mode
        for(String task : textToAppend) {
            fw.write(task + "\n");
        }
        fw.close();
    }
}
