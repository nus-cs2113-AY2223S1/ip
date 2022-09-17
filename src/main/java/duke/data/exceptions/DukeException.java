package duke.data.exceptions;

import duke.Duke;

/**
 * Exception for Duke
 */

public class DukeException extends Exception { 
    public DukeException(String message){
        super(message);
    }
    public DukeException(){
    }
}