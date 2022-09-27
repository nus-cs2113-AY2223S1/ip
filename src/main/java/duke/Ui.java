package duke;

import java.util.Scanner;

public class Ui {
    private String lineBreak = "  ____________________________________________________________";

    public void showWelcome() {
        this.printDivider();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        this.printDivider();
    }

    public void showFarewell() {
        System.out.println("\tBye! Hope to see you again soon!!");
    }

    public void printDivider() {
        System.out.println(lineBreak);
    }

    public String readInput() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

}
