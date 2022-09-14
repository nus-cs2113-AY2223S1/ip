package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException.*;

public class Duke {
    public static ArrayList<Task> ListOfTasks = new ArrayList<>();
    public static void main(String[] args) throws todoException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Communication.greet();
        Cache cache = new Cache(System.getProperty("user.dir") + "\\data\\duke.txt");
        ListOfTasks = cache.printPath();
        proceed();
        cache.update(ListOfTasks);

    }
    public static void proceed() throws todoException {


        Scanner sc = new Scanner(System.in);
        boolean isDone = false;
        String[] inputs;
        int TaskIndex;
        Task currTask;


        while (!isDone) {
            String inputString = sc.nextLine();
            inputs = inputString.split(" ", 2); // split
            if (inputs[0].equals("mark")) {
                inputString = "mark";
            } else if (inputs[0].equals("unmark")) {
                inputString = "unmark";
            } else if (inputs[0].equals("delete")){
                inputString = "delete";
            }
            try {
                if (inputString.equals("bye")) {
                    isDone = true;
                    Communication.bye();
                    break;
                }else if (inputString.equals("delete")) {
                    TaskIndex = Integer.parseInt(inputs[1])-1 ;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(ListOfTasks.get(TaskIndex));
                    Communication.delete(ListOfTasks.get(TaskIndex));
                    ListOfTasks.remove(TaskIndex);
                    for (int i = TaskIndex ; i < ListOfTasks.size();i++){
                        ListOfTasks.get(i).UpdateRemoval();
                    }
                    System.out.println("Now you have " +ListOfTasks.size() +" tasks in the list.");
                }else if (inputString.equals("list")) {
                    System.out.println("Here are the tasks in your list!");
                    Communication.list(ListOfTasks);

                } else if (inputString.equals("read book")) {
                    ListOfTasks.add(new Task("read book"));
                    System.out.println("added: read book");

                } else if (inputString.equals("return book")) {
                    ListOfTasks.add(new Task("return book"));
                    System.out.println("added: return book");

                } else if (inputString.equals("mark")) {
                    TaskIndex = Integer.parseInt(inputs[1]);// task no + task
                    currTask = ListOfTasks.get(TaskIndex - 1);
                    Communication.mark(currTask);


                } else if (inputString.equals("unmark")) {
                    TaskIndex = Integer.parseInt(inputs[1]);
                    currTask = ListOfTasks.get(TaskIndex - 1);
                    Communication.unmark(currTask);
                } else if (inputs[0].equals("todo")) {
                    if (inputs.length == 1) {
                        throw new todoException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    ListOfTasks.add(Communication.addToDo(inputs[1]));
                } else if (inputs[0].equals("deadline")) {
                    ListOfTasks.add(Communication.addDeadline(inputs[1]));

                } else if (inputs[0].equals("event")) {
                    ListOfTasks.add(Communication.addEvent(inputs[1]));

                } else {
                    throw new InvalidException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch(todoException e){
                e.talk();
            }
            catch (InvalidException e){
                e.talk();
            }
        }
    }

}
