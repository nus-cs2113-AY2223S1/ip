package main.java;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Used to read in user input and parse through the instructions
 * given in order to understand what sort of command to process
 * and action needs to take place
 */
public class Parser {
    TaskList tasks;

    /**
     * Constructor for Parser that reads in list of tasks and stores it for future use
     * @param tasks
     */
    public Parser(TaskList tasks){
        this.tasks = tasks;
    }

    /**
     * The main method used within parser, the parse method reads in the next user inputted line
     * determines what the command is based on the initial word of the input and performs
     * corresponding tasks based off of it. When needing to add a new task to the TaskList
     * it calls taskReader to take care of those various inputs, but it deals with marking and
     * umarking tasks, removing tasks, exiting the program, finding a word, and listing the task
     * list.
     */
    public void parse(){
        Scanner in = new Scanner(System.in);
        String inp = in.nextLine();

        while(!inp.equals("bye")){
            if(inp.equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.print(i + ". ");
                    System.out.println(tasks.getTask(i - 1));
                }
            }
            else if (inp.contains("mark")){
                int num = Integer.parseInt(inp.substring(inp.indexOf(" ")+1))-1;
                if(num>tasks.size()){
                    System.out.println("Out of bounds of the list");
                }
                else{
                    if(inp.contains("unmark")){
                        System.out.println("I have marked this task as not done yet");
                        tasks.getTask(num).setDone(false);
                    }
                    else{
                        System.out.println("I have marked this task as complete");
                        tasks.getTask(num).setDone(true);
                    }
                    System.out.println(tasks.getTask(num));
                    Storage.listToFile(tasks);
                }

            }
            else if(inp.contains("delete")){
                int num = Integer.parseInt(inp.substring(inp.indexOf(" ")+1))-1;
                if(num>tasks.size()){
                    System.out.println("Out of bounds of the list");
                }
                else{
                    tasks.delete(num);
                    Storage.listToFile(tasks);
                }
            }
            else if(inp.contains("find")){
                String temp = inp.substring(inp.indexOf("find")+5);
                System.out.println(temp);
                ArrayList<Integer> temps = new ArrayList<>();
                int counter = 0;
                for(int i = 0; i < tasks.size(); i++){
                    if(tasks.getTask(i).getName().contains(temp)){
                        counter++;
                        temps.add(i);
                    }
                }
                if(counter==0){
                    System.out.println("There are no matching entries for that word");
                }
                else{
                    System.out.println("Here are the matching tasks in your list");
                    for(int j = 0; j < counter; j++){
                        System.out.println((j+1)+". "+tasks.getTask(temps.get(j)));
                    }
                }
            }
            else {
                try{
                    taskReader(inp);
                    Storage.listToFile(tasks);
                }
                catch (DukeException e){
                    e.printError();
                }
            }
            inp = in.nextLine();
        }
        System.out.println();
        Storage.listToFile(tasks);
        System.out.println("Bye");
    }

    /**
     * Receives an input from the parse method in the form of the entire line of user input
     * and uses the first word of the input to determine the type of task, and reads through
     * the rest of the input in order to create the tasks requested adn add it to the task list
     * @param inp input string from user
     * @throws DukeException when the input is not formatted correctly
     */
    public void taskReader(String inp) throws DukeException{
        String comm, tas, name, date, time;
        try{
            comm = inp.substring(0, inp.indexOf(" ")).toLowerCase();
        }
        catch (StringIndexOutOfBoundsException e){
            throw new DukeException(inp);
        }
        try{
            tas = inp.substring(inp.indexOf(" ")+1);
        }
        catch (IndexOutOfBoundsException e){
            throw new DukeException(comm);
        }
        //System.out.println(inp+"\n"+comm+"\n"+tas);
        if(comm.equals("deadline")) {
            try {
                name = tas.substring(0, tas.indexOf("/"));
                time = tas.substring(tas.indexOf("/"));
            }
            catch (StringIndexOutOfBoundsException e){
                throw new DukeException(comm, tas);
            }
            if(time.equals("/")){
                throw new DukeException(comm, name);
            }
            date = time.substring(time.indexOf(" ")+1);
            tasks.add(new Deadline(name, false, date));
        }
        else if(comm.equals("event")){
            try {
                name = tas.substring(0, tas.indexOf("/"));
                time = tas.substring(tas.indexOf("/"));
            }
            catch (StringIndexOutOfBoundsException e){
                throw new DukeException(comm, tas);
            }
            if(time.equals("/")) {
                throw new DukeException(comm, name);
            }
            date = time.substring(time.indexOf(" ")+1);
            tasks.add(new Event(name, false, date));
        }
        else if(comm.equals("todo")) {
            tasks.add(new Todo(tas, false));
        }
        else{
            System.out.println("I don't know what this command means");
        }
    }
}
