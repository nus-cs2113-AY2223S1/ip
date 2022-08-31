import javax.annotation.processing.SupportedSourceVersion;
import java.util.Scanner;

public class Duke {

    public static final String BYE = "bye";

    public static void main(String[] args) {
        printWelcomeMessage();
        Task[] taskLists = new Task[100];


        String userInput = getUserInput();

        while(!userInput.equals("bye")){
            String[] userInputSplit = userInput.split(" ");
            switch(userInputSplit[0]){
            case "list":
                printTaskList(taskLists, Task.numOfTasks);
                break;

            case "mark":
                int markDoneIndex = Integer.parseInt(userInputSplit[1]) - 1;
                taskLists[markDoneIndex].setIsDone(true);
                printSetDoneMessage(taskLists[markDoneIndex]);
                break;

            case "unmark":
                int markNotDoneIndex = Integer.parseInt(userInputSplit[1]) - 1;
                taskLists[markNotDoneIndex].setIsDone(false);
                printSetNotDoneMessage(taskLists[markNotDoneIndex]);
                break;


            default:
                printEchoInput(userInput);
                taskLists[Task.numOfTasks] = new Task(userInput);
                Task.numOfTasks ++;

            }
            userInput = getUserInput();
        }

        printByeMessage();
    }

    public static String getUserInput(){
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public static void printEchoInput(String userInput){
        String echoInput =
                "    ____________________________________________________________\n"
                + "     added: "
                + userInput
                + "\n    ____________________________________________________________";
        System.out.println(echoInput);
    }

    public static void printWelcomeMessage() {
        String welcomeMessage =
                "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________";
        System.out.println(welcomeMessage);
    }

    public static void printByeMessage() {
        String byeMessage =
                "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________";
        System.out.println(byeMessage);
    }

    public static void printTaskList(Task[] taskLists, int count){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        String isDoneNotation;
        for (int i=0; i<count; i++){
            Task currTask = taskLists[i];
            if (currTask.getIsDone() == false){
                isDoneNotation = "[] ";
            }
            else {isDoneNotation = "[X] ";}

            int index = i + 1;
            System.out.println("     " + index + "." + isDoneNotation
                    + taskLists[i].getDescription());
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void printSetDoneMessage(Task task){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       [X]" + task.getDescription());
        System.out.println("    ____________________________________________________________");
    }

    public static void printSetNotDoneMessage(Task task){
        System.out.println("    ____________________________________________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       []" + task.getDescription());
        System.out.println("    ____________________________________________________________");
    }


}
