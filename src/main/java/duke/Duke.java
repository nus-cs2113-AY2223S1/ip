package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidEventInputException;
import duke.exception.InvalidTaskDescriptionException;
import duke.parser.CommandParser;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.Ui.printIntroMessage;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        // this.tasks = new TaskList(new ArrayList<>());
        this.tasks = new TaskList();
    }

    
    public void run() {
        printIntroMessage(tasks);
        Scanner sc = new Scanner(System.in);
        String fullCommand;
        boolean isExit = false;
        while (!isExit) {
            fullCommand = sc.nextLine();
            try {
                Command keyword = CommandParser.parseCommand(fullCommand);
                keyword.execute(tasks, storage, ui, fullCommand);
                isExit = keyword.isExit();
            } catch (InvalidTaskDescriptionException e) {
                Ui.showInvalidTodoInputExceptionMessage();
            } catch (InvalidCommandException e) {
                Ui.printUnknownCommand();
            } catch (DukeException e) {
                Ui.printUnknownCommand();
            } 
        }
    }

    public static void main(String[] args){
        new Duke().run();
    }
}



