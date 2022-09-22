import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static final int MAX = 100;

    public static void main(String[] args) {
        boolean isExit = false;
        welcomeMessage();
        TaskList tasks = new TaskList(MAX);
        while(!isExit){
            try{
                String commandInput = readCommand();
                Command command = new Command(Parser.parse(commandInput));
                isExit = command.executeCommand(tasks, commandInput);
            } catch (Exception e) {
                // to catch errors later
            }
        }
        goodbyeMessage();
//        while(true) {
//            Scanner ScannerObject4 = new Scanner(System.in);
//            String input = ScannerObject4.nextLine();
//
//            if(Objects.equals(input, "bye")){
//                System.out.println("Bye! See you :)");
//                break;
//            } else if (Objects.equals(input, "list")) {
//                //show list
//                System.out.println("Here are the tasks in your list:");
//                for(int task=0; task<ListCounter; task++){
//                    System.out.println((task+1) + ".[" + TaskList[task][0]+"][" + TaskList[task][1]+"] " + TaskList[task][2]);
//                }
//                continue;
//            }
//            try {
//                String command = input.substring(0, input.indexOf(' '));
//                String content = input.substring(input.indexOf(' ') + 1);
//            } catch (Exception e){
//                System.out.println("Oops I don't know what that means");
//                continue;
//            }
//            String command = input.substring(0, input.indexOf(' '));
//            String content = input.substring(input.indexOf(' ') + 1);
//
//            boolean isToDo = Objects.equals(command, "todo");
//            boolean isDeadline = Objects.equals(command, "deadline");
//            boolean isEvent = Objects.equals(command, "event");
//            boolean isMark = Objects.equals(command, "mark");
//            boolean isUnmark = Objects.equals(command, "unmark");
//
//            if(isToDo){ //add task to list
//                TaskList[ListCounter][0] = "T";
//                TaskList[ListCounter][2] = content;
//                System.out.println("Got it. I've added this task:");
//                System.out.println("[" + TaskList[ListCounter][0]+"][" + TaskList[ListCounter][1]+"] " + TaskList[ListCounter][2]);
//                System.out.println("Now you have "+ (ListCounter+1) +" tasks in your list.");
//            } else if (isDeadline) { // add deadline to list
//                TaskList[ListCounter][0] = "D";
//                String task = content.substring(0, content.indexOf('/'));
//                String timing = content.substring(content.indexOf('/') + 1);
//                TaskList[ListCounter][2] = task + "(by: " + timing + ")";
//                System.out.println("Got it. I've added this task:");
//                System.out.println("[" + TaskList[ListCounter][0]+"][" + TaskList[ListCounter][1]+"] " + TaskList[ListCounter][2]);
//                System.out.println("Now you have "+ (ListCounter+1) +" tasks in your list.");
//            } else if (isEvent) { // add to event to list
//                TaskList[ListCounter][0] = "E";
//                String task = content.substring(0, content.indexOf('/'));
//                String timing = content.substring(content.indexOf('/') + 1);
//                TaskList[ListCounter][2] = task + "(at: " + timing + ")";
//                System.out.println("Got it. I've added this task:");
//                System.out.println("[" + TaskList[ListCounter][0]+"][" + TaskList[ListCounter][1]+"] " + TaskList[ListCounter][2]);
//                System.out.println("Now you have "+ (ListCounter+1) +" tasks in your list.");
//            } else if (isMark) { //mark task
//                int TaskNumber = Integer.parseInt(content);
//                TaskList[TaskNumber-1][1] = "X";
//                System.out.println("Alright. Marked task " + TaskNumber);
//                continue;
//            } else if (isUnmark) { //unmark task
//                int TaskNumber = Integer.parseInt(content);
//                TaskList[TaskNumber-1][1] = " ";
//                System.out.println("Alright. Unmarked task " + TaskNumber);
//                continue;
//            } else {
//                System.out.println("Invalid command. Please try again!");
//                continue;
//            }
//            ListCounter++;
//        }

    }
    public static void welcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    public static void goodbyeMessage(){
        System.out.println("Bye! See you :)");
    }

    private static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }


}
