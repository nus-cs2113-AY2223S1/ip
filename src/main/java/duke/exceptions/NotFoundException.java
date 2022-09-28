package duke.exceptions;
/** Used when search term is not cointain in taskList are not recognised */
public class NotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "OOPS!!! The search term could not be found!";
    }
}
