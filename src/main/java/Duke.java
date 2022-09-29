import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static final int MAX = 100;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList();
            Storage.getFileContents(filePath, tasks);
        } catch (FileNotFoundException e) {
            Ui.showFileWriteError();
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
//                String fullCommand = ui.readCommand();
                String commandInput = ui.readCommand();
                ui.showDivider();
                Command command = new Command(Parser.parse(commandInput));
                isExit = command.executeCommand(tasks, commandInput);
            } catch (Exception e) {
                ui.showError("Invalid command! Try again :)");
            } finally {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }


//    public static void main(String[] args) {
//        new Duke("data/tasks.txt").run();
//        boolean isExit = false;
//        ui = new Ui();
//        ui.welcomeMessage();
//        TaskList tasks = new TaskList(MAX);
//        while(!isExit){
//            ui.showDivider();
//            try{
//                String commandInput = ui.readCommand();
//                Command command = new Command(Parser.parse(commandInput));
//                isExit = command.executeCommand(tasks, commandInput);
//            } catch (DukeException e) {
//                System.out.println("Could not execute command! Try again");
//            }
//        }
//        ui.goodbyeMessage();
//
//    }



}
