package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import duke.DukeException;
import duke.Parser;

public class EventTask extends Task {
    private String eventDateTime;
    private LocalDate eventDate;
    private Optional<LocalTime> eventTime;

    public EventTask(String name, String eventDateTime) throws DukeException {
        this(name, eventDateTime, false);
    }

    public EventTask(String name, String eventDateTime, boolean status) throws DukeException {
        super(name, status);
        if ("".equals(name)) {
            throw new DukeException("Event name cannot be empty");
        }
        if (eventDateTime == null) {
            throw new DukeException("Please provide a date and time (/at)");
        }
        this.eventDateTime = eventDateTime;
        this.eventDate = Parser.parseDateString(eventDateTime);
        if (eventDate == null) {
            throw new DukeException("Sorry, I don't understand this date");
        }
        this.eventTime = Optional.ofNullable(Parser.parseTimeString(eventDateTime));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s%s)", super.toString(),
                eventDate.format(DateTimeFormatter.ofPattern("E, dd MMM yyyy")),
                eventTime.isPresent() ? ", " + eventTime.get().toString() : "");
    }

    @Override
    public String serialize() {
        return String.format("event %s /at %s /done %s", getName(), eventDateTime, isDone());
    }

}
