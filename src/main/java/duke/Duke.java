package duke;

import java.util.Scanner;

/**
 * Main class for Duke - a Personal Assistant Chatbot that helps you keep track of various things
 */

public class Duke {

    private static Storage storage;
    private static TaskList taskList;
    private static Parser parser;
    private static Ui ui;
    private static boolean isExit;

    public Duke(String filepath) {
        storage = new Storage(filepath);
        taskList = new TaskList(storage);
        storage.setTaskList(taskList);
        parser = new Parser();
        ui = new Ui();
        isExit = false;
    }

    public void run() {

        storage.openFile();

        // Wait for input
        String line;
        Scanner in = new Scanner(System.in);

        ui.greet();

        // Handle user input
        while (!isExit) {
            line = in.nextLine();
            ui.printLine();
            Command command = parser.parseString(line);
            switch (command) {
            case BYE:
                ui.bye();
                isExit = true;
                break;
            case TODO:
                taskList.todo(line);
                break;
            case DEADLINE:
                taskList.deadline(line);
                break;
            case EVENT:
                taskList.event(line);
                break;
            case LIST:
                taskList.list();
                break;
            case MARK:
                taskList.mark(true, line);
                break;
            case UNMARK:
                taskList.mark(false, line);
                break;
            case DELETE:
                taskList.delete(line);
                break;
            case FIND:
                taskList.find(line);
                break;
            case INVALID:
                ui.showInvalidCommand();
                break;
            default:
                ui.showUnknownError();
                break;
            }
            ui.printLine();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
