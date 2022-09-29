package duke;
import duke.task.Task;
import duke.exceptions.InvalidCommandException;
import duke.task.Todo;
import duke.task.Deadlines;
import duke.task.Events;

import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    static ArrayList<Task> tasklist = new ArrayList<>();

    public static void Echo(String input){
        Task task;
        String[] inputArr = input.split(" ");
        int task_no;
        switch (inputArr[0]){
            case "bye":
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon");
                System.out.println("-------------------------------------------------------------------------------");
                break;
            case "list":
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Here are the tasks in your list:");
                int i = 1;
                for (Task l: tasklist){
                    System.out.print(i + ".");
                    System.out.println(l);
                    i++;
                }
                System.out.println("-------------------------------------------------------------------------------");
                break;
            case "mark":
                task_no = Integer.parseInt(inputArr[1]);
                Task marktask = tasklist.get(task_no - 1);
                marktask.setisDone(true);
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Nice! I've marked this duke.task as done:");
                System.out.println(marktask);
                System.out.println("-------------------------------------------------------------------------------");
                break;
            case "unmark":
                task_no = Integer.parseInt(inputArr[1]);
                Task unmarktask = tasklist.get(task_no - 1);
                unmarktask.setisDone(false);
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("OK, I've marked this duke.task as not done yet:");
                System.out.println(unmarktask);
                System.out.println("-------------------------------------------------------------------------------");
                break;
            case "delete":
                try {
                    removeTask(input, inputArr);
                } catch (IndexOutOfBoundsException e){
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("Invalid duke.task number.");
                    System.out.println("-------------------------------------------------------------------------------");
                }
                break;
            case "todo":
                try {
                    task = createTodo(input, inputArr);
                    tasklist.add(task);
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("Got it. I've added this duke.task:");
                    System.out.println(task);
                    System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                    System.out.println("-------------------------------------------------------------------------------");
                } catch (InvalidCommandException e){

                }
                break;
            case "deadline":
                try {
                    task = createDeadline(input, inputArr);
                    tasklist.add(task);
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("Got it. I've added this duke.task:");
                    System.out.println(task);
                    System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                    System.out.println("-------------------------------------------------------------------------------");
                } catch (InvalidCommandException e){

                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("Missing '/by' keyword after description.");
                    System.out.println("-------------------------------------------------------------------------------");
                }
                break;
            case "event":
                try {
                    task = createEvent(input, inputArr);
                    tasklist.add(task);
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("Got it. I've added this duke.task:");
                    System.out.println(task);
                    System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                    System.out.println("-------------------------------------------------------------------------------");
                } catch (InvalidCommandException e){

                } catch (StringIndexOutOfBoundsException e){
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("Missing '/at' keyword after description.");
                    System.out.println("-------------------------------------------------------------------------------");
                }
                break;
            default:
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("Wrong command, please type again.");
                System.out.println("-------------------------------------------------------------------------------");
                break;
        }
    }



    public static Task createTodo(String input, String[] inputArr) throws InvalidCommandException {
        if (inputArr.length < 2){
            throw new InvalidCommandException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(input.substring(5));
    }
    public static Task createDeadline(String input, String[] inputArr) throws InvalidCommandException {
        if (inputArr.length < 2){
            throw new InvalidCommandException("OOPS!!! The description of a deadline cannot be empty.");
        }
        int cutoff = input.indexOf("/by");
        return new Deadlines(input.substring(9, cutoff - 1), input.substring(cutoff + 4));
    }
    public static Task createEvent(String input, String[] inputArr) throws InvalidCommandException, StringIndexOutOfBoundsException{
        if (inputArr.length < 2){
            throw new InvalidCommandException("OOPS!!! The description of a event cannot be empty.");
        }

        int cutoff = input.indexOf("/at");
        return new Events(input.substring(6, cutoff - 1), input.substring(cutoff + 4));
    }

    public static void removeTask(String input, String[] inputArr){
        int task_index = Integer.parseInt(inputArr[1]);
        Task task = tasklist.get(task_index - 1);
        tasklist.remove(task_index - 1);
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Noted. I've removed this duke.task:");
        System.out.println(task);
        System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
        System.out.println("-------------------------------------------------------------------------------");

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
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can i do for you?");
        System.out.println("-------------------------------------------------------------------------------");

        String input = new String("");


        while(!input.startsWith("bye")){
            input = in.nextLine();
            Echo(input);


        }

    }
}
