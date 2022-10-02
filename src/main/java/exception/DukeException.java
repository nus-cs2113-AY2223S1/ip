package exception;

import ui.Ui;

/**
 * Handles the possible exceptions in the program
 */
public class DukeException extends Exception{
    private String errorType;

    public DukeException(String str){
        super();
        errorType = str;
    }

    public void handleError(){
        String errorMessage;
        switch(errorType){
        case "TaskTypeError":
            errorMessage = "     OOPS!!! I'm sorry, but I don't know what that means :-(";
            break;
        case "TodoDescriptionError":
            errorMessage = "     OOPS!!! The description of a todo cannot be empty.";
            break;
        case "EventDescriptionError":
            errorMessage = "     OOPS!!! The description of a event is wrong.";
            break;
        case "DeadlineDescriptionError":
            errorMessage = "     OOPS!!! The description of a deadline is wrong.";
            break;
        case "RestorationFileCorrupted":
            errorMessage = "     OOPS!!! File restoration corrupted. Restoration process stopped.";
            break;
        case "IndexParseError":
            errorMessage = "     OOPS!!! Index parse failed.";
            break;
        case "IndexOutOfBound":
            errorMessage = "     OOPS!!! Index out of bound.";
            break;
        case "CreateFileError":
            errorMessage = "     OOPS!!! Create file error.";
            break;
        case "RestoreFileError":
            errorMessage = "     OOPS!!! Restore file error.";
            break;
        default:
            errorMessage = "     OOPS!!! Some unknown errors happen.";
        }
        Ui.printErrorMessage(errorMessage);
    }
}