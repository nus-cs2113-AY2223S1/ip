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
    private static final File file = new File(filename);


    public static void openOrCreateFile() throws DukeException {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException();
        }
    }

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

    private static void markIfDone(String isDone) throws DukeException {
        if (isDone.equals(one)) {
            dukeList.markItemDone(dukeList.getListSize());
        }
        if (!isDone.equals(one) && !isDone.equals(zero)) {
            throw new DukeException();
        }

    }

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
