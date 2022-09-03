package duke.exception;

public class ExtraArgumentException extends Exception{

    @Override
    public String toString() {
        return "Extra arguments present, try again :(";
    }
}
