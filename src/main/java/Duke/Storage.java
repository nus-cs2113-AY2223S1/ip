package Duke;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void writeToFile(String filePath, ArrayList<Task> list) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f);
        for (Task task: list) {
            Boolean isDone = task.isDone;
            fw.write(task.type + "|" + isDone + "|" + task.description + "|" + task.by + System.lineSeparator());
        }
        fw.close();
    }

    public static void getFileContents(String filePath, ArrayList list) throws FileNotFoundException {
        Scanner s = new Scanner(new FileInputStream((filePath)));
        while (s.hasNext()) {
            String commands = s.nextLine();
            String inputCommands[] = commands.split("\\|", 4);
            switch (inputCommands[0]) {
            case "T":
                Todo addTodo = new Todo(inputCommands[2]);
                if (inputCommands[1] == "true") {
                    addTodo.isDone = true;
                }
                list.add(addTodo);
                break;
            case "D":
                Deadline addDeadline = new Deadline(inputCommands[2], inputCommands[3]);
                if (inputCommands[1] == "true") {
                    addDeadline.isDone = true;
                }
                list.add(addDeadline);
                break;
            case "E":
                Event addEvent = new Event(inputCommands[2], inputCommands[3]);
                if (inputCommands[1] == "true") {
                    addEvent.isDone = true;
                }
                list.add(addEvent);
                break;
            default:
                throw new FileNotFoundException();
            }
        }
    }
}
