import file.FileManager;
import handler.InputHandler;
import task.TaskList;
import ui.UI;

import java.io.FileNotFoundException;

public class Duke {
    public static void startProgram() {
            UI.welcomeUser();
            FileManager.startReading();
            InputHandler.handleInput();
    }

    public static void main(String[] args) {
        startProgram();
        TaskList.storeTasks();
    }
}
