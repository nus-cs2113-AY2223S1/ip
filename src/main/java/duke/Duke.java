package duke;

import duke.exception.DukeException;
import duke.exception.EmptyTaskDescriptionException;
import duke.exception.InvalidDeadlineInputException;
import duke.exception.InvalidEventInputException;
import duke.exception.InvalidTodoInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import static duke.ui.Ui.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.storage.FileManager.*;

public class Duke {

    public static void main(String[] args) throws DukeException {
        String line;
        Scanner in = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();

        // attempts to find file and add to list. if not found, creates a new file in a new directory
        try {
            loadInputFile(tasks);
        } catch (FileNotFoundException e) {
            createNewFile();
        }

        printIntroMessage(tasks);

        do {
            line = in.nextLine();
            String type = TaskManager.getTaskType(line);

            if (type.equals("bye")) {
                // System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(BYE_MESSAGE);
                clearCurrentFile();
                saveNewList(tasks);
                return;
            }
            switch (type) {
            case "list":
                TaskManager.printTaskList(tasks);
                break;
            case "delete":
                try {
                    TaskManager.deleteTask(tasks, line);
                } 
                catch (NumberFormatException e) {
                    System.out.println(WRONG_NUMBER_INPUT_FORMAT);
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println(ITEM_NOT_PRESENT);
                }
                break;
            case "mark":
                try {
                    TaskManager.markTask(tasks, line);
                } catch (NumberFormatException e) {
                    System.out.println(WRONG_NUMBER_INPUT_FORMAT);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(ITEM_NOT_PRESENT);
                }
                break;
            case "unmark":
                try {
                    TaskManager.unmarkTask(tasks, line);
                } catch (NumberFormatException e) {
                    System.out.println(WRONG_NUMBER_INPUT_FORMAT);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(ITEM_NOT_PRESENT);
                }
                break;
            case "total":
                    TaskManager.printTotalNumberOfItems(tasks);
                break;
            case "todo":
                try {
                    String details = TaskManager.getTaskDetails(line);
                    Task t = new Todo(details);
                    tasks.add(t);
                    printSuccessfulAdd(tasks);
                } catch (EmptyTaskDescriptionException | InvalidTodoInputException e) {
                    showInvalidTodoInputExceptionMessage();
                }
                break;
            case "deadline":
                try {
                    String details = TaskManager.getTaskDetails(line);
                    String[] split = Deadline.splitDeadlineDescription(details);
                    Task d = new Deadline(split[0], split[1]);
                    tasks.add(d);
                    printSuccessfulAdd(tasks);
                } catch (EmptyTaskDescriptionException e) {
                    showInvalidDeadlineInputExceptionMessage();
                } catch (InvalidDeadlineInputException e) {
                    showInvalidDeadlineInputExceptionMessage();
                }
                break;
            case "event":
                try {
                    String details = TaskManager.getTaskDetails(line);
                    String[] split = Event.splitEventDescription(details);
                    Task e = new Event(split[0], split[1]);
                    tasks.add(e);
                    printSuccessfulAdd(tasks);
                } catch (EmptyTaskDescriptionException e) {
                    showInvalidEventInputExceptionMessage();
                } catch (InvalidEventInputException e) {
                    showInvalidEventInputExceptionMessage();
                }
                break;
            default:
                System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } while (!line.equals("bye"));
    }
}



