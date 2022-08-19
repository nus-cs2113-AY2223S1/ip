import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static class List {
        private ArrayList<String> tasks = new ArrayList<>();

        public void addTask(String task) {
            tasks.add(task);
            printWithFormat("added: " + task);
        }

        public void printTasks() {
            int counter = 1;
            String output = "";
            for (String task : tasks) {
                output = output.concat(counter++ + ". " + task + "\n");
            }
            printWithFormat(output);
        }
    }

    public static void printWithFormat(String input) {
        String output = "";
        String[] split = input.split("\n");
        String line = "    ____________________________________________________________";
        System.out.println(line);
        for (String string: split) {
            output = output.concat( "     " + string + "\n");
        }
        System.out.print(output);
        System.out.println(line);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input, output;
        List list = new List();
        output = "Hello! I'm DuckyMoMo\n"
                + "What can I do for you?";
        printWithFormat(output);


        input = sc.nextLine();
        while (true) {
            switch (input) {
            case "bye" :
                printWithFormat("Byeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                return;
            case "list":
                list.printTasks();
                break;
            default:
                list.addTask(input);
            }

            input = sc.nextLine();
        }

    }
}
