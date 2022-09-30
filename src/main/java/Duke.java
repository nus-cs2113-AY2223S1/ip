import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static final String FILE_PATH = "data/duke.txt";

    private static Storage storage;
    private static ArrayList<Task> tasks;
    private static Ui ui;

    private Parser parser;
    private static TaskList taskList;

    public Duke(){
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new ArrayList<Task>();
        taskList = new TaskList();
        parser = new Parser();
    }

    public void run(){
        Ui.readMsg(FILE_PATH);

        try {
            tasks = Storage.loadFile(tasks, ui);
        } catch (FileNotFoundException e) {
            ui.fileNotFoundMsg();
        }
        Ui.finishReadMsg();
        Ui.welcomeMsg();

        Scanner input = new Scanner(System.in);
        String val = input.nextLine().trim();



        while (!parser.quit(val)) {
            while(val.isEmpty()){
                Ui.emptyMsg();
                val = input.nextLine().trim();
            }

            ui.separatorMsg();

            if (parser.isList(val)) {
                ui.printList(tasks);
            } else if (parser.is(val, "unmark ")) {
                taskList.markTask(val, tasks, false, ui, parser);
                try {
                    storage.saveTask(tasks);
                } catch (IOException e) {
                    ui.failToSaveMsg();
                }
            } else if (parser.is(val, "mark ")) {
                taskList.markTask(val, tasks, true, ui, parser);
                try {
                    storage.saveTask(tasks);
                } catch (IOException e) {
                    ui.failToSaveMsg();
                }
            } else if (parser.is(val, "delete ")) {
                taskList.deleteTask(val, tasks, ui, storage, parser);

                try {
                    storage.saveTask(tasks);
                } catch (IOException e) {
                    ui.failToSaveMsg();
                }
            } else {
                taskList.addTask(val, tasks, ui, parser);

                try {
                    Storage.saveLine(tasks.get(tasks.size() - 1).toString());
                } catch (IOException e) {
                    ui.failToSaveMsg();
                }
            }
            ui.separatorMsg();
            val = input.nextLine();
        }

        Ui.byeMsg();
    }




    public static void main(String[] args) {
        new Duke().run();
    }





}

