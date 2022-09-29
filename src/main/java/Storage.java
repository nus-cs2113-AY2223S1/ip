
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static void writeToFile(String filePath, ArrayList<Task> list) {
        try {
            File storedFile = new File(filePath);
            FileWriter writeFile = new FileWriter(storedFile);
            for (Task task : list) {
                Boolean isDone = task.isDone;
                writeFile.write(task.type + "|" + isDone + "|" + task.description + System.lineSeparator());
            }
            writeFile.close();
        } catch (IOException e) {
            Ui.showFileWriteError();
        }
    }

    public static void getFileContents(String filePath, TaskList tasks) throws FileNotFoundException {
        File storedFile = new File(filePath);
        if (!storedFile.getParentFile().exists()) {
            storedFile.getParentFile().mkdirs();
        }
        try {
            if (!storedFile.exists()) {
                storedFile.createNewFile();
            }
        } catch (IOException e) {
            Ui.showFileWriteError();
        }
        Scanner s = new Scanner(new FileInputStream((filePath)));
        while (s.hasNext()) {
            String command = s.nextLine();
            String inputCommands[] = command.split("\\|", 3);
            switch (inputCommands[0]) {
                case "todo":
                    Task todo = new Task("todo", inputCommands[2]);
                    TaskList.checkMarked(todo,inputCommands[1]);
                    tasks.add(todo);
                    break;
                case "deadline":
                    Task deadline = new Task("deadline", inputCommands[2]);
                    TaskList.checkMarked(deadline,inputCommands[1]);
                    tasks.add(deadline);
                    break;
                case "event":
                    Task event = new Task("event", inputCommands[2]);
                    TaskList.checkMarked(event,inputCommands[1]);
                    tasks.add(event);
                    break;
                default:
                    throw new FileNotFoundException();
            }
        }
    }
}
