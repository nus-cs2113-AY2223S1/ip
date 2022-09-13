package duke;

public class InvalidCommandException extends Exception {
     String getExceptionDescription() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
