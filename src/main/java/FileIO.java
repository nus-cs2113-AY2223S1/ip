import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public interface FileIO {

    static ArrayList<Task> loadFile() throws CorruptedDataFileException, FileNotFoundException {
        File file = new File("/Users/bromine/ip/Saves/Tasks");
        Scanner sc = new Scanner(file);

        ArrayList<Task> tasks = new ArrayList<Task>();
        while (sc.hasNext()) {
            String line = sc.nextLine();

            String[] args = line.split(" | ", 0);

            String taskType = args[0];
            String description = args[4];
            String time = (args.length > 5 ? args[6] : "");
            Boolean isDone = (Integer.parseInt(args[2]) == 1 ? true : false);

            if (taskType.equals("T")) {
                tasks.add(new Todo(description, isDone));
            } else if (taskType.equals("D")) {
                tasks.add(new Deadline(description, isDone, time));
            } else if (taskType.equals("E")) {
                tasks.add(new Event(description, isDone, time));
            } else {
                throw new CorruptedDataFileException();
            }
        }

        return tasks;
    }

    static void writeFile(ArrayList<Task> Tasks) throws IOException {
        FileWriter fw = new FileWriter("/Users/bromine/ip/Saves/Tasks_edited");
        String taskFileFormat = "";

        for (Task task : Tasks) {
            taskFileFormat = taskFileFormat + task.getTaskType() + " | " +
                    (task.getIsDone() ? "1" : "0") + " | " + task.getDescription();
            if (!task.getTaskType().equals("T")) {
                taskFileFormat = taskFileFormat + " | " + task.getTime();
            }
            taskFileFormat += String.format("%n");
        }
        fw.write(taskFileFormat);
        fw.close();
    }

}
