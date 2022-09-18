package duke.data.task;

import static duke.parser.DukeDateTimeParser.TIME_OUTPUT_PATTERN;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime eventTime;

    public Event(String taskName, LocalDateTime eventTime) {
        super(taskName);
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_OUTPUT_PATTERN);
        return eventTime.format(formatter);
    }

    @Override
    public String getTaskFullDetails() {
        return String.format("[E]%s (at: %s)",
                super.getTaskFullDetails(), this.getEventTime());
    }
}
