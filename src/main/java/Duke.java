import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<String>();
        String logo = "______________________________________________\n";
        System.out.println(logo+ " Hello! I'm Duke\n" +
                " What can I do for you?\n" + logo);
        String lineInput;
        Scanner in = new Scanner(System.in);
        lineInput = in.nextLine();
        while(!lineInput.equals("bye")){
            if(lineInput.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(Integer.toString(i + 1) + ". " + tasks.get(i));
                }
                System.out.println();
            }
            else{
                tasks.add(lineInput);
                System.out.println("added: " + lineInput + System.lineSeparator());
            }
            lineInput = in.nextLine();
        }
        System.out.println(logo + "Bye. Hope to see see you again soon!" + System.lineSeparator() + logo);
    }
}
