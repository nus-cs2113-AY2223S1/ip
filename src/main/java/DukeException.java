public class DukeException extends Exception{

    public ExceptionType exceptionType;

    public DukeException(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
}
