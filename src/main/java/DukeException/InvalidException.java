package DukeException;

public class InvalidException extends Exception{
    private String message;
    public InvalidException(String message) {
        this.message = message;
    }
    public void talk (){
        System.out.println(message);
    }
}
