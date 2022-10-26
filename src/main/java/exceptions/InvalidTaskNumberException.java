package exceptions;

/**
 * Throws when the user inputs a command that involves a task with an undefined or invalid task number.
 */
public class InvalidTaskNumberException extends Exception{
    public InvalidTaskNumberException(){
        super("____________________________________________________________\n"
                + "\n"
                + "OOPS!!! I'm sorry, but your task number is invalid :-(" + "\n"
                + "\n"
                + "____________________________________________________________\n");
    }

    public InvalidTaskNumberException(String s){
        super(s);
    }
}
