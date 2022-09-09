import Tasks.*;
import Exceptions.*;

import java.util.Scanner;
import java.util.Vector;

public class Duke {
    public static final int EVENT_KEYWORD_LENGTH = 6;
    public static final int DEADLINE_KEYWORD_LENGTH = 9;

    protected static void printGreet(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke_HTT. \n What can I do for you?");
    }

    public static void processInput(){   
        Scanner scan = new Scanner ( System.in ); 
        String inData = scan.nextLine(); // user line of input
        String[] inLine = inData.split(" "); // code process line of input into an array
        Vector<Task> tasks = new Vector<>(); // list of tasks
        boolean isFirstLine = false; 

        while (!inData.equals("bye")){
            if (isFirstLine) { // kick start the loop if first time
                inData = scan.nextLine();
                inLine = inData.split(" ");
            } else {
                isFirstLine = true;
            }

            int inputTaskIndex = Integer.parseInt(inLine[1]);
            int taskIndex = inputTaskIndex - 1;
            switch (inLine[0]){
            case "list":
                printList(tasks);
                break;

            case "mark":
            try {
                if (inputTaskIndex > tasks.size() || inputTaskIndex < 1) {
                    throw new InvalidListIndexException();
                }

                tasks.get(taskIndex).setTaskDone(true);
                
                String doneMark;
                if (tasks.get(taskIndex).getTaskDone()){
                    doneMark = "[X]";
                } else{
                    doneMark = "[ ]";
                }

                System.out.println("Nice! I've marked this task as done: \n" + doneMark +
                        tasks.get(taskIndex).getTaskTitle());
            } catch (InvalidListIndexException | NumberFormatException e) {
                System.out.println("Error - List index given invalid. Please check again.");
            }
            break;
            
            case "unmark":
            try {
                if (inputTaskIndex > tasks.size() || inputTaskIndex < 1) {
                    throw new InvalidListIndexException();
                }

                tasks.get(taskIndex).setTaskDone(false);
                
                String doneMark;
                if (tasks.get(taskIndex).getTaskDone()){
                    doneMark = "[X]";
                } else{
                    doneMark = "[ ]";
                }

                System.out.println("Nice! I've marked this task as not done: \n" + doneMark +
                        tasks.get(taskIndex).getTaskTitle());
            } catch (InvalidListIndexException | NumberFormatException e) {
                System.out.println("Error - List index given invalid. Please check again.");
            }
            break;
            
            case "todo":
            try {
                if (inLine.length <= 1) {
                    throw new InvalidTodoException();
                }

                tasks.add(new ToDo(inData.substring(5)));

                System.out.println("Got it. I've added this task: \n" + "[T][ ] "
                        + inData.substring(5) ); //////////magic number problem
                System.out.println("Now you have " + tasks.size() +  " tasks in the list.");
            } catch (InvalidTodoException e) {
                System.out.println("Error - Please input a description for your To do. ");
            }
            break;

            case "event":
                try {
                    int taskEndIndex = inData.indexOf("/at");
                    if (taskEndIndex == -1) { // no "/at"
                        throw new InvalidEventException();
                    } else if (taskEndIndex <= EVENT_KEYWORD_LENGTH) { // no task title
                        throw new InvalidTaskTitleException();
                    } else if (taskEndIndex + 3 <= inData.length()) { // no task date
                        throw new InvalidTaskDateException();
                    }

                    String eventTitle = inData.substring(EVENT_KEYWORD_LENGTH,taskEndIndex); 
                    String eventDate = inData.substring(taskEndIndex + 3); /////magic number, "/at"
                    tasks.add(new Event(eventTitle, eventDate));

                    System.out.println("Got it. I've added this event: \n" + "[E] [ ]"
                            + eventTitle + " (at: " + eventDate + ")");
                    System.out.println("Now you have " + tasks.size() +  " tasks in the list.");
                } catch (InvalidEventException e) {
                    System.out.println("Error - '/at' must be included.");
                } catch (InvalidTaskTitleException e) {
                    System.out.println("Error - input an event description");
                } catch (InvalidTaskDateException e) {
                    System.out.println("Error - input an event date.");
                }
                break;

            case "deadline":
                try {
                    int taskEndIndex = inData.indexOf("/by");
                    if (taskEndIndex == -1) { // no "/by"
                        throw new InvalidDeadlineException();
                    } else if (taskEndIndex <= DEADLINE_KEYWORD_LENGTH) { // no task title
                        throw new InvalidTaskTitleException();
                    } else if (taskEndIndex + 3 <= inData.length()) { // no task date
                        throw new InvalidTaskDateException();
                    }

                    String deadlineTitle = inData.substring(DEADLINE_KEYWORD_LENGTH, taskEndIndex); 
                    String deadlineDate = inData.substring(taskEndIndex + 3); //magic number, "/by"
                    tasks.add(new Deadline(deadlineTitle, deadlineDate));

                    System.out.println("Got it. I've added this deadline: \n" + "[D] [ ]"
                            + deadlineTitle + " (at: " + deadlineDate + ")");
                    System.out.println("Now you have " + tasks.size() +  " tasks in the list.");


                } catch (InvalidDeadlineException e){
                    System.out.println("Error - '/by' must be included.");
                } catch (InvalidTaskTitleException e) {
                    System.out.println("Error - input a deadline description");
                } catch (InvalidTaskDateException e) {
                    System.out.println("Error - input a deadline date.");
                }
                break;

            default:
            try {
                throw new InvalidGeneralInputException(); // catch all other exceptions
            } catch (InvalidGeneralInputException e) {
                System.out.println("Error - Sorry, I do not understand this input.");
            }
            }
        }
    }
    
    protected static void printGoodbyeMeessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList(Vector <Task> tasks){
        for (int i = 0; i < tasks.size(); i++) {
            String taskType = tasks.get(i).getTaskType();
            String doneMark;
            int outputIndex = i + 1;
            if (tasks.get(i).getTaskDone()){
                doneMark = "[X]";
            } else{
                doneMark = "[ ]";
            }

            switch(taskType) {
            
            case "ToDo":
                System.out.println("[T]" + doneMark + " " + outputIndex + ". " + tasks.get(i).getTaskTitle());
                break;

            case "Deadline":
                String deadline = tasks.get(i).getTaskDeadline();
                //String outputIndex = i + 1;
                System.out.println("[D]" + doneMark + " " + outputIndex + ". " + tasks.get(i).getTaskTitle() +
                        " (by: " + deadline + ")");
                break;

            case "Event":
                String eventDate = tasks.get(i).getTaskDate();
                //String outputIndex = i + 1;
                System.out.println("[E]" + doneMark + " " + outputIndex + ". " + tasks.get(i).getTaskTitle() +
                        " (at: " + eventDate + ")");
                break;

            default:
                break;
            }
        }
    }

    public static void main(String[] args) {
        printGreet();
        processInput();
        printGoodbyeMeessage();  
    }
}
