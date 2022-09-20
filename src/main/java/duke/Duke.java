package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.common.ErrorMessages;
import duke.data.TaskList;
import duke.data.exception.InvalidCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.io.IOException;
import java.util.Objects;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    public static void main(String[] args) throws IOException {
        new Duke().run(args);
    }

    public void run(String[] args) throws IOException {
        storage = new Storage();
        tasks = new TaskList();
        ui = new TextUi();

        try {
            tasks = storage.readFromFile();
        } catch (IOException e) {
            ui.showCustomText(ErrorMessages.MESSAGE_ERROR_FILE_IO);
        }

        String input;
        do {
            input = ui.getUserInput();
            try {
                Command command = Parser.parseCommand(input);
                command.execute(ui, tasks, storage);
            } catch (InvalidCommandException e) {
                ui.showCustomText(ErrorMessages.MESSAGE_ERROR_INVALID_COMMAND);
            }
        } while (!Objects.equals(input, ByeCommand.COMMAND));
    }
}