package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {

    private static Ui ui = new Ui ();
    private static Parser parser = new Parser();


    public static void main(String[] args) throws IOException {
        ui.greet();

        try {
            Storage.loadTasks();
        } catch (FileNotFoundException e){
            System.out.println ("A new file just got created!");
        }

        boolean isFinished = false;
        while (!isFinished) {
            String input = ui.userInput();
            ui.printDashLine();
            parser.parse(input);
            isFinished = (input.equals(Parser.BYE));
        }
        Storage.saveTasks();

    }



}



