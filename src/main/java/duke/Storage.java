package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> readFile() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            //System.out.println(s.nextLine());
            String toBeConverted = s.nextLine();
            Task task = stringToTask(toBeConverted);
            tasks.add(task);
        }

        return tasks;
    }
    public Task stringToTask(String toBeConverted) {
        String[] words = toBeConverted.split("\\|");
        Task convertedTask = null;
        switch (words[0]) {
        case "T":
            convertedTask = new Todo(words[2]);
            break;
        case "E":
            convertedTask = new Event(words[2], words[3]);
            break;
        case "D":
            convertedTask = new Deadline(words[2], words[3]);
            break;
        default:
            break;
        }
        if (words[1].equals("1")) {
            convertedTask.markDone();
        } else if (words[1].equals("0")){
            convertedTask.markUndone();
        }
        return convertedTask;
    }
    public void writeFile(TaskList tasks) throws IOException {
        StringBuilder toWrite = new StringBuilder(tasks.findTask(0).taskToString());
        toWrite.append(System.lineSeparator());
        for (int i = 1; i < tasks.getSize(); i += 1) {
            toWrite.append(tasks.findTask(i).taskToString()).append(System.lineSeparator());
        }
            FileWriter fw = new FileWriter(filePath);
            fw.write(toWrite.toString());
            fw.close();

    }
}
