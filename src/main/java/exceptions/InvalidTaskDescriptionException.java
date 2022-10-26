package exceptions;

/**
 * Throws when the user inputs a command that involves a task with an undefined or invalid task description.
 */
public class InvalidTaskDescriptionException extends Exception {
    public InvalidTaskDescriptionException() {
        super("____________________________________________________________\n"
                + "\n"
                + "OOPS!!! I'm sorry, but the description of your task cannot be empty :-(" + "\n"
                + "\n"
                + "____________________________________________________________\n");
    }

    public InvalidTaskDescriptionException(String s){
        super(s);
    }
}
