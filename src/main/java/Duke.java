import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static void separateLine() {
        String str = "";
        for (int i = 0; i < 60; i++) {
            str += '_';
        }
        System.out.println(str);
    }

    public static void greet() {
        separateLine();
        System.out.println("Hello! I'm Rae.");
        System.out.println("How does everything progress now?");
    }

    public static void bye() {
        separateLine();
        System.out.println("Bye! Always there waiting for you!");
        separateLine();
    }


    public static void echo(String line) {
        separateLine();
        System.out.println(line);
        separateLine();
    }


    public static void main(String[] args) {
        greet();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Storage storage = new Storage();
        while (!line.toUpperCase().equals("BYE")) {
            separateLine();
            storage.execute(line);
            separateLine();
            line = in.nextLine();
        }
        bye();
    }
}
