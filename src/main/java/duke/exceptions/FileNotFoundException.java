package duke.exceptions;

import duke.ui.Ui;

public class FileNotFoundException extends Exception{
    private static final String MESSAGE = "File not found.";

    public FileNotFoundException() {
        Ui.printLine();
        System.out.println(MESSAGE);
        Ui.printLine();
    }

    public String getMessage() {
        return MESSAGE;
    }
}
