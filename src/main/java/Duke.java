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
                    tasks[i].printTask(i);
                }
            } else if (!command.equals("bye")) {
                // If the command is not 'list' and 'bye', then it should be an 'add','mark' or 'unmark' command

                // If the command is to mark or unmark
                if (command.contains("mark") || (command.contains("unmark"))) {
                    // Since the command is mark or unmark, get the number and change the status to either marked or
                    // unmarked before printing it out in the specified format
                    int taskNumber = Integer.parseInt(command.split(" ")[1]);
                    tasks[taskNumber - 1].setStatusIcon(command.split(" ")[0]);
                    tasks[taskNumber - 1].printTask(taskNumber - 1);
                } else {
                    // If the command does not contain mark or unmark, then it should be an 'add' operation
                    // Create a new task and append the index by 1.
                    // The index would indicate when the iteration would stop.

                    if (command.contains("todo")) {
                        tasks[index] = new Todo(command.replace("todo ", ""));
                    } else if (command.contains("event")) {
                        tasks[index] = new Event(command.replace("event ", ""));
                    } else if (command.contains("deadline")){
                        tasks[index] = new Deadline(command.replace("deadline ", ""));
                    }

                    index += 1;
                    if (index > 1) {
                        System.out.println("You now have " + index + " tasks.");
                    } else {
                        System.out.println("You now have " + index + " task.");
                    }

                }

            }
        } while (!command.equals("bye"));
        // Goodbye message
        System.out.println("Bye Bye!");
    }
}
