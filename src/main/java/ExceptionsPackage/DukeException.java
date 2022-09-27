package ExceptionsPackage;

public class DukeException extends Exception{
    private static String errorMessage;
    public DukeException(String message){
        this.errorMessage = message;
    }
    public String getMessage(){
        return errorMessage;
    }

}
