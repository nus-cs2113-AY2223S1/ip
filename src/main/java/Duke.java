import task.Task;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

/**
 * the manager of the system
 */
public class Duke {

    private final String FILE_PATH = "data/duke.txt";

    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;

    private Parser parser;
    private TaskList taskList;

    /**
     * initialize the parameters
     */
    public Duke(){
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new ArrayList<>();
        taskList = new TaskList();
        parser = new Parser();
    }

    /**
     * the main structure of the program.
     */
    public void run(){
        ui.readMsg(FILE_PATH);

        try {
            tasks = storage.loadFile(tasks, ui);
        } catch (FileNotFoundException e) {
            ui.fileNotFoundMsg();
        }
        ui.finishReadMsg();
        ui.welcomeMsg();

        Scanner input = new Scanner(System.in);
        String val = input.nextLine().trim();



        while (!parser.quit(val)) {
            while(val.isEmpty()){
                ui.emptyMsg();
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
            } else if(parser.is(val, "find ")){
                taskList.find(val, tasks, parser, ui);
            } else {
                taskList.addTask(val, tasks, ui, parser);
            }
            ui.separatorMsg();
            val = input.nextLine().trim();
        }

        ui.byeMsg();
    }



    /**
     * program begins by calling Duke().run()
     */
    public static void main(String[] args) {
        new Duke().run();
    }





}

