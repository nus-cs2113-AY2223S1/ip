package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.NoSpecificTimeException;
import duke.exception.NoSpecificDeadlineException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final static String DIRECTORY = "./data/";
    private final static String FILE_PATH = "./data/duke.txt";
    private final static String TODO_SHORTCUT = "T";
    private final static String EVENT_SHORTCUT = "E";
    private final static String DEADLINE_SHORTCUT = "D";
    private final static String UNMARKED_INDICATOR = "0";
    private final static String MARKED_INDICATOR = "1";

    /**
     * Creates file and directory if it does not exist.
     *
     * @throws IOException When creating directory or file results in an error.
     */
    public Storage() throws IOException {
        File dir = new File(DIRECTORY);
        boolean isExist = dir.exists();
        if (!isExist) {
            dir.mkdir();
        }

        File f = new File(FILE_PATH);
        boolean hasFile = f.exists();
        if (hasFile) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String entry = s.nextLine();
                String[] entries = entry.split("\\s\\|\\s");

                String taskType = entries[0];
                boolean isMark = entries[1].equals("1");
                String entryText = entries[2].replace(")", "");

                importTask(taskType, entryText, isMark);

            }
        }
    }

    /**
     * Imports the task that is stored in text file back to the list of task when ran.
     *
     * @param taskType A string indicate the type of task.
     * @param description The description of the task that is imported.
     * @param isMark A boolean to indicate whether the task is marked.
     */
    public static void importTask(String taskType, String description, boolean isMark) {
        boolean isTodo = taskType.contains(TODO_SHORTCUT);
        boolean isEvent = taskType.contains(EVENT_SHORTCUT);
        boolean isDeadline = taskType.contains(DEADLINE_SHORTCUT);

        // Import the task to the specific type according to the type.
        try {
            if (isTodo) {
                TaskList.tasks.add(new Todo(description));
            } else if (isDeadline) {
                TaskList.tasks.add(new Deadline(description));
            } else if (isEvent){
                TaskList.tasks.add(new Event(description));
            }

        } catch (EmptyDescriptionException emptyException) {
            System.out.println("OOPS!!! The description cannot be empty.");
        } catch (NoSpecificDeadlineException e) {
            System.out.println("You did not enter the deadline. Please re-enter the deadline by including '/by'.");
        } catch (NoSpecificTimeException e) {
            System.out.println("You did not specify the time for the event. Please re-enter the event by including '/at'. ");
        }

        if (isMark) {
            int latestTask = TaskList.tasks.size() - 1;
            TaskList.tasks.get(latestTask).setStatusIcon("mark");
        }
    }

    /**
     * Updates the line indicated when user calls a mark or unmark operation.
     *
     * @param command A command indicating whether it's 'mark' or 'unmark'
     * @param index The index of which the statement need to be updated.
     * @throws IOException If there is an error when writing to the file.
     */
    public static void updateLine(String command, int index) throws IOException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        String text = "";
        int currentIndex = 0;

        while (s.hasNext()) {
            String currentLine = s.nextLine();

            boolean isCurrentIndex = index == currentIndex;
            if (isCurrentIndex) {
                text += changeMarkValue(currentLine, command) + System.lineSeparator();
            } else {
                text += currentLine + System.lineSeparator();
            }
            currentIndex += 1;
        }

        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(text);
        fw.close();
    }

    /**
     * Changes the marked value of the file.
     *
     * @param currentLine The line of which marked value will be changed.
     * @param markOrUnmark A string containing either "mark" or "unmark"
     * @return The string of which mark value is changed.
     */
    public static String changeMarkValue(String currentLine, String markOrUnmark) {
        if (markOrUnmark.equals("mark")) {
            String oldString = " | " + UNMARKED_INDICATOR + " | ";
            String newString = " | " + MARKED_INDICATOR + " | ";
            return currentLine.replace(oldString, newString);
        }
        String oldString = " | " + UNMARKED_INDICATOR + " | ";
        String newString = " | " + MARKED_INDICATOR + " | ";
        return currentLine.replace(newString, oldString);
    }

    /**
     * Deletes the specified line from the file.
     *
     * @param index The line that will be deleted.
     * @throws IOException When there is an error when writing to the file.
     */
    public static void deleteLine(int index) throws IOException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        String text = "";
        int currentIndex = 0;
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            boolean isNotCurrentLine = index != currentIndex;

            if (isNotCurrentLine) {
                text += currentLine + System.lineSeparator();
            }
            currentIndex += 1;
        }

        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(text);
        fw.close();
    }


    /**
     * Appends a new line to the file.
     *
     * @param taskType The type of the task to be appended.
     */
    public static void appendFile(String taskType) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);

            // Get the latest task added to the list.
            int lastIndex = TaskList.tasks.size() - 1;
            String appendTask = TaskList.tasks.get(lastIndex).getTask();

            if (taskType.equals("todo")) {
                String text = TODO_SHORTCUT + " | " + UNMARKED_INDICATOR + " | " + appendTask + System.lineSeparator();
                fw.write(text);
            } else if (taskType.equals("deadline")) {
                String text = DEADLINE_SHORTCUT + " | " + UNMARKED_INDICATOR + " | " + appendTask + System.lineSeparator();
                fw.write(text);
            } else {
                String text = EVENT_SHORTCUT + " | " + UNMARKED_INDICATOR + " | " + appendTask + System.lineSeparator();
                fw.write(text);
            }
            fw.close();

        } catch (IOException exception) {
            System.out.println("Error writing to file.");
        }
    }


}
