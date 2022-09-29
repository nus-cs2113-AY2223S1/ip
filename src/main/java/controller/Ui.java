package controller;

import tasks.Task;

public class Ui {

    public void greetUser() {
        String line = "------------------------\n";
        System.out.println(line + "Hello! I'm Juke!\nWhat can I do for you?\n" + line);
    }

    public void printExitGreeting() {
        System.out.println("\nSee you again!");
    }

    public void printStatus(Task latest, int total, boolean isAdd){
        String verb = "added";
        if (!isAdd) {
            verb = "removed";
        }

        String line = "\t------------------------\n";
        System.out.println(line + "\tGot it. I've " + verb + " this task:\n\t" + latest);
        System.out.println("\n\tNow you have " + total + " task(s) in the list.\n" + line);
    }
    
}
