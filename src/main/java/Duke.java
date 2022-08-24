import java.util.Scanner;

public class Duke {
    public static String formatList(String[] inputs) {
        String formattedString = "____________________________________________________________\n";
        for (int x = 0; x < inputs.length; x++) {
            if (inputs[x] == null) {
                break;
            }
            formattedString += (x+1) + ". " + inputs[x] + "\n";
        }
        formattedString += "____________________________________________________________\n";
        return formattedString;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        String[] inputs = new String[100];
        int index = 0;

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Matthew\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")){
                System.out.println(formatList(inputs));
            } else {
                System.out.println(
                        "____________________________________________________________\nadded: " +
                                line + "\n" +
                                "____________________________________________________________\n");
                inputs[index] = line;
                index++;
            }
        }
        System.out.println(
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
