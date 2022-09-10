package duke.command;

import duke.exception.InvalidInputException;
import duke.exception.InvalidOutputException;
import duke.exception.MissingDeadlineDescriptionException;
import duke.exception.MissingEventDescriptionException;
import duke.exception.MissingTaskNumberException;
import duke.exception.MissingTodoDescriptionException;
import duke.exception.NonIntegerTaskNumberException;
import duke.exception.OutOfBoundsTaskNumberException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public abstract class Parser {
    public static Task parseTask(String type, String input) throws MissingTodoDescriptionException,
            MissingDeadlineDescriptionException, MissingEventDescriptionException {
        int descriptionIndex;
        String description;
        Task newTask;

        switch (type) {
        case InputManager.TODO_PHRASE:
            descriptionIndex = input.indexOf(InputManager.TODO_PHRASE);

            try {
                description = input.substring(
                        descriptionIndex + InputManager.TODO_PHRASE.length() + 1, input.length());
            } catch (StringIndexOutOfBoundsException e) {
                throw new MissingTodoDescriptionException();
            }

            newTask = new Todo(description);

            break;
        case InputManager.DEADLINE_PHRASE:
            descriptionIndex = input.indexOf(InputManager.DEADLINE_PHRASE);
            int byIndex = input.indexOf(InputManager.BY_PHRASE);

            String by;
            try {
                // add one to remove space
                // minus one to remove space
                description = input.substring(
                        descriptionIndex + InputManager.DEADLINE_PHRASE.length() + 1, byIndex - 1);
                by = input.substring(byIndex + InputManager.BY_PHRASE.length() + 1, input.length());
            } catch (StringIndexOutOfBoundsException e) {
                throw new MissingDeadlineDescriptionException();
            }

            newTask = new Deadline(description, by);

            break;
        case InputManager.EVENT_PHRASE:
            descriptionIndex = input.indexOf(InputManager.EVENT_PHRASE);
            int atIndex = input.indexOf(InputManager.AT_PHRASE);

            String at;
            try {
                // add one to remove space
                // minus one to remove space
                description = input.substring(
                        descriptionIndex + InputManager.EVENT_PHRASE.length() + 1, atIndex - 1);
                at = input.substring(atIndex + InputManager.AT_PHRASE.length() + 1, input.length());
            } catch (StringIndexOutOfBoundsException e) {
                throw new MissingEventDescriptionException();
            }

            newTask = new Event(description, at);

            break;
        default:
            newTask = null;

            break;
        }

        return newTask;
    }

    public static int parseTaskNumber(String type, String input) throws MissingTaskNumberException,
            NonIntegerTaskNumberException, OutOfBoundsTaskNumberException {
        String taskNumString;
        int taskNumInt;

        switch (type) {
        case InputManager.MARK_PHRASE:
            try {
                taskNumString = input.substring(InputManager.MARK_PHRASE.length() + 1);
            } catch (StringIndexOutOfBoundsException e) {
                throw new MissingTaskNumberException();
            }

            try {
                taskNumInt = Integer.parseInt(taskNumString);
            } catch (NumberFormatException e) {
                throw new NonIntegerTaskNumberException();
            }

            if (!isValidTaskNumber(taskNumInt)) {
                throw new OutOfBoundsTaskNumberException();
            }

            break;
        case InputManager.UNMARK_PHRASE:
            try {
                taskNumString = input.substring(InputManager.UNMARK_PHRASE.length() + 1);
            } catch (StringIndexOutOfBoundsException e) {
                throw new MissingTaskNumberException();
            }

            try {
                taskNumInt = Integer.parseInt(taskNumString);
            } catch (NumberFormatException e) {
                throw new NonIntegerTaskNumberException();
            }

            if (!isValidTaskNumber(taskNumInt)) {
                throw new OutOfBoundsTaskNumberException();
            }

            break;
        default:
            taskNumInt = 1;
            break;
        }

        // zero-index
        taskNumInt -= 1;

        return taskNumInt;
    }

    public static String[] parseFileInputs(String line) throws InvalidInputException {
        String[] parts = line.split(", ");

        String taskType = parts[0];
        String markStatus = parts[1];
        String taskDescription = parts[2];

        String inputTask;
        switch (taskType) {
        case InputManager.TODO_PHRASE:
            inputTask = InputManager.TODO_PHRASE + " " + taskDescription;

            break;

        case InputManager.DEADLINE_PHRASE:
            inputTask = InputManager.DEADLINE_PHRASE + " " + taskDescription + " /by " + parts[3];

            break;

        case InputManager.EVENT_PHRASE:
            inputTask = InputManager.EVENT_PHRASE + " " + taskDescription + " /at " + parts[3];

            break;

        default:
            throw new InvalidInputException();
        }

        String inputMark;
        if (markStatus.equals("0")) {
            inputMark = InputManager.UNMARK_PHRASE + " " + (Task.getTaskCount() + 1);
        } else if (markStatus.equals("1")) {
            inputMark = InputManager.MARK_PHRASE + " " + (Task.getTaskCount() + 1);
        } else {
            throw new InvalidInputException();
        }

        String[] fileData = {inputTask, inputMark};

        return fileData;
    }

    public static String parseFileOutputs(String line) throws InvalidOutputException {
        String[] parts = line.split("]");

        String taskType = parts[0].substring(parts[0].length() - 1);
        String markStatus = parts[1].substring(parts[1].length() - 1);

        String outputTask;
        String description;
        String extra = "";
        switch (taskType) {
        case "T":
            outputTask = "todo";
            description = parts[2].substring(1);

            break;

        case "D":
            outputTask = "deadline";
            int byIndex = parts[2].indexOf(" (by: ");
            description = parts[2].substring(1, byIndex);
            extra = parts[2].substring(byIndex + 6, parts[2].length() - 1);

            break;

        case "E":
            int atIndex = parts[2].indexOf(" (at: ");
            description = parts[2].substring(1, atIndex);
            extra = parts[2].substring(atIndex + 6, parts[2].length() - 1);

            outputTask = "event";

            break;

        default:
            throw new InvalidOutputException();
        }

        String outputMark;
        if (markStatus.equals(" ")) {
            outputMark = "0";
        } else if (markStatus.equals("X")) {
            outputMark = "1";
        } else {
            throw new InvalidOutputException();
        }

        String fileData;
        if (extra.isBlank()) {
            fileData = outputTask + ", " + outputMark + ", " + description;
        }
        else {
            fileData = outputTask + ", " + outputMark + ", " + description + ", " + extra;
        }

        return fileData;
    }

    private static boolean isValidTaskNumber(int taskNum) {
        if (taskNum > Task.getTaskCount() || taskNum <= 0) {
            return false;
        }

        return true;
    }
}
