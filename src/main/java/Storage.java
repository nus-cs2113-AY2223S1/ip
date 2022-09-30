import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String path;

    /**
     * initialize the path
     *
     * @param path the path that the file is located at
     */
    public Storage(String path){
        this.path = path;
    }

    /**
     * save the tasks into the file by overwriting the original content in the file
     *
     * @param tasks list of existing tasks
     * @throws IOException when saving failed
     */
    public void saveTask(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(path);

        String textToAdd = "";
        for (int i = 0; i < tasks.size(); i++) {
            textToAdd += tasks.get(i).toString() + "\n";
        }

        fw.write(textToAdd);
        fw.close();
    }

    /**
     * read from the file and load all the tasks
     *
     * @param lineToRead a line in the file
     * @param tasks list of existing tasks
     * @param ui deals with interactions with the user
     * @return a list of loaded tasks
     */
    public ArrayList<Task> loadTask(String lineToRead, ArrayList<Task> tasks, Ui ui) {

        int idOf2nd = lineToRead.indexOf(']', lineToRead.indexOf(']') + 1);
        int idOf3rd = lineToRead.indexOf('(');
        String description, by;
        boolean status = false;
        if (lineToRead.length() > 5 && lineToRead.charAt(4) == 'X') {
            status = true;
        }


        switch (lineToRead.substring(1, 2)) {
        case "T":
            description = lineToRead.substring(idOf2nd + 2);
            tasks.add(new Todo(description, status));
            break;
        case "D":
            description = lineToRead.substring(idOf2nd + 2, idOf3rd - 1);
            by = lineToRead.substring(idOf3rd + 5, lineToRead.length() - 1);
            tasks.add(new Deadline(description, by, status));
            break;
        case "E":
            description = lineToRead.substring(idOf2nd + 2, idOf3rd - 1);
            by = lineToRead.substring(idOf3rd + 5, lineToRead.length() - 1);
            tasks.add(new Event(description, by, status));
            break;
        default:
            ui.unKnownMsg();
            return tasks;
        }


        return tasks;
    }

    /**
     * append a line to the file
     *
     * @param line the line to be appended
     * @throws IOException saving failed.
     */
    public void saveLine(String line) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(line + "\n");
        fw.close();
    }

    /**
     * load a file
     *
     * @param tasks list of existing tasks
     * @param ui deals with interactions with the user
     * @throws FileNotFoundException when the file cannot be found through the given path
     */
    public ArrayList<Task> loadFile(ArrayList<Task> tasks, Ui ui) throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);
        String lineToRead;
        while (s.hasNext()) {
            lineToRead = s.nextLine();
            tasks = loadTask(lineToRead, tasks,ui);
        }

        return tasks;
    }

}
