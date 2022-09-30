package exceptions;

public class InvalidTodoException extends Exception {
    @Override
    public String getMessage() {
        return "The description of a todo cannot be empty";
    }
}
