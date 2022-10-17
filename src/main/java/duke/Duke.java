package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Highest level of the program
 *
 */
public class Duke {

    private static Ui ui = new Ui ();
    private static Parser parser = new Parser();

    /**
     * main function to run the program
     *
     * @param args user inputs
     *
     */
    public static void main(String[] args) {
        ui.greet();

        try {
            Storage.loadTasks();
        } catch (FileNotFoundException e){
            System.out.println ("A new file just got created! \n");
        }

        boolean isFinished = false;
        while (!isFinished) {
            try {
                String input = ui.userInput();
                ui.printDashLine();
                parser.parse(input);
                isFinished = (input.equals(Parser.BYE));
            } catch (DukeException e){
                ui.printOutputs(e.getMessage());
            }

        }
        try {
            Storage.saveTasks();
        } catch (IOException e){
            ui.printOutputs(e.getMessage());
        }


    }



}



