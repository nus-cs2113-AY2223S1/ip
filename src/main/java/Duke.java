import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greet
        System.out.println("---------------------------------------------------");
        System.out.println("Duke: Hello! What can I do for you today?");

        
        while (isExit == false){
            //Getting Input
            System.out.println("---------------------------------------------------");
            System.out.print("You: ");
            String rawInput = scanner.nextLine();
            String[] inputList = rawInput.split(" ");
            String cmd = inputList[0];
            String description;

            //Responding
            switch (cmd) {
                case ("list"):
                    System.out.println("---------------------------------------------------");
                    System.out.println("Tasks: ");
                    taskList.printList();
                    break;
                case ("bye"):
                    isExit = true;
                    System.out.println("---------------------------------------------------");
                    System.out.println("Duke: Goodbye!");
                    System.out.println("---------------------------------------------------");
                    break;
                case ("mark"):
                    System.out.println("---------------------------------------------------");
                    System.out.println("Marked as done:");

                    description = String.join(" ", Arrays.copyOfRange(inputList, 1, inputList.length));
                    taskList.searchTask(description).markAsDone();

                    System.out.print("[X] ");
                    System.out.println(description);
                    break;
                case ("unmark"):
                    System.out.println("---------------------------------------------------");
                    System.out.println("Marked as not completed:");

                    description = String.join(" ", Arrays.copyOfRange(inputList, 1, inputList.length));
                    taskList.searchTask(description).markAsNotDone();

                    System.out.print("[ ] ");
                    System.out.println(description);
                    break;
                case ("todo"): //Add to list
                    System.out.println("---------------------------------------------------");
                    description = String.join(" ", Arrays.copyOfRange(inputList, 1, inputList.length));
                    System.out.println("Added:");
                    System.out.println(" [T][ ] " + description);
                    taskList.addToDo(description);
                    System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
                    break;
                case ("deadline"):
                    System.out.println("---------------------------------------------------");
                    int byPosition = 0;
                    String dueDate;
                    for (int i=0; i<inputList.length; i++){
                        if (inputList[i].equals("by")) {
                            byPosition = i;
                            break;
                        }
                    }
                    if (byPosition == 0){
                        System.out.println("Please state the deadline!");
                        break;
                    }
                    description = String.join(" ", Arrays.copyOfRange(inputList, 1, byPosition));
                    dueDate = String.join(" ", Arrays.copyOfRange(inputList, byPosition+1, inputList.length));
                    System.out.println("Added:");
                    System.out.println(" [D][ ] " + description + " (by: " + dueDate + ")");
                    taskList.addDeadline(description, dueDate);
                    System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
                    break;
                case ("event"):
                    System.out.println("---------------------------------------------------");
                    int atPosition = 0;
                    String dateTime;
                    for (int i=0; i<inputList.length; i++){
                        if (inputList[i].equals("at")){
                            atPosition = i;
                            break;
                        }
                    }
                    if (atPosition == 0){
                        System.out.println("Please state the date and time!");
                        break;
                    }
                    description = String.join(" ", Arrays.copyOfRange(inputList, 1, atPosition-1));
                    dateTime = String.join(" ", Arrays.copyOfRange(inputList, atPosition+1, inputList.length));
                    System.out.println("Added:");
                    System.out.println(" [E][ ] " + description + " (at: " + dateTime + ")");
                    taskList.addEvent(description, dateTime);
                    System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
                    break;
            }
        }
    }
}