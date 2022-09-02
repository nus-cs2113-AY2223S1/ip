public class EmptyDescriptionException extends ArrayIndexOutOfBoundsException{
    public void printEmptyDescriptionError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The description cannot be empty.\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }
}
