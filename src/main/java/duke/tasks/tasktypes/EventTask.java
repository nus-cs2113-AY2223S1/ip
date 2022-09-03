package duke.tasks.tasktypes;

import duke.tasks.Task;

/**
 * Event item that keeps track of when an item is slated to happen.
 */
public class EventTask extends Task {
    /** Keyword to mark when an event is supposed to happen */
    public static final String COMMAND_AT = "/at";

    /** Contains text following the {@link EventTask#COMMAND_AT} command. */
    private final String EVENT;

    /**
     * Splits the text into two and initializes
     * the {@link EventTask#EVENT} string.
     *
     * @param text input text to be parsed
     */
    public EventTask(String text) {
        super(text.split(COMMAND_AT)[0]);
        EVENT = text.split(COMMAND_AT)[1].trim();
    }

    /**
     * Provides type icon to be used in string formatting.
     *
     * @return Type icon "E" for "Event".
     */
    @Override
    protected String getTypeIcon() {
        return "E";
    }

    /**
     * Add a postfix that denotes when something happens
     * (e.g. <code>(at 3:00 pm)</code> )
     *
     * @return postfix string
     */
    protected String getPostFix() {
        return "(at: " + EVENT + ")";
    }
}
