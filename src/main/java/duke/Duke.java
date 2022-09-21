package duke;

import duke.exception.*;

import java.io.*;
import java.util.Scanner;


public class Duke {
    private Ui ui;
    private Storage storage;
    private Parser parser;


    public void run() throws IOException {
        start();
        runCommandUntilByeCommand();
    }


    private void start() throws IOException {
        this.storage = new Storage();
        this.ui = new Ui();

        ui.showWelcomeMessage();
    }

    private void runCommandUntilByeCommand() {
        this.parser = new Parser();
        String command;
        do {
            command = Ui.readCommand();
            try {
                this.parser.parseCommand(command);
            } catch (EmptyDescriptionException emptyException) {
                System.out.println("OOPS!!! The description of the task cannot be empty.");
            } catch (NoSpecficTimeException e) {
                System.out.println("No time is entered. Please re-enter and include '/at'.");
            } catch (NoSpecificDeadlineException e) {
                System.out.println("No deadline is entered. Please re-enter and include '/by'. ");
            } catch (IncorrectCommandException e) {
                System.out.println("Incorrect command entered. Please re-enter with the correct command.");
            } catch (IOException e) {
                System.out.println("Error writing to file.");
            }

        } while (!command.equals("bye"));
    }


    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

}
