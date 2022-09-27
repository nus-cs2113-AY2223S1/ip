package Duke;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public static void writeToFile(String filePath, ArrayList<Task> list) throws IOException {
        File storedFile = new File(filePath);
        File parent = storedFile.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        if (!storedFile.exists()) {
            storedFile.createNewFile();
        }
        FileWriter writeFile = new FileWriter(storedFile);
        for (Task task: list) {
            Boolean isDone = task.isDone;
            writeFile.write(task.type + "|" + isDone + "|" + task.description +
                    "|" + task.by + System.lineSeparator());
        }
        writeFile.close();
    }
    public static void getFileContents(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        Scanner s = new Scanner(new FileInputStream((filePath)));
        while (s.hasNext()) {
            String command = s.nextLine();
            String inputCommands[] = command.split("\\|", 4);
            switch (inputCommands[0]) {
            case "T":
                Todo addTodo = new Todo(inputCommands[2]);
                Ui.checkMarked(addTodo,inputCommands[1]);
                list.add(addTodo);
                break;
            case "D":
                Deadline addDeadline = new Deadline(inputCommands[2], inputCommands[3]);
                Ui.checkMarked(addDeadline,inputCommands[1]);
                list.add(addDeadline);
                break;
            case "E":
                Event addEvent = new Event(inputCommands[2], inputCommands[3]);
                Ui.checkMarked(addEvent,inputCommands[1]);
                list.add(addEvent);
                break;
            default:
                throw new FileNotFoundException();
            }
        }
    }
}
