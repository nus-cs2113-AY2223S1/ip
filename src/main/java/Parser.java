package main.java;

import java.util.Scanner;

public class Parser {
    TaskList tasks;
    public Parser(TaskList tasks){
        this.tasks = tasks;
    }
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
