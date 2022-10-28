package ExceptionsPackage;
/*
A custom exception which allows me to give custom ExceptionMessages.
 */
public class DukeException extends Exception{
    private static String errorMessage;
    public DukeException(String message){
        this.errorMessage = message;
    }
    public String getMessage(){
        return errorMessage;
    }

}
