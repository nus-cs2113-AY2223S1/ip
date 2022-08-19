import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input, output;
        output =
                "    ____________________________________________________________\n"
                + "    Hello! I'm DuckyMoMo\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(output);


        input = sc.nextLine();
        while (!input.equals("bye")) {
            output = "    ____________________________________________________________\n"
                    + "    " + input + "\n"
                    + "    ____________________________________________________________\n";
            System.out.println(output);
            input = sc.nextLine();
        }

        output = "    ____________________________________________________________\n"
                + "    Byeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee\n"
                + "    ____________________________________________________________\n";
        System.out.println(output);
    }
}
