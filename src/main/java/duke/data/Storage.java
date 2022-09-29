package duke.data;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadlines;
import duke.task.Events;
import duke.Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_PATH = "data/duke.txt";

    public static void saveTask(ArrayList<Task> Tasks) throws IOException {
        Path fileDirectory = Paths.get(FILE_DIRECTORY);

        if (Files.notExists(fileDirectory)) {
            Files.createFile(fileDirectory);
        }

        Path file = Paths.get(FILE_PATH);

        if (Files.notExists(file)) {
            Files.createFile(file);
        }
        FileWriter writer = new FileWriter(FILE_PATH);

        for(Task task: Tasks) {
            try {
                writer.write(task.SaveAsString() + "\n");
            } catch(IOException e) {
                System.out.println("file not found.");
            }
        }
        writer.close();
    }

    public static void loadTask(ArrayList<Task> Tasks) {
        try {
            File file = new File(FILE_PATH);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                char action = data.charAt(1);
                String description = data.substring(7);
                switch (action) {
                    case 'T':
                        Todo todo = new Todo(description);
                        if (data.charAt(4) == 'X') {
                            todo.setisDone(true);
                        }
                        Duke.Tasks.add(todo);
                        break;
                    case 'D':
                        String deadlineDescription = description.split("/by ")[0];
                        String deadlineDate = description.split("/by ")[1];
                        Deadlines deadline = new Deadlines(deadlineDescription, deadlineDate);
                        if (data.charAt(4) == 'X') {
                            deadline.setisDone(true);
                        }
                        Duke.Tasks.add(deadline);
                        break;
                    case 'E':
                        String EventDescription = description.split("/at ")[0];
                        String EventDate = description.split("/at ")[1];
                        Events event = new Events(EventDescription, EventDate);
                        if (data.charAt(4) == 'X') {
                            event.setisDone(true);
                        }
                        Duke.Tasks.add(event);
                        break;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found.");
        }
    }

}
