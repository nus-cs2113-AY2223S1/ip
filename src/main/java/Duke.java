import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static void separateLine() {
        String str = "";
        for(int i = 0; i < 60; i++) {
            str += '_';
        }
        System.out.println(str);
    }

    public static void greet() {
        separateLine();
        System.out.println("Hello! I'm Rae.");
        System.out.println("How does everything progree now?");
    }

    public static void bye() {
        separateLine();
        System.out.println("Bye! Always there waiting for you!");
        separateLine();
    }


    public static void echo() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.toUpperCase().equals("BYE")) {
            separateLine();
            System.out.println(line);
            separateLine();
            line = in.nextLine();
        }
        bye();
    }

    public static void main(String[] args) {
        greet();
        echo();
    }
}
