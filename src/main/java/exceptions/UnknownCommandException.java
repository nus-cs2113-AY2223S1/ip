package exceptions;

/**
 * Throws when the user inputs a command that is not recognised by Duke.
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("____________________________________________________________\n"
                + "\n"
                + "OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n"
                + "\n"
                + "____________________________________________________________\n");
    }

    public UnknownCommandException(String s) {
        super(s);
    }
}
