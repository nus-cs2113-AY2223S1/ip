package main.java;

import java.util.*;
public class Duke {
    public static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you");
        String inp = in.nextLine();
        while(!inp.equals("bye")){
            if(inp.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.print(i + ". ");
                    System.out.println(list.get(i - 1));
                }
            }
            else if (inp.contains("mark")){
                int num = Integer.parseInt(inp.substring(inp.indexOf(" ")+1))-1;
                if(num>list.size()){
                    System.out.println("Out of bounds of the list");
                }
                else{
                    if(inp.contains("unmark")){
                        System.out.println("I have marked this task as not done yet");
                        list.get(num).setDone(false);
                        System.out.println(list.get(num));
                    }
                    else{
                        System.out.println("I have marked this task as complete");
                        list.get(num).setDone(true);
                        System.out.println(list.get(num));
                    }
                }

            }
            else if(inp.contains("delete")){
                int num = Integer.parseInt(inp.substring(inp.indexOf(" ")+1))-1;
                if(num>list.size()){
                    System.out.println("Out of bounds of the list");
                }
                else{
                    System.out.println("I have removed this task: ");
                    System.out.println(list.get(num));
                    list.remove(num);
                    System.out.println("You now have "+list.size()+" tasks left in the list.");
                }
            }
            else {
                try{
                    taskReader(inp);
                }
                catch (DukeException e){
                    e.printError();
                }
            }
            inp = in.nextLine();
        }
        System.out.println();

        System.out.println("Bye");
    }
    public static void taskReader(String inp) throws DukeException{
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
            list.add(new Deadline(name, false, date));
            printLastTask();
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
            list.add(new Event(name, false, date));
            printLastTask();
        }
        else if(comm.equals("todo")) {
            list.add(new Todo(tas, false));
            printLastTask();
        }
    }
    public static void printLastTask(){
        System.out.println("Got it. I have added this task: ");
        System.out.println(list.get(list.size()-1));
        System.out.println("Now you have "+list.size()+" tasks in your list.");
    }
}
