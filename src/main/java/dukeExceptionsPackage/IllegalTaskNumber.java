package dukeExceptionsPackage;

import dukeExceptionsPackage.DukeException;

public class IllegalTaskNumber extends DukeException {
    public IllegalTaskNumber (String message) {
        super(message);
    }
    @Override
    public String getExceptionMessage() {
        return "This task does not exist. Try again!";
    }
}
