package DukeException;

public class todoException extends Exception {

    private String message;
    public todoException(String message) {
        this.message = message;
    }
    public void talk (){
        System.out.println(message);
    }

}
