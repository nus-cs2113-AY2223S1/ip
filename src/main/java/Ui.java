import java.util.Scanner;

public class Ui {

    public static void showFileWriteError() {
        System.out.println("Unable to write file :(");
    }

    public void welcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    public void goodbyeMessage(){
        System.out.println("Bye! See you :)");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showDivider() {
        System.out.println("_____________________________________________");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
