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

        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();

            String[] args = line.split(" | ", 0);

            String taskType = args[0];
            String description = args[4];
            String time = (args.length > 5 ? args[6] : "");
            Boolean isDone = (Integer.parseInt(args[2]) == 1);

            switch (taskType) {
            case "T":
                tasks.add(new Todo(description, isDone));
                break;
            case "D":
                tasks.add(new Deadline(description, isDone, time));
                break;
            case "E":
                tasks.add(new Event(description, isDone, time));
                break;
            default:
                throw new CorruptedDataFileException();
            }
        }

        return tasks;
    }

    static void writeFile(ArrayList<Task> Tasks) throws IOException {
        FileWriter fw = new FileWriter("/Users/bromine/ip/Saves/Tasks");
        StringBuilder taskFileFormat = new StringBuilder();

        for (Task task : Tasks) {
            taskFileFormat.append(task.getTaskType()).append(" | ")
                    .append(task.getIsDone() ? "1" : "0").append(" | ").append(task.getDescription());
            if (!task.getTaskType().equals("T")) {
                taskFileFormat.append(" | ").append(task.getTime());
            }
            taskFileFormat.append(String.format("%n"));
        }
        fw.write(taskFileFormat.toString());
        fw.close();
    }

}
