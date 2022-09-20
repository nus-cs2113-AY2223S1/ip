package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.NoSpecficTimeException;
import duke.exception.NoSpecificDeadlineException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final static String directory = "./data/";
    private final static String filePath = "./data/duke.txt";

    public Storage() throws IOException {
        File dir = new File(directory);
        boolean isExist = dir.exists();
        if (!isExist) {
            dir.mkdir();
        }

        File f = new File(filePath);
        boolean hasFile = f.exists();
        if (hasFile) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String entry = s.nextLine();
                String[] entries = entry.split("\\s\\|\\s");

                String taskType = entries[0];
                boolean isMark = entries[1].equals("1");
                String entryText = entries[2];

                importTask(taskType, entryText, isMark);

            }
        }
    }

    public static void importTask(String taskType, String command, boolean isMark) {
        boolean isTodo = taskType.contains("T");
        boolean isEvent = taskType.contains("E");
        boolean isDeadline = taskType.contains("D");

        try {
            if (isTodo) {
                TaskList.tasks.add(new Todo(command));
            } else if (isDeadline) {
                TaskList.tasks.add(new Deadline(command));
            } else if (isEvent){
                TaskList.tasks.add(new Event(command));
            }

        } catch (EmptyDescriptionException emptyException) {
            System.out.println("OOPS!!! The description cannot be empty.");
        } catch (NoSpecificDeadlineException e) {
            System.out.println("You did not enter the deadline. Please re-enter the deadline by including '/by'.");
        } catch (NoSpecficTimeException e) {
            System.out.println("You did not specify the time for the event. Please re-enter the event by including '/at'. ");
        }

        if (isMark) {
            int latestTask = TaskList.tasks.size() - 1;
            TaskList.tasks.get(latestTask).setStatusIcon("mark");
        }
    }

    public static void updateLine(String command, int index) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String text = "";
        int currentIndex = 0;
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            if (index == currentIndex) {
                text += changeMarkValue(currentLine, command) + System.lineSeparator();
            } else {
                text += currentLine + System.lineSeparator();
            }
            currentIndex += 1;
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();
    }

    public static String changeMarkValue(String currentLine, String markOrUnmark) {
        if (markOrUnmark.equals("mark")) {
            return currentLine.replace(" | 0 | ", " | 1 | ");
        }
        return currentLine.replace(" | 1 | ", " | 0 | ");
    }

    public static void deleteLine(int index) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        String text = "";
        int currentIndex = 0;
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            if (index != currentIndex) {
                text += currentLine + System.lineSeparator();
            }
            currentIndex += 1;
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();
    }

    public static void appendFile(String taskType) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            int lastIndex = TaskList.tasks.size() - 1;
            String appendTask = TaskList.tasks.get(lastIndex).getTask();

            if (taskType.equals("todo")) {
                fw.write("T | 0 | " + appendTask + System.lineSeparator());
            } else if (taskType.equals("deadline")) {
                fw.write("D | 0 | " + appendTask + System.lineSeparator());
            } else {
                fw.write("E | 0 | " + appendTask + System.lineSeparator());
            }
            fw.close();

        } catch (IOException exception) {
            System.out.println("Error writing to file.");
        }
    }


}
