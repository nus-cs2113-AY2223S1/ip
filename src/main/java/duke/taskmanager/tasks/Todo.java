package duke.taskmanager.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Todo extends Task {
    protected LocalDate date;
    protected String time;
    public Todo(String command, Character lastChar) {
        int DescStartIdx = command.indexOf(' ') + 1;
        String description;
        if (lastChar.equals(' ')) {
            description = command.substring(DescStartIdx);
        } else {
            int DescEndIdx = command.indexOf('/');
            description = command.substring(DescStartIdx, DescEndIdx);
        }
        setDescription(description);
        setDone(false);
    }
    public void findDateAndTime(String dateAndTime) {
        try {
            Date dateTime = new SimpleDateFormat("yyyy-MM-dd HHmm").parse(dateAndTime);
            date = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dateTime));
            time = new SimpleDateFormat("h:mm aa").format(dateTime);
        } catch (ParseException e) {
            System.out.println("Sorry. Date and Time cannot be parsed");
        }
    }

    public String formatDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
