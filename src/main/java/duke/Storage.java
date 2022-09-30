package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;

// Todo : check if need to add folder ?

public class Storage extends Duke {
    public static final String event = "E";
    public static final String one = "1";
    public static final String zero = "0";
    public static final String todo = "T";
    public static final String deadline = "D";
    public static final String divider = " | ";
    public static final String dividerSplit = " \\| ";

    private static File file;
    public Storage() throws DukeException {
        file = new File(filename);
        openOrCreateFile();
    }

    /**
     * checks if a duke file exists,
     * if not, creates one and opens the file
     * @throws DukeException
     */
    public static void openOrCreateFile() throws DukeException {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException();
        }
    }


    /**
     * uploads list from
     * @throws DukeException
     */
    public static void uploadDataToList() throws DukeException {
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                translateLineFromFile(data);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException();
        }
    }

    /**
     * marks isDone depending on the task in the file
     * @param isDone
     * @throws DukeException
     */
    private static void markIfDone(String isDone) throws DukeException {
        if (isDone.equals(one)) {
            dukeList.markItemDone(dukeList.getListSize());
        } else if (!isDone.equals(zero)) {
            throw new DukeException();
        }

    }
    /**
     * updates line by line from file to dukelist
     * @param line
     * @throws DukeException
     */
    public static void translateLineFromFile(String line) throws DukeException {
        String[] words = line.split(dividerSplit);
        switch (words[0]) {
            case todo: {
                TaskList.AddTodo(words[2]);
                break;
            }
            case event: {
                TaskList.AddEvent(words[2], words[3]);
                break;
            }
            case deadline: {
                TaskList.AddDeadline(words[2], words[3]);
                break;
            }
            default: {
                throw new DukeException();
            }
        }
        markIfDone(words[1]);
    }

    /**
     * saves from dukelist to file
     * @throws DukeException
     */
    public static void saveListToFile() throws DukeException {
        try {
            FileWriter fw = new FileWriter(filename);
            for (int i = 0; i < dukeList.getListSize(); i++) {
                fw.write(dukeList.getTaskFromList(i).getFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }
}
