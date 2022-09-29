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
    private static final String EVENT = "event";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";

    private static final String FILE_PATH = "data/duke.txt";


    private static final String SEPARATOR = "____________________________________________________________";

    private static Storage storage;
    private static ArrayList<Task> tasks;
    private static Ui ui;


    public static void main(String[] args) {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new ArrayList<Task>();

        Ui.readMsg(FILE_PATH);
        try {
            tasks = Storage.loadFile(tasks, ui);
        } catch (FileNotFoundException e) {
            ui.fileNotFoundMsg();
        }
        Ui.finishReadMsg();
        Ui.welcomeMsg();

        Scanner input = new Scanner(System.in);
        String val = input.nextLine();


        while (!val.equals("bye")) {
            ui.separatorMsg();
            if (val.equals("list")) {
                ui.printList(tasks);
            } else if (val.contains("unmark")) {
                markTask(val, tasks, false);
                try {
                    storage.saveTask(tasks);
                } catch (IOException e) {
                    ui.failToSaveMsg();
                }
            } else if (val.contains("mark")) {
                markTask(val, tasks, true);
                try {
                    storage.saveTask(tasks);
                } catch (IOException e) {
                    ui.failToSaveMsg();
                }
            } else if (val.contains("delete")) {
                deleteTask(val, tasks);

                try {
                    storage.saveTask(tasks);
                } catch (IOException e) {
                    ui.failToSaveMsg();
                }
            } else {
                addTask(val, tasks);
            }
            ui.separatorMsg();
            val = input.nextLine();
        }

        Ui.byeMsg();
    }


    private static int addTask(String val, ArrayList<Task> tasks) {
        String description;
        String time;
        if (val.contains(TODO)) {
            if (val.substring(TODO.length()).trim().isEmpty()) {
                Ui.emptyErrorMsg(TODO);
                return 0;
            }
            description = val.substring(TODO.length());
            tasks.add(new Todo(description));

        } else if (val.contains(DEADLINE)) {
            if (val.substring(DEADLINE.length()).trim().isEmpty()) {
                Ui.emptyErrorMsg(DEADLINE);
                return 0;
            }

            description = val.substring(DEADLINE.length(), val.indexOf("/"));
            time = val.substring((val.indexOf("/") + 4));
            tasks.add(new Deadline(description, time));

        } else if (val.contains(EVENT)) {
            if (val.substring(EVENT.length()).trim().isEmpty()) {
                Ui.emptyErrorMsg(EVENT);
                return 0;
            }
            description = val.substring(EVENT.length(), val.indexOf("/"));
            time = val.substring((val.indexOf("/") + 4));
            tasks.add(new Event(description, time));

        } else {
            ui.unKnownMsg();
            return 0;
        }

        try {
            Storage.saveLine(tasks.get(tasks.size() - 1).toString());
        } catch (IOException e) {
            ui.failToSaveMsg();
        }

        ui.numOfTaskMsg(tasks.size());
        return 1;
    }

    private static int deleteTask(String val, ArrayList<Task> tasks) {
        int id = val.indexOf(" ");
        if (id < 0) {
            ui.invalidIndexMsg();
            return 0;
        }
        String ind = val.substring(id + 1);
        int index = Integer.parseInt(ind) - 1;
        ui.removeTaskMsg(tasks.get(index));
        tasks.remove(index);
        ui.numOfTaskMsg(tasks.size());
        try {
            storage.saveTask(tasks);
        } catch (IOException e) {
            ui.failToSaveMsg();
        }
        return 1;
    }


    private static void markTask(String val, ArrayList<Task> tasks, boolean status) {
        int id = val.indexOf(" ");
        if (id < 0) {
            ui.indexEmptyMsg();
            return;
        }
        String ind = val.substring(id + 1);
        int index = Integer.parseInt(ind) - 1;

        if (index >= tasks.size()) {
            ui.indexOutOfBoundMsg();
        }

        tasks.get(index).setDone(status);
        ui.markTaskMsg(status, tasks.get(index));
    }



}

