import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public interface FileIO {

    /**
     * Load the saved data file from a given path
     *
     * @param filePath path name of the saved data file
     * @return the saved tasks in ArrayList<Task> format
     * @throws CorruptedDataFileException when data file is not in correct format
     * @throws FileNotFoundException      when data file is not found in given path
     */
    static ArrayList<Task> loadFile(String filePath) throws CorruptedDataFileException, FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();

            String[] args = line.split("//");
            for (String i : args) {
                System.out.print(i + ' ');
            }
            System.out.println();

            String taskType = args[0];
            String description = args[2];
            String time = (args.length > 3 ? args[3] : "");
            Boolean isDone = (Integer.parseInt(args[1]) == 1);

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

    /**
     * Write the current tasks in Duke to saved data file
     *
     * @param Tasks    the current tasks in Duke
     * @param filePath the path name of the saved data file
     * @throws IOException when exception in writing files occurs
     */
    static void writeFile(ArrayList<Task> Tasks, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        StringBuilder taskFileFormat = new StringBuilder();

        for (Task task : Tasks) {
            taskFileFormat.append(task.getTaskType()).append("//")
                    .append(task.getIsDone() ? "1" : "0").append("//").append(task.getDescription());
            if (!task.getTaskType().equals("T")) {
                taskFileFormat.append("//").append(task.getTime());
            }
            taskFileFormat.append(String.format("%n"));
        }
        fw.write(taskFileFormat.toString());
        fw.close();
    }

}
