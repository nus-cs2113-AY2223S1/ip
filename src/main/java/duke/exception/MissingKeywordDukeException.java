package duke.exception;

import duke.Ui;

/**
 * Represents exception with missing search keyword
 */
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
