package Duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    final String path = ("duke.txt");

    /**
     * Initializes file by creating it if it does not exist
     * @param taskList
     * @throws IOException
     */

    public void initializeFile(TaskList taskList) throws IOException {
        File duke = new File(path);
        duke.createNewFile();
        loadFromFile(taskList, duke);
    }

    /**
     * Loads from file duke.txt once it has been created
     * @param taskList
     * @param duke file we are using
     * @throws IOException
     */
    public void loadFromFile(TaskList taskList, File duke) throws IOException {
        Scanner SC = new Scanner(duke);
        String lineOfData;
        while (SC.hasNextLine()) {
            taskList.noOfTasks++;
            lineOfData = SC.nextLine();
            translateLineOfData(lineOfData, taskList);
        }
    }

    /**
     * Translates one line of data from duke.txt
     * @param lineOfData line of data in question
     * @param taskList
     */

    public void translateLineOfData(String lineOfData, TaskList taskList) {
        char type = lineOfData.charAt(0);
        char marked = lineOfData.charAt(1);
        boolean isMarked;
        if (marked == 'X') {
            isMarked = true;
        } else {
            isMarked = false;
        }
        String description;
        String time;
        if (type == 'E') {
            description = getEventDescription(lineOfData.substring(2));
            time = getEventTime(lineOfData.substring(2));
            taskList.tasks.add(new Event(description, time, isMarked));
        } else if (type == 'D') {
            description = getDeadlineDescription(lineOfData.substring(2));
            time = getDeadlineTime(lineOfData.substring(2));
            taskList.tasks.add(new Deadline(description, time, isMarked));
        } else if (type == 'T') {
            description = getTodoDescription(lineOfData.substring(2));
            taskList.tasks.add(new Todo(description, isMarked));
        }
    }

    /**
     * Gets description of event type task from file
     * @param input line present in file
     * @return event description
     */
    public String getEventDescription(String input) {
        String event = input.substring(0, input.indexOf("/at"));
        return event.trim();
    }

    /**
     * Get description of deadline type task from file
     * @param input line present in file
     * @return deadline description
     */
    public String getDeadlineDescription(String input) {
        String event = input.substring(0, input.indexOf("/by"));
        return event.trim();
    }

    /**
     * Get description of todo type task from file
     * @param input line present in file
     * @return todo description
     */

    public String getTodoDescription(String input) {
        String event = input;
        return event.trim();
    }

    /**
     * Get time of event type task from file
     * @param input line present in file
     * @return time of event
     */

    public String getEventTime(String input) {
        String time = input.substring(input.indexOf("/at") + 3);
        return time.trim();
    }

    /**
     * Get time of deadline type task
     * @param input line present in file
     * @return deadline time
     */

    public String getDeadlineTime(String input) {
        String time = input.substring(input.indexOf("/by") + 3);
        return time.trim();
    }

    /**
     * Writes all tasks currently present in taskList to the file
     * @param taskList
     * @throws IOException
     */

    public void writeAllEvents(TaskList taskList) throws IOException {
        File duke = new File(path);
        FileWriter fw = new FileWriter(duke, false);
        String description;
        String time;
        String type;
        boolean isMarked;
        for (int i = 0; i < taskList.noOfTasks; i++) {
            type = taskList.tasks.get(i).getType();
            description = taskList.tasks.get(i).getDescription();
            time = taskList.tasks.get(i).getTime();
            isMarked = taskList.tasks.get(i).isMark();
            addOneTask(type, description, time, isMarked, fw);
            fw.write('\n');
        }
        fw.close();
    }

    /**
     * Adds one task from taskList to file
     * @param type type of task
     * @param description description of task
     * @param time time of task(if exists)
     * @param isMarked whether task is marked or not
     * @param fw FileWriter object to write to file
     * @throws IOException
     */

    public void addOneTask(String type, String description, String time, boolean isMarked, FileWriter fw) throws IOException {
        if (type.equals("[E]")) {
            fw.write('E');
        } else if (type.equals("[T]")) {
            fw.write('T');
        } else if (type.equals("[D]")) {
            fw.write('D');
        }
        if (isMarked) {
            fw.write('X');
        } else {
            fw.write(' ');
        }
        fw.write(description);
        if (type.equals("[E]")) {
            fw.write("/at");
        } else if (type.equals("[D]")) {
            fw.write("/by");
        }
        if (type.equals("[E]") || type.equals("[D]"))
            fw.write(time);
    }
}
