package duke.exceptions;

public class NotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "OOPS!!! The search term could not be found!";
    }
}
