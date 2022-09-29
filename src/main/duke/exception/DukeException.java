package main.duke.exception;

/** Class for specifically creating a DukeException */
public class DukeException extends Exception {

    /** Constructor for creating a DukeException given a message */
    public DukeException(String message) {
        super(message);
    }

}
