package duke;

import duke.command.Command;
import duke.command.LoadCommand;
import duke.command.SaveCommand;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class Duke {

    //Zhou Zhou

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage();
        Command loadCommand = new LoadCommand();
        loadCommand.execute(taskList, ui, storage);

        boolean isProgramFinished = false;
        while (!isProgramFinished) {
            try {
                String input = Ui.input();
                Command command = Parser.parse(input);
                command.execute(taskList, ui, storage);
                isProgramFinished = command.isExitCommand();
            } catch (DukeException e) {
                e.handle();
            }
        }

        Command saveCommand = new SaveCommand();
        saveCommand.execute(taskList, ui, storage);

    }
}
