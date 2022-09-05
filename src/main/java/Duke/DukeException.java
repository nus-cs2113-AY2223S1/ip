package Duke;

public class DukeException {
    void todoException() {
        System.out.println("OOPS! The description of a todo cannot be empty");
    }
    void markException() {
        System.out.println("Please provide a valid task number to mark");
    }
    void unmarkException() {
        System.out.println("Please provide a valid task number to unmark");
    }
    void deadlineException() {
        System.out.println("Please provide a valid description and deadline.");
    }
    void eventException() {
        System.out.println("Please provide a valid description and time");
    }
    void notRecognizedException() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    void taskOutOfBounds() {
        System.out.println("Sorry! The given task number is out of bounds");
    }
}
