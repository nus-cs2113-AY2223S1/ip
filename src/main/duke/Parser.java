package main.duke;
import main.duke.exception.DukeException;
import main.duke.task.Deadline;
import main.duke.task.Event;
import main.duke.task.Task;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public Parser() {

    }

    /* Create a new Todo */
    public Task createTodo(String input) throws DukeException {
        int startIndex = Utils.findNextLetter("todo", input);
        if (startIndex >= input.length()) {
            throw new DukeException("You only wrote todo! Please follow the correct format: todo [task]");
        }
        return new Task(input.substring(startIndex));
    }

    /* Create a new Event with the correct date */
    public Task createEvent(String input) throws DukeException {
        int startIndex = Utils.findNextLetter("event", input);
        int dateIndex = input.indexOf("/at");
        if (dateIndex == -1) {
            throw new DukeException("Unable to create an Event! Please follow the format: event [name] /at [date]");
        }
        int endIndex = dateIndex + Utils.findNextLetter("/at", input.substring(dateIndex));
        String date = parseDate(input.substring(endIndex));
        return new Event(input.substring(startIndex, dateIndex), date);
    }

    /* Create a new Deadline with the correct due date */
    public Task createDeadline(String input) throws DukeException {
        int startIndex = Utils.findNextLetter("deadline", input);
        int dateIndex = input.indexOf("/by");
        if (dateIndex == -1) {
            throw new DukeException("Unable to create a Deadline! Please follow the format: deadline [task] /by [date]");
        }
        int endIndex = dateIndex + Utils.findNextLetter("/by", input.substring(dateIndex));
        String date = parseDate(input.substring(endIndex));
        return new Deadline(input.substring(startIndex, dateIndex), date);
    }

    public String parseDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date.replaceAll(" ", ""));
            return parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            return date;
        }
    }

    public Pattern getMarkPattern() {
        return Pattern.compile("^mark[ ]*[0-9]+[ ]*", Pattern.CASE_INSENSITIVE);
    }

    public Pattern getUnmarkPattern() {
        return Pattern.compile("^unmark[ ]*[0-9]+[ ]*", Pattern.CASE_INSENSITIVE);
    }

    public Pattern getDeletePattern() {
        return Pattern.compile("^delete[ ]*[0-9]+[ ]*", Pattern.CASE_INSENSITIVE);
    }
}
