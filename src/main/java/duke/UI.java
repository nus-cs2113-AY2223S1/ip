package duke;

public class UI {
    public void printDivider() {
        System.out.println("\t----------------------------------------------------");
    }

    public void printGreeting() {
        printDivider();
        System.out.println("\tHello! I'm Albot, your new best friend! ᕙ(`▿´)ᕗ");
        System.out.println("\tWhat can I do for you today, friend? ʕ•́ᴥ•̀ʔっ♡");
        System.out.println();
        printDivider();
    }

    public void printExitMessage() {
        printDivider();
        System.out.println("\tAww, you're leaving already? I'm gonna miss you (╥﹏╥)");
        System.out.println("\tBut anyways bye! Hope to see you again soon, friend! (ɔ◔‿◔)ɔ ♥");
        System.out.println();
        printDivider();
    }

    public void printEmptyDescriptionError() {
        printDivider();
        System.out.println("\t☹ OOPS!!! The description of a task cannot be empty...");
        System.out.println();
        printDivider();
    }

    public void printUnrecognizedCommandError() {
        printDivider();
        System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means...");
        System.out.println();
        printDivider();
    }

    public void printIndexError() {
        printDivider();
        System.out.println("\t☹ OOPS!!! Please provide a valid task index...");
        System.out.println();
        printDivider();
    }

    public void printLoadingError() {
        printDivider();
        System.out.println("\t☹ OOPS!!! Unfortunately file is not found...");
        System.out.println();
        printDivider();
    }
}
