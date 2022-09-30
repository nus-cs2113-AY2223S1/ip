package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public final Scanner in = new Scanner(System.in);

    public String userInput(){
        return in.nextLine();
    }

    public void greet() {
        String logo = "  ____                 _     \n" +
                " |  _ \\ ___  __ _  ___| |__  \n" +
                " | |_) / _ \\/ _` |/ __| '_ \\ \n" +
                " |  __/  __/ (_| | (__| | | |\n" +
                " |_|   \\___|\\__,_|\\___|_| |_|\n" +
                "                             ";

        String intro = "__________________________________________________ \n"
                + "  Hello! I'm Peach  \n"
                + "  What can I do for you? \n"
                + "__________________________________________________ \n";

        printOutputs(logo + "\n" + intro);
    }



    public void printDashLine() {
        printOutputs("__________________________________________________ \n");
    }

    public void printBye() {
        printOutputs("Bye. Hope to see you again soon! \n");
    }

    public void printOutputs(String printStatement){
        System.out.println(printStatement);
    }
}
