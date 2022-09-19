package duke.exception;

import duke.Ui;

public class MissingKeywordDukeException extends DukeException {
    public MissingKeywordDukeException() {
    }

    @Override
    public void handle(Ui ui) {
        ui.output("Please enter a keyword\n" +
                "Follow this format:\n" +
                "find {keyword}\n" +
                "Example: find girlfriend");
    }

}
