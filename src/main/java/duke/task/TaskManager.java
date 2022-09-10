package duke.task;

import java.util.ArrayList;
import duke.command.InputManager;
import duke.command.Parser;
import duke.exception.MissingDeadlineDescriptionException;
import duke.exception.MissingEventDescriptionException;
import duke.exception.MissingTaskNumberException;
import duke.exception.MissingTodoDescriptionException;
import duke.exception.NonIntegerTaskNumberException;
import duke.exception.OutOfBoundsTaskNumberException;

public abstract class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask(String type, String input) {
        final String ADD_PHRASE_START = "added: ";
        // add one because newTask has not been created yet
        final String ADD_PHRASE_END =
                "There are " + Integer.toString(Task.getTaskCount() + 1) + " task(s) in your list.";

        Task newTask;
        try {
            newTask = Parser.parseTask(type, input);
        } catch (MissingTodoDescriptionException e) {
            System.out.println("Missing Todo Description");
            return;
        } catch (MissingDeadlineDescriptionException e) {
            System.out.println("Missing Deadline Description");
            return;
        } catch (MissingEventDescriptionException e) {
            System.out.println("Missing Event Description");
            return;
        }

        // change to zero-index
        tasks.add(newTask);

        System.out.println(ADD_PHRASE_START + newTask);
        System.out.println(ADD_PHRASE_END);
    }

    public static void printList() {
        for (int i = 0; i < Task.getTaskCount(); i += 1) {
            System.out.println(Integer.toString(i + 1) + ". " + tasks.get(i));
        }
    }

    public static void markTask(String input) {
        int taskNum;

        try {
            taskNum = Parser.parseTaskNumber(InputManager.MARK_PHRASE, input);
        } catch (MissingTaskNumberException e) {
            System.out.println("Missing task number");
            return;
        } catch (NonIntegerTaskNumberException e) {
            System.out.println("Non-integer task number");
            return;
        } catch (OutOfBoundsTaskNumberException e) {
            System.out.println("Out of bounds task number");
            return;
        }

        tasks.get(taskNum).setDone();

        System.out.println("Completed! The following task is marked as done:");
        System.out.println(tasks.get(taskNum));
    }

    public static void unmarkTask(String input) {
        int taskNum;

        try {
            taskNum = Parser.parseTaskNumber(InputManager.UNMARK_PHRASE, input);
        } catch (MissingTaskNumberException e) {
            System.out.println("Missing task number");
            return;
        } catch (NonIntegerTaskNumberException e) {
            System.out.println("Non-integer task number");
            return;
        } catch (OutOfBoundsTaskNumberException e) {
            System.out.println("Out of bounds task number");
            return;
        }

        tasks.get(taskNum).setUndone();

        System.out.println("Oh no! The following task is marked as undone:");
        System.out.println(tasks.get(taskNum));
    }
}
