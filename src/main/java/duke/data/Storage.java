package duke.data;

import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

import duke.parser.Parser;
import duke.data.exceptions.DukeFileException;

public class Storage {
    public Storage() {
    }

    public static final String STORAGE_DIRECTORY = "./storage";
    public static final String STORAGE_FILE = "./storage/savedTaskList.txt";

    public static String[] save() {
        try {
            FileWriter fileWrite = new FileWriter(STORAGE_FILE);
            for (int i = 0; i < TaskList.list.size(); i++) {
                fileWrite.write(TaskList.list.get(i).toSave());
            }
            fileWrite.close();
            return new String[] { Messages.FILE_OVERWRITTEN };
        } catch (IOException e) {
            File newfile = new File(STORAGE_DIRECTORY);
            newfile.mkdir();
            save();
            return new String[] { Messages.NO_FILE, Messages.FILE_CREATED };
        }
    }

    public static String load() {
        try {
            File file = new File(STORAGE_FILE);
            Scanner fileRead = new Scanner(file);
            while (fileRead.hasNext()) {
                new Parser().parseFile(fileRead.nextLine());
            }
            fileRead.close();
            return Messages.FILE_LOADED;
        } catch (FileNotFoundException e) {
            return Messages.NO_FILE;
        } catch (ArrayIndexOutOfBoundsException e) {
            return Messages.WRONG_FILE;
        } catch (DukeFileException e) {
            return Messages.WRONG_FILE;
        }
    }

}
