package Duke.data.tasks;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalDate;

/**
 * Subclass for superclass Task
 */
public class Deadline extends Task{
    /**
     * Constructor for the Deadline class
     * Initialise taskType, description and dueDate variables
     * If given input is in yyyy-mm-dd format, will convert to date-month-year and save in dueDate
     * and save the date in assignedDate
     * @param userInput is the full string of input given by user which consist of
     *                  description and the due date
     */
    public Deadline(String userInput) {
        super();
        super.taskType = "D";
        String[] splitInputs = userInput.split("/by ", 2);
        super.description = splitInputs[0];
        try {
            super.assignedDate = LocalDate.parse(splitInputs[1]);
            String dateString =
                    super.assignedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
            super.dueDate = "by: " + dateString;
        } catch (java.time.format.DateTimeParseException e) {
            super.dueDate = "by: " + splitInputs[1];
        }
    }
}