package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {

    public Ui() {
        greet();
    }

    static Scanner in = new Scanner(System.in);

    public void line() {
        System.out.println("------------------------------");
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        line();
        System.out.println("Hi! I'm Slave Kai, Zhou Zhou's 256IQ bot\nHow can I help you?");
        line();
    }

    public String input() {
        return in.nextLine();
    }

    public void output(String... output) {
        for (String line : output) {
            System.out.println(line);
        }
    }

}
