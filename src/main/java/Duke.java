import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = "  ____                 _     \n" +
                " |  _ \\ ___  __ _  ___| |__  \n" +
                " | |_) / _ \\/ _` |/ __| '_ \\ \n" +
                " |  __/  __/ (_| | (__| | | |\n" +
                " |_|   \\___|\\__,_|\\___|_| |_|\n" +
                "                             ";

        System.out.println(logo);

        String intro = "__________________________________________________ \n"
                + "  Hello! I'm Peach  \n"
                + "  What can I do for you? \n"
                + "__________________________________________________ \n"
                + "  Bye. Hope to see you again soon! \n"
                + "__________________________________________________ \n";

        System.out.println(intro);
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (input.equals("bye") == false){
            System.out.println(input);
            in = new Scanner(System.in);
            input = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon! \n");

    }
}
