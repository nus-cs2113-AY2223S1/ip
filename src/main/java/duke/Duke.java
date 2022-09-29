package duke;

import duke.command.Storage;
import duke.command.Ui;

public abstract class Duke {
    /**
     * Starts Duke
     * 
     * @param args the arguments provided
     */
    public static void main(String[] args) {
        Storage.loadFile();

        Ui.printIntroduction();
        Ui.readInputs();
        Ui.printExit();

        Storage.saveFile();
    }
}
