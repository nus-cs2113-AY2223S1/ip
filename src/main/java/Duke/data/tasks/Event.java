package Duke.data.tasks;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalDate;

/**
 * Subclass of superclass Task
 */
public class Event extends Task{
    /**
     * Constructor for the Event class
     * Initialise taskType, description and dueDate variables
     * If given input is in yyyy-mm-dd format, will convert to date-month-year and save in dueDate
     * and save the date in assignedDate
     * @param userInput is the full string of input given by user which consists of
     *                  description and due date
     */
    public Event(String userInput) {
        super();
        super.taskType = "E";
        String[] splitInputs = userInput.split("/at ", 2);
        super.description = splitInputs[0];
        try {
            super.assignedDate = LocalDate.parse(splitInputs[1]);
            String dateString =
                    super.assignedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
            super.dueDate = "at: " + dateString;
        } catch (java.time.format.DateTimeParseException e) {
            super.dueDate = "at: " + splitInputs[1];
        }
    }
}
