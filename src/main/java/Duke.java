import java.util.Scanner;

public class Duke {
    // Duke Global values
    static Scanner in = new Scanner(System.in);
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String[] toDoList = new String[100];


    // Duke Function
    public static void echo(String line){ //Echo user input
        System.out.println(line);
    }

    public static String ask(){
        System.out.print("User input: ");
        String a;
        return a = in.nextLine();
    }
    public static void list(){
        for(int i = 0; i <= toDoList.length; i++){
            if(toDoList[i] != null){
                System.out.print(i+1);
                System.out.println(". " + toDoList[i]);
            } else {
                break;
            }
        }
    }
    public static void add(String line){
        for(int i = 0; i <= toDoList.length; i++){
            if(toDoList[i] == null){
                toDoList[i] = line;
                break;
            }
        }
        System.out.print("Add: ");
        echo(line);
    }

    public static void main(String[] args) {
        String line;
        System.out.println(logo);

        //Increment Level 0
        System.out.println("===================================================");
        System.out.println("Hello! I'm Duke made by Than Duc Huy");
        System.out.println("Instruction on how to use Duke");
        System.out.println("> Type anything to add to to-do list");
        System.out.println("> Type \"list\" to list all the tasks");
        System.out.println("> Type \"bye\" to exit");
        System.out.println("What do you want to do with the to do list?");
        System.out.println("===================================================");

        while(true){
            line = ask();
            if (line.toLowerCase().contains("bye")) { // If there is any bye, exit!
                break;
            } else if (line.equalsIgnoreCase("list")){
                list();
            } else {
                add(line);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("===================================================");
    }

}
