import java.util.Scanner;
public class Duke {

    public static String[] tasks = new String[100];
    public static int numTasks = 0;
    public static void listTasks(){
        for(int i = 0; i < numTasks; i++){
            System.out.println((i+1) + ". " + tasks[i]);
        }
    }

    public static void addTask(String newTask){
        tasks[numTasks] = newTask;
        System.out.println("added: " + newTask);
        numTasks += 1;
    }

    public static void executeUserCommand(String command){
        switch (command){
        case("list"):
            listTasks();
            break;
        default:
            addTask(command);
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
            executeUserCommand(userInput);
            userInput = sc.nextLine();
        }
    }
}
