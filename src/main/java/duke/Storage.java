package duke;

import duke.exception.DukeFileNotFoundException;
import duke.exception.DukeIOException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {


    private String fileFolder;
    private String filePath;

    public Storage(String fileFolder, String filePath) throws DukeIOException {
        //create folder if needed
        this.fileFolder = fileFolder;
        this.filePath = filePath;

        try {
            File folder = new File(fileFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }

            //create file if needed
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeIOException();
        }

    }


    public ArrayList<Task> load() throws DukeFileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File dataFile = new File(filePath);
            Scanner scanner = new Scanner(dataFile);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String category = line.substring(0, 1);
                String descriptor = line.substring(8);
                int dateIndex = descriptor.indexOf('|');
                switch (category) {
                    case "T":
                        tasks.add(new Todo(descriptor));
                        break;
                    case "E":
                        tasks.add(new Event(descriptor.substring(0, dateIndex - 1), descriptor.substring(dateIndex + 2)));
                        break;
                    case "D":
                        tasks.add(new Deadline(descriptor.substring(0, dateIndex - 1), descriptor.substring(dateIndex + 2)));
                        break;
                    default:
                        tasks.add(new Task(descriptor));
                        break;
                }
                if (line.charAt(4) == 'X') {
                    tasks.get(tasks.size() - 1).setStatus(Boolean.TRUE);
                }
            }
        }
        catch(FileNotFoundException e){
            throw new DukeFileNotFoundException();
        }

        return tasks;
    }


    public void writeToFile(TaskList tasks) throws DukeIOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            String textToAdd = "";
            for (int i = 0; i < tasks.getSize(); i++) {
                textToAdd = textToAdd + tasks.getTask(i).getSavedString() + System.lineSeparator();
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new DukeIOException();
        }
    }

    public void appendToFile(Task t) throws DukeIOException {
        try {
            FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
            String textToAppend = t.getSavedString() + System.lineSeparator();
            fw.write(textToAppend);
            fw.close();
        }
        catch (IOException e) {
            throw new DukeIOException();
        }
    }
}


