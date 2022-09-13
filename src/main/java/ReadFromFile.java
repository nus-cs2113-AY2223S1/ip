import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadFromFile {
    public static Task[] addFileContents(Path filePath) throws IOException, DukeExceptions {
        File f = new File(filePath + "/duke.txt"); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        Task[] todoList = new Task[100];


        while (s.hasNext()) {
            String line = s.nextLine();
            String[] taskParts = line.split(",");
            String taskType = taskParts[0];
            String description = taskParts[2] + " ";
            System.out.println("taskParts[0]" + taskParts[0]);
            System.out.println("taskParts[1]" + taskParts[1]);
            System.out.println("taskParts[2]" + taskParts[2]);
            FileTaskType type = FileTaskType.valueOf(taskType.toUpperCase());

            switch (type) {
            case T:
                for (int i = 0; i < todoList.length; i++) {
                    if (todoList[i] == null) {
                        Task newTask = new Todo(" " + description);
                        if (taskParts[1].equals("1"))
                            newTask.markAsDone();

                        todoList[i] = newTask;
                        break;
                    }
                }
                break;
            case D:
                System.out.println("taskParts[0]" + taskParts[3]);
                for (int i = 0; i < todoList.length; i++) {
                    if (todoList[i] == null) {
                        String deadLineDesc = description + "/by" + taskParts[3];
                        Task newTask = new Deadline(deadLineDesc);
                        if (taskParts[1].equals("1"))
                            newTask.markAsDone();

                        todoList[i] = newTask;
                        break;
                    }
                }
                break;
            case E:
                System.out.println("taskParts[3]" + taskParts[3]);
                for (int i = 0; i < todoList.length; i++) {
                    if (todoList[i] == null) {
                        String deadLineDesc = description + "/at" + taskParts[3];
                        Task newTask = new Event(deadLineDesc);
                        if (taskParts[1].equals("1"))
                            newTask.markAsDone();

                        todoList[i] = newTask;
                        break;
                    }
                }
                break;
            }
        }
        Files.delete(Paths.get(f.toURI()));
        return todoList;
    }
}
