package duke.ui;

import duke.task.List;
import duke.task.TaskList;

import java.util.Scanner;

public class UI {
    public static final String HORIZONTAL_DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    public static final String ARROW = "----->  ";
    private static final String LOGO = "  ____                                 \n"
            + " |  _ \\                                \n"
            + " | |_) | __ _ _ __   __ _ _ __   __ _  \n"
            + " |  _ < / _` | '_ \\ / _` | '_ \\ / _` | \n"
            + " | |_) | (_| | | | | (_| | | | | (_| | \n"
            + " |____/ \\__,_|_| |_|\\__,_|_| |_|\\__,_| ";

    private final Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    private void reply(String message) {
        System.out.print(HORIZONTAL_DIVIDER + message + "\n" + HORIZONTAL_DIVIDER);
    }

    public void greet() {
        reply("Hello! I'm Banana\n"
                + "How can I help you?\n"
                + LOGO);
    }

    public void bye() {
        reply("Good bye. Hope to see you again soon!");
    }

    public void confirmAdd(TaskList taskList, List list) {
        reply("Got it, I added this taskList to your list:\n"
                + ARROW + taskList
                + "\nNow you have " + list.getSize() + " taskList" + (list.getSize() == 1 ? "" : "s") + " in the list");
    }

    public void confirmMark(TaskList taskList) {
        reply("Congratulations! You have done this taskList:\n"
                + ARROW + taskList);
    }

    public void confirmUnmark(TaskList taskList) {
        reply("OK, I've marked this taskList as not done yet:\n"
                + ARROW + taskList);
    }

    public void confirmDelete(TaskList taskList, List list) {
        reply("Noted. I've removed this taskList:\n"
                + ARROW + taskList
                + "\nNow you have " + list.getSize() + " taskList" + (list.getSize() == 1 ? "" : "s") + " in the list");
    }

    public void printList(List list) {
        reply(list.toString());
    }

    public String getUserInput() {
        System.out.print("Enter command: ");
        return scanner.nextLine();
    }

    public void printError(String errorMessage) {
        reply(errorMessage);
    }
}
