package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskDescriptionException;
import duke.parser.CommandParser;
import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static duke.Ui.printByeMessage;
import static duke.Ui.printIntroMessage;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
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
            } catch (IndexOutOfBoundsException e) {
                Ui.IndexOutOfBoundsExceptionMessage();
            } catch (NumberFormatException e) {
                Ui.NumberFormatExceptionMessage();
            }
        }
    }

    // exit the program
    // public void exit() {
    //     System.exit(0);
    // }

    public static void main(String[] args){
        new Duke().run();
    }

    // public static void main(String[] args) throws DukeException {
    //     String line;
    //     Scanner in = new Scanner(System.in);

    //     TaskList tasks = initialiseTasks();

    //     printIntroMessage(tasks);

    //     do {
    //         line = in.nextLine();
    //         String type = Parser.getKeyword(line);

    //         if (type.equals("bye")) {
    //             printByeMessage();
    //             clearCurrentFile();
    //             saveNewList(tasks);
    //             return;
    //         }
    //         switch (type) {
    //         case "list":
    //             printTaskList(tasks);
    //             break;
    //         case "delete":
    //             try {
    //                 Task.deleteTask(tasks, line);
    //             } 
    //             catch (NumberFormatException e) {
    //                 System.out.println(WRONG_NUMBER_INPUT_FORMAT);
    //             }
    //             catch (IndexOutOfBoundsException e) {
    //                 System.out.println(ITEM_NOT_PRESENT);
    //             }
    //             break;
    //         case "mark":
    //             try {
    //                 Task.markTask(tasks, line);
    //             } catch (NumberFormatException e) {
    //                 System.out.println(WRONG_NUMBER_INPUT_FORMAT);
    //             } catch (IndexOutOfBoundsException e) {
    //                 System.out.println(ITEM_NOT_PRESENT);
    //             }
    //             break;
    //         case "unmark":
    //             try {
    //                 Task.unmarkTask(tasks, line);
    //             } catch (NumberFormatException e) {
    //                 System.out.println(WRONG_NUMBER_INPUT_FORMAT);
    //             } catch (IndexOutOfBoundsException e) {
    //                 System.out.println(ITEM_NOT_PRESENT);
    //             }
    //             break;
    //         case "total":
    //                 printTotalNumberOfItems(tasks);
    //             break;
    //         case "todo":
    //             try {
    //                 String details = Parser.getTaskDetails(line);
    //                 Task t = new Todo(details);
    //                 tasks.add(t);
    //                 printSuccessfulAdd(tasks);
    //             } catch (InvalidTaskDescriptionException | InvalidTodoInputException e) {
    //                 showInvalidTodoInputExceptionMessage();
    //             }
    //             break;
    //         case "deadline":
    //             try {
    //                 String details = Parser.getTaskDetails(line);
    //                 String[] split = Parser.parseDeadlineDetails(details);
    //                 Task d = new Deadline(split[0], split[1]);
    //                 tasks.add(d);
    //                 printSuccessfulAdd(tasks);
    //             } catch (InvalidTaskDescriptionException e) {
    //                 showInvalidDeadlineInputExceptionMessage();
    //             } catch (InvalidDeadlineInputException e) {
    //                 showInvalidDeadlineInputExceptionMessage();
    //             }
    //             break;
    //         case "event":
    //             try {
    //                 String details = Parser.getTaskDetails(line);
    //                 String[] split = Parser.parseEventDetails(details);
    //                 Task e = new Event(split[0], split[1]);
    //                 tasks.add(e);
    //                 printSuccessfulAdd(tasks);
    //             } catch (InvalidTaskDescriptionException e) {
    //                 showInvalidEventInputExceptionMessage();
    //             } catch (InvalidEventInputException e) {
    //                 showInvalidEventInputExceptionMessage();
    //             }
    //             break;
    //         default:
    //                 printUnknownCommand();
    //         }
    //     } while (!line.equals("bye"));
    // }


}



