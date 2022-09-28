package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    protected String filepath;
    private TaskList taskList;
    private Ui ui;

    public Storage(String filepath) {
        this.filepath = filepath;
        ui = new Ui();
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public void scanFile() throws FileNotFoundException, InvalidFormatException {
        File f = new File(this.filepath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] words = s.nextLine().split("\\|");
            switch (words[0]) {
                case "T":
                    Todo todo = new Todo(words[2], words[1].equals("1"));
                    taskList.addTask(todo, false);
                    break;
                case "D":
                    Deadline deadline = new Deadline(words[2], words[1].equals("1"), words[3]);
                    taskList.addTask(deadline, false);
                    break;
                case "E":
                    Event event = new Event(words[2], words[1].equals("1"), words[3]);
                    taskList.addTask(event, false);
                    break;
                default:
                    throw new InvalidFormatException();
            }
        }
    }

    public void openFile() {
        // Read file
        try {
            scanFile();
        } catch (FileNotFoundException | InvalidFormatException exception) {
            ui.showLoadingError();
            try {
                File file = new File(this.filepath);
                if (!file.getParentFile().mkdirs()) {
                    ui.showCreateParentFolderError();
                }
                if (file.createNewFile()) {
                    ui.showCreateFileSuccess(this.filepath);
                } else {
                    ui.showFileExistsError(this.filepath);
                }
            } catch (IOException ioException) {
                ui.showCreateFileError(this.filepath);
            }
        }
    }

    public void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        for (int i = 0; i < taskList.getSize(); i++) {
            fw.write(taskList.getTask(i).printToFile());
        }
        fw.close();
    }

}