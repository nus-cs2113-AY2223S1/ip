public class UnknownCommandException extends DukeException{

    public void UnknownCommandMessage(){
        System.out.println(
                Duke.PRINT_LINE
                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + Duke.PRINT_LINE
                );
    }
}
