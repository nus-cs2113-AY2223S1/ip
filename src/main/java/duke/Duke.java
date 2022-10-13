package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException.*;
/**
 * Duke is a task list manager.
 */
public class Duke {
    public static ArrayList<TaskList> ListOfTasks = new ArrayList<>();

    /**
     * Launches the bot and says hello. Reads in the previous task list in cache and take user inputs to.
     * @param args User inputs.
     */
    public static void main(String[] args) throws todoException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ui.greet();
        Storage cache = new Storage(System.getProperty("user.dir") + "\\data\\duke.txt");
        ListOfTasks = cache.printPath();
        Parser();
        cache.update(ListOfTasks);

    }
    /**
     * Executes commands
     * todo [task name]: to add a ToDo Task.
     * deadline [task name] to add a Deadline task.
     * event [task name] to add a Event task.
     * bye: to exit.
     * mark [index]: to mark done for the task with given index.
     * unmark [index]: to unmark done for the task with given index.
     * delete [index]: to delete the task with given index.
     * find [String]: to find the corresponding task.
     * list: to list all ongoing tasks.
     */
    public static void Parser() throws todoException {


        Scanner sc = new Scanner(System.in);
        /** Boolean to indicate if "bye" has been input by user */
        boolean isDone = false;

        String[] inputs;
        int TaskIndex;
        TaskList currTask;
        ArrayList<TaskList> searchResults;


        while (!isDone) {
            String inputString = sc.nextLine();
            inputs = inputString.split(" ", 2); // split
            if (inputs[0].equals("mark")) {
                inputString = "mark";
            } else if (inputs[0].equals("unmark")) {
                inputString = "unmark";
            } else if (inputs[0].equals("delete")){
                inputString = "delete";
            } else if (inputs[0].equals("find")){
                inputString = "find";
            }
            try {
                if (inputString.equals("bye")) {
                    isDone = true;
                    ui.bye();
                    break;
                }else if (inputString.equals("delete")) {
                    TaskIndex = Integer.parseInt(inputs[1])-1 ;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(ListOfTasks.get(TaskIndex));
                    ui.delete(ListOfTasks.get(TaskIndex));
                    ListOfTasks.remove(TaskIndex);
                    for (int i = TaskIndex ; i < ListOfTasks.size();i++){
                        ListOfTasks.get(i).UpdateRemoval();
                    }
                    System.out.println("Now you have " +ListOfTasks.size() +" tasks in the list.");
                }else if (inputString.equals("list")) {
                    System.out.println("Here are the tasks in your list!");
                    ui.list(ListOfTasks);

                } else if (inputString.equals("mark")) {
                    TaskIndex = Integer.parseInt(inputs[1]);// task no + task
                    currTask = ListOfTasks.get(TaskIndex - 1);
                    ui.mark(currTask);


                } else if (inputString.equals("unmark")) {
                    TaskIndex = Integer.parseInt(inputs[1]);
                    currTask = ListOfTasks.get(TaskIndex - 1);
                    ui.unmark(currTask);
                } else if(inputString.equals("find")){

                    ui.find(inputs[1],ListOfTasks);

                } else if (inputs[0].equals("todo")) {
                    if (inputs.length == 1) {
                        throw new todoException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    ListOfTasks.add(ui.addToDo(inputs[1]));
                } else if (inputs[0].equals("deadline")) {
                    ListOfTasks.add(ui.addDeadline(inputs[1]));

                } else if (inputs[0].equals("event")) {
                    ListOfTasks.add(ui.addEvent(inputs[1]));

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
