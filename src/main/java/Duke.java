import java.util.Scanner;
public class Duke {

    public static Task[] tasks = new Task[100];
    public static int numTasks = 0;

    public static void listTasks(){
        System.out.println("Beep beep, listing out the tasks....Loading.....");
        for(int i = 0; i < numTasks; i++){
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
    }

    public static void addTask(String newTask){
        tasks[numTasks] = new Task(newTask);
        System.out.println("added: " + newTask);
        numTasks += 1;
        System.out.println("Beep boop, now you have " + numTasks + " tasks");

    }

    public static void addTodo(String newToDo){
        tasks[numTasks] = new Todo(newToDo);
        System.out.println("HELLO BEEP, added a new ToDo: ");
        System.out.println("\t" + tasks[numTasks].toString());
        numTasks += 1;
        System.out.println("Beep boop, now you have " + numTasks + " tasks");
    }

    public  static void addDeadline(String param){
        int deadlineIndex = param.indexOf("/by");
        String description = param.substring(0, deadlineIndex);
        String deadlineBy = param.substring(deadlineIndex + 4);
        tasks[numTasks] = new Deadline(description, deadlineBy);
        System.out.println("OH NO BEEP BEEP, a new Deadline: " + description);
        System.out.println("\t" + tasks[numTasks].toString());
        numTasks += 1;
        System.out.println("Beep boop, now you have " + numTasks + " tasks");
    }

    public  static void addEvent(String param){
        int deadlineIndex = param.indexOf("/at");
        String description = param.substring(0, deadlineIndex);
        String deadlineBy = param.substring(deadlineIndex + 4);
        tasks[numTasks] = new Event(description, deadlineBy);
        System.out.println("OH NO BEEP BEEP, a new Event: ");
        System.out.println("\t" + tasks[numTasks].toString());
        numTasks += 1;
        System.out.println("Beep boop, now you have " + numTasks + " tasks");
    }

    public static void setTask(int whichTask, boolean done){
        final String MESSAGE_DONE = "Nice! I've marked this task as done:";
        final String MESSAGE_NOT_DONE = "OK, I've marked this task as not done yet:";
        final String ERROR_OUT_OF_BOUND = "Sorry, the task does not seem to exist :<";
        tasks[whichTask].setStatus(done);

        if(whichTask > numTasks){
            System.out.println(ERROR_OUT_OF_BOUND);
        }

        if(done){
            System.out.println(MESSAGE_DONE);
            System.out.println("\t" + tasks[whichTask].toString());
        } else {
            System.out.println(MESSAGE_NOT_DONE);
            System.out.println("\t" + tasks[whichTask].toString());
        }
    }

    public static void processUserInput(String userInput){
        String[] splitInput = userInput.split(" ", 2);
        String command = splitInput[0];
        String parameters = "false input";
        if (splitInput.length > 1){
            parameters = splitInput[1];
        }

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
        case("todo"):
            addTodo(parameters);
            break;
        case("deadline"):
            addDeadline(parameters);
            break;
        case("event"):
            addEvent(parameters);
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
            System.out.println("BEEP BEEP >>>> TERMINATING >>>> PROCESS >>>>>>");
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
