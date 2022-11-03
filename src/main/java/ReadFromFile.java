import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {
    public static ArrayList<Task> addFileContents(Path filePath) throws IOException, DukeExceptions {
        File f = new File(filePath + "/duke.txt"); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> todoList = new ArrayList<>();


        while (s.hasNext()) {
            String line = s.nextLine();
            String[] taskParts = line.split(",");
            String taskType = taskParts[0];
            String description = taskParts[2] + " ";
            FileTaskType type = FileTaskType.valueOf(taskType.toUpperCase());

            switch (type) {
            case T:
                Task newTask = new Todo(" " + description);
                if (taskParts[1].equals("1"))
                    newTask.markAsDone();

                todoList.add(newTask);
                break;
            case D:
                String deadLineDesc = description + "/by" + taskParts[3];
                Task newDeadlineTask = new Deadline(deadLineDesc);

                if (taskParts[1].equals("1"))
                    newDeadlineTask.markAsDone();

                todoList.add(newDeadlineTask);
                break;
            case E:
                String eventLineDesc = description + "/at" + taskParts[3];
                Task newEventTask = new Event(eventLineDesc);

                if (taskParts[1].equals("1"))
                    newEventTask.markAsDone();

                todoList.add(newEventTask);
                break;
            }
        }
        Files.delete(Paths.get(f.toURI()));
        return todoList;
    }
}
