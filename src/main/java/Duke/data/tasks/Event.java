package Duke.data.tasks;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalDate;

public class Event extends Task{
    public Event(String userInput) {
        super();
        super.taskType = "E";
        String[] splitInputs = userInput.split("/at ", 2);
        super.description = splitInputs[0];
        try {
            super.assignedDate = LocalDate.parse(splitInputs[1]);
            String dateString =
                    super.assignedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
            super.dueDate = "at :" + dateString;
        } catch (java.time.format.DateTimeParseException e) {
            super.dueDate = "at :" + splitInputs[1];
        }
    }
}
