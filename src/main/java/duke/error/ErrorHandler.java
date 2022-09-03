package duke.error;

import duke.error.exceptions.CustomException;
import duke.ui.UserInterface;

public class ErrorHandler {
    public static void printErrorMessage(CustomException e){
        UserInterface.print(e.getExceptionMessage());
    }
}
