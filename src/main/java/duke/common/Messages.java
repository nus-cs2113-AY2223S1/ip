package duke.common;

/**
 * <code>Messages</code> stores all the static messages to be displayed to the users in user interface.
 */
public enum Messages {
    MESSAGE_GREETING("Hello! I'm Duke"),
    MESSAGE_INQUIRY("What can I do for you?"),
    MESSAGE_TASK_LISTING_HEADER("Here are the tasks in your list:"),
    MESSAGE_ADD_TASK_ACKNOWLEDGEMENT("Got it. I've added this task: "),
    MESSAGE_DELETE_TASK_ACKNOWLEDGEMENT("Noted. I've removed this task:"),
    MESSAGE_MARK_TASK_ACKNOWLEDGEMENT("Nice! I've marked this task as done:"),
    MESSAGE_UNMARK_TASK_ACKNOWLEDGEMENT("OK, I've marked this task as not done yet:"),
    MESSAGE_FAREWELL("Bye. Hope to see you again soon!");

    final String message;

    Messages(String message) {
        this.message = message;
    }

    /**
     * Return the message stored in the corresponding Messages enum.
     *
     * @return A string storing the message
     */
    @Override
    public String toString() {
        return message;
    }
}
