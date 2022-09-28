package Duke;

import Duke.Tasks.TaskManager;
import Duke.Storage.Storage;
import Duke.UI.UI;
import Duke.Parser.Parser;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final UI ui;
    private final Parser parser;
    private static Storage storage = null;
    static TaskManager tasks = new TaskManager();

    public Duke ()  {
        this.ui = new UI();
        this.parser = new Parser();
        storage = new Storage();
    }

    public void run() {
        UI.WelcomeMessage();
        try {
            storage.loadData(tasks);
        } catch (IOException e) {
            UI.loadingFileError();
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if(Parser.parseCommand(command) == false) {
                break;
            }
        }
        UI.GoodbyeMessage();
    }



    public static void main(String[] args) {
        new Duke().run();
    }


}


