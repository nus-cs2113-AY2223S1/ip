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

public class Storage {
    private static String path;
    public Storage(String path){
        this.path = path;
    }

    public static void saveTask(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(path);

        String textToAdd = "";
        for (int i = 0; i < tasks.size(); i++) {
            textToAdd += tasks.get(i).toString() + "\n";
        }

        fw.write(textToAdd);
        fw.close();
    }

    public static ArrayList<Task> loadTask(String lineToRead, ArrayList<Task> tasks, Ui ui) {

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

    public static void saveLine(String line) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(line + "\n");
        fw.close();
    }

    public static ArrayList<Task> loadFile(ArrayList<Task> tasks, Ui ui) throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);
        String lineToRead;
        while (s.hasNext()) {
            lineToRead = s.nextLine();
            tasks = Storage.loadTask(lineToRead, tasks,ui);
        }

        return tasks;
    }

}
