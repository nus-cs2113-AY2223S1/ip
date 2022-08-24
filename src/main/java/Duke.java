import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Task[] tasks = new Task[100];
        // 'command' is the response from user input and string is the
        String command;
        // 'list' is all the text stored and index where the array will stop
        String[] list = new String[100];
        int index = 0;

        Scanner in = new Scanner(System.in);

        // Pikachu logo to show in front
        String logo =
        "⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿\n" +
        "⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿⣿⣿⣿\n" +
        "⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿\n" +
        "⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿\n" +
        "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿⣿⣿⣿\n" +
        "⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿\n" +
        "⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿\n" +
        "⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿⣿⣿⣿\n" +
        "⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿⣿⣿⣿\n" +
        "⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿\n" +
        "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿\n";
        
        System.out.println(logo);
        System.out.println("Harrlo! This is Wilson. What do you want?");

        // Loop that stops only when user enter "bye"
        do{
            command = in.nextLine();

            // If user select list, show all the task
            if (command.equals("list")) {
                for (int i = 0; i < index; i += 1) {
                    System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getTask());
                }
            } else if (!command.equals("bye")) {
                // If the command is not 'list' and 'bye', then it should be an 'add','mark' or 'unmark' command

                // If the command is to mark or unmark
                if (command.contains("mark") || (command.contains("unmark"))) {
                    // Since the command is mark or unmark, get the number and change the status to either marked or
                    // unmarked before printing it out in the specified format
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    tasks[taskNumber - 1].setStatusIcon(command.split(" ")[0]);
                    System.out.println("  [" + tasks[taskNumber - 1].getStatusIcon() + "] " +
                            tasks[taskNumber - 1].getTask());
                } else {
                    // If the command does not contain mark or unmark, then it should be an 'add' operation
                    // Create a new task and append the index by 1.
                    // The index would indicate when the iteration would stop.
                    tasks[index] = new Task(command);
                    System.out.println("added: " + command);
                    index += 1;
                }

            }
        } while (!command.equals("bye"));
        // Goodbye message
        System.out.println("Bye Bye!");
    }
}
