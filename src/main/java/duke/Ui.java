package duke;

import java.util.Scanner;

public class Ui {
    public static String getCommandFromUser(Scanner in) {
        return in.nextLine();
    }

    public static void printResponseToUser(String response) {
        if (response != null) {
            System.out.println(response);
        }
    }
}
