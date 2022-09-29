import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.Character.getType;

public class Duke {
    private static final String EVENT = "event";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";

    private static final String FILE_PATH = "data/duke.txt";


    private static final String SEPARATOR = "____________________________________________________________";
    private static final int LENGTH = 100;



    public static void main(String[] args){
        Task[] tasks = new Task[LENGTH];
        int length = 0;

        File f = new File(FILE_PATH);

        readMsg();
        try {
            length = loadFile(f,tasks, length);
        } catch (FileNotFoundException e) {
            System.out.println("File or folder not found");
        }
        finishReadMsg();
        welcomeMsg();

        Scanner input = new Scanner(System.in);
        String val = input.nextLine();

        while(!val.equals("bye")){
            System.out.println(SEPARATOR);
            if(val.equals("list")){
                printList(tasks, length);
            } else if (val.contains("unmark")) {
                markTask(val, tasks, false, "OK, I've marked this task as not done yet:");
                try{
                    saveTask(tasks,length);
                } catch (IOException e){
                    System.out.println("failed to save");
                }
            } else if (val.contains("mark")){
                markTask(val, tasks, true, "Nice! I've marked this task as done:");
                try{
                    saveTask(tasks,length);
                } catch (IOException e){
                    System.out.println("failed to save");
                }
            } else{
                length += addTask(val, tasks, length);
            }
            System.out.println(SEPARATOR);
            val = input.nextLine();
        }

        byeMsg();
    }

    private static void saveTask(Task[] tasks, int length) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);

        String textToAdd = "";
        for(int i = 0; i < length; i++){
            textToAdd += tasks[i].toString() + "\n";
        }

        fw.write(textToAdd);
        fw.close();
    }


    private static void saveLine(String line) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH,true);
        fw.write(line + "\n");
        fw.close();
    }


    private static int addTask(String val, Task[] tasks, int length){
        String textToAppend = "";
        String description;
        String time;
        if(val.contains(TODO)){
            if(val.substring(TODO.length()).trim().isEmpty()){
                emptyError(TODO);
                return 0;
            }
            description = val.substring(TODO.length());
            tasks[length] = new Todo(description);


        } else if (val.contains(DEADLINE)) {
            if(val.substring(DEADLINE.length()).trim().isEmpty()){
                emptyError(DEADLINE);
                return 0;
            }
            description = val.substring(DEADLINE.length(), val.indexOf("/"));
            time =  val.substring((val.indexOf("/") + 4));
            tasks[length] = new Deadline(description,time);


        } else if (val.contains(EVENT)) {
            if(val.substring(EVENT.length()).trim().isEmpty()){
                emptyError(EVENT);
                return 0;
            }
            description = val.substring(EVENT.length(), val.indexOf("/"));
            time =  val.substring((val.indexOf("/") + 4));
            tasks[length] = new Event(description, time);

        }else{
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            return 0;
        }

        try {
            saveLine(tasks[length].toString());
        }catch (IOException e){
            System.out.println("failed to save: " + tasks[length]);
        }

        length++;
        System.out.println("Now you have " + length + " tasks in the list.");
        return 1;
    }

    private static void emptyError(String taskName){
        System.out.println( "☹ OOPS!!! The description of a " + taskName + " cannot be empty.");
    }

    private static void markTask(String val, Task[] tasks, boolean status, String x) {
        int id = val.indexOf(" ");
        if(id < 0){
            System.out.println("☹ OOPS!!! The index of the task cannot be empty.");
            return;
        }
        String ind = val.substring(id + 1);
        int index = Integer.parseInt(ind) - 1;
        tasks[index].setDone(status);
        System.out.println(x);
        System.out.println(tasks[index]);
    }

    private static void printList(Task[] tasks, int length) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < length; i++) {
            System.out.println("  " + (i + 1) + "." + tasks[i]);
        }
    }

    private static int loadFile(File f, Task[] tasks, int length) throws FileNotFoundException{
        Scanner s = new Scanner(f);
        String lineToRead;
        boolean has = s.hasNext();
        while (s.hasNext()) {
            lineToRead = s.nextLine();
            length += loadTask(lineToRead,tasks, length);
        }

        return length;
    }

    private static int loadTask(String lineToRead, Task[] tasks, int length){
        //index of the second ]
        int idOf2nd = lineToRead.indexOf(']',lineToRead.indexOf(']') + 1);
        int idOf3rd = lineToRead.indexOf('(');
        String description, by;
        boolean status = false;
        if(lineToRead.length() > 5 && lineToRead.charAt(4) == 'X'){
            status = true;
        }


        switch(lineToRead.substring(1,2)){
        case "T":
            description = lineToRead.substring(idOf2nd + 2);
            tasks[length] = new Todo(description, status);
            break;
        case "D":
            description = lineToRead.substring(idOf2nd + 2,idOf3rd - 1);
            by = lineToRead.substring(idOf3rd + 5, lineToRead.length() - 1);
            tasks[length] = new Deadline(description, by, status);
            break;
        case "E":
            description = lineToRead.substring(idOf2nd + 2,idOf3rd - 1);
            by = lineToRead.substring(idOf3rd + 5, lineToRead.length() - 1);
            tasks[length] = new Event(description,by, status);
            break;
        default:
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            return 0;
        }


        return 1;
    }


    private static void byeMsg() {
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }

    private static void welcomeMsg() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
    }

    private static void readMsg() {
        System.out.println("____________________________________________________________\n" +
                " Reading from " + FILE_PATH + "\n"+
                " start loading\n" +
                "____________________________________________________________");
    }

    private static void finishReadMsg() {
        System.out.println("____________________________________________________________\n" +
                " done loading.\n" +
                "____________________________________________________________");
    }
}
