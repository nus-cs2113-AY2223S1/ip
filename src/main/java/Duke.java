import java.util.Scanner;

public class Duke {
    static TaskManager tasks = new TaskManager();
    static String PRINT_LINE = "____________________________________________________________\n";

    public static void main(String[] args) {

        WelcomeLogo();
        System.out.println(
                PRINT_LINE +
                        " Hello! I'm King Bob\n" +
                        " What can I do for you?\n" +
                        PRINT_LINE
        );
        Scanner sc = new Scanner(System.in);
        boolean isFinished = false;
        int taskNum = 0;


        do {
            String command = sc.nextLine();

            if (command.equals("bye")) {
                isFinished = true;
            } else if (command.startsWith("mark")) {
                taskNum = Integer.parseInt(command.substring(command.length() - 1));
                tasks.markTask(taskNum);
            } else if (command.startsWith("unmark")) {
                taskNum = Integer.parseInt(command.substring(command.length() - 1));
                tasks.unmarkTask(taskNum);
            } else if (command.equals("list")) { // list
                tasks.printList();
            } else if (command.startsWith("todo")) {
                tasks.addToDo(command);
            } else if (command.startsWith("event")) {
                tasks.addEvent(command);
            } else if (command.startsWith("deadline")) {
                tasks.addDeadline(command);
            }

        } while (isFinished != true);
        System.out.println(
                PRINT_LINE
                        + "Bye. Come back soon! :) \n"
                        + PRINT_LINE
        );
    }

    public static void WelcomeLogo(){
        String logo = "⠀⠀⠀⠀⠀⣠⡾⣿⣷⢤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⣠⣤⣴⠋⠀⢻⣿⣧⣈⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠰⡇⢀⣿⣀⠀⠿⠋⠙⠛⠀⡟⢦⠀⠀⠀⠀⠀⠀⠀⢀⣠⠶⠛⠉⣁⣀⣀⣀⣈⠉⠛⠶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⢀⡹⠟⠃⢈⣳⣄⠀⠀⠀⣰⠷⠋⠀⠀⠀⠀⠀⠀⣴⠯⢥⡖⣶⡿⣋⡤⠒⠒⠦⣍⠢⡀⠀⠙⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⣼⠁⠀⠀⠘⢧⣈⣷⠒⠛⣧⠀⠀⠀⠀⠀⠀⠀⡾⠭⢭⣿⣾⠏⣰⣿⣿⠀⠀⠀⠘⣷⠸⠶⢛⣩⣭⣙⡲⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⡴⠙⣄⠀⠀⠀⢀⡇⠘⢻⡞⠁⠀⠀⠀⠀⠀⠀⣼⠃⠀⠀⠙⣿⡀⢿⠟⠋⠀⠀⠀⠀⣿⢀⣾⣯⠀⠀⠀⠹⣮⢳⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠛⠦⠊⢹⠁⠘⡏⠙⠶⢯⣉⠓⢦⣀⡀⠀⠀⣰⡏⠀⠀⠀⠀⠘⣷⡘⢦⣀⠀⠀⣀⡼⠋⢸⣿⡿⠀⠀⠀⠀⣿⠘⡇⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠈⠳⠶⠛⠀⠀⠀⠈⠓⢤⣀⠉⠓⢺⣄⠙⣆⠀⠀⠀⢠⣌⡛⠲⠬⠍⠉⠥⠶⣦⡘⣆⠀⠀⠀⠀⣠⡟⣸⠁⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢲⡞⠘⣆⠘⢦⠀⠀⢸⡻⢍⣦⣄⡀⠀⠀⠀⠘⠳⣬⡑⠒⠒⢚⣩⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡾⠀⠀⠈⢧⣨⣷⣤⣀⠳⢄⡈⠓⠛⢶⣦⣤⡀⠀⠀⠉⠉⠉⢹⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣇⡀⠀⣠⠎⠻⠟⠁⠈⠑⠲⠭⣑⠲⠤⠴⠟⠃⠀⠀⠀⠀⢠⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⠀⢉⡻⠃⠀⠀⠀⣰⠋⠓⠦⢄⣀⠉⡉⠓⢲⣤⡤⠤⠖⢒⣽⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡏⠉⠀⠀⠀⠀⠀⢧⣀⠀⠀⠀⢀⡽⠃⠀⠸⣧⠷⠖⠊⠉⠉⠓⠦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢦⣤⡀⠀⠀⠀⠀⠈⠉⠓⠒⠋⠀⠀⠀⢠⠋⠀⠀⡼⠋⠙⠒⠤⣄⣉⠙⠒⢶⣦⠤⡖⢒⠦\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠈⠙⠓⢤⣄⡀⠀⠀⠀⠀⠀⠀⠀⢰⡏⠑⠒⣾⠁⠀⠀⠀⠀⠀⠈⠉⠳⠿⠴⣄⣰⣯⡔\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠓⣲⠞⠉⠉⠉⠻⡶⠦⣤⣀⣀⡀⠈⢳⣶⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠁⠀⠀⠀⠀⠀⢷⣤⣶⣶⣾⡿⣯⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠭⠤⠖⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
        System.out.println(logo);
    }
}
