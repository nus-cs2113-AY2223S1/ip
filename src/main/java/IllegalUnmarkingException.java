public class IllegalUnmarkingException extends DukeException {
    public IllegalUnmarkingException (String message) {
        super(message);
    }
   @Override
   public String getExceptionMessage() {
        return "This item is still unmarked. Do it soon!";
   }

}
