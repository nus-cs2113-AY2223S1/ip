package duke;

import java.io.IOException;
import java.util.Scanner;
import duke.task.TaskManager;
import duke.ui.Ui;
import duke.storage.Storage;

public class Duke {

    public static void run() {
        Ui.greetUser();
        Scanner input = new Scanner(System.in);
        TaskManager taskManager = null;
        try {
            taskManager = Storage.loadFile();
        } catch (IOException e) {
            System.out.println("Something went wrong trying to load the file: " + e.getMessage());
        }
        boolean isProgramFinished = false; //variable to indicate if the program should be terminated
        while(!isProgramFinished) {
            String curr = input.nextLine();
            if(curr.equals("bye")) {
                isProgramFinished = true;
                Ui.sayByeToUser();
            } else if(curr.equals("list")) {
                taskManager.listTasks();
            } else {
                taskManager.handleInput(curr);
            }
        }
    }
    public static void main(String[] args) {
        Duke.run();
    }
}
