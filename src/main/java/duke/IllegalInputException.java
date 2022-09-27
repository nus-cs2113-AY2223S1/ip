package duke;

public class IllegalInputException extends Exception{

    private String errorMessage;

    public IllegalInputException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
