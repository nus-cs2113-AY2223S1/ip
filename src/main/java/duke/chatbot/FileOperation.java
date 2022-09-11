package duke.chatbot;

import duke.task.Task;
import duke.text.ErrorText;
import duke.text.InfoText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperation {
    protected static final String DIRECTORY_PATH = "data";
    protected static final String FILE_PATH = "data/duke.txt";
    protected static final String DONE = "X";
    protected static final String NUMERIC_DONE = "1";
    protected static final String NUMERIC_NOT_DONE = "0";
    protected static final String DELIMITER = " | ";
    protected static final String DELIMITER_WITH_BACKSLASH = " \\| ";

    public static File prepareFile(ChatBot chatbot) throws IOException {
        //@@author chydarren-reused
        // Reused from https://stackoverflow.com/a/38985883
        // with minor modifications
        File directory = new File(DIRECTORY_PATH);
        if(!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(FILE_PATH);
        if(!file.exists()) {
            file.createNewFile();
        }
        chatbot.printOutput(String.format(String.valueOf(InfoText.INFO_FILE), FILE_PATH));
        return file;
        //@@author
    }

    public static void readFromFile(ChatBot chatbot) {
        try {
            File file = prepareFile(chatbot);
            Scanner in = new Scanner(file);

            while (in.hasNext()) {
                String[] inputSplits = in.nextLine().split(DELIMITER_WITH_BACKSLASH);
                boolean isDone = inputSplits[1].equals(NUMERIC_DONE);
                switch (inputSplits[0]) {
                case "T":
                    chatbot.addTask("todo", inputSplits[2], isDone, false);
                    break;
                case "D":
                    chatbot.addTask("deadline", String.format("%s /by %s", inputSplits[2],
                            inputSplits[3]), isDone, false);
                    break;
                case "E":
                    chatbot.addTask("event", String.format("%s /at %s", inputSplits[2],
                            inputSplits[3]), isDone, false);
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            chatbot.printOutput(String.valueOf(ErrorText.ERROR_FILE_NOT_FOUND));
        } catch (IOException e) {
            chatbot.printOutput(String.valueOf(ErrorText.ERROR_FILE_IO));
        }
    }

    public static void writeToFile(ChatBot chatbot, ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                String taskType = Character.toString(task.getTaskDetails().charAt(1));
                String taskStatus = task.getStatusIcon().equals(DONE) ? NUMERIC_DONE : NUMERIC_NOT_DONE;
                String taskTitle = task.getTitle();
                String textToWrite = String.format("%s%s%s%s%s", taskType, DELIMITER,
                        taskStatus, DELIMITER, taskTitle);
                switch (taskType) {
                case "D":
                    textToWrite += String.format("%s%s", DELIMITER, task.getDueBy());
                    break;
                case "E":
                    textToWrite += String.format("%s%s", DELIMITER, task.getTime());
                    break;
                default:
                    break;
                }
                fw.write(textToWrite + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            chatbot.printOutput(String.valueOf(ErrorText.ERROR_FILE_IO));
        }
    }
}
