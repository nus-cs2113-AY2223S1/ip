package duke;

import java.util.Arrays;
import java.util.Scanner;

public class Ui {

    public Ui() {
        greet();
    }

    static Scanner in = new Scanner(System.in);

    public static void line() {
        System.out.println("------------------------------");
    }

    public static void greet() {
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

    public static String input() {
        return in.nextLine();
    }

    public static void outputWithLines(String... output) {
        Ui.line();
        for (String line : output) {
            System.out.println(line);
        }
        Ui.line();
    }

    public static void outputWithoutLines(String output) {
        System.out.println(output);
    }


}
