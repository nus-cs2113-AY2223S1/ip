import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "______________________________________________\n";
        System.out.println(logo+ " Hello! I'm Duke\n" +
                " What can I do for you?\n" + logo);
        String lineInput;
        Scanner in = new Scanner(System.in);
        lineInput = in.nextLine();
        while(!lineInput.equals("bye")){
            System.out.println(lineInput + System.lineSeparator());
            lineInput = in.nextLine();
        }
        System.out.println(logo + "Bye. Hope to see see you again soon!" + System.lineSeparator() + logo);
    }
}
