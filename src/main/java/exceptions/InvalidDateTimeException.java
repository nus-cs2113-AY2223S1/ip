package exceptions;

/**
 * Throws when the user inputs a command that involves a task with an undefined or invalid date/time.
 */
public class InvalidDateTimeException extends Exception{
    public InvalidDateTimeException(){
        super("____________________________________________________________\n"
                + "\n"
                + "OOPS!!! I'm sorry, but the date and/or time cannot be empty :-(" + "\n"
                + "\n"
                + "____________________________________________________________\n");
    }

    public InvalidDateTimeException(String s){
        super(s);
    }
}
