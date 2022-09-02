public class UnknownCommandException extends DukeException{
    public void printUnknownCommandError() {
        String error = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________";
        System.out.println(error);
    }
}
