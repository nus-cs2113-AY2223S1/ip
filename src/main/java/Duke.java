import java.util.Scanner;
public class Duke {

    public static Task[] tasks = new Task[100];
    public static int numTasks = 0;

    public static void listTasks(){
        for(int i = 0; i < numTasks; i++){
            System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() +"] " + tasks[i].getTask());
        }
    }

    public static void addTask(String newTask){
        tasks[numTasks] = new Task(newTask);
        System.out.println("added: " + newTask);
        numTasks += 1;
    }

    public static void setTask(int whichTask, boolean done){
        final String MESSAGE_DONE = "Nice! I've marked this task as done:";
        final String MESSAGE_NOT_DONE = "OK, I've marked this task as not done yet:";
        tasks[whichTask].setStatus(done);

        if(done){
            System.out.println(MESSAGE_DONE);
            System.out.println("\t[X] " + tasks[whichTask].getTask());
        } else {
            System.out.println(MESSAGE_NOT_DONE);
            System.out.println("\t[ ] " + tasks[whichTask].getTask());
        }
    }

    public static void processUserInput(String userInput){
        String[] splitInput = userInput.split(" ");
        String command = splitInput[0];

        switch (command){
        case("list"):
            listTasks();
            break;
        case("mark"):
            //assume input of length 2
            setTask(Integer.parseInt(splitInput[1]) - 1,true);
            break;
        case("unmark"):
            setTask(Integer.parseInt(splitInput[1]) - 1, false);
            break;
        default:
            addTask(userInput);
            break;
        }
    }
    public static void greetUser(){
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        final String LOGO = "\n" +
        "     _________________________________________\n" +
        "   /  _____________________________________   \\ \n" +
        "   | |                                     |  | \n" +
        "   | |  C:\\> Initiating programme_         |  | \n" +
        "   | |                                     |  | \n" +
        "   | |                                     |  | \n" +
        "   | |                                     |  | \n" +
        "   | |                                     |  | \n" +
        "   | |                                     |  | \n" +
        "   | |_____________________________________|  | \n" +
        "    \\_____________________________________/ \n" +
        "       \\________________________________/ \n" +
        "        _________________________________ \n" +
        "   _-'.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.--- `-_ \n" +
        "_-'.-.-. .---.-.-.-.-.-.-.-.-.-.-.-.-.-.-.--..-.-.`-_ \n";

        System.out.println(LOGO);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static boolean toExit(String userInput) {
        if (userInput.equals("bye")){
            System.out.println("Bye!!!! See you again :D");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        greetUser();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while(!toExit(userInput)){
            processUserInput(userInput);
            userInput = sc.nextLine();
        }
    }
}
