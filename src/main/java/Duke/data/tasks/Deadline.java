package Duke.data.tasks;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalDate;

public class Deadline extends Task{
    public Deadline(String userInput) {
        super();
        super.taskType = "D";
        String[] splitInputs = userInput.split("/by ", 2);
        super.description = splitInputs[0];
        try {
            super.assignedDate = LocalDate.parse(splitInputs[1]);
            String dateString =
                    super.assignedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
            super.dueDate = "by :" + dateString;
        } catch (java.time.format.DateTimeParseException e) {
            super.dueDate = "by :" + splitInputs[1];
        }
    }
}