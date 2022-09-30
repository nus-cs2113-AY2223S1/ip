package exceptions;

public class InvalidEventException extends Exception{
     @Override
     public String getMessage() {
          return "Please enter when this event is happening!";
     }
}
