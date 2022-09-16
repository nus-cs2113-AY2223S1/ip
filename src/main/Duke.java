package main;

import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Path;
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
        fileToList();
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
                    }
                    else{
                        System.out.println("I have marked this task as complete");
                        list.get(num).setDone(true);
                    }
                    System.out.println(list.get(num));
                    listToFile();
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
                    listToFile();
                }
            }
            else {
                try{
                    taskReader(inp);
                    listToFile();
                }
                catch (DukeException e){
                    e.printError();
                }
            }
            inp = in.nextLine();
        }
        System.out.println();
        listToFile();
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
        else{
            System.out.println("I don't know what this command means");
        }
    }
    public static void printLastTask(){
        System.out.println("Got it. I have added this task: ");
        System.out.println(list.get(list.size()-1));
        System.out.println("Now you have "+list.size()+" tasks in your list.");
    }
    public static void listToFile(){
        try{
            File f = new File("docs/duke.txt");
            try{
                if(!(f.createNewFile())){
                    f.delete();
                    f = new File("docs/duke.txt");
                }
            }
            catch(IOException e){

            }
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Task t : list){
                String don;
                System.out.println("here");
                if(t.getDone()){
                    don = "True";
                }
                else{
                    don = "False";
                }
                if(t.classInfo().equals("Todo") || t.classInfo().equals("Task")){
                    System.out.println("todo file");
                    bw.write("T," + don + "," + t.getName() + System.lineSeparator());
                }
                else if(t.classInfo().equals("Event")){
                    System.out.println("event file");
                    bw.write("E,"+don+","+t.getName()+","+t.getDate()+System.lineSeparator());
                }
                else if(t.classInfo().equals("Deadline")){
                    System.out.println("deadline file");
                    bw.write("D,"+don+","+t.getName()+","+t.getDate()+System.lineSeparator());
                }
            }
            bw.flush();
            bw.close();
        }
        catch (IOException e){
            System.out.println("Problem with file");
        }

    }
    public static void fileToList(){

        File f = new File("docs/duke.txt");

        try{
            if(!(f.createNewFile())){
                Scanner sc = new Scanner(f);
                while(sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] lin = line.split(",");
                    Boolean temp;
                    temp = lin[1].equals("True");
                    if(lin[0].equals("T")){
                        list.add(new Todo(lin[2], temp));
                    }
                    else if(lin[0].equals("D")){
                        list.add(new Deadline(lin[2], temp, lin[4]));
                    }
                    else if(lin[0].equals("E")){
                        list.add(new Event(lin[2], temp, lin[4]));
                    }
                }
            }
        }
        catch (IOException e){
            System.out.println("Problem occurred with the file");
        }
    }
}
