package exceptions;

public class InvalidEventException extends Exception{
     
     /**
     * Returns error message
     * 
     * @return string representing the error message
     */
     @Override
     public String getMessage() {
          return "Please enter when this event is happening!";
     }
}
