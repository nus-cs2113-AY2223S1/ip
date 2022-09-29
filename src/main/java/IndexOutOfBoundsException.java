public class IndexOutOfBoundsException extends Exception{
    public String toString(){
        String errorMessage = "    ____________________________________________________________\n"
                + "     ☹ OOPS!!! The please give a valid input.\n"
                + "    ____________________________________________________________";
        return errorMessage;
    }
}
