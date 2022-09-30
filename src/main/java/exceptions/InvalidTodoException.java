package exceptions;

public class InvalidTodoException extends Exception {
    
    /**
     * Returns error message
     * 
     * @return string representing the error message
     */
    @Override
    public String getMessage() {
        return "The description of a todo cannot be empty";
    }
}
