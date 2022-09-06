public class DukeException extends Exception{
    private String errorType;
    public DukeException(String str){
        super();
        errorType = str;
    }

    void handleError(){
        switch(errorType){
        case "TaskTypeError":
            printTaskTypeErrorMessage();
            break;
        case "TodoDescriptionError":
            printTodoDescriptionErrorMessage();
            break;
        case "EventDescriptionError":
            printEventDescriptionErrorMessage();
            break;
        case "DeadlineDescriptionError":
            printDeadlineDescriptionErrorMessage();
            break;
        }
    }

    void printTaskTypeErrorMessage(){
        Util.printSplitLine();
        System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        Util.printSplitLine();
    }

    void printTodoDescriptionErrorMessage(){
        Util.printSplitLine();
        System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
        Util.printSplitLine();
    }

    void printEventDescriptionErrorMessage(){
        Util.printSplitLine();
        System.out.println("     ☹ OOPS!!! The description of a event is wrong.");
        Util.printSplitLine();
    }

    void printDeadlineDescriptionErrorMessage(){
        Util.printSplitLine();
        System.out.println("     ☹ OOPS!!! The description of a deadline is wrong.");
        Util.printSplitLine();
    }
}