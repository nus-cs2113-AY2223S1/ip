import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    private static void printLine(){
        System.out.println("---------------------------------------------------");
    }

    private static String getStringFromList(String[] inputList, int fromIndex, int toIndex) throws IllegalArgumentException{
        if (fromIndex >= toIndex){
            throw new IllegalArgumentException();
        }
        return String.join(" ", Arrays.copyOfRange(inputList, fromIndex, toIndex));
    }

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
        printLine();
        System.out.println("Duke: Hello! What can I do for you today?");

        
        while (isExit == false){
            //Getting Input
            printLine();
            System.out.print("You: ");
            String rawInput = scanner.nextLine();
            String[] inputList = rawInput.split(" ");
            String cmd = inputList[0];
            String description;

            //Responding
            switch (cmd) {
                case ("list"):
                    printLine();

                    //Printing result
                    System.out.println("Tasks: ");
                    taskList.printList();
                    break;

                case ("bye"):
                    printLine();
                    isExit = true;

                    //Printing result
                    System.out.println("Duke: Goodbye!");
                    printLine();
                    break;

                case ("mark"):
                    printLine();
                    
                    //Handle empty task
                    try{
                        description = getStringFromList(inputList, 1, inputList.length);
                        taskList.searchTask(description).markAsDone();
                    } catch (NullPointerException e){ //invalid 
                        System.out.println("Please enter a valid task! ");
                        break;
                    } catch (IllegalArgumentException e){ //empty
                        System.out.println("Task cannot be empty!");
                        break;
                    }

                    //Printing result
                    System.out.println("Marked as done:");
                    System.out.print("[X] ");
                    System.out.println(description);
                    break;

                case ("unmark"):
                    printLine();
                    
                    //Handle empty/invalid task
                    try{
                        description = getStringFromList(inputList, 1, inputList.length);
                        taskList.searchTask(description).markAsNotDone();
                    } catch (NullPointerException e){
                        System.out.println("Please enter a valid task! ");
                        break;
                    } catch (IllegalArgumentException e){ //empty
                        System.out.println("Task cannot be empty!");
                        break;
                    }
                    
                    //Printing result
                    System.out.println("Marked as not completed:");
                    System.out.print("[ ] ");
                    System.out.println(description);
                    break;

                case ("todo"): //Add to list
                    printLine();

                    try{
                        description = getStringFromList(inputList, 1, inputList.length);
                    } catch (IllegalArgumentException e){
                        System.out.println("Task cannot be empty!");
                        break;
                    }

                    //Printing result
                    System.out.println("Added:");
                    System.out.println(" [T][ ] " + description);
                    taskList.addToDo(description);
                    System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
                    break;

                case ("deadline"):
                    printLine();
                    int byPosition = 0;
                    String dueDate;
                    for (int i=0; i<inputList.length; i++){
                        if (inputList[i].equals("by")) {
                            byPosition = i;
                            break;
                        }
                    }
                    
                    // Handle invalid input
                    try {
                        description = getStringFromList(inputList, 1, byPosition);
                        dueDate = getStringFromList(inputList, byPosition+1, inputList.length);
                    } catch (IllegalArgumentException e){
                        System.out.println("Invalid input: task/date not given!");
                        break;
                    }

                    //Printing result
                    System.out.println("Added:");
                    System.out.println(" [D][ ] " + description + " (by: " + dueDate + ")");
                    taskList.addDeadline(description, dueDate);
                    System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
                    break;

                case ("event"):
                    printLine();
                    int atPosition = 0;
                    String dateTime;
                    for (int i=0; i<inputList.length; i++){
                        if (inputList[i].equals("at")){
                            atPosition = i;
                            break;
                        }
                    }

                    //Handle invalid input
                    try{
                        description = getStringFromList(inputList, 1, atPosition);
                        dateTime = getStringFromList(inputList, atPosition+1, inputList.length);
                    } catch (IllegalArgumentException e){
                        System.out.println("Invalid input: task/date not given!");
                        break;
                    }

                    System.out.println("Added:");
                    System.out.println(" [E][ ] " + description + " (at: " + dateTime + ")");
                    taskList.addEvent(description, dateTime);
                    System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
                    break;

                default: //unknown command
                printLine();
                System.out.println("Sorry, I don't understand what that means :( ");
                break;
            }
        }
    }
}