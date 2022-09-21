import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    static ArrayList<Task> tasklist = new ArrayList<>();

    public static void Echo(String input){
        String[] inputArr = input.split(" ");
        int taskno;
        switch (inputArr[0]){
            case ("bye"):
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon");
                System.out.println("-------------------------------------------------------------------------------");
                break;
            case ("list"):
                System.out.println("-------------------------------------------------------------------------------");
                int i = 1;
                for (Task task: tasklist){
                    System.out.println(i + ".[" + task.getStatusIcon() + "] " + task.description);
                    i++;
                }
                System.out.println("-------------------------------------------------------------------------------");
                break;
            case ("mark"):
                taskno = Integer.parseInt(inputArr[1]);
                Task marktask = tasklist.get(taskno - 1);
                marktask.setisDone(true);
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + marktask.getStatusIcon() + "] " + marktask.description);
                System.out.println("-------------------------------------------------------------------------------");
                break;
            case ("unmark"):
                taskno = Integer.parseInt(inputArr[1]);
                Task unmarktask = tasklist.get(taskno - 1);
                unmarktask.setisDone(false);
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + unmarktask.getStatusIcon() + "] " + unmarktask.description);
                System.out.println("-------------------------------------------------------------------------------");

            default:
                if (input.contains("unmark")){
                    break;
                }else {
                    Task task = new Task(input);
                    tasklist.add(task);
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("added: " + input);
                    System.out.println("-------------------------------------------------------------------------------");
                    break;
                }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner in = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
        System.out.println("-------------------------------------------------------------------------------");

        String input = new String("");

        while(input.contains("bye") == false){
            input = in.nextLine();
            Echo(input);
        }
        //String[] List = new String[100];
//        int ListNo = 0;
//        do{
//            line = in.nextLine();
//            if (line.equals("bye")){
//                break;
//            }else if(line.compareTo("list") != 0) {
//                if(line.contains("mark")){
//                    int i = line.indexOf(" ") + 1;
//                    Task t = new Task(List[Integer.parseInt(line.substring(i))-1]);
//                    t.markAsDone();
//                    System.out.println("Nice! I've marked this task as done:");
//                    System.out.println("[" + t.getStatusIcon() + "] " + List[Integer.parseInt(line.substring(i))-1]);
//                } else if(line.contains("unmark")) {
//                    int i = line.indexOf(" ") + 1;
//                    Task t = new Task(List[Integer.parseInt(line.substring(i))-1]);
//                    t.unmarkAsNotDone();
//                    System.out.println("Ok, I've marked this task as not done yet:");
//                    System.out.println("[" + t.getStatusIcon() + "] " + List[Integer.parseInt(line.substring(i))-1]);
//                } else {
//                    System.out.println("added: " + line);
//                    List[ListNo] = line;
//                    ListNo++;
//                }
//            }else if(line.compareTo("list") == 0){
//                for(int i = 0; i < ListNo; i++) {
//                    Task t = new Task(List[i]);
//                    System.out.println((i+1) + ".[ " + t.getStatusIcon() + "] " + List[i]);
//                }
//            }
//        } while (line.compareTo("bye") != 0);
//        System.out.println("Bye. Hope to see you again soon!");
    }
}
