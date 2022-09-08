public class IllegalMarkingException extends DukeException {
    public IllegalMarkingException (String message) {
        super(message);
    }
    @Override
    public String getExceptionMessage() {
        return "This item has already been marked. Try doing another task!";
    }

}

