package duke.data.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.common.Constants.TIME_OUTPUT_PATTERN;

public class Deadline extends Task {
    private LocalDateTime deadlineTime;

    public Deadline(String taskName, LocalDateTime deadlineTime) {
        super(taskName);
        this.deadlineTime = deadlineTime;
    }

    public String getDeadlineTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_OUTPUT_PATTERN);
        return deadlineTime.format(formatter);
    }

    public LocalDate getDeadlineDate() {
        LocalDate localDate = deadlineTime.toLocalDate();
        return localDate;
    }

    @Override
    public String getTaskFullDetails() {
        return String.format("[D]%s (by: %s)",
                super.getTaskFullDetails(), this.getDeadlineTime());
    }
}
