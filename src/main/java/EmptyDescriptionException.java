public class EmptyDescriptionException extends Exception{
    public String toString(){
        String errorMessage = "    ____________________________________________________________\n"
                            + "     ☹ OOPS!!! The description of a todo cannot be empty.\n"
                            + "    ____________________________________________________________\n";
        return errorMessage;
    }
}
