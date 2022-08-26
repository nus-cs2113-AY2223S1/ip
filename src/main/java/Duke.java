import java.util.Scanner;
public class Duke {

    public static Task[] tasks = new Task[100];
    public static int numTasks = 0;
    public static void listTasks(){
        for(int i = 0; i < numTasks; i++){
            if(tasks[i].getDone()) {
                System.out.println((i + 1) + ".[X] " + tasks[i].getTask());
            } else {
                System.out.println((i + 1) + ".[ ] " + tasks[i].getTask());
            }
        }
    }

    public static void addTask(String newTask){
        tasks[numTasks] = new Task(newTask);
        System.out.println("added: " + newTask);
        numTasks += 1;
    }

    public static void setTask(int whichTask, boolean done){
        tasks[whichTask].setDone(done);

        if(done){
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("\t[X] " + tasks[whichTask].getTask());
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("\t[ ] " + tasks[whichTask].getTask());
        }
    }

    public static void processUserInput(String userInput){
        String[] splitedInput = userInput.split(" ");
        String command = splitedInput[0];

        switch (command){
        case("list"):
            listTasks();
            break;
        case("mark"):
            //assume input of length 2
            setTask(Integer.parseInt(splitedInput[1]) - 1,true);
            break;
        case("unmark"):
            setTask(Integer.parseInt(splitedInput[1]) - 1, false);
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
